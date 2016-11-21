package pl.librontkrzysztof.controller;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pl.librontkrzysztof.dao.ImageTokenDao;
import pl.librontkrzysztof.model.ImageToken;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("roles")
@RequestMapping(value = "/admin/imageToken")
public class ImageTokenController {


    @Autowired
    private ImageTokenDao fileUploadDao;


    @RequestMapping(value = {"", "/list"} , method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("imageTokens", fileUploadDao.findAll());
        return "admin/imageToken/list";
    }

    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    public String showUploadForm(ModelMap model) {
        return "admin/imageToken/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request,
                                   @RequestParam CommonsMultipartFile fileUpload, ModelMap model) throws Exception {

        if (fileUpload != null && fileUpload.getSize() > 0) {

                System.out.println("Saving file: " + fileUpload.getOriginalFilename());

                ImageToken uploadFile = new ImageToken();
                uploadFile.setName(fileUpload.getOriginalFilename());
                uploadFile.setData(fileUpload.getBytes());
                fileUploadDao.save(uploadFile);
            model.addAttribute("color", "success");
            model.addAttribute("success", "Token obrazkowy został dodany poprawnie");
        }else{
            model.addAttribute("color", "danger");
            model.addAttribute("success", "Token obrazkowy NIE został dodany poprawnie");
        }



        return "admin/imageToken/success";
    }


    @RequestMapping(value = { "/delete-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
        try {
            fileUploadDao.deleteById(Integer.parseInt(id));
            return "redirect:/admin/imageToken/list";
        }catch(Exception e){

            model.addAttribute("color", "danger");
            model.addAttribute("success", "Token obrazkowy NIE został usunięty poprawnie, ponieważ jest przypisany do użytkownika.");
            return "admin/imageToken/success";
        }


    }

}

