package com.springdata.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.cassandra.core.CassandraOperations;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.springdata.example.model.Person;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		CassandraOperations cassandraOperations = context.getBean("cassandraTemplate", CassandraOperations.class);
		insertPerson(cassandraOperations);
		selectPerson(cassandraOperations);
	}

	private static void selectPerson(CassandraOperations cassandraOperations) {
		Select select = QueryBuilder.select().from("person");
		System.out.println("Person ID - " + cassandraOperations.queryForObject(select, Person.class));
	}

	private static void insertPerson(CassandraOperations cassandraOperations) {
		cassandraOperations.update(new Person("123123123", "Alison", 35));
	}

}
