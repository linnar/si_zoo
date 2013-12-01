package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;


@Entity
public class Feed extends Model{
	
	@Id
	public String name;
	public String amount;
	public String minimum;
	
	public static Model.Finder<String,Feed> find = new Model.Finder<>(String.class,Feed.class); 
	
	Feed(String name,int amount,int minimum){
		this.name=name;
		this.amount=""+amount;
		this.minimum=""+minimum;
	}
	
	//Vytvori krmivo a ulozi ho do databaze
	public static Feed create(String name,int amount,int minimum){
		Feed feed = new Feed(name,amount,minimum);
		feed.save();
		return feed;
	}
	
	//Vyhleda krmivo podle nazvu
	public static List<Feed> findFeedByName(String name){
		return find.where().eq("name", name).findList();
	}
	
	//Vyhleda krmivo podle mnozstvi
	public static List<Feed> findFeedByAmount(int amount){
		return find.where().eq("amount", ""+amount).findList();
	}
	
	//Vyhleda krmivo podle minimalni meze
	public static List<Feed> findFeedByMinimum(int minimum){
		return find.where().eq("minimum", ""+minimum).findList();
	}
	
	//ZATIM NEFUNGUJE
	//Vyhleda krmivo, jehoz mnozstvi je pod minimalni mezi 
	public static List<Feed> findFeedUnderMinimum(){
		return find.where().lt("amount","minimum").findList();
	}
	
	public String remove(){
		if(Animal.findAnimalByFeed(this.name).size() == 0){
			this.delete();
			return null;
		}else{
			return "Can't remove this feed. There are animals who eat this feed!";
		}
	}
	
	
}
