/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Petr
 */
public class DBConnector {
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(PreparedStatements.PATH_FULL);
    }
    
    public void closeConnection(Connection conn) throws UnableToCloseDatabaseCorrectlyException{
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new UnableToCloseDatabaseCorrectlyException("Closing of database failed.");
        }
    }
}
