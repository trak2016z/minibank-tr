package pl.librontkrzysztof.controller;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pl.librontkrzysztof.dao.WalletDao;

import pl.librontkrzysztof.model.Wallet;
import pl.librontkrzysztof.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

@Controller
@RequestMapping("/dashboard/bankaccount")
public class BankAccountController {

    @Autowired
    WalletDao walletDao;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "", "/list"}, method = RequestMethod.GET)
    public String accountList(ModelMap model) {
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("wallets", walletDao.findByUserId(user.getId()));
        return "user/bankaccount/list";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addAccount(ModelMap model){
        return "user/bankaccount/add";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addAccountConfirm(HttpServletRequest request, ModelMap model){

        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());

        Wallet wallet = new Wallet();
        wallet.setUser(user);

        String AB = "0123456789";
        SecureRandom rnd = new SecureRandom();
        boolean generate = false;
        Iban iban = null;
        do {

            StringBuilder sb = new StringBuilder(12);
            for (int i = 0; i < 12; i++)
                sb.append(AB.charAt(rnd.nextInt(AB.length())));


            iban = new Iban.Builder()
                    .countryCode(CountryCode.PL)
                    .bankCode("1234567")
                    .accountNumber(sb.toString())
                    .buildRandom();
            if(walletDao.findByNumber(iban.toString()) != null){
                generate = true;
            }

        }while (generate);

        wallet.setName(request.getParameter("name"));
        wallet.setNumber(iban.toString());
        wallet.setContent(new BigDecimal(0.0));
        wallet.setActive(true);
        walletDao.save(wallet);

        model.addAttribute("success", "Konto zostało dodane poprawnie.");
        return "user/bankaccount/success";
    }

    @RequestMapping(path = {"/delete-{id}"}, method = RequestMethod.GET)
    public String delete(ModelMap model, @PathVariable String id){
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        Wallet wallet = walletDao.findById(Integer.parseInt(id));
        if(wallet == null){
            model.addAttribute("error", "Takie konto nie istnieje!");
            return "user/bankaccount/error";
        }
        else {
            if (wallet.getUser().getSsoId().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                if (wallet.getContent().compareTo(new BigDecimal(0.0)) == -1) {
                    model.addAttribute("error", "Nie można zawiesić konta z zawartością!");
                    return "user/bankaccount/error";
                } else {
                    wallet.setActive(false);
                    walletDao.update(wallet);
                    model.addAttribute("success", "Konto zostało zawieszone!");
                    return "user/bankaccount/success";
                }
            } else {
                model.addAttribute("error", "Nie jesteś właścicielem tego konta!");
                return "user/bankaccount/error";
            }
        }
    }

}
