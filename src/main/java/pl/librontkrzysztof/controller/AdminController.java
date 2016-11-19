package pl.librontkrzysztof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.librontkrzysztof.model.User;

import java.util.List;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminController {
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        return "admin/dashboard";
    }
}
