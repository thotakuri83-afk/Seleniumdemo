package com.demo.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.util.logging.Level;

//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl = "https://the-internet.herokuapp.com/login";

    // -------------------------
    //   BROWSER SETUP
    // -------------------------
	/*
	 * @BeforeMethod public void setUp() { WebDriverManager.chromedriver().setup();
	 * driver = new ChromeDriver(); driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	 * driver.get(baseUrl); }
	 */

    // Firefox setup (if you want to use it later,better not use)
    @BeforeMethod
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(baseUrl);
        
        
    }

    // -------------------------
    //   SCREENSHOT METHOD
    // -------------------------
    public void takeScreenshot(String testName) {
        try {
            // Capture screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create screenshot folder
            String folderPath = System.getProperty("user.dir") + File.separator + "screenshots";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            // Create filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File dest = new File(folderPath + File.separator + testName + "_" + timestamp + ".png");

            Files.copy(src.toPath(), dest.toPath());

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 	 
    // -------------------------
    //   CLOSE BROWSER + TAKE SCREENSHOT
    // -------------------------
    @AfterMethod
    public void tearDown(ITestResult result) {

        // Take screenshot for every test (pass or fail)
        takeScreenshot(result.getName());

        if (driver != null) {
            driver.quit();
        }
    }
}
