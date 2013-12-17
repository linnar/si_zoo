/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.DAO;

import Database.PreparedStatements;
import Database.SQLBuilder;
import Database.entity.Staff;
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
public class DAOStaff {

    public String insert(Connection conn, Staff staff) throws SQLException, UnableToCloseDatabaseCorrectlyException {

        Statement stm = null;
        ResultSet rs = null;

        try {

            stm = conn.createStatement();

            rs = stm.executeQuery(PreparedStatements.STAFF_GET_LAST_ID);
            rs.next();
            int id;
            if (rs.getString(1) == null) {
                id = 0;
            } else {
                id = Integer.parseInt(rs.getString(1));
            }

            String command = SQLBuilder.buildStaffInsert(id + 1, staff.getPersonalId(), staff.getName(), staff.getLastname(), staff.getBirth(), staff.getContact());

            stm.executeUpdate(command);

        } catch (SQLException ex) {
            throw ex;
        } finally {
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

    public ArrayList<Staff> select(Connection conn, int id,String personalId, String name, String lastname, String birth, String contact) throws UnableToObtainDataFromDatabaseException, UnableToCloseDatabaseCorrectlyException {
        ArrayList<Staff> list = new ArrayList<Staff>();


        Statement stm = null;
        ResultSet rs = null;
        try {
            String command = SQLBuilder.buildStaffSelect(id,personalId, name, lastname, birth, contact);
            stm = conn.createStatement();
            rs = stm.executeQuery(command);

            while (rs.next()) {
                list.add(new Staff(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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

    public String delete(Connection conn, int id) throws SQLException, UnableToCloseDatabaseCorrectlyException {
        Statement stm = null;
        ResultSet rs = null;

        try {
            String command = SQLBuilder.buildStaffDelete(id);
            stm = conn.createStatement();
            stm.executeUpdate(command);

        } catch (SQLException ex) {
            throw ex;
        } finally {
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

    public String update(Connection conn, int id, Staff staff) throws SQLException, UnableToCloseDatabaseCorrectlyException {

        Statement stm = null;
        ResultSet rs = null;

        try {

            stm = conn.createStatement();

            String command = SQLBuilder.buildStaffUpdate(id,staff.getPersonalId(), staff.getName(), staff.getLastname(), staff.getBirth(), staff.getContact());

            stm.executeUpdate(command);

        } catch (SQLException ex) {
            throw ex;
        } finally {
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
