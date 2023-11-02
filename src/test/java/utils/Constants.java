package utils;

public class Constants {
    // CONFIGURATION_FILEPATH=> name of variable
        public static final String CONFIGURATION_FILEPATH =//public-> access modifier, static , we dont need to create object,
    // the path to config property
                System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
        public static final int IMPLICIT_WAIT = 10;
        public static final int EXPLICIT_WAIT = 20;
    public static final String TESTDATA_FILEPATH =
            System.getProperty("user.dir")+"/src/test/resources/testdata/batch17excel.xlsx";
    public static final String SCREENSHOT_FILEPATH =
            System.getProperty("user.dir")+"/screenshots/";

    }