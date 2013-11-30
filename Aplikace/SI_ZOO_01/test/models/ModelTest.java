package models;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.avaje.ebean.Ebean;

import play.libs.Yaml;
import play.test.WithApplication;
import static play.test.Helpers.*; 
import models.*;

public class ModelTest extends WithApplication {
	
	
	@Before
	public void setUp(){
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void insertEmployee(){
		Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee bob = Employee.find.where().eq("contact", "bob@mail.com").findUnique();
		assertNotNull(bob);
		assertEquals(bob.lastName,"Bobinski");
	}

	@Test
	public void insertFeed(){
		Feed.create("wheat", 5000, 200);
		Feed feed = Feed.find.where().eq("name", "wheat").findUnique();
		assertNotNull(feed);
		assertEquals(feed.name,"wheat");
	}
	
	@Test
	public void insertAnimal(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal = Animal.find.where().eq("chipNumber", "7777777").findUnique();
		assertNotNull(animal);
		assertEquals(animal.name,"Bobo");
	}
	
	@Test
	public void getWhoCaresForAnimal1(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal = Animal.find.where().eq("chipNumber", "7777777").findUnique();
		bob.addAnimal("7777777");
		List<Employee> whoCares = Employee.findEmployeeByAnimal(animal);
		assertEquals(1,whoCares.size());
		assertEquals(whoCares.get(0).lastName,"Bobinski");
	}
	
	@Test
	public void getWhoCaresForAnimal2(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal = Animal.find.where().eq("chipNumber", "7777777").findUnique();
		animal.addTheOneWhoCares("jane@mail.com");
		bob.addAnimal("7777777");
		jane.addAnimal("7777777");
		List<Employee> whoCares = Employee.findEmployeeByAnimal(animal);
		assertEquals(2,whoCares.size());
		assertEquals(whoCares.get(0).lastName,"Bobinski");
		assertEquals(whoCares.get(1).lastName,"NotBobinski");
	}
	
}