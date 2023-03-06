package application;

import db.DB;
import exceptions.DbException;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Connection conn = null;
        PreparedStatement prepSt = null;

        try {
            conn = DB.getConnection();

            prepSt = conn.prepareStatement("UPDATE seller " +
                    "SET basesalary = basesalary + ? " +
                    "WHERE departmentid = ?");

            prepSt.setDouble(1, 2000.00);
            prepSt.setInt(2, 2);

            int rowsAffected = prepSt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected);
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(prepSt);
            DB.closeConnection();
        }


    }
}