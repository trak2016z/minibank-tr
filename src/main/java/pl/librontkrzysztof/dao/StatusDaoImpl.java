package pl.librontkrzysztof.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.Status;

import java.util.List;

@Repository("statusDao")
@Transactional
public class StatusDaoImpl extends AbstractDao<Integer, Status> implements StatusDao {

    @Override
    public Status findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Status> findAll() {
        Criteria criteria = getSession().createCriteria(Status.class);
        criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY ID"));
        return (List<Status>) criteria.list();
    }
}
