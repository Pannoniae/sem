package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LanguageReport implements Report {

    protected final String query;

    public LanguageReport(String query) {
        this.query = query;
    }
    @Override
    public void execute() {
        try {
            App a = new App();
            Connection conn = a.connect("db:3306");
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                String queryResult1 = rs.getString("cl.language");
                String queryResult2 = rs.getString("TotalPopulation");
                String queryResult3 = rs.getString("Percentage");
                System.out.println(queryResult1 + " : " + queryResult2 + " : " +
                        queryResult3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
