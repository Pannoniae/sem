package com.napier.sem;

import java.sql.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntegrationTests {

    @Test
    public void testConnection() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://0.0.0.0:3306/world", "root", "123");

        assertNotNull(conn);

        if(conn != null){
            System.out.println("db connected");
            conn.close();
        }

    }

    @Test
    public void getSomeData() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://0.0.0.0:3306/world", "root", "123");
        PreparedStatement prst = conn.prepareStatement("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC LIMIT 10;");
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
//    static App app;
//
//    @BeforeAll
//    static void init(){
//        app = new App();
//        app.connect("0.0.0.0:3306");
//    }
//
//    @Test
//    void testCapitalCityReport(){
//        CapitalCityReport capitalCityReport;
//        capitalCityReport = new CapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID ORDER BY population DESC;");
//        capitalCityReport.execute();
//        System.out.println("Capital City Report ++");
//    }
//
//    @Test
//    void testCityReport(){
//        CityReport cityReport;
//        cityReport = new CityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code ORDER BY population DESC;");
//        cityReport.execute();
//        System.out.println("City Report ++");
//    }
//
//    @Test
//    void testCountryReport(){
//        CountryReport countryReport;
//        countryReport = new CountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC;");
//        countryReport.execute();
//        System.out.println("Country Report ++");
//    }
//
//    @Test
//    void testLanguageReport(){
//        LanguageReport languageReport;
//        languageReport = new LanguageReport("SELECT cl.Language, SUM(c.Population) AS TotalPopulation, (SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100 AS Percentage FROM countrylanguage cl JOIN country co ON cl.CountryCode = co.Code JOIN city c ON co.Code = c.CountryCode WHERE cl.Language = 'Chinese' GROUP BY cl.Language ORDER BY TotalPopulation DESC;");
//        languageReport.execute();
//        System.out.println("Language Report ++");
//    }
//
//    @Test
//    void testLimitedCapitalCityReport(){
//        LimitedCapitalCityReport limitedCapitalCityReport;
//        limitedCapitalCityReport = new LimitedCapitalCityReport("SELECT city.name, country.name, city.population, FROM city, country WHERE city.countryCode = country.code AND country.capital = city.ID ORDER BY population DESC;", 5);
//        limitedCapitalCityReport.execute();
//        System.out.println("Limited Capital City Report ++");
//    }
//
//    @Test
//    void testLimitedCityReport(){
//        LimitedCityReport limitedCityReport;
//        limitedCityReport = new LimitedCityReport("SELECT city.name, country.name, city.district, city.population, FROM city, country WHERE city.countryCode = country.code ORDER BY population DESC;", 5);
//        limitedCityReport.execute();
//        System.out.println("Limited City Report ++");
//    }
//
//    @Test
//    void testLimitedCountryReport(){
//        LimitedCountryReport limitedCountryReport;
//        limitedCountryReport = new LimitedCountryReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC;", 5);
//        limitedCountryReport.execute();
//        System.out.println("Limited Country Report ++");
//    }
//
////    @Test
////    void testPopulationReport(){
////        PopulationReport populationReport;
////        populationReport = new PopulationReport("SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC;", 5);
////        populationReport.execute();
////        System.out.println("Population Report ++");
////    }
//
//    @Test
//    void TestMainEmpty() {
//        String[] arrayArgs = {};
//        App.main(arrayArgs);
//    }
//
//    @Test
//    void TestMain() {
//        String[] arrayArgs = {"localhost:33060"};
//        App.main(arrayArgs);
//    }
//
//    @Test
//    void TestDisconnectNoDB() {
//        App a = new App();
//        a.disconnect();
//    }
//
//    @Test
//    void TestDisconnectDB() {
//        App a = new App();
//        a.connect("db:3306");
//        a.disconnect();
//    }
//
//    @Test
//    void TestConnectDB() {
//        App a = new App();
//        a.connect("db:3306");
//    }
}
