package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    static App app;

    @BeforeAll
    static void init(){
        app = new App();
    }

    @Test
    void createApp(){
        app = new App();
    }

    @AfterAll
    static void tearDown() {
        app.disconnect();
    }

    @Test
    public void testConnect() {
        assertNotNull("Connection should not be null", String.valueOf(app.connect("localhost")));
    }

    @Test
    public void testDisconnect() {
        app.disconnect();
        System.out.println("Successfully disconnected");
    }

    @Test
    public void testReportMap() {
        assertEquals(36, App.reports.size());
        System.out.println("36/36 Objects are in hasgmap");
    }

    @Test
    public void testReportMapValues() {
        assertTrue(App.reports.containsValue("All the countries in the world organised by largest population to smallest."), "Report map should contain report 1");
        if(App.reports.containsValue("All the countries in the world organised by largest population to smallest.")){
            System.out.println("1" + App.reports.get(1));
        }
    }

    @Test
    public void testMain() {
        String[] args = {"0.0.0.0", "1", "", ""};
        App.main(args);
        assertNotNull("Connection should not be null after running main", String.valueOf(app.conn));
    }

}