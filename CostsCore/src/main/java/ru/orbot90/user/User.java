package ru.orbot90.user;

import ru.orbot90.record.Cost;

import javax.persistence.*;
import java.util.List;

/**
 * Класс, описывающий пользователя
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "user_full_name")
    private String fullName;
    @Column(name = "user_password", nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Cost> costs;
    @Column(name = "balance")
    private long balance;

    public User() {
        balance = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
