package pl.librontkrzysztof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.librontkrzysztof.dao.CardDao;
import pl.librontkrzysztof.dao.TokenDao;
import pl.librontkrzysztof.model.Card;
import pl.librontkrzysztof.model.Token;
import pl.librontkrzysztof.model.User;
import pl.librontkrzysztof.model.Wallet;
import pl.librontkrzysztof.service.UserService;

import java.security.SecureRandom;

@Controller
@RequestMapping("/dashboard/card")
public class CardController {

    @Autowired
    CardDao cardDao;

    @Autowired
    UserService userService;

    @Autowired
    TokenDao tokenDao;

    @RequestMapping(path = {"/", "/list"}, method = RequestMethod.GET)
    public String showCard(ModelMap model){
        User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("cards", cardDao.findByUserId(user.getId()));
        return "user/card/list";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addCard(ModelMap model){

        User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());

        Card card = new Card();
        card.setUser(user);
        card.setActive(false);
        cardDao.save(card);

        String AB = "0123456789qwertyuiopasdfghjklzxcvbnm";
        SecureRandom rnd = new SecureRandom();
        for(int j = 0; j<40; j++) {
            StringBuilder sb = new StringBuilder(5);
            for (int i = 0; i < 5; i++)
                sb.append(AB.charAt(rnd.nextInt(AB.length())));
            Token token = new Token();
            token.setCard(card);
            token.setContent(sb.toString());
            token.setUsed(false);
            token.setNumber(j+1);
            tokenDao.save(token);
        }

        model.addAttribute("success", "Dodano prawidłowo kolejną kartę kodów jednorazowych.");
        return "user/card/add";
    }

    @RequestMapping(path = {"/delete-{id}"}, method = RequestMethod.GET)
    public String delete(ModelMap model, @PathVariable String id){
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        Card card = cardDao.findById(Integer.parseInt(id));
        if(card == null){
            model.addAttribute("error", "Taka karta kodów jednorazowych nie istnieje!");
            return "user/bankaccount/error";
        }
        else {
            if (card.getUser().getSsoId().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                card.setActive(false);
                cardDao.save(card);
                model.addAttribute("success", "Karta kodów jednorazowych została zawieszona!");
                return "user/card/success";
            } else {
                model.addAttribute("error", "Nie jesteś właścicielem tej karty kodów jednorazowych!");
                return "user/card/error";
            }
        }
    }

    @RequestMapping(path = {"/activate-{id}"}, method = RequestMethod.GET)
    public String activate(ModelMap model, @PathVariable String id){
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        Card card = cardDao.findById(Integer.parseInt(id));
        if(card == null){
            model.addAttribute("error", "Taka karta kodów jednorazowych nie istnieje!");
            return "user/bankaccount/error";
        }
        else {
            if (card.getUser().getSsoId().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                if(!cardDao.findActive(user.getId())) {
                    if(card.getActive()==0){
                        model.addAttribute("error", "Karta kodów jednorazowych musi posiadać wolne tokeny, aby mogła zostać aktywowana!");
                        return "user/card/error";
                    }
                    else {
                        card.setActive(true);
                        cardDao.save(card);
                        model.addAttribute("success", "Karta kodów jednorazowych została aktywowana!");
                        return "user/card/success";
                    }
                }
                else
                {
                    model.addAttribute("error", "Nie można mieć aktywnych dwóch kart kodów jednorazowych! Należy dezaktywować aktywną kartę.");
                    return "user/card/error";
                }
            } else {
                model.addAttribute("error", "Nie jesteś właścicielem tej karty kodów jednorazowych!");
                return "user/card/error";
            }
        }
    }

}
