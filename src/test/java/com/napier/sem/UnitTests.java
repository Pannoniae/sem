package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    static App app;

    @BeforeAll
    static void init(){
        app = new App();
    }

    @Test
    void createApp(){
        app = new App();
    }

    @AfterAll
    static void tearDown() {
        app.disconnect();
    }

    @Test
    public void testConnect() {
        assertNotNull("Connection should not be null", String.valueOf(app.connect("localhost")));
    }

    @Test
    public void testDisconnect() {
        app.disconnect();
        System.out.println("Successfully disconnected");
    }

    @Test
    public void testReportMap() {
        assertEquals(36, App.reports.size());
        System.out.println("36/36 Objects are in hasgmap");
    }

    @Test
    public void testReportMapValues() {
        assertTrue(App.reports.containsValue("All the countries in the world organised by largest population to smallest."), "Report map should contain report 1");
        if(App.reports.containsValue("All the countries in the world organised by largest population to smallest.")){
            System.out.println("1 " + App.reports.get(1));
        }
        assertTrue(App.reports.containsValue("All the countries in a continent organised by largest population to smallest."), "Report map should contain report 2");
        assertTrue(App.reports.containsValue("All the countries in a region organised by largest population to smallest."), "Report map should contain report 3");
        assertTrue(App.reports.containsValue("The top N populated countries in the world where N is provided by the user."), "Report map should contain report 4");
        assertTrue(App.reports.containsValue("The top N populated countries in a continent where N is provided by the user."), "Report map should contain report 5");
        assertTrue(App.reports.containsValue("The top N populated countries in a region where N is provided by the user."), "Report map should contain report 6");
        assertTrue(App.reports.containsValue("All the cities in the world organised by largest population to smallest."), "Report map should contain report 7");
        assertTrue(App.reports.containsValue("All the cities in a continent organised by largest population to smallest."), "Report map should contain report 8");
        assertTrue(App.reports.containsValue("All the cities in a region organised by largest population to smallest."), "Report map should contain report 9");
        assertTrue(App.reports.containsValue("All the cities in a country organised by largest population to smallest."), "Report map should contain report 10");
        assertTrue(App.reports.containsValue("All the cities in a district organised by largest population to smallest."), "Report map should contain report 11");
        assertTrue(App.reports.containsValue("The top N populated cities in the world where N is provided by the user."), "Report map should contain report 12");
        assertTrue(App.reports.containsValue("The top N populated cities in a continent where N is provided by the user."), "Report map should contain report 13");
        assertTrue(App.reports.containsValue("The top N populated cities in a region where N is provided by the user."), "Report map should contain report 14");
        assertTrue(App.reports.containsValue("The top N populated cities in a country where N is provided by the user."), "Report map should contain report 15");
        assertTrue(App.reports.containsValue("The top N populated cities in a district where N is provided by the user."), "Report map should contain report 16");
        assertTrue(App.reports.containsValue("All the capital cities in the world organised by largest population to smallest."), "Report map should contain report 17");
        assertTrue(App.reports.containsValue("All the capital cities in a continent organised by largest population to smallest."), "Report map should contain report 18");
        assertTrue(App.reports.containsValue("All the capital cities in a region organised by largest to smallest."), "Report map should contain report 19");
        assertTrue(App.reports.containsValue("The top N populated capital cities in the world where N is provided by the user."), "Report map should contain report 20");
        assertTrue(App.reports.containsValue("The top N populated capital cities in a continent where N is provided by the user."), "Report map should contain report 21");
        assertTrue(App.reports.containsValue("The top N populated capital cities in a region where N is provided by the user."), "Report map should contain report 22");
        assertTrue(App.reports.containsValue("The population of people, people living in cities, and people not living in cities in each continent."), "Report map should contain report 23");
        assertTrue(App.reports.containsValue("The population of people, people living in cities, and people not living in cities in each region."), "Report map should contain report 24");
        assertTrue(App.reports.containsValue("The population of people, people living in cities, and people not living in cities in each country."), "Report map should contain report 25");
        assertTrue(App.reports.containsValue("The population of the world."), "Report map should contain report 26");
        assertTrue(App.reports.containsValue("The population of a chosen continent."), "Report map should contain report 27");
        assertTrue(App.reports.containsValue("The population of a chosen region."), "Report map should contain report 28");
        assertTrue(App.reports.containsValue("The population of a chosen country."), "Report map should contain report 29");
        assertTrue(App.reports.containsValue("The population of a chosen district."), "Report map should contain report 30");
        assertTrue(App.reports.containsValue("The population of a selected city."), "Report map should contain report 31");
        assertTrue(App.reports.containsValue("Total number of people and the percentage of the world population speaking Chinese sorted from greatest to smallest."), "Report map should contain report 32");
        assertTrue(App.reports.containsValue("Total number of people and the percentage of the world population speaking English sorted from greatest to smallest."), "Report map should contain report 33");
        assertTrue(App.reports.containsValue("Total number of people and the percentage of the world population speaking Hindi sorted from greatest to smallest."), "Report map should contain report 34");
        assertTrue(App.reports.containsValue("Total number of people and the percentage of the world population speaking Spanish sorted from greatest to smallest."), "Report map should contain report 35");
        assertTrue(App.reports.containsValue("Total number of people and the percentage of the world population speaking Arabic sorted from greatest to smallest."), "Report map should contain report 36");

    }

    @Test
    public void testMain() {
        String[] args = {"0.0.0.0", "1", "", ""};
        App.main(args);
        assertNotNull("Connection should not be null after running main", String.valueOf(app.conn));
    }

    @Test
    void testCapitalCityReport(){
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

    @Test
    void testGetAbsPath() {
        String absPath = CapitalCityReport.getAbsPath();
        assertNotNull(absPath);
        assertTrue(absPath.endsWith("data/results.md"));
    }

}