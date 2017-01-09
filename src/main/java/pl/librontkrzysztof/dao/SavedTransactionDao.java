package pl.librontkrzysztof.dao;

import pl.librontkrzysztof.model.SavedTransaction;

import java.util.List;

public interface SavedTransactionDao {
    void save(SavedTransaction data);
    SavedTransaction findById(int id);
    List<SavedTransaction> findByUserId(int id);
    List<SavedTransaction> findAll();
    void deleteById(int id);
}
