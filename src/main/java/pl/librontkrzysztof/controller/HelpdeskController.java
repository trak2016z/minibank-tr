package pl.librontkrzysztof.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard")
public class HelpdeskController {

    @Autowired
    HelpdeskDao helpdeskDao;

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/helpdesk", "/helpdesk/list"}, method = RequestMethod.GET)
    public String helpdeskList(ModelMap model) {
        User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("helpdesks", helpdeskDao.findByUserId(user.getId()));
        return "user/helpdesk/list";
    }

    @RequestMapping(value = {"/helpdesk/add"}, method = RequestMethod.GET)
    public String helpdeskAdd(ModelMap model) {
        User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        Helpdesk helpdesk = new Helpdesk();
        helpdesk.setUser(user);
        model.addAttribute("helpdesk", helpdesk);
        return "user/helpdesk/add";
    }

    @RequestMapping(value = {"/helpdesk/add"}, method = RequestMethod.POST)
    public String helpdeskAddPOST(@Valid Helpdesk helpdesk, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "user/helpdesk/add";
        }

        helpdeskDao.save(helpdesk);
        model.addAttribute("success", "Zgłoszenie zostało dodane poprawnie");
        return "user/helpdesk/success";
    }
}
