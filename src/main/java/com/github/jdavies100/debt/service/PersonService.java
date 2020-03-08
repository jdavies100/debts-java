package com.github.jdavies100.debt.service;

import com.github.jdavies100.debt.exception.PersonNotFoundException;
import com.github.jdavies100.debt.model.Person;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface PersonService {

    Person createPerson(String name);

    List<Person> getPeople();

    Person getPerson(String id) throws PersonNotFoundException;

    void removePerson(String id) throws PersonNotFoundException;

    Person savePerson(Person person);

}
