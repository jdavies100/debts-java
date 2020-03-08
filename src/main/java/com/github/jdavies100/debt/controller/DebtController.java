package com.github.jdavies100.debt.controller;

import com.github.jdavies100.debt.service.DebtService;
import com.github.jdavies100.debt.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DebtController {

    private DebtService debtService;

    @Autowired
    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    @PostMapping(value = "person/{personId}/debts")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Person addDebt(@PathVariable("personId") String personId, @RequestParam("amount") double amount) throws Exception {
        return debtService.addDebt(personId, amount);
    }

//    @PutMapping(value = "person/{personId}/debts")
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody
//    Person modifyDebt(@PathVariable("personId") String personId, @RequestParam("amount") double amount) throws Exception {
//        return debtService.addDebt(personId, amount);
//    }

    @DeleteMapping(value = "person/{personId}/debts/{debtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    Person removeDebt(@PathVariable("personId") String personId, @PathVariable("debtId") String debtId)
        throws Exception {
        return debtService.removeDebt(personId, debtId);
    }

    @DeleteMapping(value = "person/{personId}/debts/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    Person removeAllDebts(@PathVariable("personId") String personId) throws Exception {
        return debtService.removeAllDebts(personId);
    }

}
