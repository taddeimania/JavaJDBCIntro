package io.joel.jdbcintro;

import io.joel.jdbcintro.helpers.DatabaseManager;
import io.joel.jdbcintro.model.Stat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager dbm = new DatabaseManager(connection);
            WelcomeMenu(dbm);


        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
        }
    }

    public static void WelcomeMenu(DatabaseManager dbm) throws SQLException{
        System.out.println("=========================================================");
        System.out.println("Welcome to Stat Database 3000. What would you like to do?");
        System.out.println("1) Show All Stats");
        System.out.println("2) Add a new Stat");
        System.out.println("3) Update an Existing Stat");
        System.out.println("=========================================================");

        Scanner scanner = new Scanner( System.in );
        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                System.out.println("Now showing all stat data");
                List<Stat> results = Stat.findAll(dbm);
                for (Stat stat : results){
                    System.out.println(stat);
                }
                break;
            case 2:
                System.out.println("What is the name of the player?");
                String name = scanner.next();
                System.out.println("How Many Wins?");
                int wins = scanner.nextInt();
                System.out.println("How Many Losses?");
                int losses = scanner.nextInt();
                new Stat(name, wins, losses, dbm.getStatement()).Save();

                break;
            case 3:
                System.out.println("Which player name would you like to update?");
                break;
            default:
                System.out.println("Sorry, invalid input.");
        }

        WelcomeMenu(dbm);
    }
}
