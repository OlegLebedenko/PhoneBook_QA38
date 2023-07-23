package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
  //     WebDriver wd;
    EventFiringWebDriver wd;
       HelperUser user;

       HelperContact helperContact;

       String browser;
       Properties properties;

    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }

    public HelperUser getUser() {

        return user;
    }

    public HelperContact getHelperContact() {

        return helperContact;
    }
    public String getEmail() {
        return properties.getProperty("web.email");
    }
    public String getPassword() {

        return properties.getProperty("web.password");
    }

    //   @BeforeSuite
   public void init() throws IOException {
   //     wd = new ChromeDriver();
       String target = System.getProperty("target", "prod");
       properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
       if(browser.equals(BrowserType.CHROME)){
           wd = new EventFiringWebDriver(new ChromeDriver());
           logger.info("Tests starting Chrome");
       }else if(browser.equals(BrowserType.FIREFOX)){
           wd = new EventFiringWebDriver(new FirefoxDriver());
           logger.info("Tests starting Firefox");
       }

       wd.register(new WdListener());
        user = new HelperUser(wd);
        helperContact = new HelperContact(wd);
       //    wd.manage().window().maximize();
    //   wd.get("https://telranedu.web.app/home");
       wd.navigate().to(properties.getProperty("web.baseURL"));
       wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

 //   @AfterSuite
    public void tearDown(){

    //    wd.quit();
    }
}
