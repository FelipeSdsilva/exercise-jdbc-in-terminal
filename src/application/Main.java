package application;

import db.DB;
import exceptions.DbIntegrityException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int row1 = st.executeUpdate("UPDATE seller SET basesalary =  2090 WHERE departmentid = 1");

            //  int x = 1;

            //    if (x < 2) {
            //         throw new SQLException("Fake error");
            //  }

            int row2 = st.executeUpdate("UPDATE seller SET basesalary = 3090 WHERE departmentid =  2");

            conn.commit();

            System.out.println("Row1: " + row1 + "\n"
                    + "Row2: " + row2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbIntegrityException("Transactional rollback! caused by: " + e.getMessage());
            } catch (SQLException ex) {

            }

        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}