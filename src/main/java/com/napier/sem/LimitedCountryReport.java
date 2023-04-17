package com.napier.sem;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class LimitedCountryReport extends CountryReport {

    public LimitedCountryReport(String query) {
        super(query);
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
            sb.append("| ------------ | ------------ | ----------------- | -------------- | ------------------ | ----------------|\n");

            while(rs.next()) {
                String queryResult1 = rs.getString("country.code");
                String queryResult2 = rs.getString("country.name");
                String queryResult3 = rs.getString("country.continent");
                String queryResult4 = rs.getString("country.region");
                String queryResult5 = rs.getString("country.population");
                String queryResult6 = rs.getString("country.capital");

                sb.append("| " + queryResult1 + " | " + queryResult2 + " | " + queryResult3 + " | " + queryResult4 + " | " + queryResult5 + " | " + queryResult6 + " |\n");
            }
//            String getCurrDir = System.getProperty("user.dir");
            String filePath = "/data/results.md"; // getCurrDir +
            String results = sb.toString();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))){
                writer.write(results);
            }
            System.out.println(results);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
