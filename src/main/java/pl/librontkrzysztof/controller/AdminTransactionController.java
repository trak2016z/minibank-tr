package pl.librontkrzysztof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.librontkrzysztof.dao.StatusDao;
import pl.librontkrzysztof.dao.TransactionDao;
import pl.librontkrzysztof.dao.WalletDao;
import pl.librontkrzysztof.model.Transaction;
import pl.librontkrzysztof.model.Wallet;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/transaction")
public class AdminTransactionController {
    @Autowired
    TransactionDao transactionDao;

    @Autowired
    WalletDao walletDao;

    @Autowired
    StatusDao statusDao;

    @RequestMapping(value = {"/", "", "/list"}, method = RequestMethod.GET)
    public String helpdeskList(ModelMap model) {

        model.addAttribute("transactions", transactionDao.findAll());
        return "admin/transaction/list";
    }

    @RequestMapping(value = {"/status-{id}"}, method = RequestMethod.GET)
    public String status(ModelMap model, @PathVariable String id) {
        Transaction transaction = transactionDao.findById(Integer.parseInt(id));
        Wallet wallet = walletDao.findByNumber(transaction.getWallet_number());
        Wallet source_wallet = transaction.getSource_wallet();

        model.addAttribute("transaction", transaction);

        model.addAttribute("statuses", statusDao.findAll());

        if(wallet != null) {
            model.addAttribute("number_exist", 1);
            model.addAttribute("user", wallet.getUser());
        }else {
            model.addAttribute("number_exist", 0);
            model.addAttribute("user", null);
        }

        if(source_wallet.getContent().subtract(transaction.getValue()).signum() != -1) {
            model.addAttribute("value_ok", 1);
        }else {
            model.addAttribute("value_ok", 0);
        }


        return "admin/transaction/status";
    }

    @RequestMapping(value = {"/status-{id}"}, method = RequestMethod.POST)
    public String changeStatus(ModelMap model, @PathVariable String id, @RequestParam("status") String stat) {
        Integer status = Integer.parseInt(stat);
        Transaction transaction = transactionDao.findById(Integer.parseInt(id));
        switch(status){
            case 1:
                break;
            case 2:
                Wallet wallet = walletDao.findByNumber(transaction.getWallet_number());
                Wallet source_wallet = transaction.getSource_wallet();
                source_wallet.setContent(source_wallet.getContent().subtract(transaction.getValue()));
                wallet.setContent(wallet.getContent().add(transaction.getValue()));
                walletDao.save(source_wallet);
                walletDao.save(wallet);
                transaction.setStatus(statusDao.findById(2));
                transaction.setConfirmed(true);
                break;
            case 3:
                transaction.setStatus(statusDao.findById(3));
                transaction.setConfirmed(true);
                break;
            case 4:
                transaction.setStatus(statusDao.findById(4));
                transaction.setConfirmed(true);
                break;
        }

            transactionDao.save(transaction);

        model.addAttribute("color", "success");
        model.addAttribute("success", "Udało się zmienić status transakcji.");
        return "admin/transaction/success";
    }

}
