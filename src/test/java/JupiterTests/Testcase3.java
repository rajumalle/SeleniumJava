package JupiterTests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.JupiterPages;

public class Testcase3 {

    @Test
    @Parameters("Browser")
    public void testCase3(String browser){
        try{
            System.setProperty("Browser",browser);
            //Getting Jupiter Page instance
            JupiterPages jupiterPages =new JupiterPages();

            //To navigate to application
            jupiterPages.navigateToJupiterApplication();

            //1.From the home page go to shop page
            jupiterPages.navigateToShopPage();

            //2. Click buy button 2 times on “Funny Cow”
            jupiterPages.addToysToCart("Funny Cow",2);

            //3. Click buy button 1 time on “Fluffy Bunny”
            jupiterPages.addToysToCart("Fluffy Bunny",1);

            //4.Click the cart menu
            jupiterPages.clickOnCartMenu();

            //5.Verify the items are in the cart
            jupiterPages.verifyItemsInCart();

        }catch (Exception e){
            System.out.println("Exception Occurred"+e.getLocalizedMessage());
            Assert.fail("Failed in Testcase2-"+e.getLocalizedMessage());
        }
    }
}
