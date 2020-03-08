package com.github.jdavies100.debt.service;

import com.github.jdavies100.debt.model.Person;
import org.springframework.stereotype.Component;

@Component
public interface DebtService {

    Person addDebt(String personId, double amount) throws Exception;

    Person removeDebt(String personId, String debtId) throws Exception;

    Person removeAllDebts(String personId) throws Exception;

}
