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
	public List<Staff> caresForMe = new ArrayList<>();
	@ManyToMany
	public List<Feed> iEat = new ArrayList<>();
	
	public static Model.Finder<String, Animal> find = new Model.Finder<>(String.class,Animal.class);
	
	private Animal(String chipNumber,String birthDate,String specie,String name,Staff employee,Feed feed){
		this.chipNumber=chipNumber;
		this.birthDate=birthDate;
		this.specie=specie;
		this.name=name;
		this.caresForMe.add(employee);
		this.iEat.add(feed);
	}
	
	//vytvori zvire a ulozi ho do databaze
	public static Animal create(String chipNumber,String birthDate,String specie,String name,String employeeEmail,String feedName){
		Animal animal = new Animal(chipNumber,birthDate,specie,name,Staff.find.ref(employeeEmail),Feed.find.ref(feedName));
		animal.save();
		animal.saveManyToManyAssociations("caresForMe");
		animal.saveManyToManyAssociations("iEat");
		return animal;
	}
	
	//Vyhleda seznam zvirat podle osoby, ktera se o ne stara
	public static List<Animal> findAnimalByEmployee(String email){
		return find.fetch("caresForMe").where().eq("caresForMe.contact", email).findList();
	}
	
	//Vyhleda seznam zvirat podle krmiva
	public static List<Animal> findAnimalByFeed(String feedName){
		return find.fetch("iEat").where().eq("iEat.name", feedName).findList();
	}
	
	//Vyhleda seznam zvirat podle jmena
	public static List<Animal> findAnimalByName(String animalName){
		return find.where().eq("name", animalName).findList();
	}
	
	//Vyhleda seznam zvirat podle druhu
	public static List<Animal> findAnimalBySpecie(String specie){
		return find.where().eq("specie", specie).findList();
	}
	
	//Vyhleda seznam zvirat podle data narozeni
	public static List<Animal> findAnimalByBirthDate(String birthDate){
		return find.where().eq("birthDate", birthDate).findList();
	}
	
	//Vyhleda seznam zvirat podle cisla chipu
	public static List<Animal> findAnimalByChipNumber(String chipNumber){
		return find.where().eq("chipNumber", chipNumber).findList();
	}
	
	//Prida osobu ktera se stara o dane zvire
	public void addTheOneWhoCares(String employeeEmail){
		this.caresForMe.add(Staff.find.ref(employeeEmail));
		this.update();
		this.saveManyToManyAssociations("caresForMe");
	}
	
	//Odebere osobu ktera se stara o dane zvire
	public void removeTheOneWhoCares(String employeeEmail){
		this.caresForMe.remove(Staff.find.ref(employeeEmail));
		this.update();
		this.saveManyToManyAssociations("caresForMe");
	}
	
	//Prida krmivo ktere zvire ji
	public void addWhatIEat(String feedName){
		this.iEat.add(Feed.find.ref(feedName));
		this.update();
		this.saveManyToManyAssociations("iEat");
	}

	//Odebere krmivo ktere zvire ji
	public void removeWhatIEat(String feedName){
		this.iEat.remove(Feed.find.ref(feedName));
		this.update();
		this.saveManyToManyAssociations("iEat");
	}
	
	//Zrusi zvire a odebere ho vsem zamestnancum, kteri se o nej starali
	public void remove(){
		List<Staff> list = Staff.findEmployeeByAnimal(this.chipNumber);
		for(Staff emp: list){
			emp.removeAnimal(this.chipNumber);
		}
		this.caresForMe = null;
		this.iEat = null;
		this.delete();
	}
	
	public void edit(String name){
		this.name=name;
		this.update();
	}
	
	/*public static List<Employee> findEmployeeByAnimal(Animal animal){
		//return Employee.find.fetch("caresForMe").where().eq("caresForMe.caresFor.chipNumber", animal.chipNumber).findList();
		//return Employee.find.fetch("caresForMe").fetch("caresForMe.caresFor").where().eq("caresForMe.caresFor.chipNumber", animal.chipNumber).findList();
	}*/
	
}
