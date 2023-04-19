package com.napier.sem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LimitedCityReport extends CityReport {

    public LimitedCityReport(String query, String path) {
        super(query, path);
    }
    public static String getAbsPath(){
        return System.getProperty("user.dir") + "/data/test-results.md";
    }

    @Override
    public void execute() {
        try {
            App a = new App();
            Connection conn = a.connect("0.0.0.0");
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();
            StringBuilder sb = new StringBuilder();
            sb.append("| City Name | Country Name | City District | City Population |" + "\n");
            sb.append("| --------- | ------------ | ------------- | --------------- |\n");

            while (rs.next()) {
                String queryResult1 = rs.getString("city.name");
                String queryResult2 = rs.getString("country.name");
                String queryResult3 = rs.getString("city.district");
                String queryResult4 = rs.getString("city.population");
                sb.append("| " + queryResult1 + " | " + queryResult2 + " | " + queryResult3 + " | " + queryResult4 + " |\n");

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
