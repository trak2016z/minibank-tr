package pl.librontkrzysztof.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.ImageToken;
import pl.librontkrzysztof.model.Wallet;

import java.util.List;

@Repository("walletDao")
@Transactional
public class WalletDaoImpl extends AbstractDao<Integer, Wallet> implements WalletDao {
    @Override
    public void save(Wallet data) {
        persist(data);
    }

    @Override
    public void update(Wallet data) {
        persist(data);
    }

    @Override
    public Wallet findById(int id) {
        Wallet wallet = getByKey(id);
        return wallet;
    }

    @Override
    public Wallet findByNumber(String number) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("number", number));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Wallet> wallet = (List<Wallet>) criteria.list();

        if(wallet.size() > 0)
            return wallet.get(0);
        else return null;
    }

    @Override
    public List<Wallet> findByUserId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Wallet> wallets = (List<Wallet>) criteria.list();

        return wallets;
    }

    @Override
    public List<Wallet> findActiveByUserId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        criteria.add(Restrictions.eq("active", true));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Wallet> wallets = (List<Wallet>) criteria.list();

        return wallets;
    }

    @Override
    public List<Wallet> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Wallet> wallets = (List<Wallet>) criteria.list();

        return wallets;
    }

    @Override
    public void deleteById(int id) {
        Wallet entity = getByKey(id);
        delete(entity);
    }
}
