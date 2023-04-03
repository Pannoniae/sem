package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LimitedCapitalCityReport extends CapitalCityReport {
    protected final int limit;

    public LimitedCapitalCityReport(String query, int limit) {
        super(query);
        this.limit = limit;
    }

    @Override
    public void execute() {
        try {
            App a = new App();
            Connection conn = a.connect("0.0.0.0");
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            for (int i = 0; i < limit; i++) {
                String queryResult1 = rs.getString("city.name");
                String queryResult2 = rs.getString("city.country");
                String queryResult3 = rs.getString("city.population");
                System.out.println(queryResult1 + " : " + queryResult2 + " : " +
                        queryResult3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
