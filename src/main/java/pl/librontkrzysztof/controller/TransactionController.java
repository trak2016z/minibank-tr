package pl.librontkrzysztof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.librontkrzysztof.dao.*;
import pl.librontkrzysztof.model.Card;
import pl.librontkrzysztof.model.SavedTransaction;
import pl.librontkrzysztof.model.Token;
import pl.librontkrzysztof.model.Transaction;
import pl.librontkrzysztof.service.UserService;
import pl.librontkrzysztof.validator.TransactionValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/dashboard/transaction")
public class TransactionController {

    @Autowired
    WalletDao walletDao;

    @Autowired
    CardDao cardDao;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    UserService userService;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    StatusDao statusDao;

    @Autowired
    SavedTransactionDao savedTransactionDao;

    @RequestMapping(path = {"/new/{accountid}"}, method = RequestMethod.GET)
    public String newTransaction(ModelMap model, @PathVariable String accountid){
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());

        if(cardDao.findActive(user.getId())) {
            model.addAttribute("source_wallets", walletDao.findActiveByUserId(user.getId()));
            model.addAttribute("accountid", accountid);
            model.addAttribute("transaction", new TransactionValidator());
            model.addAttribute("savetransactions", savedTransactionDao.findByUserId(user.getId()));
            return "user/transaction/new";
        }
        else
        {
            return "user/transaction/needcard";
        }
    }

    @RequestMapping(path = {"/new/{accountid}"}, method = RequestMethod.POST)
    public String newTransaction2(@PathVariable String accountid, @Valid @ModelAttribute("transaction") TransactionValidator transaction, BindingResult result, ModelMap model){
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());

        if(cardDao.findActive(user.getId())) {
            model.addAttribute("source_wallets", walletDao.findActiveByUserId(user.getId()));

            model.addAttribute("accountid", accountid);
            model.addAttribute("source_wallet", transaction.getSource_wallet());
            if(result.hasErrors())
            {
                return "user/transaction/new";
            }
            Card card = cardDao.findActiveByUser(user.getId());
            Token token = card.getNewToken();
            token.setUsed(true);
            tokenDao.save(token);
            transaction.setValue(transaction.getValue().setScale(2, BigDecimal.ROUND_DOWN));

            model.addAttribute("token", token);
            model.addAttribute("token_id", token.getId());
            return "user/transaction/new2";
        }
        else
        {
            return "user/transaction/needcard";
        }
    }

    @RequestMapping(path = {"/new/check"}, method = RequestMethod.POST)
    public String newTransaction3(@Valid @ModelAttribute("transaction") TransactionValidator transaction, BindingResult result, ModelMap model, HttpServletRequest request){
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());

        if(cardDao.findActive(user.getId())) {
            model.addAttribute("source_wallets", walletDao.findActiveByUserId(user.getId()));

            model.addAttribute("source_wallet", transaction.getSource_wallet());
            if(result.hasErrors())
            {
                return "user/transaction/new";
            }

            Token tok = tokenDao.findById(Integer.parseInt(request.getParameter("token_id")));
            if(!tok.getContent().equals(transaction.getToken())){
                FieldError ssoError =new FieldError("transaction","token","Niepoprawny token!");
                result.addError(ssoError);
                Card card = cardDao.findActiveByUser(user.getId());
                Token token = card.getNewToken();
                token.setUsed(true);
                tokenDao.save(token);
                model.addAttribute("token", token);
                model.addAttribute("token_id", token.getId());
                return "user/transaction/new2";
            }

            Transaction trans = new Transaction();
            trans.setAddress1(transaction.getAddress1());
            trans.setAddress2(transaction.getAddress2());
            trans.setName(transaction.getName());
            trans.setSource_wallet(walletDao.findById(transaction.getSource_wallet()));
            trans.setWallet_number(transaction.getWallet_number());
            trans.setToken(tok);
            trans.setTitle(transaction.getTitle());
            trans.setValue(transaction.getValue());
            trans.setOrder_date(new Date());
            trans.setStatus(statusDao.findById(1));
            transactionDao.save(trans);

            if(transaction.getTemplate()){
                SavedTransaction savedTransaction = new SavedTransaction(transaction, user);
                savedTransactionDao.save(savedTransaction);
            }
            return "user/transaction/success";
        }
        else
        {
            return "user/transaction/needcard";
        }
    }


    @RequestMapping(path = {"/history", "/history/list"}, method = RequestMethod.GET)
    public String list(ModelMap model){
        pl.librontkrzysztof.model.User user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("transactions", transactionDao.findByUserId(user.getId()));
        return "user/history/list";
    }

}
