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

    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if(!app.getUser().isLogged()){
            String email = "dara@mail.com", password = "Km12356#";
            app.getUser().openLoginForm();
            app.getUser().fillLoginForm(email, password);
            app.getUser().submitLogin();
        }
    }

    @Test(priority = 1)
    public void removeAContactPositive(){
        int countBefore = app.getHelperContact().getElementCount(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        logger.info("Amount of contacts before removing is " + countBefore);
        app.getHelperContact().removeContact();
        app.getHelperContact().pause(5000);
        int countAfter = app.getHelperContact().getElementCount(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        logger.info("Amount of contacts after removing is " + countAfter);
        int res = countBefore - countAfter;
        logger.info(res + " contact was removed");
        Assert.assertEquals(res, 1);
    }

    @Test(priority = 2)
    public void removeAllContactsPositive(){
       while(!app.getHelperContact().isNoContact()){
           app.getHelperContact().removeContact();
           app.getHelperContact().pause(1000);
       }
       logger.info("All contacts are removed");
       Assert.assertTrue(app.getHelperContact().isNoContact());

    }
}
