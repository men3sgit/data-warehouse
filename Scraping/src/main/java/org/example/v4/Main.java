package org.example.v4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");

        // Create EdgeOptions to specify the Edge browser binary location (optional)
        EdgeOptions edgeOptions = new EdgeOptions();
        // Uncomment the next line and specify the actual path to the Edge browser binary if needed.
        // edgeOptions.setBinary("path/to/edge/browser/binary");

        // Create an instance of the EdgeDriver with the EdgeOptions
        WebDriver driver = new EdgeDriver(edgeOptions);

        // Implicitly wait for up to 10 seconds for elements to become available
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.baeldung.com/java-csv");
    }
}
