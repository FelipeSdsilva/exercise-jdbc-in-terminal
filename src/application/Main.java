package application;

import db.DB;
import exceptions.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Connection conn = null;
        PreparedStatement prepSt = null;

        try {
            conn = DB.getConnection();

            prepSt = conn.prepareStatement("INSERT INTO seller " +
                    "(name, email, birthdate, basesalary, departmentid) " +
                    "VALUES " +
                    "(?,?,?,?,?)");

            prepSt.setString(1, "Felipe Sousa");
            prepSt.setString(2, "felipesouls@gmail.com");
            prepSt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.parse("09/05/1994 00:00", formatter)));
            prepSt.setDouble(4, 3000.00);
            prepSt.setInt(5, 4);

            int rowsAffected = prepSt.executeUpdate();

            System.out.println("Done! Row affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(prepSt);
            DB.closeConnection();
        }


    }
}