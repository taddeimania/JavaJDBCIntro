package io.joel.jdbcintro.model;

import io.joel.jdbcintro.helpers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Stat {
    private int id;
    private String name;
    private int wins;
    private int losses;
    private Statement statement;

    public Stat(String name, int wins, int losses, Statement statement) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.statement = statement;
    }

    public Stat(String name, int wins, int losses, Statement statement, int id) {
        this(name, wins, losses, statement);
        this.id = id;
    }

    public void Save() throws SQLException {
        String formattedSql = String.format("INSERT INTO stats (name, wins, losses) VALUES ('%s', %s, %s)", name, wins, losses);
        statement.executeUpdate(formattedSql);
    }

    public void Update() throws SQLException {
        String formattedSql = String.format("UPDATE stats SET name = '%s', wins = %s, losses = %s WHERE id = %s", name, wins, losses, id);
        statement.executeUpdate(formattedSql);
    }

    public static List<Stat> findAll(DatabaseManager dbm) throws SQLException {
        ResultSet rs = dbm.findAll("stats");
        List<Stat> tempCollection = new ArrayList<>();
        Statement tempStatement = dbm.getStatement();

        while (rs.next()) {
            String name = rs.getString("name");
            int wins = rs.getInt("wins");
            int losses = rs.getInt("losses");
            Stat tempStat = new Stat(name, wins, losses, tempStatement, rs.getInt("id"));
            tempCollection.add(tempStat);
        }
        return tempCollection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
