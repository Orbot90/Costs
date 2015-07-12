package ru.orbot90.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.orbot90.record.Cost;
import ru.orbot90.user.User;

import java.util.Date;
import java.util.List;

/**
 * Created by orbot on 12.07.15.
 */
@Repository
public class CostRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Cost cost) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = cost.getUser();
        user.addCost(cost);
        if(cost.isCost()) {
            user.decreaseBal(cost.getValue());
        } else {
            user.increaseBal(cost.getValue());
        }
        cost.setCurrentBalance(user.getBalance());
        session.save(cost);
        session.update(user);
        tx.commit();
        session.close();
    }

    public List<Cost> getListOfRecordsByUser(User user) {
        Session session = sessionFactory.openSession();
        List<Cost> list = session.getNamedQuery(Cost.GET_COST_LIST_BY_USER)
                .setString("username", user.getUserName()).list();
        return list;
    }

    public List<Cost> getListOfRecordsByDates(User user, Date beginDate, Date finishDate) {
        return null;
    }
}
