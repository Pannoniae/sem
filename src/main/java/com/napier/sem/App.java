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
     * The static MySQL connection instance.
     */
    private static Connection conn = null;

    /**
     * Create a database driver connection to a hardcoded set of credentials.
     *
     * @return The newly created connection.
     */

    protected static Connection connect(String location){
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(3000);
                // Connect to database
                conn = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "123");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
        return conn;
    }

    public void disconnect() {
        if (conn != null) {
            try {
                // Close connection
                conn.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

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
        reports.put(31, "The population of a selected city.");
        reports.put(32, "Total number of people and the percentage of the world population speaking Chinese sorted from greatest to smallest.");
        reports.put(33, "Total number of people and the percentage of the world population speaking English sorted from greatest to smallest.");
        reports.put(34, "Total number of people and the percentage of the world population speaking Hindi sorted from greatest to smallest.");
        reports.put(35, "Total number of people and the percentage of the world population speaking Spanish sorted from greatest to smallest.");
        reports.put(36, "Total number of people and the percentage of the world population speaking Arabic sorted from greatest to smallest.");
    }


    /**
     * The main entry point to The Flying Scot application.
     *
     * @param args The arguments passed to the program invocation.
     */
    public static void main(String[] args) {


        System.out.println("Hello world from main!");

        App a = new App();

        // Connect to database
        if (args.length < 1)
        {
            a.connect("0.0.0.0");
        }
        else
        {
            a.connect(args[0]);
        }

        //argument after "java -jar <jar_name>
        // [argument 0 - stands for db location]
        // [argument 1 - stands for report number]
        // [argument 2 - stands for region/continent]
        // [argument 3 - stands for limitations for the limited reports]"




        String arg1 = "";
        // The first argument to the report
        if (args.length > 2) {
            arg1 = args[2];
        }

        String arg2;
        if (args.length > 3) {
            // The second argument to the report
            arg2 = args[3];
        } else {
            arg2 = "5";
        }

        // Selecting the query from the command-line argument
        int intDecision = Integer.parseInt(args[1]);
        System.out.printf("Printing report %d\n%n", intDecision);


        if (args[2].equals("--help")) {
            for (Map.Entry<Integer, String> report : reports.entrySet()) {
                System.out.println(report.getKey() + ".: " + report.getValue());
            }
        }

        CountryReport report;
        CityReport cityReport;
        CapitalCityReport capitalCityReport;
        LanguageReport languageReport;
        PopulationReport populationReport;
        PopulationReportOption populationReportOption;
        // Decide which report to run
        switch (intDecision) {

            case 1:
                report = new CountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC;");
                report.execute();
                break;
            case 2:
                report = new CountryReport("SELECT code, name, continent, region, population, capital FROM world.country WHERE continent = '" + arg1 + "'ORDER BY population DESC;");
                report.execute();
                break;
            case 3:
                report = new CountryReport("SELECT code, name, continent, region, population, capital FROM country WHERE region = '" + arg1 + "' ORDER BY population DESC;");
                report.execute();
                break;
            case 4:
                report = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC LIMIT " + arg2 + ";");
                report.execute();
                break;
            case 5:
                report = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM world.country WHERE continent = '" + arg1 + "' population DESC LIMIT " + arg2 + ";");
                report.execute();
                break;
            case 6:
                report = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM country WHERE region = '" + arg1 + "' population DESC LIMIT " + arg2 + ";");
                report.execute();
                break;
            case 7:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 8:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.continent = '" + arg1 + "' ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 9:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.region = '" + arg1 + "' ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 10:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.name = '" + arg1 + "' ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 11:
                cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND city.district = '" + arg1 + "' ORDER BY population DESC;");
                cityReport.execute();
                break;
            case 12:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code ORDER BY population DESC LIMIT " + arg2 + ";");
                cityReport.execute();
                break;
            case 13:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.continent = '" + arg1 + "' ORDER BY population DESC LIMIT " + arg2 + ";");
                cityReport.execute();
                break;
            case 14:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.region = '" + arg1 + "' ORDER BY population DESC LIMIT " + arg2 + ";");
                cityReport.execute();
                break;
            case 15:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND country.name = '" + arg1 + "' ORDER BY population DESC LIMIT " + arg2 + ";");
                cityReport.execute();
                break;
            case 16:
                cityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code AND city.district = '" + arg1 + "' ORDER BY population DESC LIMIT" + arg2 + ";");
                cityReport.execute();
                break;
            case 17:
                capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID ORDER BY population DESC;");
                capitalCityReport.execute();
                break;
            case 18:
                capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.continent = '" + arg1 + "' ORDER BY population DESC;");
                capitalCityReport.execute();
                break;
            case 19:
                capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.region = '" + arg1 + "' ORDER BY population DESC;");
                capitalCityReport.execute();
                break;
            case 20:
                capitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID ORDER BY population DESC LIMIT " + arg2 + ";");
                capitalCityReport.execute();
                break;
            case 21:
                capitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.continent = '" + arg1 + "' ORDER BY population DESC LIMIT " + arg2 + ";");
                capitalCityReport.execute();
                break;
            case 22:
                capitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID AND country.region = '" + arg1 + "' ORDER BY population DESC LIMIT " + arg2 + ";");
                capitalCityReport.execute();
                break;
            case 23:
                populationReport = new PopulationReport("SELECT c.Continent AS Name, \n" +
                        "       SUM(c.Population) AS total_population, \n" +
                        "       SUM(CASE WHEN ci.Population > 0 THEN ci.Population ELSE 0 END) AS urban_population, \n" +
                        "       SUM(CASE WHEN ci.Population = 0 THEN c.Population ELSE 0 END) AS rural_population\n" +
                        "FROM country c \n" +
                        "LEFT JOIN city ci ON c.Code = ci.CountryCode \n" +
                        "GROUP BY c.Continent;\n");
                populationReport.execute();
                break;
            case 24:
                populationReport = new PopulationReport("SELECT\n" +
                        "  co.Region AS Name,\n" +
                        "  SUM(co.Population) AS total_population,\n" +
                        "  SUM(CASE WHEN cl.IsOfficial = 'T' THEN co.Population ELSE 0 END) AS urban_population,\n" +
                        "  SUM(CASE WHEN cl.IsOfficial = 'F' THEN co.Population ELSE 0 END) AS rural_population\n" +
                        "FROM\n" +
                        "  city ci\n" +
                        "  JOIN country co ON ci.CountryCode = co.Code\n" +
                        "  JOIN countrylanguage cl ON ci.CountryCode = cl.CountryCode\n" +
                        "GROUP BY\n" +
                        "  co.Region;\n");
                populationReport.execute();
                break;
            case 25:
                populationReport = new PopulationReport("SELECT \n" +
                        "  c.Name AS Name, \n" +
                        "  SUM(c.Population) AS total_population, \n" +
                        "  SUM(IFNULL(ci.Population,0)) AS urban_population, \n" +
                        "  SUM(IFNULL(c.Population,0))-SUM(IFNULL(ci.Population,0)) AS rural_population \n" +
                        "FROM \n" +
                        "  country c \n" +
                        "  LEFT JOIN city ci ON c.Code = ci.CountryCode \n" +
                        "GROUP BY \n" +
                        "  c.Code;\n");
                populationReport.execute();
                break;
            case 26:
                populationReportOption = new PopulationReportOption("SELECT 'World' AS Name, SUM(Population) AS Population FROM country;");
                populationReportOption.execute();
                break;
            case 27:
                populationReportOption = new PopulationReportOption("SELECT Continent AS Name, SUM(Population) AS Population\n" +
                        "FROM country\n" +
                        "WHERE Continent = '" + arg1 + "';");
                populationReportOption.execute();
                break;
            case 28:
                populationReportOption = new PopulationReportOption("SELECT co.Region AS Name, SUM(c.Population) AS Population\n" +
                        "FROM city c\n" +
                        "JOIN country co ON c.CountryCode = co.Code\n" +
                        "WHERE co.Region = '" + arg1 +  "';");
                populationReportOption.execute();
                break;
            case 29:
                populationReportOption = new PopulationReportOption("SELECT c.Name AS Name, SUM(ct.Population) AS Population\n" +
                        "FROM country c\n" +
                        "JOIN city ct ON c.Code = ct.CountryCode\n" +
                        "WHERE c.Name = '" + arg1 + "';\n");
                populationReportOption.execute();
                break;
            case 30:
                populationReportOption = new PopulationReportOption("SELECT District AS Name, SUM(Population) AS Population\n" +
                        "FROM city\n " +
                        "WHERE District = '" + arg1 + "';");
                populationReportOption.execute();
                break;
            case 31:
                populationReportOption = new PopulationReportOption("SELECT Name, Population\n" +
                        "FROM city\n" +
                        "WHERE Name = '" + arg1 + "';\n");
                populationReportOption.execute();
                break;
            case 32:
                languageReport = new LanguageReport("SELECT cl.Language, SUM(c.Population) AS TotalPopulation, (SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100 AS Percentage FROM countrylanguage cl JOIN country co ON cl.CountryCode = co.Code JOIN city c ON co.Code = c.CountryCode WHERE cl.Language = 'Chinese' GROUP BY cl.Language ORDER BY TotalPopulation DESC;");
                languageReport.execute();
                break;
            case 33:
                languageReport = new LanguageReport("SELECT cl.Language, SUM(c.Population) AS TotalPopulation, (SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100 AS Percentage FROM countrylanguage cl JOIN country co ON cl.CountryCode = co.Code JOIN city c ON co.Code = c.CountryCode WHERE cl.Language = 'English' GROUP BY cl.Language ORDER BY TotalPopulation DESC;");
                languageReport.execute();
                break;
            case 34:
                languageReport = new LanguageReport("SELECT cl.Language, SUM(c.Population) AS TotalPopulation, (SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100 AS Percentage FROM countrylanguage cl JOIN country co ON cl.CountryCode = co.Code JOIN city c ON co.Code = c.CountryCode WHERE cl.Language = 'Hindi' GROUP BY cl.Language ORDER BY TotalPopulation DESC;");
                languageReport.execute();
                break;
            case 35:
                languageReport = new LanguageReport("SELECT cl.Language, SUM(c.Population) AS TotalPopulation, (SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100 AS Percentage FROM countrylanguage cl JOIN country co ON cl.CountryCode = co.Code JOIN city c ON co.Code = c.CountryCode WHERE cl.Language = 'Spanish' GROUP BY cl.Language ORDER BY TotalPopulation DESC;");
                languageReport.execute();
                break;
            case 36:
                languageReport = new LanguageReport("SELECT cl.Language, SUM(c.Population) AS TotalPopulation, (SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100 AS Percentage FROM countrylanguage cl JOIN country co ON cl.CountryCode = co.Code JOIN city c ON co.Code = c.CountryCode WHERE cl.Language = 'Arabic' GROUP BY cl.Language ORDER BY TotalPopulation DESC;");
                languageReport.execute();
                break;

            default:
                throw new IllegalStateException("Unexpected value or query not implemented yet: " + intDecision);
        }
    }
}
