import Utilities.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.StreamSupport;

public class iOSAdsTests {

    private  MitmproxyJava proxy;
    private AppiumDriver driver;
    private AppiumDriverLocalService services;
    private List<InterceptedMessage> messages = new ArrayList<InterceptedMessage>();
    iHRTLocators locs;

    @BeforeTest
    public void start() throws TimeoutException, IOException, URISyntaxException {

        //proxy = new MitmproxyJava("/usr/local/bin/mitmdump", (InterceptedMessage m) -> {
           // System.out.println("intercepted request for " + m.requestURL.toString());
           // messages.add(m);
           // return m;
      //  });
       // proxy.start();

    }
    @AfterTest
    public void quit() throws IOException, InterruptedException {
        //proxy.stop();
        services.stop();
        //driver.quit();

    }
    @Test(enabled = true)
    void IOSTestSample() throws TimeoutException, IOException, URISyntaxException, InterruptedException {

        services = driverSetup.AppiumServerStart();
        System.out.println("Appium server started");
        driver = driverSetup.getDriver("iOS", services);

        locs = new iHRTLocators(driver);
        System.out.println("Server and simulator started");
        driver.getPageSource();

        LogEntries StartLogs = driver.manage().logs().get("syslogs");
        StreamSupport.stream(StartLogs.spliterator(), false).limit(50).forEach(System.out::println);
        //iHRTLocators.Login1.click();
        // AdsCode.iHRTLogin.Login_iHRT(driver1);
        // AdsCode.iHRTLogin.emailLogin_iHRT(driver1);
        //Thread.sleep(2000);
        //AdsCode.iHRTLogin.changesettings(driver1);

        //new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(locs.gettabRadio()));

        Utilities.Wait(driver,locs.tabRadio);
        locs.tabRadio.click();
        driver.getPageSource();
        Thread.sleep(20000);
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(locs.inlineads));
        locs.inlineads.isDisplayed();
        System.out.println("Inline Ad diaplyed");
        //services1.stop();
        long size = messages.stream().filter((m) -> m.requestURL.getHost().contains("pubads.g.doubleclick.net")).count();
        System.out.print(size);
        InterceptedMessage appiumIORequest1 = messages.stream().filter((m) -> m.requestURL.getHost().contains("pubads.g.doubleclick.net")).skip(size-2).findAny().get();
        System.out.println( appiumIORequest1.requestURL);
        String url = appiumIORequest1.requestURL.toString();
        List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), Charset.forName("UTF-8"));
        for(NameValuePair param: params){
            System.out.println(param.getName() + ":"+param.getValue());
        }

        System.out.println("Appium server stopped");


    }
}
