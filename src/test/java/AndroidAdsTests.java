import AdsCode.AdsDriverSetUp;
import AdsCode.VerifyAds;
import AdsCode.iHRTLocatorsAds;
import AdsCode.iHRTLogin;
import Utilities.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class AndroidAdsTests {






        private AppiumDriver driver;
        private AppiumDriverLocalService services;
        // private List<InterceptedMessage> messages = new ArrayList<InterceptedMessage>();
        iHRTLocatorsAds locs;

        @BeforeTest
        public void start() throws TimeoutException, IOException, URISyntaxException {

            // proxy = new MitmproxyJava("/usr/local/bin/mitmdump", (InterceptedMessage m) -> {
            //  System.out.println("intercepted request for " + m.requestURL.toString());
            // messages.add(m);
            //  return m;
            //});
            // proxy.start();

            // driver.manage().logs().get("logcat");
        }

        @AfterTest
        public void quit() throws IOException, InterruptedException {
            //((CanRecordScreen) driver).stopRecordingScreen();
            // proxy.stop();
            services.stop();
            //driver.quit();

        }


        @Test(enabled = false)
        void AndroidSetupTest() throws TimeoutException, IOException, URISyntaxException, InterruptedException {

            services = AdsDriverSetUp.AppiumServerStart();
            System.out.println("Appium server started");
            DesiredCapabilities options = new DesiredCapabilities();
            options.setCapability("deviceName", "Androidtest");
            //options.setCapability("avd","Pixel_XL_API_29");
            options.setCapability("platformName", "Android");
            options.setCapability("platformVersion", "9");
            options.setCapability("automationName", "UiAutomator2");
            options.setCapability("autoGrantPermissions", "true");
            //options.setCapability("noReset","true");

            options.setCapability("app", "/Users/Shruti/Desktop/untitled folder/iHRAndroidBuild/iHeartRadio-google-mobile-ampprod-debug.apk");
            options.setCapability("uiautomator2ServerInstallTimeout", "60000");
            options.setCapability("abdExecTimeout", "60000");
            options.setCapability("--proxy-server", "localhost:8080");
            options.setCapability("ACCEPT_SSL_CERTS", true);
            //options.setCapability("--set ssl_version_client","all");
            driver = new AndroidDriver<MobileElement>(services, options);
            System.out.println("Certificate set");
            Thread.sleep(10000);
            iHRTLogin.Login_iHRT(driver);
            iHRTLogin.emailLogin_iHRT(driver);
            Thread.sleep(10000);
            //AdsCode.iHRTLogin.DismissFirstLoginAlert(driver);
            iHRTLogin.changesettings(driver);
            Thread.sleep(10000);
            iHRTLogin.SelectTestersOptions(driver);
            Thread.sleep(10000);
            iHRTLogin.DisableAdsFUX(driver);
            Thread.sleep(2000);
            iHRTLogin.changesettings(driver);
            iHRTLogin.changesettings(driver);
            Thread.sleep(10000);
            driver.getPageSource();
            //InterceptedMessage appiumIORequest = messages.stream().filter((m) -> m.requestURL.getPath().contains("events")).findFirst().get();
            // System.out.println(new String(appiumIORequest.requestBody, StandardCharsets.UTF_8));
            // AdsCode.VerifyAds.VerifyRequestEvents(new String(appiumIORequest.requestBody, StandardCharsets.UTF_8),"action","app_open");
            //JsonParser parser = new JsonParser();
            //JsonObject jsonobj = (JsonObject)parser.parse(new String(appiumIORequest.requestBody, StandardCharsets.UTF_8));
            //System.out.println(jsonobj);
            //System.out.println(jsonobj.get("action"));
        }


        @Test
        void LiveRadioInlineAds() throws TimeoutException, IOException, URISyntaxException, InterruptedException {
            services = AdsDriverSetUp.AppiumServerStart();
            AppiumDriver driver = AdsDriverSetUp.getDriver("Android", services);



            Thread.sleep(10000);
            driver.getPageSource();
            LogEntries StartLogs = driver.manage().logs().get("logcat");
            iHRTLogin.LiveRadioSelect(driver);

            Thread.sleep(30000);

            VerifyAds.VerifyInlineAdDisplayed(driver);
            Thread.sleep(10000);
            LogEntries secondCallToLogs = driver.manage().logs().get("logcat");
            Stream<LogEntry> str = Utilities.getAdsLogStreams(secondCallToLogs);
            for(LogEntry log : secondCallToLogs){
                var message = log.getMessage();

                if(message.contains("Debug CONTENT")){
                    if(message.contains("cust_params")) {
                        String cust_params = Utilities.getParamValue(message.substring(message.indexOf("uri") + 6),"cust_params");
                        if(cust_params.contains("hometab=radio")){
                            System.out.println("Required Ads Request has cust_params: "+  cust_params);
                        }
                        else System.out.println("Printing Else.........Required Ads Request has cust_params: "+  cust_params);




                    }
                }





            }


        }

        @Test
        void LiveProfileInlineAds() throws TimeoutException, IOException, URISyntaxException, InterruptedException {
            PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
            services = AdsDriverSetUp.AppiumServerStart();
            AppiumDriver driver = AdsDriverSetUp.getDriver("Android", services);
            Set<String> logtypes = driver.manage().logs().getAvailableLogTypes();
            String TestFile = "/Users/Shruti/Desktop/untitled folder/logcatlogs.txt";
            File FC = new File(TestFile);
            FC.createNewFile();
            PrintWriter pw = new PrintWriter(TestFile);
            System.out.println("suported log types: " + logtypes.toString());
            //List<LogEntry> logs = driver.manage().logs().get("logcat").getAll();


            System.out.println("Printing App Launch Logs..................");


            iHRTLogin.LiveRadioSelect(driver);
            LogEntries StartLogs = driver.manage().logs().get("logcat");

            iHRTLogin.radioSelector(driver);
            Thread.sleep(30000);

            LogEntries secondCallToLogs = driver.manage().logs().get("logcat");

            StreamSupport.stream(secondCallToLogs.spliterator(), false)
                    .filter((I)->I.getMessage().contains("https://pubads.g.doubleclick.net/gampad/ads"))
            ;
            for(LogEntry log : secondCallToLogs){
                var message = log.getMessage();

                if(message.contains("Debug CONTENT")){
                    if(message.contains("cust_params")) {
                        if(message.contains("ccrpos=8004"))
                            System.out.println("Printing Headers: " + message.substring(message.indexOf("uri")+6));

                        List<NameValuePair> params = URLEncodedUtils.parse(new URI(message.substring(message.indexOf("uri")+6)), Charset.forName("UTF-8"));
                        for(NameValuePair param: params){
                            System.out.println(param.getName() + ":"+param.getValue());
                            pw.println(param.getName() + ":"+param.getValue());
                        }

                    }
                }





            }

            //var message =StreamSupport.stream(secondCallToLogs.spliterator(), false).filter((d)->d.getMessage().contains("pubads.g.doubleclick.net")).limit(10).findFirst().get();
            //System.out.println(message);

            System.out.println("Printing only Live station Http logs:.............");

            System.out.println(Utilities.getAdbLogCat());

            //Utilities.Wait(driver, locs.liveStation);
            //locs.liveStation.isDisplayed();
            //locs.liveStation.click();

            //long size = messages.stream().filter((m) -> m.requestURL.getHost().startsWith("us.api.iheart.com/api/v3/playback/livestation/reporting")).count();
            //InterceptedMessage appiumIORequest1 = messages.stream().filter((m) -> m.requestURL.getHost().startsWith("Us.api.iheart.com/api/v3/playback/livestation/reporting")).findFirst().get();
            //System.out.println(appiumIORequest1.requestURL);
            // List<NameValuePair> params = URLEncodedUtils.parse(new URI(appiumIORequest1.requestURL.toString()), Charset.forName("UTF-8"));
            //for (NameValuePair param : params) {
            // System.out.println(param.getName() + ":" + param.getValue());
            // }


        }

        @Test
        void PlaylistDirectoryInlineAds() throws TimeoutException, IOException, URISyntaxException, InterruptedException {

            services = AdsDriverSetUp.AppiumServerStart();
            AppiumDriver driver = AdsDriverSetUp.getDriver("Android", services);
            iHRTLocatorsAds locs = new iHRTLocatorsAds(driver);
            Thread.sleep(10000);
            driver.getPageSource();
            LogEntries StartLogs = driver.manage().logs().get("logcat");
            if(locs.isDisplayed()){
                locs.clickPlaylistatb();
                Thread.sleep(10000);
                System.out.println("Playlist tab loaded");
            }
            VerifyAds.VerifyInlineAdDisplayed(driver);
            LogEntries secondCallToLogs = driver.manage().logs().get("logcat");
            for(LogEntry log : secondCallToLogs) {
                var message = log.getMessage();

                if (message.contains("Debug CONTENT")) {
                    if (message.contains("cust_params")) {
                        String cust_params = Utilities.getParamValue(message.substring(message.indexOf("uri") + 6), "cust_params");
                        Assert.assertTrue(cust_params.contains("hometab=playlists")); {
                            System.out.println("Required Ads Request has cust_params: " + cust_params);

                            if(cust_params.contains("ccrpos=8004")){
                                System.out.println("Required Inline ad and ad request is displayed, with ccrpos = 8004 and hometab = playlists");
                                break;
                            }
                        } //else System.out.println("Printing Else.........Required Ads Request has cust_params: " + cust_params);


                    }
                }
            }
        }

        @Test
        void PodcastDirectoryInlineAds(){}

        @Test
       void YourLibraryInlineAds() throws TimeoutException, IOException, URISyntaxException, InterruptedException {

        services = AdsDriverSetUp.AppiumServerStart();
        AppiumDriver driver = AdsDriverSetUp.getDriver("Android", services);



        Thread.sleep(10000);
        driver.getPageSource();
        LogEntries StartLogs = driver.manage().logs().get("logcat");
        VerifyAds.VerifyInlineAdDisplayed(driver);
        for(LogEntry log : StartLogs){
            var message = log.getMessage();

            if(message.contains("Debug CONTENT")){
                if(message.contains("cust_params")) {
                    String cust_params = Utilities.getParamValue(message.substring(message.indexOf("uri") + 6),"cust_params");
                    if(cust_params.contains("hometab=yourlibrary")){
                        System.out.println("Required Ads Request has cust_params: "+  cust_params);
                    }
                    else System.out.println("Printing Else.........Required Ads Request has cust_params: "+  cust_params);




                }
            }





        }
    }


}
