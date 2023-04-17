package com.napier.sem;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PopulationReportOption implements Report {
    protected final String query;

    public PopulationReportOption(String query) {
        this.query = query;
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
            String filePath = "/data/results.md";
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
