/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vyjimky;

/**
 *
 * @author Petr
 */
public class UnableToObtainDataFromDatabaseException extends Exception{

    public UnableToObtainDataFromDatabaseException() {
    }

    public UnableToObtainDataFromDatabaseException(String string) {
        super(string);
    }

    public UnableToObtainDataFromDatabaseException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UnableToObtainDataFromDatabaseException(Throwable thrwbl) {
        super(thrwbl);
    }

    public UnableToObtainDataFromDatabaseException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
