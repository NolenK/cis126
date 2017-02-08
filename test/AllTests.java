package test;

import greentealatte.src.GreenTeaLatte;

public class AllTests {
    public static void main(String[] args) {
        GreenTeaLatte driver = new GreenTeaLatte("Running all tests");

        driver.setIndentationToSpaces(4);

        DFlipFlopTest.defineTests(driver);
        RegisterTest.defineTests(driver);

        driver.run();
    }
}
