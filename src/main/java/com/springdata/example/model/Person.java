package com.springdata.example.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class Person {
	
	@PrimaryKey
	private String id;
	private String firstname;
	private String lastname;
	private int age;
	private String emailAddress;


	public Person(String id, String firstname, String lastname, int age, String emailAddress) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.emailAddress = emailAddress;
	}

	public String getId() {
		return id;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getAge() {
		return age;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + firstname + " " + lastname + ", age=" + age + ", email=" + emailAddress + "]";
	}

}
