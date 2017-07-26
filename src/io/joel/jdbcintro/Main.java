package io.joel.jdbcintro;

import io.joel.jdbcintro.helpers.DatabaseManager;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager db = new DatabaseManager(connection);
            db.DropStatsTable();
            db.CreateStatsTable();

            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO stats (name, wins, losses) VALUES ('Joel', 10, 2)");
            ResultSet rs = statement.executeQuery("SELECT * FROM stats");

            while (rs.next()) {
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                System.out.printf("%s %s %s", name, wins, losses);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
        }
    }
}
