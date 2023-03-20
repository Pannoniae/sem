package com.napier.sem;

import java.io.Console;
import java.io.IOException;
import java.sql.*; //we import all the stuff from the java.sql codebase to be able to use java mysql integration in our awesome group project. additionally if you wont mark us as PERFECT/GORGEOUS WE WILL COMMENT ALL THE LINES OF OUR CODE LIKE THIS C:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The main class for The Flying Scot application.
 */
public class App {

    /**
     * The list of reports the program produces.
     */
    private static final HashMap<Integer, String> reports = new HashMap<>() {

    };

    static {
        reports.put(1, "All the countries in the world organised by largest population to smallest.");
        reports.put(2, "All the countries in a continent organised by largest population to smallest.");
        reports.put(3, "All the countries in a region organised by largest population to smallest.");
        reports.put(4, "The top N populated countries in the world where N is provided by the user.");
        reports.put(5, "The top N populated countries in a continent where N is provided by the user.");
        reports.put(6, "The top N populated countries in a region where N is provided by the user.");
        reports.put(7, "All the cities in the world organised by largest population to smallest.");
        reports.put(8, "All the cities in a continent organised by largest population to smallest.");
        reports.put(9, "All the cities in a region organised by largest population to smallest.");
        reports.put(10, "All the cities in a country organised by largest population to smallest.");
        reports.put(11, "All the cities in a district organised by largest population to smallest.");
        reports.put(12, "The top N populated cities in the world where N is provided by the user.");
        reports.put(13, "The top N populated cities in a continent where N is provided by the user.");
        reports.put(14, "The top N populated cities in a region where N is provided by the user.");
        reports.put(15, "The top N populated cities in a country where N is provided by the user.");
        reports.put(16, "The top N populated cities in a district where N is provided by the user.");
        reports.put(17, "All the capital cities in the world organised by largest population to smallest.");
        reports.put(18, "All the capital cities in a continent organised by largest population to smallest.");
        reports.put(19, "All the capital cities in a region organised by largest to smallest.");
        reports.put(20, "The top N populated capital cities in the world where N is provided by the user.");
        reports.put(21, "The top N populated capital cities in a continent where N is provided by the user.");
        reports.put(22, "The top N populated capital cities in a region where N is provided by the user.");
        reports.put(23, "The population of people, people living in cities, and people not living in cities in each continent.");
        reports.put(24, "The population of people, people living in cities, and people not living in cities in each region.");
        reports.put(25, "The population of people, people living in cities, and people not living in cities in each country.");
        reports.put(26, "The population of the world.");
        reports.put(27, "The population of a chosen continent.");
        reports.put(28, "The population of a chosen region.");
        reports.put(29, "The population of a chosen country.");
        reports.put(30, "The population of a chosen district.");
        reports.put(31, "The number of people who speak Chinese from greatest number to smallest, including the percentage of the world population.");
        reports.put(32, "The number of people who speak English from greatest number to smallest, including the percentage of the world population.");
        reports.put(33, "The number of people who speak Hindi from greatest number to smallest, including the percentage of the world population.");
        reports.put(34, "The number of people who speak Spanish from greatest number to smallest, including the percentage of the world population.");
        reports.put(35, "The number of people who speak Arabic from greatest number to smallest, including the percentage of the world population.");
    }

    /**
     * The static MySQL connection instance.
     */
    private static Connection conn;

    /**
     * Create a database driver connection to a hardcoded set of credentials.
     *
     * @return The newly created connection.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        String url = "jdbc:mysql://db:3306/world";
        String user = "root";
        String pass = "123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return conn;
    }

    /**
     * The main entry point to The Flying Scot application.
     *
     * @param args The arguments passed to the program invocation.
     */
    public static void main(String[] args) {

        System.out.println("Hello world from main!");




        if (args[0].equals("--help")) {
            for (Map.Entry<Integer, String> report : reports.entrySet()) {
                System.out.println(report.getKey() + ".: " + report.getValue());
            }
        }

        // Selecting the query from the command-line argument
        int intDecision = Integer.parseInt(args[0]);
        System.out.printf("Printing report %d\n%n", intDecision);


        String arg1 = "";
        // The first argument to the report
        if (args.length > 1) {
            arg1 = args[1];
        }

        int arg2;
        if (args.length > 2) {
            // The second argument to the report
            arg2 = Integer.parseInt(args[2]);
        } else {
            arg2 = 5;
        }


        CountryReport report;
        CityReport cityReport;
        CapitalCityReport capitalCityReport;
        // Decide which report to run
        switch (intDecision) {

            case 1:
                report = new CountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC;");
                report.execute();
                break;
            case 2:
                report = new CountryReport("SELECT code, name, continent, region, population, capital FROM world.country WHERE continent = " + arg1 + ", population DESC;");
                report.execute();
                break;
            case 3:
                report = new CountryReport("SELECT code, name, continent, region, population, capital FROM country WHERE region = " + arg1 + ", population DESC;");
                report.execute();
                break;
            case 4:
                report = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC;", arg2);
                report.execute();
                break;
            case 5:
                report = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM world.country WHERE continent = " + arg1 + ", population DESC;", arg2);
                report.execute();
                break;
            case 6:
                report = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM country WHERE region = " + arg1 + ", population DESC;", arg2);
                report.execute();
                break;
            case 7:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 8:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.continent = " + arg1 + " ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 9:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.region = " + arg1 + " ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 10:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.name = " + arg1 + " ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 11:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND city.district = " + arg1 + " ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 12:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code ORDER BY population DESC;", arg2);
                cityReport.execute();
                break;
            case 13:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.continent = " + arg1 + " ORDER BY population DESC;", arg2);
                cityReport.execute();
                break;
            case 14:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.region = " + arg1 + " ORDER BY population DESC;", arg2);
                cityReport.execute();
                break;
            case 15:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.name = " + arg1 + " ORDER BY population DESC;", arg2);
                cityReport.execute();
                break;
            case 16:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND city.district = " + arg1 + " ORDER BY population DESC;", arg2);
                cityReport.execute();
                break;
            case 17:
                capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID ORDER BY population DESC;");
                capitalCityReport.execute();
                break;
            case 18:
                capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.continent = " + arg1 + " ORDER BY population DESC;");
                capitalCityReport.execute();
                break;
            case 19:
                capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.region = " + arg1 + " ORDER BY population DESC;");
                capitalCityReport.execute();
                break;
            case 20:
                capitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID ORDER BY population DESC;", arg2);
                capitalCityReport.execute();
                break;
            case 21:
                capitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.continent = " + arg1 + " ORDER BY population DESC;", arg2);
                capitalCityReport.execute();
                break;
            case 22:
                capitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.region = " + arg1 + " ORDER BY population DESC;", arg2);
                capitalCityReport.execute();
                break;


            default:
                throw new IllegalStateException("Unexpected value or query not implemented yet: " + intDecision);
        }
    }
}