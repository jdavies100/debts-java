package com.github.jdavies100.debt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DebtNotRemovedException extends Exception {

    public DebtNotRemovedException(String debtId, String personId) {
        super("Unable to remove debt with id: " + debtId
            + " from person with id: " + personId);
    }
}
