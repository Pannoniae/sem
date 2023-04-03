package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CapitalCityReport implements Report {

    protected final String query;

    public CapitalCityReport(String query) {
        this.query = query;
    }
    @Override
    public void execute() {
        try {
            App a = new App();
            Connection conn = a.connect("0.0.0.0");
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                String queryResult1 = rs.getString("city.name");
                String queryResult2 = rs.getString("country.name");
                String queryResult3 = rs.getString("city.population");
                System.out.println(queryResult1 + " : " + queryResult2 + " : " +
                        queryResult3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
