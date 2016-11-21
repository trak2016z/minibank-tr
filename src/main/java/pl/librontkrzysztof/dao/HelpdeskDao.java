package pl.librontkrzysztof.dao;
import pl.librontkrzysztof.model.Helpdesk;

import java.util.List;


public interface HelpdeskDao {
    void save(Helpdesk data);
    Helpdesk findById(int id);
    List<Helpdesk> findByUserId(int id);
    List<Helpdesk> findAll();
    List<Helpdesk> findAllUnread();
    void deleteById(int id);
}
