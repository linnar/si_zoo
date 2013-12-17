/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.DAO;

import Database.entity.Feed;
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
public class DAOFeed {

    public String insert(Connection conn,Feed feed) throws SQLException, UnableToCloseDatabaseCorrectlyException{
        
        Statement stm = null;
        ResultSet rs = null;

        try{
            
            stm = conn.createStatement();
            
            rs = stm.executeQuery(PreparedStatements.FEED_GET_LAST_ID);
            rs.next();
            int id;
            if (rs.getString(1) == null) {
                id = 0;
            } else {
                id = Integer.parseInt(rs.getString(1));
            }
            
            String command = SQLBuilder.buildFeedInsert(id+1,feed.getName(),feed.getAmount(),feed.getMinimum());

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
    
    public ArrayList<Feed> select(Connection conn,int id, String name,String amount, String minimum) throws UnableToObtainDataFromDatabaseException, UnableToCloseDatabaseCorrectlyException {
        ArrayList<Feed> list = new ArrayList<Feed>();


        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            String command = SQLBuilder.buildFeedSelect(id,name,amount,minimum);
            stm = conn.createStatement();            
            rs = stm.executeQuery(command);            

            while (rs.next()) {
                list.add(new Feed(Integer.parseInt(rs.getString(1)), rs.getString(2),rs.getString(3),rs.getString(4)));
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
    
    public ArrayList<Feed> selectUnderLimit(Connection conn) throws UnableToObtainDataFromDatabaseException, UnableToCloseDatabaseCorrectlyException {
        ArrayList<Feed> list = new ArrayList<Feed>();


        Statement stm = null;
        ResultSet rs = null;
        try {
            String command = PreparedStatements.SELECT_UNDER_LIMIT;
            stm = conn.createStatement();            
            rs = stm.executeQuery(command);            

            while (rs.next()) {
                list.add(new Feed(Integer.parseInt(rs.getString(1)), rs.getString(2),rs.getString(3),rs.getString(4)));
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
            stm = conn.createStatement();
            String command = SQLBuilder.buildFeedDelete(id);
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
    
    public String update(Connection conn,int id,Feed feed) throws SQLException, UnableToCloseDatabaseCorrectlyException{
        
        Statement stm = null;
        ResultSet rs = null;

        try{
            
            stm = conn.createStatement();
            
            String command = SQLBuilder.buildFeedUpdate(id,feed.getName(),feed.getAmount(),feed.getMinimum());

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
