package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        Statement st = null;
        ResultSet rt = null;

        try {
            st = conn.createStatement();

            rt = st.executeQuery("SELECT * FROM department");

            while (rt.next()) {
                System.out.println(rt.getInt("id") + "," + rt.getString("name"));
            }

        } catch (SQLException e) {

        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rt);
            DB.closeConnection();
        }
    }
}