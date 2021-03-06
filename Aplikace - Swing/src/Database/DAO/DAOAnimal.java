/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.DAO;

import Database.entity.Animal;
import Database.PreparedStatements;
import Database.SQLBuilder;
import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Petr
 */
public class DAOAnimal {
    
    public String insert(Connection conn,Animal animal) throws SQLException, UnableToCloseDatabaseCorrectlyException{
        
        Statement stm = null;
        ResultSet rs = null;

        try{
            
            stm = conn.createStatement();
            
            rs = stm.executeQuery(PreparedStatements.ANIMAL_GET_LAST_ID);
            rs.next();
            int id;
            if (rs.getString(1) == null) {
                id = 0;
            } else {
                id = Integer.parseInt(rs.getString(1));
            }
            
            String command = SQLBuilder.buildAnimalInsert(id+1,animal.getChip(),animal.getBirth(),animal.getSpecie(),animal.getName());

            stm.executeUpdate(command);
            
        }catch(SQLException ex){
            throw ex;
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                return "ok";
            } catch (SQLException ex) {
                throw new UnableToCloseDatabaseCorrectlyException("Unable to close database correctly.");
            }
        }
        
    }
    
    public ArrayList<Animal> select(Connection conn,int id, String chip,String birth, String specie,String name) throws UnableToObtainDataFromDatabaseException, UnableToCloseDatabaseCorrectlyException {
        ArrayList<Animal> list = new ArrayList<Animal>();


        Statement stm = null;
        ResultSet rs = null;
        try {
            String command = SQLBuilder.buildAnimalSelect(id,chip,birth,specie,name);
            stm = conn.createStatement();            
            rs = stm.executeQuery(command);            

            while (rs.next()) {
                list.add(new Animal(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            throw new UnableToObtainDataFromDatabaseException("Unable to obtain data from database.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                throw new UnableToCloseDatabaseCorrectlyException("Unable to close database correctly.");
            }
        }
        return list;
    }
    
    public String delete(Connection conn,int id) throws SQLException, UnableToCloseDatabaseCorrectlyException{
        Statement stm = null;
        ResultSet rs = null;

        try{
            String command = SQLBuilder.buildAnimalDelete(id);
            stm = conn.createStatement();
            stm.executeUpdate(command);
            
        }catch(SQLException ex){
            throw ex;
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                return "ok";
            } catch (SQLException ex) {
                throw new UnableToCloseDatabaseCorrectlyException("Unable to close database correctly.");
            }
        }
        
    }
    
    public String update(Connection conn,int id,Animal animal) throws SQLException, UnableToCloseDatabaseCorrectlyException{
        
        Statement stm = null;
        ResultSet rs = null;

        try{
            
            stm = conn.createStatement();
            
            String command = SQLBuilder.buildAnimalUpdate(id,animal.getChip(),animal.getBirth(),animal.getSpecie(),animal.getName());

            stm.executeUpdate(command);
            
        }catch(SQLException ex){
            throw ex;
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                return "ok";
            } catch (SQLException ex) {
                throw new UnableToCloseDatabaseCorrectlyException("Unable to close database correctly.");
            }
        }
        
    }

}
