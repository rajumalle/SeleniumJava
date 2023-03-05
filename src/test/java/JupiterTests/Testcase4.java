package JupiterTests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.JupiterPages;

public class Testcase4 {

    @Test
    @Parameters("Browser")
    public void testCase4(String browser){
        try{
            System.setProperty("Browser",browser);
            JupiterPages jupiterPages =new JupiterPages();
            jupiterPages.navigateToJupiterApplication();
            jupiterPages.navigateToShopPage();

            //1.Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear-->Adding to Cart
            jupiterPages.addToysToCart("Stuffed Frog",2);
            jupiterPages.addToysToCart("Fluffy Bunny",5);
            jupiterPages.addToysToCart("Valentine Bear",3);

            //2. Go to the cart page
            jupiterPages.clickOnCartMenu();

            //3.Verify the price for each product
            //4. Verify that each product’s sub total = product price * quantity
            //5. Verify that total = sum(sub totals)
            jupiterPages.verifyPriceOfProducts();

        }catch (Exception e){
            System.out.println("Exception Occurred"+e.getLocalizedMessage());
            Assert.fail("Failed in Testcase2-"+e.getLocalizedMessage());
        }
    }
}
