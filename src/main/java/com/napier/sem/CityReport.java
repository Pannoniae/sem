package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CityReport implements Report {
    protected final String query;

    public CityReport(String query) {
        this.query = query;
    }

    @Override
    public void execute() {
        try {
            Connection conn = App.getConnection();
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                String queryResult1 = rs.getString("city.name");
                String queryResult2 = rs.getString("country.name");
                String queryResult3 = rs.getString("city.district");
                String queryResult4 = rs.getString("city.population");
                System.out.println(queryResult1 + " : " + queryResult2 + " : " +
                        queryResult3 + " : " + queryResult4);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
