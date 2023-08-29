package tests;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTests extends TestBase {
//    WebDriver wd;
//
//
//     @BeforeMethod
//    public void init(){
//         wd = new ChromeDriver();
//         wd.get("https://telranedu.web.app/home");
//         wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//    }

@BeforeMethod(alwaysRun = true)
public void precondition(){
    if(app.getUser().isLogged()) {
        app.getUser().logout();
    }
}
//    @Test
//    public void loginPositiveTest(){
//
//         // open login form
//         wd.findElement(By.xpath("//*[.='LOGIN']")).click();
//
//         // fill login form
//         WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//         emailInput.click();
//         emailInput.clear();
//         emailInput.sendKeys("dara@mail.com");
//
//         WebElement passInput = wd.findElement(By.xpath("//input[2]"));
//         passInput.click();
//         passInput.clear();
//         passInput.sendKeys("Km12356#");
//
//         // click on button Login
//        wd.findElement(By.xpath("//button[1]")).click();
//
//        // Assert
//    //    Assert.assertTrue(wd.findElements(By.xpath("//*[.='Sign Out']")).size()>0);
//    //    Assert.assertTrue(wd.findElements(By.xpath("//button")).size()>0);
//        pause(5000);
//        Assert.assertTrue(isElementPresent(By.xpath("//button")));
//    }

    @Test(groups = {"smoke","positive"})
    public void loginPositiveTestBase(){
        String email = "dara@mail.com", password = "Km12356#";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitLogin();
        app.getUser().pause(5000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(groups = {"positive"})
    public void loginPositiveTestUser(){

    User user = new User()
            .withEmail("lag@mail.ru")
            .withPassword("Kl23456*");
    app.getUser().openLoginForm();
    app.getUser().fillLoginForm(user.getEmail(), user.getPassword());
    app.getUser().submitLogin();
    app.getUser().pause(5000);
    Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(groups = {"regress","positive"})
    public void loginPositiveTestUserUser(){

        User user = new User()
                .withEmail("vek@co.il")
                .withPassword("Lom12345678909#");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(5000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

//    @Test
//    public void loginNegativeTestWrongEmail(){
//        wd.findElement(By.xpath("//*[.='LOGIN']")).click();
//
//        // fill login form
//        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys("daramail.com");
//
//        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
//        passInput.click();
//        passInput.clear();
//        passInput.sendKeys("Km12356#");
//
//        // click on button Login
//        wd.findElement(By.xpath("//button[1]")).click();
//
//        // Assert
//    }
//
    @Test(groups = {"negative"})
    public void loginNegativeTestWrongPassword(){
        String email = "dara@mail.com", password = "Km123567";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test(groups = {"regress", "negative"})
    public void loginNegativeTestWrongEmail(){
    String email = "daramail.com", password = "Km12356#";
    app.getUser().openLoginForm();
    app.getUser().fillLoginForm(email, password);
    app.getUser().submitLogin();
    Assert.assertTrue(app.getUser().isWrongFormatMessage());
    Assert.assertTrue(app.getUser().isAlertPresent());
 //   app.getUser().isAlertPresent();
    }




}
