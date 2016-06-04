package com.springdata.example;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.cassandra.core.CassandraOperations;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.springdata.example.model.Person;
import com.springdata.example.repo.PersonRepository;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		CassandraOperations cassandraOperations = context.getBean("cqlTemplate", CassandraOperations.class);
		
		PersonRepository personRepo = context.getBean(PersonRepository.class);
		//PersonRepository personRepo = (PersonRepository)context.getBean("personRepository");
		
		Iterator<Person> personIterator = personRepo.findAll().iterator();
		
		while(personIterator.hasNext()) {
			System.out.println(personIterator.next());
		}
		
		List<Person> persons = personRepo.findByLastname("Ware");
		
		for(Person person : persons) {
			System.out.println(person);
		}
		//insertPerson(cassandraOperations);
		//Person person = selectPerson(cassandraOperations);
		//insertLoginEventByQueryBuilder(cassandraOperations, person);
	}

	private static void insertLoginEventByQueryBuilder(
			CassandraOperations cassandraOperations, Person person) {
		
		Insert insert = QueryBuilder.insertInto("login_event");
			   insert.setConsistencyLevel(ConsistencyLevel.ONE);
			   insert.value("person_id", person.getId());
			   insert.value("event_time", new Date());
			   insert.value("event_code", 1);
			   insert.value("ip_address", "localhost");
			   
		cassandraOperations.execute(insert);
		
	}

	private static Person selectPerson(CassandraOperations cassandraOperations) {
		String cqlOne = "select * from person where id = '123123123'";
		Person person = cassandraOperations.selectOne(cqlOne, Person.class);
		System.out.println(String.format("Found People with FirstName [%s] LastName [%s] for id [%s]", person.getFirstname(), person.getLastname(), person.getId()));
		return person;
	}

	private static void insertPerson(CassandraOperations cassandraOperations) {
		cassandraOperations.update(new Person("123123123", "Nathan", "Ware", 35, "nathanware@gmail.com"));
	}

}
