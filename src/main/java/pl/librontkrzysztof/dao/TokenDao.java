package pl.librontkrzysztof.dao;

import pl.librontkrzysztof.model.Token;
import java.util.List;

public interface TokenDao {
    void save(Token data);
    Token findById(int id);
    Token findRandom(int id);
    List<Token> findByCardId(int id);
    List<Token> findAll();
    void deleteById(int id);
}
