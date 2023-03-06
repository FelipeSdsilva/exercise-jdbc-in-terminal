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
 /*
            prepSt = conn.prepareStatement("INSERT INTO seller " +
                    "(name, email, birthdate, basesalary, departmentid) " +
                    "VALUES " +
                    "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            prepSt.setString(1, "Felipe Sousa");
            prepSt.setString(2, "felipesouls@gmail.com");
            prepSt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.parse("09/05/1994 00:00", formatter)));
            prepSt.setDouble(4, 3000.00);
            prepSt.setInt(5, 4);
*/
            prepSt = conn.prepareStatement("INSERT INTO department (name) VALUES ('Food'),('Toys')",
                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = prepSt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = prepSt.getGeneratedKeys();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    /*
                    System.out.println("Done! id= " + id);
                    System.out.println("Seller: { " +
                            "\nname: '" + rs.getString("name") + "'," +
                            "\nemail: " + rs.getString("email") + "," +
                            "\nbirthdate: " + rs.getTimestamp("birthdate").toLocalDateTime().format(formatter) + "," +
                            "\nbasesalary: " + rs.getDouble("basesalary") + "," +
                            "\ndepartmentid: " + rs.getInt("departmentid") + "\n};");
                      */
                }
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