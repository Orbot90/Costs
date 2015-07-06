package ru.orbot90.record;

import ru.orbot90.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Класс, описывающий одну запись о растратах, либо доходах
 */
@Entity
@Table(name = "costs")
public class Cost {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "value", nullable = false)
    private double value;
    @ManyToOne
    private User user;
    @ElementCollection
    @CollectionTable(name = "tag")
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
    private long currentBalance;

    public Cost() {
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

    public long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(long currentBalance) {
        this.currentBalance = currentBalance;
    }
}
