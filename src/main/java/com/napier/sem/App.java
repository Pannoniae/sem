package com.napier.sem;

import java.sql.*; //we import all the stuff from the java.sql codebase to be able to use java mysql integration in our awesome group project. additionally if you wont mark us as PERFECT/GORGEOUS WE WILL COMMENT ALL THE LINES OF OUR CODE LIKE THIS C:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    static Connection conn;

    public static Connection getConnection(){

        String url = "jdbc:mysql://0.0.0.0:3306/world";
        String user = "root";
        String pass = "123";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        }catch (Exception ex) {
            System.out.println("" + ex);
        }
        return conn;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Connection conn;
        PreparedStatement prst;
        ResultSet rs;

        System.out.println("Choose the needed information : \n" +
                "1. All the countries in the world organised by largest population to smallest. \n" +
                "2. All the countries in a continent organised by largest population to smallest. \n");

        System.out.println("Required case : ");

        int intDecision = scanner.nextInt();

        switch(intDecision){
            case(1):
                System.out.println("U chose 1 \n");
                try{
                    conn = App.getConnection();
                    prst = conn.prepareStatement("SELECT name, population FROM country ORDER BY population DESC;");
                    rs = prst.executeQuery();

                    while(rs.next()) {
                        String queryResult1 = rs.getString("country.name");
                        String queryResult2 = rs.getString("country.population");
                        System.out.println(queryResult1 + " : " + queryResult2);
                    }
                }
                catch(Exception ex){
                    System.out.println("" + ex);
                }
                break;

            case(2):
                System.out.println("U chose 2");
                try{
                    conn = App.getConnection();
                    prst = conn.prepareStatement("SELECT name, continent, population FROM world.country ORDER BY continent, population DESC;");
                    rs = prst.executeQuery();

                    while(rs.next()) {
                        String queryResult1 = rs.getString("country.name");
                        String queryResult2 = rs.getString("country.continent");
                        String queryResult3 = rs.getString("country.population");
                        System.out.println(queryResult1 + ", " + queryResult2 + " : " + queryResult3 );
                    }
                }
                catch(Exception ex){
                    System.out.println("" + ex);
                }
                break;

        }
    }
}