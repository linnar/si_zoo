/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.*;
import Database.DAO.DAOStaff;
import Database.entity.Staff;
import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import view.MainWindow;
import view.entity_view.StaffView;
import view.list_view.StaffListView;

/**
 *
 * @author Eliška
 */
public class StaffManager {

    private String[] filt={null,null,null,null,null};
    private ArrayList<Staff> list = new ArrayList<>();
    private DBConnector con;
    private DAOStaff dao;
    
    public StaffManager(DBConnector connector) throws UnableToObtainDataFromDatabaseException {
        this.con = connector;
        this.dao = new DAOStaff();
        this.list = this.getList(-1, null, null, null, null, null);
        new StaffListView(this, this.list).setVisible(true);
    }
    
    public void editStaff(int ID, String personalNumber,String name,String lastName,String dateOfBirth,String contact){
        new StaffView(this, ID,  personalNumber, name, lastName, dateOfBirth, contact).setVisible(true);
    }
    
    public void newStaff(){
        new StaffView(this).setVisible(true);
    }
    
    public void filterStaff(){
        new StaffView(this, filt[0],filt[1],filt[2],filt[3],filt[4]).setVisible(true);
    }

    public void filter(int ID, String personalNumber,String name,String lastName,String dateOfBirth,String contact) throws UnableToObtainDataFromDatabaseException {
        filt[0]=personalNumber;
        filt[1]=name;
        filt[2]=lastName;
        filt[3]=dateOfBirth;
        filt[4]=contact;
        new StaffListView(this,this.getList(ID ,personalNumber, name, lastName, dateOfBirth, contact)).setVisible(true);
    }
    
    public void storno() throws UnableToObtainDataFromDatabaseException{
        new StaffListView(this,this.getList(-1, null, null, null, null, null)).setVisible(true);
    }
    
    public void add(String personalNumber,String name,String lastName,String dateOfBirth,String contact) throws UnableToCloseDatabaseCorrectlyException, UnableToObtainDataFromDatabaseException{
        try {
            Connection conn = con.getConnection();
            this.dao.insert(conn, new Staff(personalNumber, name, lastName, dateOfBirth, contact));  
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex){
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new StaffListView(this,this.getList(-1, null, null, null, null, null)).setVisible(true);
    }
    
    public void update(int ID, String personalNumber,String name,String lastName,String dateOfBirth,String contact) throws UnableToObtainDataFromDatabaseException, UnableToCloseDatabaseCorrectlyException{
        try {
            Connection conn = con.getConnection();
            this.dao.update(conn, ID, new Staff(personalNumber, name, lastName, dateOfBirth, contact));  
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex){
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new StaffListView(this,this.getList(-1, null, null, null, null, null)).setVisible(true);
    }
    
    public void home(){
        new MainWindow(this.con).setVisible(true);
    }
    
    public ArrayList<Staff> getList(int ID, String personalNumber,String name,String lastName,String dateOfBirth,String contact) throws UnableToObtainDataFromDatabaseException{
       ArrayList<Staff> list;
        try {
            Connection conn = con.getConnection();
            list = this.dao.select(conn, ID, personalNumber, name, lastName, dateOfBirth, contact);  
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex){
            throw new UnableToObtainDataFromDatabaseException(ex);
        }
        return list;
    }

    public void deleteStaff(int ID) throws UnableToCloseDatabaseCorrectlyException, UnableToObtainDataFromDatabaseException {
    try {
            Connection conn = con.getConnection();
            this.dao.delete(conn, ID);  
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex){
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new StaffListView(this,this.getList(-1, null, null, null, null, null)).setVisible(true);
    }
}
