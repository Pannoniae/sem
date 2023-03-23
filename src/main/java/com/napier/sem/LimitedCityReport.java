package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LimitedCityReport extends CityReport {
    protected final int limit;

    public LimitedCityReport(String query, int limit) {
        super(query);
        this.limit = limit;
    }

    @Override
    public void execute() {
        try {
            App a = new App();
            Connection conn = a.connect("db:3306");
            PreparedStatement prst = conn.prepareStatement(query);
            ResultSet rs = prst.executeQuery();

            for (int i = 0; i < limit; i++) {
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
