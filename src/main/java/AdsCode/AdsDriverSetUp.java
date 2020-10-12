package AdsCode;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class AdsDriverSetUp {

    public static AppiumDriverLocalService AppiumServerStart(){
        System.out.println("Into driver setup");
        AppiumDriverLocalService service;

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();

        serviceBuilder.usingAnyFreePort();
        // /usr/local/bin/node
        serviceBuilder.usingDriverExecutable(new File("/usr/local/bin/node"));
        // /usr/local/bin/appium
        serviceBuilder.withAppiumJS(new File("/usr/local/bin/appium"));
        serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
        serviceBuilder.withArgument(GeneralServerFlag.SHELL);
        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));


        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
        return service;
    }

    public static void AppiumServerStop(){


    }

    public static AppiumDriver getDriver(String platformtype, AppiumDriverLocalService service) throws IOException, TimeoutException, URISyntaxException {
        AppiumDriver driver;
       /* List<InterceptedMessage> messages = new ArrayList<InterceptedMessage>();


        MitmproxyJava proxy = new MitmproxyJava("/usr/local/bin/mitmdump", (InterceptedMessage m) -> {
            System.out.println("intercepted request for " + m.requestURL.toString());
            messages.add(m);
            return m;
        });
        //System.out.println("Hello World");
        proxy.start();*/
        if (platformtype== "Android") {
            DesiredCapabilities options = new DesiredCapabilities();
            options.setCapability("deviceName", "Androidtest");  //Pixel_XL_API_29
            options.setCapability("udid","988ed8345945415949");
            //options.setCapability("avd","Pixel_XL_API_29");
            options.setCapability("platformName", "Android");
            options.setCapability("platformVersion", "9");  //emulator: 10.0.0
            options.setCapability("automationName", "UiAutomator2");
            options.setCapability("autoGrantPermissions","true");
            options.setCapability("noReset","true");
            options.setCapability("autoAcceptAlerts","true");

            //options.setCapability("appWaitPackage", "com.clearchannel.iheartradio.controller.debug");
            //options.setCapability("appWaitActivity", "com.clearchannel.iheartradio.controller.activities.NavDrawerActivity");
            //options.setCapability("appPackage", "com.clearchannel.iheartradio.controller.debug");
            //options.setCapability("appActivity", "com.clearchannel.iheartradio.controller.activities.NavDrawerActivity");
            options.setCapability("app","/Users/Shruti/Desktop/untitled folder/iHRAndroidBuild/iHeartRadio-google-mobile-ampprod-debug.apk");
            options.setCapability("uiautomator2ServerInstallTimeout", "60000");
            options.setCapability("abdExecTimeout", "60000");
            //options.setCapability("--proxy-server","localhost:8080");
            options.setCapability("ACCEPT_SSL_CERTS",true);
            options.setCapability("clearDeviceLogsOnStart","true");
            //options.setCapability("--set ssl_version_client","all");


            driver = new AndroidDriver<MobileElement>(service, options);
            return driver;
        }
        else if(platformtype== "iOS"){
            DesiredCapabilities options = new DesiredCapabilities();

            options.setCapability("platformName", "iOS");
            options.setCapability("platformVersion", "12.1.2");
            options.setCapability("deviceName", "shruti's iPhone");
            options.setCapability("udid","7c5a957c61aecfa86b681d737bbeba0b26512cf1");
            //options.setCapability(CapabilityType.BROWSER_NAME,"safari");
            //options.setCapability("automationName", "XCUITest");
            options.setCapability("bundleId","com.clearchannel.iheartradio.enterprise");
            options.setCapability("startIWDP","true");
            options.setCapability("noReset","true");
            options.setCapability("useNewWDA","false");
            //options.setCapability("showIOSLog","true");
            //options.setCapability("app","/Users/Shruti/Desktop/untitled folder/iHRiOSAppCenter/iHeartRadio.ipa");  //  /Users/Shruti/Downloads/iHeartRadio.ipa
            options.setCapability("xcodeOrgId","BCN32U332R");
            options.setCapability("xcodeSigningId","iPhone Developer");
            options.setCapability("autoAcceptAlerts","true");
            options.setCapability("SHOW_XCODE_LOG","true");
            //options.setCapability("updatedWDABundleId","com.Shruti7505.WebDriverAgentRunner");
            driver = new IOSDriver<MobileElement>(service,options);
            return driver;
        }
        return driver=null;

    }
}
