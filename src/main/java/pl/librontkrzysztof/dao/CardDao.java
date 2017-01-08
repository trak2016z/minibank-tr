package pl.librontkrzysztof.dao;

import pl.librontkrzysztof.model.Card;
import java.util.List;

public interface CardDao {
    void save(Card data);
    Card findById(int id);
    boolean findActive(int id);
    Card findActiveByUser(int id);
    List<Card> findByUserId(int id);
    List<Card> findAll();
    void deleteById(int id);
}
