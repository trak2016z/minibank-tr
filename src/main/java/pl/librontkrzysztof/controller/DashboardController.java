package pl.librontkrzysztof.controller;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.librontkrzysztof.dao.HelpdeskDao;
import pl.librontkrzysztof.model.Helpdesk;
import pl.librontkrzysztof.model.User;
import pl.librontkrzysztof.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String dashboard(ModelMap model) {
        return "user/dashboard";
    }


}
