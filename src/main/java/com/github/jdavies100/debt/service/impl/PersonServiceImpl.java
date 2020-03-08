package com.github.jdavies100.debt.service.impl;

import com.github.jdavies100.debt.exception.PersonNotFoundException;
import com.github.jdavies100.debt.service.PersonService;
import com.github.jdavies100.debt.model.Person;
import com.github.jdavies100.debt.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person createPerson(String name) {
        return this.savePerson(new Person(name));
    }

    @Override
    public List<Person> getPeople() {
        return (List<Person>) repository.findAll();
    }

    @Override
    public Person getPerson(String id) throws PersonNotFoundException {
        return findById(id);
    }

    @Override
    public void removePerson(String id) throws PersonNotFoundException {
        Person person = findById(id);
        repository.delete(person);
    }

    @Override
    public Person savePerson(Person person) {
        return repository.save(person);
    }

    private Person findById(String id) throws PersonNotFoundException {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
