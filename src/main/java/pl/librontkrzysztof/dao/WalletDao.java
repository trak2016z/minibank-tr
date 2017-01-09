package pl.librontkrzysztof.dao;


import pl.librontkrzysztof.model.Wallet;

import java.util.List;

public interface WalletDao {
    void save(Wallet data);
    void update(Wallet data);
    Wallet findById(int id);
    Wallet findByNumber(String number);
    List<Wallet> findByUserId(int id);
    List<Wallet> findAll();
    void deleteById(int id);
    List<Wallet> findActiveByUserId(int id);
}
