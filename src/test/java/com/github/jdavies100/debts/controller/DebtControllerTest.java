package com.github.jdavies100.debts.controller;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import com.github.jdavies100.debts.controller.helpers.PersonControllerHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class DebtControllerTest {

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
  public void addDebt() {
  }

  @Test
  public void removeDebt() {
  }

  @Test
  public void removeAllDebts() {
  }
}
