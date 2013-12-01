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
	
	//Vytvori zamestnance a ulozi ho do databaze
	public static Employee create(String contact,String name,String lastname,String birthDate){
		Employee employee = new Employee(contact,name,lastname,birthDate);
		employee.save();
		employee.saveManyToManyAssociations("caresFor");
		return employee;
	}
	
	//Vyhleda zamestnance podle zvirete, o ktere se stara
	public static List<Employee> findEmployeeByAnimal(String chipNumber){
		return find.fetch("caresFor").where().eq("caresFor.chipNumber", chipNumber).findList();
	}
	
	//Vyhleda zamestnance podle kontaktu(email)
	public static List<Employee> findEmployeeByContact(String contact){
		return find.where().eq("contact", contact).findList();
	}
	
	//Vyhleda zamestnance podle jmena
	public static List<Employee> findEmployeeByName(String name){
		return find.where().eq("name", name).findList();
	}
	
	//Vyhleda zamestnance podle prijmeni
	public static List<Employee> findEmployeeByLastName(String lastName){
		return find.where().eq("lastName", lastName).findList();
	}
	
	//Vyhleda zamestnance podle data narozeni
	public static List<Employee> findEmployeeByBirthDate(String birthDate){
		return find.where().eq("birthDate", birthDate).findList();
	}
	
	Employee(String contact,String name,String lastname,String birthDate){
		this.contact=contact;
		this.name=name;
		this.lastName=lastname;
		this.birthDate=birthDate;
		
	}
	
	//Prida zamestnanci zvire, o ktere se bude starat
	public void addAnimal(String chipNumber){
		this.caresFor.add(Animal.find.ref(chipNumber));
		this.save();
		this.saveManyToManyAssociations("caresFor");
	}
	
	//Odstrani zamestnanci zvire, o ktere se stara
	public void removeAnimal(String chipNumber){
		this.caresFor.remove(Animal.find.ref(chipNumber));
		this.save();
		this.saveManyToManyAssociations("caresFor");
	}
	
	//Odstrani zamestnance, pokud uz nejsou zvirata, o ktere by se staral
	public String remove(){
		if(Animal.findAnimalByEmployee(this.contact).size() == 0 && caresFor.size() == 0){
			this.delete();
			return null;
		}else{
			return "Can't remove this employee. There are animals who are connected to this employee!";
		}
	}
	
	
}
