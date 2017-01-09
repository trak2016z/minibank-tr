package pl.librontkrzysztof.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.Transaction;

import java.util.List;

@Repository("transactionDao")
@Transactional
public class TransactionDaoImpl extends AbstractDao<Integer, Transaction> implements TransactionDao {
    static final Logger logger = LoggerFactory.getLogger(TransactionDaoImpl.class);

    @Override
    public void save(Transaction data) {
        persist(data);
    }

    @Override
    public Transaction findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Transaction> findByUserId(int id) {

        List<Transaction> transactions = getSession().createSQLQuery(
                "Select * from transactions t LEFT JOIN wallets w ON t.source_wallet_id = w.id where w.user_id ="+id)
                .addEntity(Transaction.class).list();

        return transactions;
    }

    @Override
    public List<Transaction> findByWalletId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("source_wallet.id", id));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Transaction> transactions = (List<Transaction>) criteria.list();
        return transactions;
    }

    @Override
    public List<Transaction> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("confirmed", false));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Transaction> transactions = (List<Transaction>) criteria.list();
        return transactions;
    }

    @Override
    public void deleteById(int id) {
        deleteById(id);
    }
}
