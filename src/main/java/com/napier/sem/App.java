package com.napier.sem;

import java.sql.*; //we import all the stuff from the java.sql codebase to be able to use java mysql integration in our awesome group project. additionally if you wont mark us as PERFECT/GORGEOUS WE WILL COMMENT ALL THE LINES OF OUR CODE LIKE THIS C:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * The main class for The Flying Scot application.
 */
public class App {

    /**
     * The static MySQL connection instance.
     */
    static Connection conn;

    /**
     * Create a database driver connection to a hardcoded set of credentials.
     *
     * @return The newly created connection.
     */
    public static Connection getConnection() {

        String url = "jdbc:mysql://0.0.0.0:3306/world";
        String user = "root";
        String pass = "123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return conn;
    }

    /**
     * The main entry point to The Flying Scot application.
     * @param args The arguments passed to the program invocation.
     */
    public static void main(String[] args) {

        Connection conn;
        PreparedStatement prst;
        ResultSet rs;

        /**
         * 1. All the countries in the world organised by largest population to smallest.
         * 2. All the countries in a continent organised by largest population to smallest.
         * 3. All the countries in a region organised by largest population to smallest.
         */

        // Selecting the query from the command-line argument
        int intDecision = Integer.parseInt(args[0]);
        System.out.printf("Printing report %d\n%n", intDecision);

        // Decide which report to run
        switch (intDecision) {

            case (1):
                try {
                    conn = App.getConnection();
                    prst = conn.prepareStatement("SELECT name, population FROM country ORDER BY population DESC;");
                    rs = prst.executeQuery();

                    while (rs.next()) {
                        String queryResult1 = rs.getString("country.name");
                        String queryResult2 = rs.getString("country.population");
                        System.out.println(queryResult1 + " : " + queryResult2);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case (2):
                try {
                    conn = App.getConnection();
                    prst = conn.prepareStatement("SELECT name, continent, population FROM world.country ORDER BY continent, population DESC;");
                    rs = prst.executeQuery();

                    while (rs.next()) {
                        String queryResult1 = rs.getString("country.name");
                        String queryResult2 = rs.getString("country.continent");
                        String queryResult3 = rs.getString("country.population");
                        System.out.println(queryResult1 + ", " + queryResult2 + " : " + queryResult3);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case (3):
                try {
                    conn = App.getConnection();
                    prst = conn.prepareStatement("SELECT name, region, population FROM country ORDER BY region, population DESC;");
                    rs = prst.executeQuery();

                    while (rs.next()) {
                        String queryResult1 = rs.getString("country.name");
                        String queryResult2 = rs.getString("country.region");
                        String queryResult3 = rs.getString("country.population");
                        System.out.println(queryResult1 + ", " + queryResult2 + " : " + queryResult3);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }
}