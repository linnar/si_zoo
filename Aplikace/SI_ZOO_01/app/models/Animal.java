package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class Animal extends Model{

	@Id
	public String chipNumber;
	public String birthDate;
	public String specie;
	public String name;
	@ManyToMany
	public List<Employee> caresForMe = new ArrayList<>();
	@ManyToMany
	public List<Feed> iEat = new ArrayList<>();
	
	public static Model.Finder<String, Animal> find = new Model.Finder<>(String.class,Animal.class);
	
	Animal(String chipNumber,String birthDate,String specie,String name,Employee employee,Feed feed){
		this.chipNumber=chipNumber;
		this.birthDate=birthDate;
		this.specie=specie;
		this.name=name;
		this.caresForMe.add(employee);
		this.iEat.add(feed);
	}
	
	public static Animal create(String chipNumber,String birthDate,String specie,String name,String employeeEmail,String feedName){
		Animal animal = new Animal(chipNumber,birthDate,specie,name,Employee.find.ref(employeeEmail),Feed.find.ref(feedName));
		animal.save();
		animal.saveManyToManyAssociations("caresForMe");
		animal.saveManyToManyAssociations("iEat");
		return animal;
	}
	
	public void addTheOneWhoCares(String employeeEmail){
		this.caresForMe.add(Employee.find.ref(employeeEmail));
		this.saveManyToManyAssociations("caresForMe");
	}
	
	/*public static List<Employee> findEmployeeByAnimal(Animal animal){
		//return Employee.find.fetch("caresForMe").where().eq("caresForMe.caresFor.chipNumber", animal.chipNumber).findList();
		//return Employee.find.fetch("caresForMe").fetch("caresForMe.caresFor").where().eq("caresForMe.caresFor.chipNumber", animal.chipNumber).findList();
	}*/
	
}
