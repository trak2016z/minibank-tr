package pl.librontkrzysztof.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.Card;

import java.util.List;

@Repository("cardDao")
@Transactional
public class CardDaoImpl extends AbstractDao<Integer, Card> implements CardDao {

    @Override
    public void save(Card data) {
        persist(data);
    }

    @Override
    public Card findById(int id) {
        Card card = getByKey(id);
        if (card != null) {
            Hibernate.initialize(card.getUser());
        }
        return card;
    }

    @Override
    public boolean findActive(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        criteria.add(Restrictions.eq("active", true));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        int size = criteria.list().size();
        if(size==0)
            return false;
        else
            return true;
    }

    @Override
    public Card findActiveByUser(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        criteria.add(Restrictions.eq("active", true));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (Card)criteria.list().get(0);

    }

    @Override
    public List<Card> findByUserId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Card> cards = (List<Card>) criteria.list();

        return cards;
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {
        Card entity = getByKey(id);
        delete(entity);
    }
}
