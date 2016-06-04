package com.springdata.example.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.springdata.example.model.Person;

public interface PersonRepository extends CassandraRepository<Person> {
	
	@Query("select * from Person where lastname=?0")
	List<Person> findByLastname(String lastname);
	
}
