package com.github.jdavies100.debt.service.impl;

import com.github.jdavies100.debt.exception.DebtNotRemovedException;
import com.github.jdavies100.debt.exception.PersonNotFoundException;
import com.github.jdavies100.debt.service.DebtService;
import com.github.jdavies100.debt.service.PersonService;
import com.github.jdavies100.debt.model.Debt;
import com.github.jdavies100.debt.model.Person;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtServiceImpl implements DebtService {

    private PersonService personService;

    @Autowired
    public DebtServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Person addDebt(String personId, double amount) throws PersonNotFoundException {
        Person person = personService.getPerson(personId);
        person.getDebts().add(new Debt(amount));
        return personService.savePerson(person);
    }

    @Override
    public Person removeDebt(String personId, String debtId) throws DebtNotRemovedException, PersonNotFoundException {
        Person person = personService.getPerson(personId);
        Set<Debt> debts = person.getDebts();

        Optional<Debt> removable = debts.stream()
                .filter(debt -> Objects.equals(debt.getId(), debtId)).findFirst();

        if (removable.isPresent()) {
            debts.remove(removable.get());
            return personService.savePerson(person);
        }

        throw new DebtNotRemovedException(debtId, personId);
    }

    @Override
    public Person removeAllDebts(String personId) throws PersonNotFoundException {
        Person person = personService.getPerson(personId);
        person.getDebts().clear();
        return personService.savePerson(person);
    }


}
