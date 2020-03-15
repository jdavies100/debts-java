package com.github.jdavies100.debts.service.impl;

import com.github.jdavies100.debts.controller.PersonController;
import com.github.jdavies100.debts.exception.DebtNotRemovedException;
import com.github.jdavies100.debts.exception.PersonNotFoundException;
import com.github.jdavies100.debts.model.Debt;
import com.github.jdavies100.debts.model.Person;
import com.github.jdavies100.debts.service.DebtService;
import com.github.jdavies100.debts.service.PersonService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtServiceImpl implements DebtService {

  private static final Logger LOG = LoggerFactory.getLogger(DebtServiceImpl.class);
  private PersonService personService;

  @Autowired
  public DebtServiceImpl(PersonService personService) {
    this.personService = personService;
  }

  @Override
  public Person addDebt(String personId, BigDecimal amount) throws PersonNotFoundException {
    Person person = personService.getPerson(personId);
    Debt debt = new Debt(personId, amount);
    LOG.debug("Creating new debt for person with id: {}, debt: {}, returning: {}", personId, debt, person);
    person.getDebts().add(debt);
    return personService.savePerson(person);
  }

  @Override
  public Person removeDebt(String personId, String debtId) throws DebtNotRemovedException, PersonNotFoundException {
    Person person = personService.getPerson(personId);
    Set<Debt> debts = person.getDebts();

    Optional<Debt> removable = debts.stream()
        .filter(debt -> Objects.equals(debt.getId(), debtId)).findFirst();

    if (removable.isPresent()) {
      Debt debt = removable.get();
      LOG.debug("Removing debt from person with id: {}, deleting: {}", personId, debtId);
      debts.remove(debt);
      return personService.savePerson(person);
    }

    throw new DebtNotRemovedException(debtId, personId);
  }

  @Override
  public Person removeAllDebts(String personId) throws PersonNotFoundException {
    Person person = personService.getPerson(personId);
    Set<Debt> debts = person.getDebts();
    LOG.debug("Removing all debts from person with id: {}, deleting:", personId);
    debts.clear();
    return personService.savePerson(person);
  }

}
