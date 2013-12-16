/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


/**
 *
 * @author Petr
 */
public class DBInit {

    public DBInit() {
        initializeDB();
    }
    
    private String initializeDB(){
        try {
            // JDBC driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            return null;
        } catch (java.lang.ClassNotFoundException e) {
            return "Databazi nebylo mozne vytvorit.";
        }
    }
}
