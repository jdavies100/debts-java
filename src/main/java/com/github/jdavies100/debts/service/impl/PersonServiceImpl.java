package com.github.jdavies100.debts.service.impl;

import com.github.jdavies100.debts.exception.PersonNotFoundException;
import com.github.jdavies100.debts.model.Person;
import com.github.jdavies100.debts.repository.PersonRepository;
import com.github.jdavies100.debts.service.PersonService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);
  private PersonRepository repository;

  @Autowired
  public PersonServiceImpl(PersonRepository repository) {
    this.repository = repository;
  }

  @Override
  public Person createPerson(String name) {
    Person person = new Person(name);
    LOG.debug("Creating person with name: {}, returning: {}", name, person);
    return savePerson(person);
  }

  @Override
  public List<Person> getPeople() {
    List<Person> people = (List<Person>) repository.findAll();
    LOG.debug("Getting people, returning: {}", people);
    return people;
  }

  @Override
  public Person getPerson(String id) throws PersonNotFoundException {
    Person person = findById(id);
    LOG.debug("Getting person by id: {}, returning: {}", id, person);
    return person;
  }

  @Override
  public void removePerson(String id) throws PersonNotFoundException {
    Person person = findById(id);
    LOG.debug("Deleting person by id: {}, deleting: {}", id, person);
    repository.delete(person);
  }

  @Override
  public Person updatePerson(String id, String name) throws PersonNotFoundException {
    Person person = findById(id);
    person.setName(name);
    LOG.debug("Updating person with id: {}, name: {}, returning: {}", id, name, person);
    return savePerson(person);
  }

  @Override
  public Person savePerson(Person person) {
    return repository.save(person);
  }

  private Person findById(String id) throws PersonNotFoundException {
    return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
  }

}
