package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LimitedCountryReport extends CountryReport {
    protected final int limit;

    public LimitedCountryReport(String query, int limit) {
        super(query);
        this.limit = limit;
    }

    @Override
    public void execute() {
        try {
            Connection conn = App.getConnection();
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            for (int i = 0; i < limit; i++) {
                String queryResult1 = rs.getString("country.code");
                String queryResult2 = rs.getString("country.name");
                String queryResult3 = rs.getString("country.continent");
                String queryResult4 = rs.getString("country.region");
                String queryResult5 = rs.getString("country.population");
                String queryResult6 = rs.getString("country.capital");
                System.out.println(queryResult1 + " : " + queryResult2 + " : " +
                        queryResult3 + " : " + queryResult4 + " : " +
                        queryResult5 + " : " + queryResult6);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
