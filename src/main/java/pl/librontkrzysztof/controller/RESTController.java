package pl.librontkrzysztof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.librontkrzysztof.dao.ImageTokenDao;
import pl.librontkrzysztof.model.User;
import pl.librontkrzysztof.service.UserService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class RESTController {

    @Autowired
    private ImageTokenDao imageTokenDao;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = {"/getImageToken/{userId}", "/MiniBank-1.0.0/getImageToken/{userId}"})
    String getImageToken(@PathVariable String userId) throws IOException {
        User user = userService.findBySSO(userId);
        ByteArrayInputStream bais = null;
        if(user != null) {
            bais = new ByteArrayInputStream(user.getImage_token().getData());
        }else{
            bais = new ByteArrayInputStream(imageTokenDao.findRandom().getData());
        }

        BufferedImage sourceImage = ImageIO.read(bais);

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("EEEE, d MMMM yyyy");


        Date today = Calendar.getInstance().getTime();

        String text = df.format(today);
        String text2 =  df2.format(today);
        String text3 = "$MiniBank";

        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // initializes necessary graphic properties
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g2d.setComposite(alphaChannel);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Tahoma", Font.BOLD, 24));
        FontMetrics fontMetrics = g2d.getFontMetrics();


        // calculates the coordinate where the String is painted
        //int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;

        int centerX = 110;
        int centerY = 20;

        // paints the textual watermark
        g2d.drawString(text, centerX, centerY);
        g2d.setFont(new Font("Tahoma", 0, 13));
        g2d.drawString(text2, centerX, centerY+24);

        g2d.setFont(new Font("Tahoma", 0, 11));
        Rectangle2D rect = fontMetrics.getStringBounds(text3, g2d);
        centerX = sourceImage.getWidth() - ((int) rect.getWidth() / 2);
        centerY = sourceImage.getHeight() - 10;

        g2d.drawString(text3, centerX, centerY);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(sourceImage,"png",baos);

        byte[] imageBytes = baos.toByteArray();
        baos.close();

        byte[] encodeBase64 = Base64.encode(imageBytes);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        //model.addAttribute("userImage", base64Encoded );

        return base64Encoded;
    }
}
