package com.github.jdavies100.debts.repository;

import com.github.jdavies100.debts.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

}
