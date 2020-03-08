package com.github.jdavies100.debts.controller;

import com.github.jdavies100.debts.exception.PersonNotFoundException;
import com.github.jdavies100.debts.service.PersonService;
import com.github.jdavies100.debts.model.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/person", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Person createPerson(@RequestParam("name") String name) {
        return personService.createPerson(name);
    }

    @GetMapping(value = "/person", produces = "application/json")
    @ResponseStatus(HttpStatus.OK) public @ResponseBody List<Person> getAllPersons() {
        return personService.getPeople();
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Person getPersonById(@PathVariable("id") String id) throws PersonNotFoundException {
        return personService.getPerson(id);
    }

    @DeleteMapping(value = "/person/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonById(@PathVariable("id") String id) throws Exception {
        personService.removePerson(id);
    }
}
