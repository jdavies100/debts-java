package com.github.jdavies100.debts.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person {

  public Person() {
  }

  public Person(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.debts = new HashSet<>();
  }

  @Id
  private String id;

  private String name;

  @OneToMany(mappedBy = "personId", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Debt> debts;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Debt> getDebts() {
    return debts;
  }

  @Override
  public String toString() {
    return "Person{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", debts=" + debts +
        '}';
  }
}
