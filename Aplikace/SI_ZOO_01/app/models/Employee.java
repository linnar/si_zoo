package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class Employee extends Model{
	
	@Id
	public String contact;
	public String name;
	public String lastName;
	public String birthDate;
	@ManyToMany
	public List<Animal> caresFor = new ArrayList<>();
	
	public static Model.Finder<String, Employee> find = new Model.Finder<>(String.class, Employee.class);
	
	
	//public static Long getIdFromName(String )
	
	public static Employee create(String contact,String name,String lastname,String birthDate){
		Employee employee = new Employee(contact,name,lastname,birthDate);
		employee.save();
		employee.saveManyToManyAssociations("caresFor");
		return employee;
	}
	
	public static List<Employee> findEmployeeByAnimal(Animal animal){
		return find.fetch("caresFor").where().eq("caresFor.chipNumber", animal.chipNumber).findList();
	}
	

	Employee(String contact,String name,String lastname,String birthDate){
		this.contact=contact;
		this.name=name;
		this.lastName=lastname;
		this.birthDate=birthDate;
		
	}
	
	
	public void addAnimal(String chipNumber){
		this.caresFor.add(Animal.find.ref(chipNumber));
		this.saveManyToManyAssociations("caresFor");
	}
}
