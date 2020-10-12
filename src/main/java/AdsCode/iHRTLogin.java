package AdsCode;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class iHRTLogin {

    public static void Login_iHRT(AppiumDriver driverAgent1)  {

        try {
            Thread.sleep((5000));
            WebElement loginBtn = driverAgent1.findElement(By.id(iHRTLocatorsAds.loginBtn));
            loginBtn.click();
        }
        catch (Exception e){
            System.out.println("Exception");
            driverAgent1.quit();
        }

    }

    public static void emailLogin_iHRT(AppiumDriver driverAgent1){
        try {
            Thread.sleep((5000));
            WebElement EmailField = driverAgent1.findElement(By.id(iHRTLocatorsAds.emailField));
            EmailField.sendKeys("adsTest@test.com");

            WebElement PasswordField = driverAgent1.findElement(By.id(iHRTLocatorsAds.passwordField));
            PasswordField.sendKeys("Test@123");

            WebElement EmailLogin = driverAgent1.findElement(By.id(iHRTLocatorsAds.emailLogin));
            if(EmailLogin.isEnabled()) {
                System.out.println("Email login button is enabled.");
                Thread.sleep((1000));
                EmailLogin.click();
                System.out.println("Email login button is clicked.");

            }
            else System.out.println("Email login button is disabled.");
        }
        catch (Exception e){
            System.out.println("Exception");
            driverAgent1.quit();
        }


    }

    public static void changesettings(AppiumDriver driverAgent1){
        try{
            WebDriverWait wait = new WebDriverWait(driverAgent1,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iHRTLocatorsAds.homeppageSettings_accessibilityiD)));
            WebElement settings = driverAgent1.findElement(By.xpath(iHRTLocatorsAds.homeppageSettings_accessibilityiD));
             settings.click();
        }
        catch (StaleElementReferenceException exe){
            WebElement settings = driverAgent1.findElement(By.xpath(iHRTLocatorsAds.homeppageSettings_accessibilityiD));
            settings.click();

        }
        catch (Exception e){
            System.out.println("Exception");
            driverAgent1.quit();
        }
    }

    public static void SelectTestersOptions(AppiumDriver driverAgent1) throws InterruptedException {
        //TouchActions touchAction = new TouchActions(driverAgent1);
        //touchAction.scroll(826,1400).perform();
        Dimension size = driverAgent1.manage().window().getSize();
        int startY = (int)(size.height*0.90);
        int endY = (int)(size.height*0.20);
        System.out.println("end y-axis: "+endY+ " start y axis:" +startY);
        TouchAction action = new TouchAction(driverAgent1);
        action.press(PointOption.point(900,startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3))).moveTo(PointOption.point(900,0)).release().perform();
        //touchAction.moveTo(PointOption.point(210,140)).perform();

        Thread.sleep(500);
        WebElement testerOptions = driverAgent1.findElement(By.id(iHRTLocatorsAds.testersOptions));
        if(testerOptions.isDisplayed()){
            testerOptions.click();
        }
        else {
        System.out.println("Testeroptions not displayed.");
        }
    }

    public static void DisableAdsFUX(AppiumDriver driverAgent1){

        WebElement adsFUX = driverAgent1.findElement(By.xpath(iHRTLocatorsAds.adsGraceDisable));

        System.out.println(adsFUX.isSelected());
        adsFUX.click();
    }

    public static void LiveRadioSelect(AppiumDriver<MobileElement> driverAgent1){
        try {
            WebDriverWait wait = new WebDriverWait(driverAgent1, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(iHRTLocatorsAds.RadioBtn)));
            MobileElement radioBtn = driverAgent1.findElement(By.id(iHRTLocatorsAds.RadioBtn));
            radioBtn.click();
        }
        catch (Exception e) {

            System.out.println("Cannot click on radio tab, exception: "+e.getMessage());
        }

    }

    public static void radioSelector(AppiumDriver driverAgent1){
        try {
            WebDriverWait wait = new WebDriverWait(driverAgent1, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(iHRTLocatorsAds.radioSelect)));
            WebElement radioBtn = driverAgent1.findElement(By.id(iHRTLocatorsAds.radioSelect));
            radioBtn.click();
        }
        catch (Exception e) {

            System.out.println("Cannot click on radio selector , exception: "+e.getMessage());
        }

    }

public static void SelectYourLibrary(AppiumDriver driverAgent1){
        WebElement yourlib = driverAgent1.findElement(By.id(iHRTLocatorsAds.YourLibrary));
        yourlib.click();
}

public static void DismissFirstLoginAlert(AppiumDriver driverAgent1){
    WebDriverWait wait = new WebDriverWait(driverAgent1,10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(iHRTLocatorsAds.intialpopup_id)));

        if(driverAgent1.findElement(By.id(iHRTLocatorsAds.intialpopup_id)).isDisplayed()){
            driverAgent1.findElement(By.id(iHRTLocatorsAds.intialPopupDismiss)).click();
    }
        else {System.out.println("No Alert found.");}
}

}
