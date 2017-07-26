package io.joel.jdbcintro;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
        }
    }
}
