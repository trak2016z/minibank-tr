package pl.librontkrzysztof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.librontkrzysztof.dao.HelpdeskDao;
import pl.librontkrzysztof.model.Helpdesk;

@Controller
@RequestMapping("/admin/helpdesk")
public class AdminHelpdeskController {

    @Autowired
    HelpdeskDao helpdeskDao;

    @RequestMapping(value = {"/", "", "/list"}, method = RequestMethod.GET)
    public String helpdeskList(ModelMap model) {

        model.addAttribute("helpdesks", helpdeskDao.findAllUnread());
        return "admin/helpdesk/list";
    }

    @RequestMapping(value = {"/answer-{id}"}, method = RequestMethod.GET)
    public String answer(ModelMap model, @PathVariable String id) {

        model.addAttribute("helpdesks", helpdeskDao.findById(Integer.parseInt(id)));
        return "admin/helpdesk/answer";
    }

    @RequestMapping(value = {"/answer-{id}"}, method = RequestMethod.POST)
    public String answerPOST(ModelMap model, @PathVariable String id, @RequestParam(value="answer", required=true) String answer) {
        Helpdesk help = helpdeskDao.findById(Integer.parseInt(id));
        help.setAnswer(answer);
        helpdeskDao.save(help);
        return "redirect:/admin/helpdesk/list";
    }

    @RequestMapping(value = {"/delete-{id}"}, method = RequestMethod.GET)
    public String delete(ModelMap model, @PathVariable String id) {
        helpdeskDao.deleteById(Integer.parseInt(id));
        return "redirect:/admin/helpdesk/list";
    }
}
