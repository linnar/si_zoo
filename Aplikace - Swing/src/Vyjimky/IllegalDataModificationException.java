/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vyjimky;

/**
 * Vyjimka signalizujici ze doslo k nepovolenym zmenam v ulozene hre (neshoduje se hashcode ulozenych dat s hashcodem ulozenym v souboru)
 * @author Petr
 */
public class IllegalDataModificationException  extends Exception{

    public IllegalDataModificationException() {
    }

    public IllegalDataModificationException(String string) {
        super(string);
    }

    public IllegalDataModificationException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public IllegalDataModificationException(Throwable thrwbl) {
        super(thrwbl);
    }

    public IllegalDataModificationException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
