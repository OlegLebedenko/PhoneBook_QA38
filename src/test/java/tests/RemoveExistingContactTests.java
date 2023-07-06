package tests;

import models.Contact;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveExistingContactTests extends TestBase{

    Logger logger = LoggerFactory.getLogger(RemoveExistingContactTests.class);

    @BeforeMethod
    public void precondition(){
        if(!app.getUser().isLogged()){
            String email = "dara@mail.com", password = "Km12356#";
            app.getUser().openLoginForm();
            app.getUser().fillLoginForm(email, password);
            app.getUser().submitLogin();
        }
    }

    @Test
    public void removeContactPositive(){
        int count1 = app.getHelperContact().getElementCount(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        System.out.println(count1);
        app.getHelperContact().click(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        app.getHelperContact().click(By.xpath("//button[.='Remove']"));
        app.getHelperContact().pause(5000);
        int count2 = app.getHelperContact().getElementCount(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        System.out.println(count2);
        int count = count1 - count2;
        System.out.println(count);
        logger.info(count + " contact removed");
        Assert.assertEquals(count, 1);
    }
}
