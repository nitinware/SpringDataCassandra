package com.springdata.example;

import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.cassandra.core.CassandraOperations;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.springdata.example.model.Person;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		CassandraOperations cassandraOperations = context.getBean("cassandraTemplate", CassandraOperations.class);
		//insertPerson(cassandraOperations);
		Person person = selectPerson(cassandraOperations);
		insertLoginEventByQueryBuilder(cassandraOperations, person);
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
		String cqlOne = "select * from person where id = '1234567890'";
		Person person = cassandraOperations.selectOne(cqlOne, Person.class);
		System.out.println(String.format("Found People with Name [%s] for id [%s]", person.getName(), person.getId()));
		return person;
	}

	private static void insertPerson(CassandraOperations cassandraOperations) {
		cassandraOperations.update(new Person("123123123", "Alison", 35));
	}

}
