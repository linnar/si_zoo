package models;

import javax.persistence.*;
import play.db.ebean.Model;


@Entity
public class Feed extends Model{
	
	@Id
	public String name;
	public int amount;
	public int minimum;
	
	public static Model.Finder<String,Feed> find = new Model.Finder<>(String.class,Feed.class); 
	
	Feed(String name,int amount,int minimum){
		this.name=name;
		this.amount=amount;
		this.minimum=minimum;
	}
	
	public static Feed create(String name,int amount,int minimum){
		Feed feed = new Feed(name,amount,minimum);
		feed.save();
		return feed;
	}
}
