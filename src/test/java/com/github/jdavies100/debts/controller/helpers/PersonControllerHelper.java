package com.github.jdavies100.debts.controller.helpers;

import static com.github.jdavies100.debts.controller.helpers.HttpTypes.DELETE;
import static com.github.jdavies100.debts.controller.helpers.HttpTypes.GET;
import static com.github.jdavies100.debts.controller.helpers.HttpTypes.POST;
import static com.github.jdavies100.debts.controller.helpers.HttpTypes.PUT;

import com.github.jdavies100.debts.model.Person;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

public class PersonControllerHelper {

  private MockMvc mockMvc;

  private String NAME = "name";

  public PersonControllerHelper(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  private List<Person> executeEndpoint(String json) {
    Gson gson = new Gson();
    try {
      return gson.fromJson(json, new TypeToken<List<Person>>() {
      }.getType());
    } catch (IllegalStateException | JsonSyntaxException e) {
      return Collections.singletonList(gson.fromJson(json, Person.class));
    }
  }

  public List<Person> postPerson(String name) throws Exception {
    return executeEndpoint(new PersonMvcBuilder(mockMvc)
        .endpoint("/person")
        .queryType(POST)
        .param(NAME, name)
        .httpStatus(HttpStatus.CREATED.value())
        .build());
  }

  public List<Person> getPeople() throws Exception {
    return executeEndpoint(new PersonMvcBuilder(mockMvc)
        .endpoint("/person")
        .queryType(GET)
        .httpStatus(HttpStatus.OK.value())
        .build());
  }

  public List<Person> getPersonById(String id, HttpStatus status) throws Exception {
    return executeEndpoint(new PersonMvcBuilder(mockMvc)
        .endpoint("/person/" + id)
        .queryType(GET)
        .httpStatus(status.value())
        .build());
  }

  public void deletePersonById(String id) throws Exception {
    executeEndpoint(new PersonMvcBuilder(mockMvc)
        .endpoint("/person/" + id)
        .queryType(DELETE)
        .httpStatus(HttpStatus.NO_CONTENT.value())
        .build());
  }

  public List<Person> updatePersonById(String id, String name) throws Exception {
    return executeEndpoint(new PersonMvcBuilder(mockMvc)
        .endpoint("/person/" + id)
        .queryType(PUT)
        .param(NAME, name)
        .httpStatus(HttpStatus.OK.value())
        .build());
  }
}
