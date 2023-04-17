package com.napier.sem;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CountryReport implements Report {
    protected final String query;
    protected String path;

    public CountryReport(String query, String path) {
        this.query = query;
        this.path = path;
    }
    public static String getAbsPath(){
        return System.getProperty("user.dir") + "/data/results.md";
    }
    @Override
    public void execute() {
        try {
            App a = new App();
            Connection conn = a.connect("0.0.0.0");
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            StringBuilder sb = new StringBuilder();
            sb.append("| Country Code | Country Name | Country Continent | Country Region | Country Population | Country Capital |" + "\n");
            sb.append("| --------- | --------- | ---------- | ---------- | ----------- | ----------- |\n");

            while (rs.next()) {
                String queryResult1 = rs.getString("country.code");
                String queryResult2 = rs.getString("country.name");
                String queryResult3 = rs.getString("country.continent");
                String queryResult4 = rs.getString("country.region");
                String queryResult5 = rs.getString("country.population");
                String queryResult6 = rs.getString("country.capital");

                sb.append("| " + queryResult1 + " | " + queryResult2 + " | " + queryResult3 + " | " + queryResult4 + " | " + queryResult5 + " | " + queryResult6 + " |\n");
            }

            String results = sb.toString();

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))){
                writer.write(results);
            }
            System.out.println(results);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
