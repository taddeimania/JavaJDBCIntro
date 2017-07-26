package io.joel.jdbcintro;

import io.joel.jdbcintro.helpers.DatabaseManager;
import io.joel.jdbcintro.model.Stat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager db = new DatabaseManager(connection);
            db.DropStatsTable();
            db.CreateStatsTable();
            Statement statement = db.getStatement();

            Stat joelStat = new Stat("Peanut", 3, 10, statement);
            joelStat.Save();

            Stat joeMontana = new Stat("Joe Montana", 750, 2, statement);
            joeMontana.Save();

            List<Stat> results = Stat.findAll(db);
            for (Stat stat : results) {
                stat.setWins(0);
                stat.Update();
            }

            results = Stat.findAll(db);
            for (Stat stat : results) {
                System.out.println(stat);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
        }
    }
}
