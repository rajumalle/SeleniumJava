package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import utilities.DriverFactory;
import utilities.ReportConfiguration;

import java.util.List;

//Page Object  Model implementation
public class JupiterPages {

    WebDriver driver;
    ReportConfiguration report=new ReportConfiguration();
    BasePage basePage = new BasePage();

    /**
     * Constructor to get driver & initialize Page Elements
     */
    public JupiterPages(){
        driver= DriverFactory.getDriver();
        PageFactory.initElements(driver,this);
    }

    public static String JupiterURL= "https://jupiter.cloud.planittesting.com/";
    //public static String JupiterURL= "https://jupiter2.cloud.planittesting.com/";

    @FindBy(xpath="//a[@class='btn btn-success btn-large']")
    public static WebElement StartShoppingLink;

    //********************** Contact Page Objects********************************//
    @FindBy(xpath="//*[@id=\"nav-contact\"]/a")
    public static WebElement ContactPageLink;

    @FindBy(xpath="//*[@id=\"forename\"]")
    public static WebElement ForeNameEditField;

    @FindBy(xpath=" //*[@id=\"forename-err\"]")
    public static WebElement ForeNameError;

    @FindBy(xpath="//*[@id=\"email\"]")
    public static WebElement EmailEditField;

    @FindBy(xpath="//*[@id=\"email-err\"]")
    public static WebElement EmailFieldError;

    @FindBy(xpath="//*[@id=\"message\"]")
    public static WebElement MessageEditField;

    @FindBy(xpath="//*[@id=\"message-err\"]")
    public static WebElement MessageFieldError;

    @FindBy(css=".form-actions .btn-primary")
    public static WebElement SubmitButton;

    @FindBy(xpath="//div[@class='alert alert-success']")
    public static WebElement SuccessMessage;

    //**********************Shop Page Objects *********************************************//
    @FindBy(xpath = "//*[@id='nav-shop']/a")
    public static WebElement ShopPageLink;

    @FindBy(css = ".products")
    public static WebElement Products;

    @FindBy(xpath = " //*[@id='nav-cart']/a")
    public static WebElement CartLink;

    @FindBy(xpath="//table[@class='table table-striped cart-items']")
    public static WebElement CartItemsTable;

    /**
     * To navigate to Jupiter Application
     */
    public void navigateToJupiterApplication()  {
        try{
            driver.get(JupiterURL);
            report.log("Jupiter Application URL -->"+JupiterURL);
            basePage.waitForSeconds(2);
             report.takeScreenshot(driver,"After navigating to Application URL");
            if(basePage.checkElementExists(StartShoppingLink)){
                report.logResult("Navigated to Jupiter Application Home Page",LogStatus.PASS);
            }else {
                report.logResult("Failed to navigate to Jupiter Application Home Page",LogStatus.FAIL);
            }
        }
        catch (Exception e){
             report.takeScreenshot(driver,"Failed to navigate to Jupiter Application Home Page");
            report.logResult("Failed to navigate to Jupiter Application Home Page",LogStatus.FAIL);
        }
    }

    public void navigateToContactPage() {
        try{
            basePage.click(JupiterPages.ContactPageLink);
            basePage.waitForSeconds(2);
            if(basePage.checkElementExists(ForeNameEditField)){
                report.logResult("Successfully Navigated to Contact Page",LogStatus.PASS);
                 report.takeScreenshot(driver,"Navigated to Contact Page");
            }else{
                report.logResult("Failed to navigate to Contact Page",LogStatus.FAIL);
            }
        }
        catch (Exception e){
            report.logResult("Failed to navigate to Contact Page",LogStatus.FAIL);
        }
    }

    public void submitContactForm() {
        try{
            basePage.click(JupiterPages.SubmitButton);
             report.takeScreenshot(driver,"Submitted contact page form");
        }
        catch (Exception e){
            report.logResult("Failed to click on Submit Button in Contact Page",LogStatus.FAIL);
        }
    }

    public void validateContactFormMandatoryErrors() {
        try{
            if(basePage.checkElementExists(ForeNameError) && basePage.checkElementExists(EmailFieldError) &&
                    basePage.checkElementExists(MessageFieldError)){
                report.logResult("Mandatory Error messages are present",LogStatus.PASS);
                 report.takeScreenshot(driver,"Mandatory Error messages are present");
            }else{
                report.logResult("Mandatory Error messages are not present",LogStatus.FAIL);
            }
        }
        catch (Exception e){
            report.logResult("Failed to validate mandatory Error messages",LogStatus.FAIL);
        }
    }

    public void enterMandatoryFieldsInContactPage(String forename, String email, String message) {
        try{
            basePage.enterText(ForeNameEditField,forename);
            basePage.enterText(EmailEditField,email);
            basePage.enterText(MessageEditField,message);
            basePage.waitForSeconds(2);
             report.takeScreenshot(driver,"Contact Page after entering values in to mandatory field");
        }
        catch (Exception e){
            report.logResult("Failed to enter mandatory fields in Contact Page",LogStatus.FAIL);
        }
    }

    public void validateErrorMessagesNotExist() {
        try{
            if(basePage.checkElementExists(ForeNameError) || basePage.checkElementExists(EmailFieldError) ||
                    basePage.checkElementExists(MessageFieldError)){
                report.logResult("Mandatory Error messages are not gone",LogStatus.FAIL);
            }else{
                report.logResult("Mandatory Error messages are gone",LogStatus.PASS);
                 report.takeScreenshot(driver,"Mandatory Error messages are gone");
            }
        }
        catch (Exception e){
            report.logResult("Failed to validate mandatory error messages, Exception-"+e.getMessage(),LogStatus.FAIL);
        }
    }

    public void validateSuccessMessage() {
        try{
            //wait for form submission completion
            basePage.waitForSeconds(10);
            if(basePage.checkElementExists(SuccessMessage)){
                report.logResult("Success Message Validated",LogStatus.PASS);
                 report.takeScreenshot(driver,"Success Message after submitting in contact form");
            }else{
                report.logResult("Failed: Success Message not exists",LogStatus.FAIL);
            }
        }
        catch (Exception e){
            report.logResult("Failed to validate Success Message, Exception-"+e.getMessage(),LogStatus.FAIL);
        }
    }


    public void navigateToShopPage() {
        try{
            basePage.click(JupiterPages.ShopPageLink);
            basePage.waitForSeconds(2);
             report.takeScreenshot(driver,"After clicking on Shop Page Link");
            if(basePage.checkElementExists(Products)){
                report.logResult("Successfully Navigated to Shop Page",LogStatus.PASS);
            }else{
                report.logResult("Failed to navigate to Shop Page",LogStatus.FAIL);
            }
        }
        catch (Exception e){
            report.logResult("Failed to navigate to Shop Page",LogStatus.FAIL);
        }
    }

    public void addToysToCart(String toyName, int quantity) {
        try{
            WebElement toyWebElement = getToyWebElement(toyName);
            if(basePage.checkElementExists(toyWebElement)){
                for(int i=0;i<quantity;i++){
                    toyWebElement.click();
                }
                 report.takeScreenshot(driver,"Added toy-"+toyName +" with quantity-"+quantity);
            }else{
                report.logResult("No product found with the name-"+toyName,LogStatus.FAIL);
            }
        }
        catch (Exception e){
            report.logResult("Failed to add Toys To Cart",LogStatus.FAIL);
        }
    }

    /**
     * To get the toy web element based on toy name
     * @param toyName
     * @return
     */
    WebElement getToyWebElement(String toyName){
        WebElement toyElement = null;
        int count = 0;
        try{
            List<WebElement> productElements= driver.findElements(By.cssSelector(".product-title"));
            for( WebElement productElement:productElements){
                count++;
                if(productElement.getText().equalsIgnoreCase(toyName)){
                    System.out.println("Product element found with name-"+productElement.getText());
                    toyElement=productElement;
                    break;
                }
            }
            System.out.println("Product found at-"+count);
            String cssValueOfButton = ".product:nth-child("+count+") .btn-success";
            System.out.println("css Value Of Button->"+cssValueOfButton);
            toyElement= driver.findElement(By.cssSelector(cssValueOfButton));
        }catch (Exception e){
            report.logResult("Failed to get Toy Web Element",LogStatus.FAIL);
        }
        return toyElement;
    }

    public void clickOnCartMenu() {
        try{
            basePage.click(JupiterPages.CartLink);
             report.takeScreenshot(driver,"After Clicking on cart");
        }
        catch (Exception e){
            report.logResult("Failed to add Toys To Cart",LogStatus.FAIL);
        }
    }

    public void verifyItemsInCart() {
        try{
            if(basePage.checkElementExists(CartItemsTable)){
                 report.takeScreenshot(driver,"Cart Items table page");
                List<WebElement> tableRowsList = CartItemsTable.findElements(By.tagName("tr"));
                int numberOfRows = tableRowsList.size();
                report.log("Number of rows in cart items table-"+numberOfRows);
                for(int i=1; i<numberOfRows-2;i++){
                    WebElement tableRow = tableRowsList.get(i);
                    List<WebElement> columnsList = tableRow.findElements(By.tagName("td"));
                    for (WebElement column : columnsList) {
                        System.out.print(column.getText() + "       ");
                    }
                    System.out.println();
                }
            }else{
                report.logResult("Cart Items table not exist, No items added to cart",LogStatus.FAIL);
            }
        }
        catch (Exception e){
            report.logResult("Failed to verify Items In Cart"+e.getMessage(),LogStatus.FAIL);
        }
    }

    public void verifyPriceOfProducts() {
        float totalPrice=0.0f;
        float sumOfSubTotals= 0.0f;
        try{
            if(basePage.checkElementExists(CartItemsTable)){
                 report.takeScreenshot(driver,"Cart Items table exist");
                WebElement tableRow=null;
                String itemName=null;
                float price=0.0f;
                int quantity = 0;
                float expectedSubTotals=0.0f;
                float actualSubtotal=0.0f;

                List<WebElement> tableRowsList = CartItemsTable.findElements(By.tagName("tr"));
                int numberOfRows = tableRowsList.size();
                report.log("Number of rows in cart items table-"+numberOfRows);

                //fetching table header row from table rows
                WebElement tableHeaderRow = tableRowsList.get(0);
                List<WebElement> tableHeaderColumns = tableHeaderRow.findElements(By.tagName("th"));

                for(int i=1; i<numberOfRows-2;i++){

                    tableRow = tableRowsList.get(i);
                    List<WebElement> columnsList = tableRow.findElements(By.tagName("td"));

                    for(int j=0; j<tableHeaderColumns.size(); j++){
                        WebElement headerColumn= tableHeaderColumns.get(j);
                        if(headerColumn.getText().equalsIgnoreCase("Item")){
                            itemName = columnsList.get(j).getText();
                        }
                        else if(headerColumn.getText().equalsIgnoreCase("Price")) {
                            price = Float.parseFloat(columnsList.get(j).getText().substring(1));
                        }
                        else if(headerColumn.getText().equalsIgnoreCase("Quantity")) {
                            //Fetching quantity value
                            WebElement quantityElement = columnsList.get(j).findElements(By.cssSelector(".input-mini")).get(0);
                            quantity = Integer.parseInt(quantityElement.getAttribute("value"));
                        }
                        else if(headerColumn.getText().equalsIgnoreCase("Subtotal")){
                            //fetching displayed subtotal
                            expectedSubTotals = Float.parseFloat(columnsList.get(j).getText().substring(1));
                        }
                    }

                    //calculating actual subtotal
                    actualSubtotal = price*quantity;
                    actualSubtotal = (float) (Math.round(actualSubtotal*100.0)/100.0);

                    report.log("Item-->"+itemName+"   price-->"+price+"   quantity-->"+quantity+
                            "    Expected SubTotals:-"+expectedSubTotals+"   Actual Subtotal:-"+actualSubtotal);
                    if(actualSubtotal == expectedSubTotals){
                        report.logResult("Validated that sub total = product price * quantity for Item-->"+itemName
                                +"  ******** -->Expected Sub totals:"+expectedSubTotals+"  == Actual Subtotal:-"+actualSubtotal,LogStatus.PASS);
                    }
                    sumOfSubTotals= sumOfSubTotals+actualSubtotal;
                }
                sumOfSubTotals = (float) (Math.round(sumOfSubTotals*100.0)/100.0);
                report.logResult("Sum Of sub totals -->"+sumOfSubTotals,LogStatus.PASS);

                //To fetch total price and compare with sum of sub totals
                tableRow = tableRowsList.get(numberOfRows-2);
                List<WebElement> columnsList = tableRow.findElements(By.tagName("td"));
                WebElement totalWebElement = columnsList.get(0).findElements(By.cssSelector(".total")).get(0);
                String totalText = totalWebElement.getText();
                totalPrice = Float.parseFloat(totalText.split(":")[1]);
                report.logResult("Total Price->"+totalPrice, LogStatus.PASS);
                if(totalPrice == sumOfSubTotals){
                    report.logResult("Total Price:-"+totalPrice+" , sum Of SubTotals:-"+sumOfSubTotals,LogStatus.PASS);
                }
            }else{
                report.logResult("Cart Items table not exist, No items added to cart",LogStatus.FAIL);
            }
        }
        catch (Exception e){
            report.logResult("Failed to verify Price Of Products"+e.getMessage(),LogStatus.FAIL);
        }
    }
}
