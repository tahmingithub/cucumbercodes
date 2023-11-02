package utils;

import io.cucumber.java.et.Ja;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver; // initialize.  WebDriver -> interface

    public static void openBrowserAndLaunchApplication() { // open browser and start program
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);//Constants.CONFIGURATION_FILEPATH)->from constants
        switch (ConfigReader.getPropertyValue("browser")) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("invalid browser name");// like different browser likeedge
        }
        // launch application
        driver.manage().window().maximize();
        // launch url
        driver.get(ConfigReader.getPropertyValue("url"));
        initializePageObjects();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));// Constants.IMPLICIT_WAIT) from constants
    }

    public static void sendText(WebElement element, String textToSend) { //  element need to be clear before you send text
        element.clear();
        element.sendKeys(textToSend);
    }

    public static WebDriverWait getWait() {   //  getWait->method,  1, wait 20 second
        WebDriverWait wait = new WebDriverWait(driver,   // explicit wait rely on condition
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }

    public static void waitForClickability(WebElement element){   //2, condition until element is clickable
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {   //3 ,then click on element
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() { // getJSExecutor->method name
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }
    public void closeBrowser() {
        driver.quit();
    }
    //take screenshot method for capturing all the screenshots
    public static byte[] takeScreenshot(String fileName){ // method
        TakesScreenshot ts = (TakesScreenshot) driver;
        //in cucumber screenshot should be taken in array of byte format
        //since the size of the image is in byte, that's why the return type of this
        //method is array of byte
        byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        //we will pass the path of ss from constants class
        try {
            FileUtils.copyFile
                    (sourceFile, new File
                            (Constants.SCREENSHOT_FILEPATH +
                                    fileName+" "+
                                    getTimeStamp("yyyy-MM-dd-HH-mm-ss")
                                    +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picByte;
    }

    //in java we have a module which returns current date and time
    public static String getTimeStamp(String pattern){ // method
        Date date = new Date();
        //after getting the date, I need to format it as per my requirement
        SimpleDateFormat sdf =  new SimpleDateFormat(pattern);
        // it will return the formatted date as per the pattern in string format
        return sdf.format(date);


    }

}


