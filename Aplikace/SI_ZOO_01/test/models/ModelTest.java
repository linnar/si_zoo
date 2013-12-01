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
		assertEquals("Bobinski",bob.lastName);
	}

	@Test
	public void insertFeed(){
		Feed.create("wheat", 5000, 200);
		Feed feed = Feed.find.where().eq("name", "wheat").findUnique();
		assertNotNull(feed);
		assertEquals("wheat",feed.name);
	}
	
	@Test
	public void insertAnimal(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal = Animal.find.where().eq("chipNumber", "7777777").findUnique();
		assertNotNull(animal);
		assertEquals("Bobo",animal.name);
	}
	
	@Test
	public void getWhoCaresForAnimal1(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal = Animal.find.where().eq("chipNumber", "7777777").findUnique();
		bob.addAnimal("7777777");
		List<Employee> whoCares = Employee.findEmployeeByAnimal(animal.chipNumber);
		assertEquals(1,whoCares.size());
		assertEquals("Bobinski",whoCares.get(0).lastName);
	}
	
	@Test
	public void getWhoCaresForAnimal2(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Employee pablo = Employee.create("pablo@mail.com", "Pablo", "AlsoNotBobinski", "44.44.4444");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal = Animal.find.where().eq("chipNumber", "7777777").findUnique();
		animal.addTheOneWhoCares("jane@mail.com");
		animal.addTheOneWhoCares("pablo@mail.com");
		bob.addAnimal("7777777");
		jane.addAnimal("7777777");
		pablo.addAnimal("7777777");
		List<Employee> whoCaresForAnimal = Employee.findEmployeeByAnimal(animal.chipNumber);
		assertEquals(3,whoCaresForAnimal.size());
		assertEquals("Bobinski",whoCaresForAnimal.get(0).lastName);
		assertEquals("NotBobinski",whoCaresForAnimal.get(1).lastName);
		assertEquals("AlsoNotBobinski",whoCaresForAnimal.get(2).lastName);
	}
	
	@Test
	public void getWhatIEat(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		animal2.addWhatIEat("cheese");
		List<Animal> whoEatsThisFeed1 = Animal.findAnimalByFeed("wheat");
		List<Animal> whoEatsThisFeed2 = Animal.findAnimalByFeed("cheese");
		List<Animal> whoEatsThisFeed3 = Animal.findAnimalByFeed("meat");
		assertEquals(2,whoEatsThisFeed1.size());
		assertEquals(true,whoEatsThisFeed1.contains(animal1));
		assertEquals(true,whoEatsThisFeed1.contains(animal2));

		assertEquals(1,whoEatsThisFeed2.size());
		assertEquals(false,whoEatsThisFeed2.contains(animal1));
		assertEquals(true,whoEatsThisFeed2.contains(animal2));
		
		assertEquals(0,whoEatsThisFeed3.size());
		assertEquals(whoEatsThisFeed3.contains(animal1),false);
		assertEquals(whoEatsThisFeed3.contains(animal2),false);
	}
	
	@Test
	public void getWhatIEat2(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		animal2.addWhatIEat("cheese");
		List<Animal> whoEatsThisFeed1 = Animal.findAnimalByFeed("wheat");
		animal2.removeWhatIEat("wheat");
		List<Animal> whoEatsThisFeed2 = Animal.findAnimalByFeed("wheat");

		assertEquals(2,whoEatsThisFeed1.size());
		assertEquals(true,whoEatsThisFeed1.contains(animal1));
		assertEquals(true,whoEatsThisFeed1.contains(animal2));

		assertEquals(1,whoEatsThisFeed2.size());
		assertEquals(true,whoEatsThisFeed2.contains(animal1));
		assertEquals(false,whoEatsThisFeed2.contains(animal2));
	}
	
	@Test
	public void animalSelect(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed bread = Feed.create("bread", 5000, 300);
		Feed popcorn = Feed.create("popcorn", 5000, 10000);
		Feed spaghetti = Feed.create("spaghetti", 2000, 300);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		Animal animal3 = Animal.create("5555555", "??.??.????", "unicorn", "Ponny", "jane@mail.com", "popcorn");
		animal2.addWhatIEat("cheese");
		
		List<Animal> list1 = Animal.findAnimalByBirthDate("22.22.2222");
		List<Animal> list2 = Animal.findAnimalByFeed("cheese");
		List<Animal> list3 = Animal.findAnimalByChipNumber("6666666");
		List<Animal> list4 = Animal.findAnimalBySpecie("T-Rex");
		List<Animal> list5 = Animal.findAnimalByFeed("wheat");
		
		assertEquals(1,list1.size());
		assertEquals(true,list1.contains(animal1));

		assertEquals(1,list2.size());
		assertEquals(true,list2.contains(animal2));

		assertEquals(1,list3.size());
		assertEquals(true,list3.contains(animal2));

		assertEquals(1,list4.size());
		assertEquals(true,list4.contains(animal2));

		assertEquals(2,list5.size());
		assertEquals(true,list5.contains(animal1));
		assertEquals(true,list5.contains(animal2));
	}
	
	@Test
	public void animalRename(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed bread = Feed.create("bread", 5000, 300);
		Feed popcorn = Feed.create("popcorn", 5000, 10000);
		Feed spaghetti = Feed.create("spaghetti", 2000, 300);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		Animal animal3 = Animal.create("5555555", "??.??.????", "unicorn", "Ponny", "jane@mail.com", "popcorn");
		animal2.addWhatIEat("cheese");
		
		List<Animal> list1 = Animal.findAnimalByChipNumber("6666666");
		animal2.edit("Benjamin Franklin");
		List<Animal> list2 = Animal.findAnimalByChipNumber("6666666");
		
		assertEquals(1,list1.size());
		assertEquals(1,list2.size());

		assertEquals("Franklin",list1.get(0).name);
		assertEquals("Benjamin Franklin",list2.get(0).name);
		
	}
	
	@Test
	public void employeeSelect(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed bread = Feed.create("bread", 5000, 300);
		Feed popcorn = Feed.create("popcorn", 5000, 10000);
		Feed spaghetti = Feed.create("spaghetti", 2000, 300);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		Animal animal3 = Animal.create("5555555", "??.??.????", "unicorn", "Ponny", "jane@mail.com", "popcorn");
		animal2.addWhatIEat("cheese");

		List<Employee> list1 = Employee.findEmployeeByLastName("Bobinski");
		List<Employee> list2 = Employee.findEmployeeByName("Jane");
		List<Employee> list3 = Employee.findEmployeeByBirthDate("33.33.3333");

		assertEquals(1,list1.size());
		assertEquals("bob@mail.com",list1.get(0).contact);
		assertEquals(1,list2.size());
		assertEquals("jane@mail.com",list2.get(0).contact);
		assertEquals(1,list3.size());
		assertEquals("jane@mail.com",list3.get(0).contact);
	}
	
	@Test
	public void employeeRename(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed bread = Feed.create("bread", 5000, 300);
		Feed popcorn = Feed.create("popcorn", 5000, 10000);
		Feed spaghetti = Feed.create("spaghetti", 2000, 300);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		Animal animal3 = Animal.create("5555555", "??.??.????", "unicorn", "Ponny", "jane@mail.com", "popcorn");
		animal2.addWhatIEat("cheese");

		List<Employee> list1 = Employee.findEmployeeByContact("bob@mail.com");
		bob.edit("Bobby", "StillBobinski");
		List<Employee> list2 = Employee.findEmployeeByContact("bob@mail.com");
		
		assertEquals(1,list1.size());
		assertEquals(1,list2.size());

		assertEquals("Bob",list1.get(0).name);
		assertEquals("Bobby",list2.get(0).name);
		assertEquals("Bobinski",list1.get(0).lastName);
		assertEquals("StillBobinski",list2.get(0).lastName);
	}
	
	@Test
	public void feedSelect(){ 
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed bread = Feed.create("bread", 5000, 300);
		Feed popcorn = Feed.create("popcorn", 5000, 10000);
		Feed spaghetti = Feed.create("spaghetti", 2000, 300);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		animal2.addWhatIEat("cheese");

		List<Feed> list1 = Feed.findFeedByName("spaghetti");
		List<Feed> list2 = Feed.findFeedByAmount(5000);
		List<Feed> list3 = Feed.findFeedByAmount(2000);
		List<Feed> list4 = Feed.findFeedByMinimum(300);
		List<Feed> list5 = Feed.findFeedUnderMinimum();

		assertEquals(1,list1.size());
		assertEquals(true,list1.contains(spaghetti));
		
		assertEquals(3,list2.size());
		assertEquals(false,list2.contains(spaghetti));
		assertEquals(true,list2.contains(wheat));
		assertEquals(true,list2.contains(bread));
		assertEquals(true,list2.contains(popcorn));

		assertEquals(2,list3.size());
		assertEquals(true,list3.contains(spaghetti));
		assertEquals(true,list3.contains(cheese));
		
		assertEquals(2,list4.size());
		assertEquals(true,list4.contains(spaghetti));
		assertEquals(true,list4.contains(bread));
		
		/*assertEquals(1,list5.size());
		assertEquals(true,list5.contains(popcorn));*/
	}
	
	@Test
	public void feedRename(){
		Employee bob = Employee.create("bob@mail.com", "Bob", "Bobinski", "11.11.1111");
		Employee jane = Employee.create("jane@mail.com", "Jane", "NotBobinski", "33.33.3333");
		Feed wheat = Feed.create("wheat", 5000, 200);
		Feed bread = Feed.create("bread", 5000, 300);
		Feed popcorn = Feed.create("popcorn", 5000, 10000);
		Feed spaghetti = Feed.create("spaghetti", 2000, 300);
		Feed cheese = Feed.create("cheese", 2000, 100);
		Animal animal1 = Animal.create("7777777", "22.22.2222", "elephant", "Bobo", "bob@mail.com", "wheat");
		Animal animal2 = Animal.create("6666666", "??.??.-100 000 000", "T-Rex", "Franklin", "jane@mail.com", "wheat");
		Animal animal3 = Animal.create("5555555", "??.??.????", "unicorn", "Ponny", "jane@mail.com", "popcorn");
		animal2.addWhatIEat("cheese");
		
		List<Feed> list1 = Feed.findFeedByName("wheat");
		wheat.edit(2500, 150);
		List<Feed> list2 = Feed.findFeedByName("wheat");
		wheat.decreaseAmount(150);
		List<Feed> list3 = Feed.findFeedByName("wheat");
		
		assertEquals(1,list1.size());
		assertEquals(1,list2.size());
		assertEquals(1,list3.size());

		assertEquals("5000",list1.get(0).amount);
		assertEquals("2500",list2.get(0).amount);
		assertEquals("2350",list3.get(0).amount);
		assertEquals("200",list1.get(0).minimum);
		assertEquals("150",list2.get(0).minimum);
		assertEquals("150",list3.get(0).minimum);
	}
}