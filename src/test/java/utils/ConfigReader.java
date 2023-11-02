package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader { //so configReader reads the config.properties
    static Properties prop;

    public static Properties readProperties(String filePath){  // method to load data from properties file into java

        try {
            FileInputStream fis = new FileInputStream(filePath);  //  we move it to ram cus it is fast.
            // filepath is location of file
            prop = new Properties(); //create class
            prop.load(fis);//  //object of the class
        } catch (FileNotFoundException e) {  // child exception
            e.printStackTrace();
        } catch (IOException e) {  // child exception
            e.printStackTrace();
        }
        return prop;   //
    }
    public static String getPropertyValue(String key){  // method , key ->browser, value->chrome
        //from prop object we are calling get property method
        // and we will pass the key to fetch the value
        return prop.getProperty(key);  //
    }
}