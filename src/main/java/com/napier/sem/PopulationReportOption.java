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

public class PopulationReportOption implements Report {
    protected final String query;
    protected String path;

    public PopulationReportOption(String query, String path) {
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
            sb.append("| Name | Population |" + "\n");
            sb.append("| --------- | --------- |\n");

            while (rs.next()) {
                String queryResult1 = rs.getString("Name");
                String queryResult2 = rs.getString("Population");

                sb.append("| " + queryResult1 + " | " + queryResult2 + " |\n");
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
