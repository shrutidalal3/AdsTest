package AdsCode;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.logging.Logger;

public class VerifyAds {
   private static Logger logger = Logger.getLogger(VerifyAds.class.getName());

    public static void VerifyInlineAdDisplayed(AppiumDriver driverAgent1){
        try{
            WebDriverWait wait = new WebDriverWait(driverAgent1,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iHRTLocatorsAds.inlineAds_xp)));
            WebElement InlineAd = driverAgent1.findElement(By.xpath(iHRTLocatorsAds.inlineAds_xp));
            if(InlineAd.isDisplayed()){

                System.out.println("Inline Ad is displayed");
            }
            else System.out.println("Inline Ad is not displayed");
        }
        catch (StaleElementReferenceException exe){
            WebElement InlineAd = driverAgent1.findElement(By.xpath(iHRTLocatorsAds.inlineAds_xp));
            if(InlineAd.isDisplayed()){

                System.out.println("Inline Ad is displayed");
            }

        }
        catch (Exception e){
            System.out.println("Exception: "+ e);
            //driverAgent1.quit();
        }


    }

    public static void VerifyRequestEvents(String requestBody,String eventname,String expValue){
        JsonParser parser = new JsonParser();
        JsonObject jsonobj = (JsonObject)parser.parse(requestBody);
        String reqValue = jsonobj.get(eventname).toString();
        if(reqValue.contains(expValue)){
            logger.info("Test log");
            System.out.println("Required event is displayed, value is: "+reqValue);
        }
        else {
            System.out.println("Required object is not found, displayed event obejct is: "+reqValue);
            Assert.fail();
        }
    }
}
