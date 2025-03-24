package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.Base;

public class TestCases {
    HomePage homePage = new HomePage();

    @Test
    public void quicktest () {
        HomePage.randomtest();
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }


}
