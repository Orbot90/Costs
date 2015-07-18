package ru.orbot90.user;

import org.hibernate.annotations.*;
import ru.orbot90.record.Cost;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс, описывающий пользователя
 */
@Entity
@Table(name = "users")
@NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where userName = :userName")
public class User {

    public static final String FIND_BY_USERNAME = "User.findByName";

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "user_email", nullable = false, unique = true)
    private String eMail;
    @Column(name = "user_full_name")
    private String fullName;
    @Column(name = "user_password", nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Cost> costs;
    @Column(name = "balance")
    private double balance;
    @Column(name = "start_balance")
    private double startBalance;

    private String role = "ROLE_USER";

    public User() {
        balance = 0;
    }

    public User(String userName, String password, String balance, String email) {
        this.userName = userName;
        this.password = password;
        this.balance = Double.parseDouble(balance);
        this.startBalance = Double.parseDouble(balance);
        this.eMail = email;
        this.role = "ROLE_USER";
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addCost(Cost cost) {
        costs.add(cost);
    }

    public void increaseBal(double money) {
        this.balance += money;
    }

    public void decreaseBal(double money) {
        this.balance -= money;
    }

    public double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(double startBalance) {
        this.startBalance = startBalance;
    }
}
