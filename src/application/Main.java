package application;

import db.DB;
import exceptions.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Connection conn = null;
        PreparedStatement prepSt = null;

        try {
            conn = DB.getConnection();

            prepSt = conn.prepareStatement("DELETE FROM department " +
                    "WHERE " +
                    "id =  ?");

            prepSt.setInt(1, 5);

            int rowsAffected = prepSt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected);
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(prepSt);
            DB.closeConnection();
        }
    }
}