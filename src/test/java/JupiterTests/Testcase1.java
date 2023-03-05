package JupiterTests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.JupiterPages;

public class Testcase1 {

    @Test
    @Parameters("Browser")
    public void testCase1(String browser){
        try{
            System.setProperty("Browser",browser);
            //Getting Jupiter Page instance
            JupiterPages jupiterPages =new JupiterPages();

            //To navigate to application
            jupiterPages.navigateToJupiterApplication();

            //1.From the home page go to contact page
            jupiterPages.navigateToContactPage();

            //2.Click submit button
            jupiterPages.submitContactForm();

            //3.Validate errors
            jupiterPages.validateContactFormMandatoryErrors();

            //4.Populate mandatory fields
            jupiterPages.enterMandatoryFieldsInContactPage("John","John123@planit.net.au","Testing");

            //5.Validate errors are gone
           // jupiterPages.validateErrorMessagesNotExist();

        }catch (Exception e){
            System.out.println("Exception Occurred"+e.getLocalizedMessage());
            Assert.fail("Failed in Testcase1-"+e.getLocalizedMessage());
        }
    }
}
