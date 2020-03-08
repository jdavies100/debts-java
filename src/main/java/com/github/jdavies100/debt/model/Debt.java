package com.github.jdavies100.debt.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEBTS")
public class Debt {

    public Debt() {}

    public Debt(double amount) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
    }

    @Id
    private String id;

    private double amount;

    public double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

}
