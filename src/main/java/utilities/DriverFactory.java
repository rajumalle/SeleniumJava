package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

//Singleton class for driver
public class DriverFactory {

    private static WebDriver driver=null;
    private DriverFactory(){
    }

    public static WebDriver getDriver(){
        String browser = System.getProperty("Browser");
        if(driver==null) {
            System.out.println("Browser name provided from testng->"+browser);
            if(browser.contains("chrome")){
                System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver");
                driver= new ChromeDriver();
            }else if(browser.contains("firefox")){
                System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver");
                driver = new FirefoxDriver();
            }
            assert driver != null;
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void closeDriver(){
        if(driver!=null){
            if(System.getProperty("Browser").contains("web")){
                driver.close();
            }
            driver.quit();
            driver=null;
        }
    }
}
