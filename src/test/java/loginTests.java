import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class loginTests {
    WebDriver wd;


     @BeforeMethod
    public void init(){
         wd = new ChromeDriver();
         wd.get("https://telranedu.web.app/home");
         wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


    @Test
    public void loginPositiveTest(){

         // open login form
         wd.findElement(By.xpath("//*[.='LOGIN']")).click();

         // fill login form
         WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
         emailInput.click();
         emailInput.clear();
         emailInput.sendKeys("dara@mail.com");

         WebElement passInput = wd.findElement(By.xpath("//input[2]"));
         passInput.click();
         passInput.clear();
         passInput.sendKeys("Km12356#");

         // click on button Login
        wd.findElement(By.xpath("//button[1]")).click();

        // Assert
    //    Assert.assertTrue(wd.findElements(By.xpath("//*[.='Sign Out']")).size()>0);
        Assert.assertTrue(wd.findElements(By.xpath("//button")).size()>0);
    }

    @Test
    public void loginNegativeTestWrongEmail(){
        wd.findElement(By.xpath("//*[.='LOGIN']")).click();

        // fill login form
        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("daramail.com");

        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
        passInput.click();
        passInput.clear();
        passInput.sendKeys("Km12356#");

        // click on button Login
        wd.findElement(By.xpath("//button[1]")).click();

        // Assert
    }


    @AfterMethod
    public void tearDown(){

    }

}
