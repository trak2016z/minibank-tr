package pl.librontkrzysztof.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.librontkrzysztof.model.ImageToken;
import pl.librontkrzysztof.model.User;

import java.awt.*;
import java.util.*;
import java.util.List;


@Repository("ImageTokenDao")
public class ImageTokenDaoImpl extends AbstractDao<Integer, ImageToken> implements ImageTokenDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(ImageToken data) {
        persist(data);
    }

    @Override
    @Transactional
    public ImageToken findById(int id) {
        ImageToken imageToken = getByKey(id);
        return imageToken;
    }

    @Override
    @Transactional
    public ImageToken findRandom() {

        Criteria criteria = getSession().createCriteria(ImageToken.class);
        criteria.add(Restrictions.sqlRestriction("1=1 order by RANDOM()"));
        criteria.setMaxResults(1);
        return (ImageToken) criteria.list().get(0);

    }

    @Override
    @Transactional
    public List<ImageToken> findAll() {
        Criteria criteria = getSession().createCriteria(ImageToken.class);
        criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY ID"));
        return (List<ImageToken>) criteria.list();
    }

    public ImageTokenDaoImpl(){}

    public ImageTokenDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        ImageToken imageToken = getByKey(id);
        delete(imageToken);
    }
}
