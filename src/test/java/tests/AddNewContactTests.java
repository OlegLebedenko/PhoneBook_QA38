package tests;

import models.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

    Logger logger = LoggerFactory.getLogger(AddNewContactTests.class);

@BeforeMethod(alwaysRun = true)
    public void precondition(){
    if(!app.getUser().isLogged()){
        String email = "dara@mail.com", password = "Km12356#";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitLogin();
    }
}

@Test(invocationCount = 5, groups = {"positive"})
    public void addNewContactPositive(){
    int i = (int)(System.currentTimeMillis()/1000)%3600;
    Contact contact = Contact.builder()
            .name("Ron_" + i)
            .lastName("Bokker")
            .phone("8123541" + i)
            .email("reva" + i + "@mail.com")
            .address("Holon")
            .description("friend")
            .build();

    logger.info("Phone number is " + contact.getPhone());

    app.getHelperContact().openContactForm();
    app.getHelperContact().fillContactForm(contact);
    app.getHelperContact().submitContactForm();
    app.getHelperContact().pause(5);
    Assert.assertTrue(app.getHelperContact().isContactCreated(contact));
}
}
