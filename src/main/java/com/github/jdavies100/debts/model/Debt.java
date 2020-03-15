package com.github.jdavies100.debts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEBTS")
public class Debt {

  public Debt() {
  }

  public Debt(String personId, BigDecimal amount) {
    this.personId = personId;
    this.id = UUID.randomUUID().toString();
    this.amount = amount;
    this.timeStamp = new Date();
  }

  @Id
  private String id;

  @JsonIgnore
  private String personId;

  private Date timeStamp;

  private BigDecimal amount;

  public String getId() {
    return id;
  }

  public String getPersonId() {
    return personId;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "Debt{" +
        "id='" + id + '\'' +
        ", personId='" + personId + '\'' +
        ", timeStamp=" + timeStamp +
        ", amount=" + amount +
        '}';
  }
}
