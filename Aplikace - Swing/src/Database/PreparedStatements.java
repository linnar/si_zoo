/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Petr
 */
public class PreparedStatements {
    
    //<editor-fold defaultstate="collapsed" desc="Connection necessities">
    public static final String PATH_FULL = "jdbc:derby:SI_ZOO_DB;create=true;user=SI_ZOO;password=mojetajneheslo";
    public static final String PATH = "jdbc:derby:SI_ZOO_DB;create=true;";
    public static final String USER = "SI_ZOO";
    public static final String PASSWORD = "mojetajneheslo";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Names of columns">
    public final static String TABLE_ANIMAL_ID="ANIMAL_ID";
    public final static String TABLE_ANIMAL_CHIP="ANIMAL_CHIP";
    public final static String TABLE_ANIMAL_BIRTH="ANIMAL_BIRTH";
    public final static String TABLE_ANIMAL_SPECIE="ANIMAL_SPECIE";
    public final static String TABLE_ANIMAL_NAME="ANIMAL_NAME";
    
    public final static String TABLE_STAFF_ID="STAFF_ID";
    public final static String TABLE_STAFF_PERSONAL_ID="STAFF_PERSONAL_ID";
    public final static String TABLE_STAFF_NAME="STAFF_NAME";
    public final static String TABLE_STAFF_LASTNAME="STAFF_LASTNAME";
    public final static String TABLE_STAFF_BIRTH="STAFF_BIRTH";
    public final static String TABLE_STAFF_CONTACT="STAFF_CONTACT";
    
    
    public final static String TABLE_FEED_ID="FEED_ID";
    public final static String TABLE_FEED_NAME="FEED_NAME";
    public final static String TABLE_FEED_AMOUNT="FEED_AMOUNT";
    public final static String TABLE_FEED_MINIMUM="FEED_MINIMUM";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Selects">
    public final static String SELECT_ANIMAL = "SELECT * from APP.ANIMAL where ANIMAL_ID = %s and ANIMAL_CHIP = %s and ANIMAL_BIRTH = %s and ANIMAL_SPECIE = %s and ANIMAL_NAME = %s";
    public final static String SELECT_STAFF = "SELECT * from APP.STAFF where STAFF_ID = %s and STAFF_PERSONAL_ID = %s and STAFF_NAME = %s and STAFF_LASTNAME = %s and STAFF_BIRTH = %s and STAFF_CONTACT = %s";
    public final static String SELECT_FEED = "SELECT * from APP.FEED where FEED_ID = %s and FEED_NAME = %s and FEED_AMOUNT = %s and FEED_MINIMUM = %s";
    public final static String SELECT_UNDER_LIMIT = "SELECT * from APP.FEED where FEED_AMOUNT < FEED_MINIMUM";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Max-Id getters">
    public final static String ANIMAL_GET_LAST_ID = "select max(ANIMAL_ID) from APP.ANIMAL";
    public final static String STAFF_GET_LAST_ID = "select max(STAFF_ID) from APP.STAFF";
    public final static String FEED_GET_LAST_ID = "select max(FEED_ID) from APP.FEED";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Inserts">
    public final static String INSERT_ANIMAL = "INSERT INTO APP.ANIMAL (ANIMAL_ID, ANIMAL_CHIP, ANIMAL_BIRTH, ANIMAL_SPECIE, ANIMAL_NAME) VALUES (%s,%s,%s,%s,%s)";
    public final static String INSERT_STAFF = "INSERT INTO APP.STAFF (STAFF_ID, STAFF_PERSONAL_ID, STAFF_NAME, STAFF_LASTNAME, STAFF_BIRTH,STAFF_CONTACT) VALUES (%s,%s,%s,%s,%s,%s)";
    public final static String INSERT_FEED = "INSERT INTO APP.FEED (FEED_ID, FEED_NAME, FEED_AMOUNT, FEED_MINIMUM) VALUES (%s,%s,%s,%s)";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Deletes">
    public final static String DELETE_ANIMAL = "DELETE from APP.ANIMAL where ANIMAL_ID = %s";
    public final static String DELETE_STAFF = "DELETE from APP.STAFF where STAFF_ID = %s";
    public final static String DELETE_FEED = "DELETE from APP.FEED where FEED_ID = %s";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Updates">
    public final static String UPDATE_ANIMAL = "UPDATE APP.ANIMAL SET ANIMAL_CHIP = %s, ANIMAL_BIRTH = %s, ANIMAL_SPECIE = %s, ANIMAL_NAME = %s WHERE ANIMAL_ID = %s";
    public final static String UPDATE_STAFF = "UPDATE APP.STAFF SET STAFF_PERSONAL_ID = %s, STAFF_NAME = %s, STAFF_LASTNAME = %s, STAFF_BIRTH = %s, STAFF_CONTACT = %s WHERE STAFF_ID = %s";
    public final static String UPDATE_FEED = "UPDATE APP.FEED SET FEED_NAME = %s, FEED_AMOUNT = %s, FEED_MINIMUM = %s WHERE FEED_ID = %s";
    //</editor-fold>

}
