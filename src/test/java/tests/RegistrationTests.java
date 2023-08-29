package tests;

import manager.ProviderData;
import manager.TestNgListener;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestNgListener.class)

public class RegistrationTests extends TestBase {
//    WebDriver wd;
//
//
//    @BeforeMethod
//    public void init(){
//        wd = new ChromeDriver();
//        wd.get("https://telranedu.web.app/home");
//        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//    }

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }


    @Test(groups = {"smoke", "positive"})
    public void registrationPositiveTestBase() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
        String email = "mara" + i + "@mail.com", password = "Uv12356#";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(groups = {"positive"})
    public void registrationPositiveTestUser() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
        User user = new User()
                .withEmail("fara" + i + "@mail.com")
                .withPassword("Mr12356#");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

//    @Test
//    public void registrationPositiveTest() {
//        // open login form
//        wd.findElement(By.xpath("//*[.='LOGIN']")).click();
//
//        // fill login form
//
//        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
//
//        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys("zara" + i + "@mail.com");
//
//        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
//        passInput.click();
//        passInput.clear();
//        passInput.sendKeys("Kv12356#");
//
//        // click on button Registration
//        wd.findElement(By.xpath("//button[2]")).click();
//
//        // Assert
//        //    Assert.assertTrue(wd.findElements(By.xpath("//*[.='Sign Out']")).size()>0);
//        //  Assert.assertTrue(wd.findElements(By.xpath("//button")).size()>0);
//        pause(5000);
//        Assert.assertTrue(isElementPresent(By.xpath("//button")));
//    }

    //    @Test
//    public void registrationNegativeTestWrongPassword() {
//
//        wd.findElement(By.xpath("//*[.='LOGIN']")).click();
//
//        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
//
//        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys("mara" + 1 + "@mail.ru");
//
//        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
//        passInput.click();
//        passInput.clear();
//        passInput.sendKeys("kv12356#");
//
//        wd.findElement(By.xpath("//button[2]")).click();
//
//    }
    @Test(groups = {"regress", "positive"}, dataProvider = "userDtoCSV", dataProviderClass = ProviderData.class)
    public void registrationPositiveTestUserCSV(User user) {
     logger.info("Registration starts with email: " + user.getEmail() + " and password: " + user.getPassword());
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(groups = {"regress", "negative"})
    public void registrationNegativeTestWrongEmail() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
        String email = "mara" + i + "mail.ru", password = "Kv12356#";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test(groups = {"negative"})
    public void registrationNegativeTestWrongPassword() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
        String email = "mara" + i + "@mail.com", password = "kv12356#";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isWrongFormatMessage());
        Assert.assertTrue(app.getUser().isAlertPresent());

    }

    @AfterClass(alwaysRun = true)
    public void postcondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }


}
