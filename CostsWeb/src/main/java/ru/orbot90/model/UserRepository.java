package ru.orbot90.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.orbot90.user.User;



/**
 * Created by orbot on 11.07.15.
 */
@Repository
@Transactional(readOnly = true)
public class UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User save(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.save(user);
        tx.commit();
        session.close();
        return user;
    }

    public User getUserByUserName(String userName) {
        Session session = sessionFactory.openSession();
        User user = (User)session.getNamedQuery(User.FIND_BY_USERNAME)
                .setString("userName", userName)
                .uniqueResult();
        session.close();
        return user;
    }

}
