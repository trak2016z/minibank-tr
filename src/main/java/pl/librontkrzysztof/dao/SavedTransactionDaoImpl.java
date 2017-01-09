package pl.librontkrzysztof.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.SavedTransaction;
import pl.librontkrzysztof.model.Transaction;

import java.util.List;

@Repository("savedTransactionDao")
@Transactional
public class SavedTransactionDaoImpl extends AbstractDao<Integer, SavedTransaction> implements SavedTransactionDao {
    @Override
    public void save(SavedTransaction data) {
        persist(data);
    }

    @Override
    public SavedTransaction findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<SavedTransaction> findByUserId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<SavedTransaction> transactions = (List<SavedTransaction>) criteria.list();
        return transactions;
    }

    @Override
    public List<SavedTransaction> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("confirmed", false));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<SavedTransaction> transactions = (List<SavedTransaction>) criteria.list();
        return transactions;
    }

    @Override
    public void deleteById(int id) {
        deleteById(id);
    }
}
