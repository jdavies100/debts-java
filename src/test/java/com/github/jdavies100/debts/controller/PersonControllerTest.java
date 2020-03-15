package com.github.jdavies100.debts.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import com.github.jdavies100.debts.controller.helpers.PersonControllerHelper;
import com.github.jdavies100.debts.model.Person;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class PersonControllerTest {

  private static final String NAME = "name";

  @Rule
  public ExpectedException thrown;
  @Autowired
  private MockMvc mockMvc;
  private PersonControllerHelper helper;

  @Before
  public void setUp() {
    this.helper = new PersonControllerHelper(mockMvc);
  }

  @Test
  public void createPerson() throws Throwable {
    String name = RandomStringUtils.randomAlphanumeric(10);

    List<Person> people = helper.postPerson(name);
    assertEquals(1, people.size());
    Person person = people.get(0);

    assertNotNull(person.getId());
    assertTrue(person.getId()
        .matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"));
    assertEquals(name, person.getName());
    assertTrue(person.getDebts().isEmpty());
  }

  @Test
  public void getAllPersons() throws Throwable {
    for (int i = 0; i < 4; i++) {
      helper.postPerson(NAME);
    }

    List<Person> people = helper.getPeople();

    assertEquals(4, people.size());
  }

  @Test
  public void getPersonById() throws Throwable {
    String name = RandomStringUtils.randomAlphanumeric(10);

    String id = helper.postPerson(name)
        .get(0).getId();

    List<Person> people = helper.getPersonById(id, HttpStatus.OK);
    assertEquals(1, people.size());
    Person person = people.get(0);

    assertEquals(id, person.getId());
    assertEquals(name, person.getName());
    assertTrue(person.getDebts().isEmpty());
  }

  @Test
  public void deletePersonById() throws Throwable {
    String id = helper.postPerson(NAME)
        .get(0).getId();
    helper.deletePersonById(id);

    helper.getPersonById(id, HttpStatus.NOT_FOUND);
  }

  @Test
  public void updatePersonById() throws Throwable {
    String id = helper.postPerson(NAME)
        .get(0).getId();
    String fakeName = RandomStringUtils.randomAlphanumeric(10);
    List<Person> people = helper.updatePersonById(id, fakeName);
    assertEquals(1, people.size());
    Person person = people.get(0);

    assertEquals(id, person.getId());
    assertEquals(fakeName, person.getName());
    assertTrue(person.getDebts().isEmpty());
  }
}
