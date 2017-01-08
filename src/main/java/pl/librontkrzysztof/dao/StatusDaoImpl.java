package pl.librontkrzysztof.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.Status;

@Repository("statusDao")
@Transactional
public class StatusDaoImpl extends AbstractDao<Integer, Status> implements StatusDao {

    @Override
    public Status findById(int id) {
        return getByKey(id);
    }
}
