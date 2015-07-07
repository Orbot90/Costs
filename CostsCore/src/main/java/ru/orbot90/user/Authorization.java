package ru.orbot90.user;

import org.hibernate.exception.ConstraintViolationException;
import ru.orbot90.datasource.CostsDAO;

/**
 * Created by orbot on 07.07.15.
 */
public class Authorization {
    private static Authorization instance;
    private Authorization() {

    }

    public static Authorization getInstance() {
        if(null == instance) {
            instance = new Authorization();
        }
        return instance;
    }

    public boolean userJoin(String userName, String password, String balance) {
        User user = new User(userName, password, balance);
        try {
            CostsDAO.getInstance().save(user);
        } catch (ConstraintViolationException e) {
            return false;
        }
        return true;
    }
}
