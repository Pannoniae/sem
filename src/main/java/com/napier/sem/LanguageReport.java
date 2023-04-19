package com.napier.sem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LanguageReport implements Report {

    protected final String query;
    protected String path;

    public LanguageReport(String query, String path) {
        this.query = query;
        this.path = path;
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
            sb.append("| Country Language | Total Population | Percentage |" + "\n");
            sb.append("| --------- | --------- | ---------- |\n");

            while (rs.next()) {
                String queryResult1 = rs.getString("cl.language");
                String queryResult2 = rs.getString("TotalPopulation");
                String queryResult3 = rs.getString("Percentage");

                sb.append("| " + queryResult1 + " | " + queryResult2 + " | " + queryResult3 + " |\n");
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
