package com.napier.sem;

import java.sql.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ConvertWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntegrationTests {

    @Test
    public void testConnection() throws SQLException{ //testing the ability to connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://0.0.0.0:3306/world", "root", "123");

        assertNotNull(conn);

        if(conn != null){
            System.out.println("db connected");
            conn.close();
        }

    }

    @Test
    public void testDisconnection() throws SQLException{ //testing the disconnect method
        App app = new App();
        app.connect("0.0.0.0");
        app.disconnect();

        System.out.println("Successfully disconnected");

    }

    @Test
    public void getSomeData() throws SQLException{ //testing the ability of app to speak to docker and display information
        Connection conn = DriverManager.getConnection("jdbc:mysql://0.0.0.0:3306/world", "root", "123");
        PreparedStatement prst = conn.prepareStatement("SELECT code, name, continent, region, population, capital FROM world.country ORDER BY population DESC LIMIT 10;");
        ResultSet rs = prst.executeQuery();

        while (rs.next()){
            while (rs.next()) {
                String queryResult1 = rs.getString("code");
                String queryResult2 = rs.getString("name");
                String queryResult3 = rs.getString("population");
                System.out.println(queryResult1 + " : " + queryResult2 + " : " +
                        queryResult3);
            }
        }
    }

    @Test
    public void db(){
        App app = new App();
        app.connect("0.0.0.0");

    }

    @Test
    void testCapitalCityReport(){ //testing all the report classes methods
        CapitalCityReport capitalCityReport;
        capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population FROM city JOIN country ON city.countryCode = country.code AND country.capital = city.ID ORDER BY population DESC;", CapitalCityReport.getAbsPath());
        capitalCityReport.execute();
        System.out.println("Capital City Report ++");
    }

    @Test
    void testCityReport(){
        CityReport cityReport;
        cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population FROM city JOIN country ON city.countryCode = country.code ORDER BY population DESC;", CityReport.getAbsPath());
        cityReport.execute();
        System.out.println("City Report ++");
    }

    @Test
    void testCountryReport(){
        CountryReport countryReport;
        countryReport = new CountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC;", CountryReport.getAbsPath());
        countryReport.execute();
        System.out.println("Country Report ++");
    }

    @Test
    void testLanguageReport(){
        LanguageReport languageReport;
        languageReport = new LanguageReport("SELECT cl.Language, SUM(c.Population) AS TotalPopulation, (SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100 AS Percentage FROM countrylanguage cl JOIN country co ON cl.CountryCode = co.Code JOIN city c ON co.Code = c.CountryCode WHERE cl.Language = 'Chinese' GROUP BY cl.Language ORDER BY TotalPopulation DESC;", LanguageReport.getAbsPath());
        languageReport.execute();
        System.out.println("Language Report ++");
    }

    @Test
    void testLimitedCapitalCityReport(){
        LimitedCapitalCityReport limitedCapitalCityReport;
        limitedCapitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population FROM city JOIN country ON city.CountryCode = country.code AND country.capital = city.ID ORDER BY population DESC LIMIT 5;", LimitedCapitalCityReport.getAbsPath());
        limitedCapitalCityReport.execute();
        System.out.println("Limited Capital City Report ++");
    }

    @Test
    void testLimitedCityReport(){
        LimitedCityReport limitedCityReport;
        limitedCityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population FROM city JOIN country ON city.countryCode = country.code ORDER BY population DESC LIMIT 5;", LimitedCityReport.getAbsPath());
        limitedCityReport.execute();
        System.out.println("Limited City Report ++");
    }

    @Test
    void testLimitedCountryReport(){
        LimitedCountryReport limitedCountryReport;
        limitedCountryReport = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC LIMIT 5;", LimitedCountryReport.getAbsPath());
        limitedCountryReport.execute();
        System.out.println("Limited Country Report ++");
    }

    @Test
    void testMain(){
        String[] arrayArgs = {"0.0.0.0", "1", "1", "5"};
        App.main(arrayArgs);
    }

    @Test
    void testPopulationReport(){
        PopulationReport populationReport;
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
                "  co.Region;\n", PopulationReport.getAbsPath());
        populationReport.execute();
        System.out.println("Population Report ++");
    }
    @Test
    void testPopulationReportOption(){
        PopulationReportOption populationReportOption;
        populationReportOption = new PopulationReportOption("SELECT Name, Population\n" +
                "FROM city\n" +
                "WHERE Name = 'Kyiv';\n", PopulationReportOption.getAbsPath());
        populationReportOption.execute();
        System.out.println("Population Report Option ++");
    }



}
