/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.entity.Animal;
import Database.DAO.DAOAnimal;
import Database.DBConnector;
import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import view.list_view.AnimalListView;
import view.entity_view.AnimalView;
import view.MainWindow;

/**
 *
 * @author Lenushta
 */
public class AnimalManager {

    private String[] filt = {null, null, null, null};
    private ArrayList<Animal> list = new ArrayList<>();
    private DBConnector con;
    private DAOAnimal dao;

    public AnimalManager(DBConnector connector) throws UnableToObtainDataFromDatabaseException {
        this.con = connector;
        this.dao = new DAOAnimal();
        this.list = this.getList(-1, null, null, null, null);
        new AnimalListView(this, this.list).setVisible(true);
    }

    public void editAnimal(int ID, String chipNumber, String name, String dateOfBirth, String species) {
        new AnimalView(this, ID, chipNumber, name, dateOfBirth, species).setVisible(true);
    }

    public void newAnimal() {
        new AnimalView(this).setVisible(true);
    }

    public void filterAnimal() {
        new AnimalView(this, filt[0], filt[1], filt[2], filt[3]).setVisible(true);
    }

    public void filter(String chipNumber, String name, String dateOfBirth, String species) throws UnableToObtainDataFromDatabaseException {
        filt[0] = chipNumber;
        filt[1] = name;
        filt[2] = dateOfBirth;
        filt[3] = species;
        new AnimalListView(this, this.getList(-1, chipNumber, name, dateOfBirth, species)).setVisible(true);
    }

    public void storno() throws UnableToObtainDataFromDatabaseException {
        new AnimalListView(this, this.getList(-1, null, null, null, null)).setVisible(true);
    }

    public void add(String chipNumber, String name, String dateOfBirth, String species) throws UnableToCloseDatabaseCorrectlyException, UnableToObtainDataFromDatabaseException {
        try {
            Connection conn = con.getConnection();
            this.dao.insert(conn, new Animal(chipNumber, dateOfBirth, species, name));
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex) {
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new AnimalListView(this, this.getList(-1, null, null, null, null)).setVisible(true);
    }

    public void update(int ID, String chipNumber, String name, String dateOfBirth, String species) throws UnableToCloseDatabaseCorrectlyException, UnableToObtainDataFromDatabaseException {
        try {
            Connection conn = con.getConnection();
            this.dao.update(conn, ID, new Animal(chipNumber, dateOfBirth, species, name));
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex) {
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new AnimalListView(this, this.getList(-1, null, null, null, null)).setVisible(true);
    }

    public void home() {
        new MainWindow(this.con).setVisible(true);
    }

    public ArrayList<Animal> getList(int ID, String chipNumber, String name, String dateOfBirth, String species) throws UnableToObtainDataFromDatabaseException {
        ArrayList<Animal> list;
        try {
            Connection conn = con.getConnection();
            list = this.dao.select(conn, ID, chipNumber, dateOfBirth, species, name);
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
            throw new UnableToObtainDataFromDatabaseException(ex);
        }
        return list;
    }

    public void deleteAnimal(Animal animal) throws UnableToObtainDataFromDatabaseException, UnableToCloseDatabaseCorrectlyException {
        try {
            Connection conn = con.getConnection();
            this.dao.delete(conn, animal.getId());
            con.closeConnection(conn);
        } catch (SQLException | UnableToCloseDatabaseCorrectlyException ex) {
            throw new UnableToCloseDatabaseCorrectlyException(ex);
        }
        new AnimalListView(this, this.getList(-1, null, null, null, null)).setVisible(true);
    }
}
