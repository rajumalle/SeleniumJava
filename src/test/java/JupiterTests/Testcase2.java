package JupiterTests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.JupiterPages;

public class Testcase2 {

    @Test
    @Parameters("Browser")
    public void testCase2(String browser){
        try{
            System.setProperty("Browser",browser);
            //Getting Jupiter Page instance
            JupiterPages jupiterPages =new JupiterPages();

            //To navigate to application
            jupiterPages.navigateToJupiterApplication();

            //1.From the home page go to contact page
            jupiterPages.navigateToContactPage();

            //2.Populate mandatory fields
            jupiterPages.enterMandatoryFieldsInContactPage("John","John123@planit.net.au","Testing");

            //3.Click submit button
            jupiterPages.submitContactForm();

            //4.Validate successful submission message
            jupiterPages.validateSuccessMessage();

        }catch (Exception e){
            System.out.println("Exception Occurred"+e.getLocalizedMessage());
            Assert.fail("Failed in Testcase2-"+e.getLocalizedMessage());
        }
    }
}
