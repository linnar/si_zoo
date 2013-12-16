/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DAO.DAOFeed;
import Database.DBConnector;
import Database.entity.Feed;
import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import view.MainWindow;
import view.entity_view.FoodView;
import view.list_view.FoodListView;

/**
 *
 * @author Lenushta
 */
public class FoodManager {
    
    private String[] filt={null,null,null};
    private ArrayList<Feed> list = new ArrayList<>();
     private DBConnector con;
    private DAOFeed dao;
    
    public FoodManager(DBConnector connector) throws UnableToObtainDataFromDatabaseException {
        this.con = connector;
        this.dao = new DAOFeed();
        this.list = this.getList(-1, null, null, null);
        new FoodListView(this, this.getList(-1, null, null, null)).setVisible(true);
    }
    
    public void editFood(int ID, String name,String quantity,String minimum){
        new FoodView(this, ID, name, quantity, minimum).setVisible(true);
    }
    
    public void newFood(){
        new FoodView(this).setVisible(true);
    }
    
    public void filterFood(){
        new FoodView(this, filt[0],filt[1],filt[2]).setVisible(true);
    }

    public void filter(String name,String quantity,String minimum) throws UnableToObtainDataFromDatabaseException {
        filt[0]=name;
        filt[1]=quantity;
        filt[2]=minimum;
        new FoodListView(this,this.getList(-1, name, quantity, minimum)).setVisible(true);
    }
    
    public void storno() throws UnableToObtainDataFromDatabaseException{
        new FoodListView(this, this.getList(-1, null, null, null)).setVisible(true);
    }
    
    public void add(String name,String quantity,String minimum) throws UnableToCloseDatabaseCorrectlyException, UnableToObtainDataFromDatabaseException{
        try {
            Connection conn = con.getConnection();
            this.dao.insert(conn, new Feed(name, quantity, minimum));
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex) {
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new FoodListView(this, this.getList(-1, null, null, null)).setVisible(true);
    }
    
    public void update(int ID, String name,String quantity,String minimum) throws UnableToCloseDatabaseCorrectlyException, UnableToObtainDataFromDatabaseException{
        try {
            Connection conn = con.getConnection();
            this.dao.update(conn, ID, new Feed(name, quantity, minimum));
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex) {
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new FoodListView(this, this.getList(-1, null, null, null)).setVisible(true);
    }
    
    public void home(){
        new MainWindow(this.con).setVisible(true);
    }
    
    public ArrayList<Feed> getList(int ID, String name,String quantity,String minimum) throws UnableToObtainDataFromDatabaseException{
        ArrayList<Feed> list;
        try {
            Connection conn = con.getConnection();
            list = this.dao.select(conn, ID, name, quantity, minimum);
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
            throw new UnableToObtainDataFromDatabaseException(ex);
        }
        return list;
    }

    public void deleteFood(Feed food) throws UnableToCloseDatabaseCorrectlyException, UnableToObtainDataFromDatabaseException {
    try {
            Connection conn = con.getConnection();
            this.dao.delete(conn, food.getId());
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex) {
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new FoodListView(this, this.getList(-1, null, null, null)).setVisible(true);
    }
}
