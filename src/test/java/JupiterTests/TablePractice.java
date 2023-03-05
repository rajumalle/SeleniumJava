package JupiterTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import org.yaml.snakeyaml.Yaml;
import utilities.DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class TablePractice {

    @Test
    public void tableTest(){

        System.setProperty("Browser","local-web-chrome");
        WebDriver driver= DriverFactory.getDriver();
        driver.get("https://jupiter.cloud.planittesting.com/");
        WebElement cartTable= driver.findElement
                (By.xpath("//table[@class='table table-striped cart-items']"));

        List<WebElement> tableRows = cartTable.findElements(By.tagName("tr"));
        int numberOfRows= tableRows.size();
        System.out.println("Number of rows->"+numberOfRows);

        for (int i=1;i<numberOfRows-2;i++){
            System.out.println("************ROW->"+i+"***************");
            WebElement firstRow= tableRows.get(i);
            List<WebElement> tableColumns= firstRow.findElements(By.tagName("td"));
            System.out.println("Number of columns in row->"+tableColumns.size());
            String toyName= tableColumns.get(0).getText();
            String price= tableColumns.get(1).getText();//price->"$10.99"-->convert to float
            float actualPrice= Float.parseFloat(price.substring(1));
            System.out.println("actual price->"+actualPrice);

            WebElement quantityElement=tableColumns.get(2).findElement(By.tagName("input"));
            int actualQuantity = Integer.parseInt(quantityElement.getAttribute("value"));
            System.out.println("actualQuantity->"+actualQuantity);

            float calculatedSubTotal= actualPrice*actualQuantity;
            float subTotal=Float.parseFloat(tableColumns.get(3).getText().substring(1));// convert to double and compare->21.98

            if(calculatedSubTotal==subTotal){
                System.out.println("Both are equal");//pass
            }else {
                System.out.println("Both are not equal");//fail
            }
            System.out.println("***************************");
        }
    }
}
