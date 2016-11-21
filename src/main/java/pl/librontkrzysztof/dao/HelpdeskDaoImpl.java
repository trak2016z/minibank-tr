package pl.librontkrzysztof.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.Helpdesk;

import java.util.List;

@Repository("helpdeskDao")
@Transactional
public class HelpdeskDaoImpl extends AbstractDao<Integer, Helpdesk> implements HelpdeskDao {

    @Override
    public void save(Helpdesk data) {
        persist(data);
    }

    @Override
    public Helpdesk findById(int id) {
        Helpdesk user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUser());
        }
        return user;
    }

    @Override
    public List<Helpdesk> findByUserId(int id) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("datee"));
        criteria.add(Restrictions.eq("user.id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Helpdesk> helpdesks = (List<Helpdesk>) criteria.list();

        return helpdesks;
    }

    @Override
    public List<Helpdesk> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("datee"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Helpdesk> helpdesks = (List<Helpdesk>) criteria.list();

        return helpdesks;
    }

    @Override
    public List<Helpdesk> findAllUnread() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("datee"));
        criteria.add(Restrictions.or(Restrictions.isNull("answer"),
                Restrictions.eq("answer", "")));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Helpdesk> helpdesks = (List<Helpdesk>) criteria.list();

        return helpdesks;
    }

    @Override
    public void deleteById(int id) {
        Helpdesk entity = getByKey(id);
        delete(entity);
    }
}
