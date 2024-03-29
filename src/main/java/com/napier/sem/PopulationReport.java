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

public class PopulationReport implements Report {
    protected final String query;
    protected String path;

    public PopulationReport(String query, String path) {
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
            sb.append("| Name | Total Population | Urban Population | Rural Population |" + "\n");
            sb.append("| --------- | --------- | ---------- | ---------- |\n");

            while (rs.next()) {
                String queryResult1 = rs.getString("Name");
                String queryResult2 = rs.getString("total_population");
                String queryResult3 = rs.getString("urban_population");
                String queryResult4 = rs.getString("rural_population");

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
