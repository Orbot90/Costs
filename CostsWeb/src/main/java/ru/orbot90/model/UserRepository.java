package ru.orbot90.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.orbot90.record.Cost;
import ru.orbot90.user.User;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.stream.Collectors;


/**
 * Created by orbot on 11.07.15.
 */
@Repository
@Transactional
public class UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.save(user);
        return user;
    }

    public User getUserByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.getNamedQuery(User.FIND_BY_USERNAME)
                .setString("userName", userName)
                .uniqueResult();
        return user;
    }

    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        if(user.getCosts().size() > 0) {
            Cost cost = user.getCosts()
                    .stream()
                    .sorted((cost1, cost2) -> ((Long) cost1.getId()).compareTo((Long) cost2.getId()))
                    .collect(Collectors.toList()).get(0);
            user.setBalance(cost.getCurrentBalance());
        } else {
            user.setBalance(user.getStartBalance());
        }
        session.update(user);
    }

}
