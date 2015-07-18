package ru.orbot90.record;

import org.hibernate.annotations.*;
import ru.orbot90.user.User;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Класс, описывающий одну запись о растратах, либо доходах
 */
@Entity
@Table(name = "costs")
@NamedQuery(name = Cost.DELETE_COST_BY_ID, query = "delete from Cost c where id = :id")
public class Cost {

    public static final String DELETE_COST_BY_ID = "Cost.deleteById";

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "value", nullable = false)
    private double value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_cost")
    private User user;
    @ElementCollection(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> tags;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;
    // растрата или доход
    @Column(name = "type", nullable = false)
    private boolean cost;
    // баланс по состоянию на эту запись
    @Column(name = "cur_balance")
    private double currentBalance;

    public Cost() {
    }

    public Cost(double value, String description, boolean cost, List<String> tags, User user) {
        this.value = value;
        this.user = user;
        this.tags = tags;
        this.description = description;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCost() {
        return cost;
    }

    public void setCost(boolean cost) {
        this.cost = cost;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
