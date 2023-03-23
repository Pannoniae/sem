package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UnitTests {

    static App app;

    @BeforeAll
    static void init(){
        app = new App();
    }

    @Test
    void createApp(){
        App a = new App();
    }


    
}