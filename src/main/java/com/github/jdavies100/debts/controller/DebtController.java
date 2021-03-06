package com.github.jdavies100.debts.controller;

import com.github.jdavies100.debts.model.Person;
import com.github.jdavies100.debts.service.DebtService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DebtController {

  private static final Logger LOG = LoggerFactory.getLogger(DebtController.class);
  private DebtService debtService;

  @Autowired
  public DebtController(DebtService debtService) {
    this.debtService = debtService;
  }

  @PostMapping(value = "person/{personId}/debts")
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody
  Person addDebt(@PathVariable("personId") String personId, @RequestParam("amount") BigDecimal amount)
      throws Exception {
    LOG.info("Creating new debt for person with id: {}, amount: {}", personId, amount);
    return debtService.addDebt(personId, amount);
  }

  @DeleteMapping(value = "person/{personId}/debts/{debtId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public @ResponseBody
  Person removeDebt(@PathVariable("personId") String personId, @PathVariable("debtId") String debtId)
      throws Exception {
    LOG.debug("Removing debt from person with id: {}, debt id: {}", personId, debtId);
    return debtService.removeDebt(personId, debtId);
  }

  @DeleteMapping(value = "person/{personId}/debts")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public @ResponseBody
  Person removeAllDebts(@PathVariable("personId") String personId) throws Exception {
    LOG.debug("Removing all debts from person with id: {}", personId);
    return debtService.removeAllDebts(personId);
  }

}
