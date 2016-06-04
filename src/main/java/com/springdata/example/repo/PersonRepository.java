package com.springdata.example.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springdata.example.model.Person;

public interface PersonRepository extends CrudRepository<Person, String> {
	
	@Query("select * from Person where lastname=?0")
	List<Person> findByLastname(String lastname);
	
}
