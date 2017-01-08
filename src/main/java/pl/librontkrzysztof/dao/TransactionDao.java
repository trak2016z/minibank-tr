package pl.librontkrzysztof.dao;


import pl.librontkrzysztof.model.Transaction;

import java.util.List;

public interface TransactionDao {
    void save(Transaction data);
    Transaction findById(int id);
    List<Transaction> findByUserId(int id);
    List<Transaction> findByWalletId(int id);
    List<Transaction> findAll();
    void deleteById(int id);
}
