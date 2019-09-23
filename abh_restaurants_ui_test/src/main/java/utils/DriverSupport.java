package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Properties;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverSupport {
    private WebDriver driver;
    private Capabilities capabilities;

    public WebDriver initDriver(String browser) {
        java.util.Properties p = new Properties();

        if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("headless");
            options.addArguments("--start-maximized");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("acceptInsecureCerts",true);
            driver = new FirefoxDriver(capabilities);

        } else if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
            driver = new ChromeDriver();
        } else if (browser.equals("safari")) {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setUseTechnologyPreview(true);
            driver = new SafariDriver(safariOptions);
        }

        if (browser.contains("remote")) {
            if (browser.equals("remote-firefox")) {
                capabilities = DesiredCapabilities.firefox();
            } else if (browser.equals("remote-chrome")) {
                capabilities = DesiredCapabilities.chrome();
            }
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                        capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
