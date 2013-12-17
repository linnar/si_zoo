/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vyjimky;

/**
 *
 * @author Petr
 */
public class UnableToCloseDatabaseCorrectlyException extends Exception{

    public UnableToCloseDatabaseCorrectlyException() {
    }

    public UnableToCloseDatabaseCorrectlyException(String string) {
        super(string);
    }

    public UnableToCloseDatabaseCorrectlyException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UnableToCloseDatabaseCorrectlyException(Throwable thrwbl) {
        super(thrwbl);
    }

    public UnableToCloseDatabaseCorrectlyException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
