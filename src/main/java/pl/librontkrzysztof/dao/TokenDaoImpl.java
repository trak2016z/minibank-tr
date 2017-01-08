package pl.librontkrzysztof.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.Token;

import java.util.List;

@Repository("tokenDao")
@Transactional
public class TokenDaoImpl extends AbstractDao<Integer, Token> implements TokenDao{

    @Override
    public void save(Token data) {
        persist(data);
    }

    @Override
    public Token findById(int id) {
        return getByKey(id);
    }

    @Override
    public Token findRandom(int id) {
        Criteria criteria = getSession().createCriteria(Token.class);
        criteria.add(Restrictions.sqlRestriction("where card_id = "+id+" order by RANDOM()"));
        criteria.setMaxResults(1);
        return (Token) criteria.list().get(0);
    }

    @Override
    public List<Token> findByCardId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("card.id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Token> tokens = (List<Token>) criteria.list();

        return tokens;
    }

    @Override
    public List<Token> findAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {
        deleteById(id);
    }
}
