package tests;

import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    public Logger logger = LoggerFactory.getLogger(TestBase.class);

   static ApplicationManager app = new ApplicationManager();

//    WebDriver wd;


    @BeforeSuite
    public void setUp(){
       app.init();

    }

    @AfterSuite
    public void stop(){

        app.tearDown();
    }

    @BeforeMethod
    public void startLogger(Method method){

        logger.info("Method " + method.getName() + " is started");
    }

    @AfterMethod
    public void end(){

        logger.info("================================================================");
    }

//    public void click(By locator){
//
//        wd.findElement(locator).click();
//    }
//
//    public void type(By locator, String text){
//        WebElement element = wd.findElement(locator);
//        element.click();
//        element.clear();
//        element.sendKeys(text);
//    }
//
//    public void pause(int millis){
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//    public void openLoginForm(){
//
//        wd.findElement(By.xpath("//*[.='LOGIN']")).click();
//    }
//
//    public void fillLoginForm(String email, String password){
//        type(By.xpath("//input[1]"),email);
//        type(By.xpath("//input[2]"),password);
//
//    }
//
//    public void submitRegistration(){
//
//        click(By.xpath("//button[2]"));
//    }
//
//    public void submitLogin(){
//
//        click(By.xpath("//button[1]"));
//    }

//    public boolean isElementPresent(By locator){
//
//        return wd.findElements(locator).size()>0;
//    }

//    public void logout(){
//        click(By.xpath("//*[.='Sign Out']"));
//    }
//
//    public boolean isLogged(){
//       return isElementPresent((By.xpath("//*[.='Sign Out']")));
//    }
}
