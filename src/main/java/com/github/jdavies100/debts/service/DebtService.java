package com.github.jdavies100.debts.service;

import com.github.jdavies100.debts.model.Person;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public interface DebtService {

  Person addDebt(String personId, BigDecimal amount) throws Exception;

  Person removeDebt(String personId, String debtId) throws Exception;

  Person removeAllDebts(String personId) throws Exception;

}
