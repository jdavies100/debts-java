package com.github.jdavies100.debt.model;

import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "PERSON")
public class Person {

    public Person() {}

    public Person(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.debts = new HashSet<>();
    }

    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "id")
    private Set<Debt> debts;

    public Set<Debt> getDebts() {
        return debts;
    }

}
