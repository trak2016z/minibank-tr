package pl.librontkrzysztof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;
import pl.librontkrzysztof.dao.CardDao;
import pl.librontkrzysztof.dao.ImageTokenDao;
import pl.librontkrzysztof.dao.SavedTransactionDao;
import pl.librontkrzysztof.model.Card;
import pl.librontkrzysztof.model.SavedTransaction;
import pl.librontkrzysztof.model.Token;
import pl.librontkrzysztof.model.User;
import pl.librontkrzysztof.service.UserService;
import pl.librontkrzysztof.validator.TransactionValidator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.List;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RESTController {

    @Autowired
    private ImageTokenDao imageTokenDao;

    @Autowired
    private UserService userService;

    @Autowired
    private CardDao cardDao;

    @Autowired
    SavedTransactionDao savedTransactionDao;

    @RequestMapping(method = RequestMethod.GET, value = {"/getImageToken/{userId}", "/MiniBank-1.0.0/getImageToken/{userId}"})
    public String getImageToken(@PathVariable String userId) throws IOException {
        User user = userService.findBySSO(userId);
        ByteArrayInputStream bais = null;
        if(user != null) {
            bais = new ByteArrayInputStream(user.getImage_token().getData());
        }else{
            bais = new ByteArrayInputStream(imageTokenDao.findRandom().getData());
        }

        BufferedImage sourceImage = ImageIO.read(bais);

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("EEEE, d MMMM yyyy", new Locale("pl", "PL"));


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

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = {"/getCard/{cardId}", "/MiniBank-1.0.0/getCard/{cardId}"}, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getCard(@PathVariable String cardId) throws IOException {
        Card card = cardDao.findById(Integer.parseInt(cardId));
        ByteArrayInputStream bais = null;
        if(card != null) {
            final DefaultResourceLoader loader = new DefaultResourceLoader();
            Resource resource = loader.getResource("classpath:karta_kodow.png");
            BufferedImage cardImage = ImageIO.read(resource.getFile());




            String text = "$MiniBank CARD ID: "+card.getId().toString();

            Graphics2D g2d = (Graphics2D) cardImage.getGraphics();

            // initializes necessary graphic properties
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
            g2d.setComposite(alphaChannel);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Tahoma", Font.BOLD, 14));
            FontMetrics fontMetrics = g2d.getFontMetrics();

            Set<Token> tokens = card.getTokens();

            g2d.drawString(text, 5, 18);
            g2d.setColor(Color.BLACK);
            int centerX = 20;
            int centerY = 55;
            g2d.setFont(new Font("Tahoma", 0, 10));

            int i = 0;
            int j = 0;
            for(Token tok : tokens){
                    g2d.drawString(tok.getContent(), centerX+(j*59), centerY+(i*18));

                i++;
                if(i==8){
                    j++;
                    i=0;
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(cardImage, "png", baos);

            byte[] imageBytes = baos.toByteArray();
            baos.close();

            return imageBytes;
        }
        else
            return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = {"/getSavedTransaction/{id}", "/MiniBank-1.0.0/getSavedTransaction/{id}"})
    public @ResponseBody java.util.List<TransactionValidator> getSavedTransaction(@PathVariable String id)  {
        java.util.List<TransactionValidator> list = new ArrayList<TransactionValidator>();
        TransactionValidator transactionValidator = new TransactionValidator(savedTransactionDao.findById(Integer.parseInt(id)));
        list.add(transactionValidator);
        return list;
    }
}
