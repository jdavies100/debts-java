package com.github.jdavies100.debts.service;

import com.github.jdavies100.debts.exception.PersonNotFoundException;
import com.github.jdavies100.debts.model.Person;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface PersonService {

  Person createPerson(String name);

  List<Person> getPeople();

  Person getPerson(String id) throws PersonNotFoundException;

  void removePerson(String id) throws PersonNotFoundException;

  Person updatePerson(String id, String name) throws PersonNotFoundException;

  Person savePerson(Person person);

}
