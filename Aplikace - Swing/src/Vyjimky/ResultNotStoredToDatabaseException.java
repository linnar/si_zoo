/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vyjimky;

/**
 *
 * @author Petr
 */
public class ResultNotStoredToDatabaseException extends Exception{

    public ResultNotStoredToDatabaseException() {
    }

    public ResultNotStoredToDatabaseException(String string) {
        super(string);
    }

    public ResultNotStoredToDatabaseException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ResultNotStoredToDatabaseException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ResultNotStoredToDatabaseException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
