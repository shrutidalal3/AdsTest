package AdsCode;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class iHRTLocatorsAds {





        protected final AppiumDriver driver;

        public iHRTLocatorsAds(AppiumDriver driver){
            this.driver =driver;
            PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);

        }

        public static String loginBtn = "com.clearchannel.iheartradio.controller.debug:id/login_button";
        public static String emailField ="com.clearchannel.iheartradio.controller.debug:id/email";
        public static String passwordField ="com.clearchannel.iheartradio.controller.debug:id/password";
        public static String emailLogin = "com.clearchannel.iheartradio.controller.debug:id/email_login";
        public static String homeppageSettings_accessibilityiD =  "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";  //"Navigate up";
        public static String testersOptions = "com.clearchannel.iheartradio.controller.debug:id/tester_options_item";
        public static String adsGrace = "com.clearchannel.iheartradio.controller.debug:id/label_ad_grace_period";
        public static String adsGraceDisable = "//android.widget.RadioGroup[@resource-id=\"com.clearchannel.iheartradio.controller.debug:id/ad_grace_period_radio_group\"]/android.widget.RadioButton[2]\n";
        public static String inlineAds_xp = "//*[contains(@resource-id,\"inner_ad_container\")]//*[@resource-id=\"aw0\"]"; // //*[@resource-id=\"com.clearchannel.iheartradio.controller.debug:id/inner_ad_container\"]//android.webkit.WebView";  // //*[@resource-id="com.clearchannel.iheartradio.controller.debug:id/inner_ad_container"]//android.webkit.WebView   //*[@resource-id="aw0"]
        public static String myAccountSettings = "com.clearchannel.iheartradio.controller.debug:id/signup_or_login_item";
        public static String useZipcode = "com.clearchannel.iheartradio.controller.debug:id/label_use_zip_code";
        public static String enterZipcode = "com.clearchannel.iheartradio.controller.debug:id/dialog_prompt_edit_text";
        public static String saveZipcode = "android:id/button1";


        //region BottomMenu
        public static String RadioBtn = "com.clearchannel.iheartradio.controller.debug:id/menu_radio"; //menu_playlists
        public static String YourLibrary = "com.clearchannel.iheartradio.controller.debug:id/menu_library";  //accessibility_id = "Your Library"
        public static String transistionads = "com.clearchannel.iheartradio.controller.debug:id/home_ad_view_holder";
        public static String TransAdsClose = "com.clearchannel.iheartradio.controller.debug:id/home_ad_close_button";
        public static String playerAds_xp = "//*[@resource-id=\"com.clearchannel.iheartradio.controller.debug:id/player_ad_container\"]//*[@resource-id=\"aw0\"]";
        public static String playerAdsClose = "com.clearchannel.iheartradio.controller.debug:id/player_ad_close_button";
        public static String radioSelect = "com.clearchannel.iheartradio.controller.debug:id/list_item_tile_with_text";

        //endregion

        //region AppAlerts
        public static String findStationsnearYou_id = "com.clearchannel.iheartradio.controller.debug:id/alertTitle";  //text ="Find stations near you"
        public static String findStationOkayBtn_id = "android:id/button1";
        public static String intialpopup_id = "message";
        public static String intialPopupDismiss =  "dismissed";



        //endregion

        //void initElements(){
        //PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
        //}

        @AndroidFindBy(id="com.clearchannel.iheartradio.controller.debug:id/login_button")
        @iOSXCUITFindBy(accessibility="AnimationOnboardingViewController-LoginButton-UIButton")
        public static WebElement Login1;

        //accessibility = "tab_radio"
        @iOSXCUITFindBy(iOSNsPredicate= "label == 'Radio'")
        public  WebElement tabRadio;

        //public static String selector = "type =='XCUIElementTypeImage' AND visible== 1";
        //@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView[@name=\"LiveBrowseView-CollectionView-UICollectionView\"]/XCUIElementTypeOther[2]")
        // @iOSXCUITFindBy( iOSNsPredicate= "type =='XCUIElementTypeImage' AND name =='Advertisement' AND visible == 1")
        // @HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.CHAIN)
        @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeLink' AND visible == 1")
        WebElement inlineads ;

        @iOSXCUITFindBy(accessibility = "CUIImageTitleSubtitle2TileCell-0:0")
        public WebElement liveStation1;

        @iOSXCUITFindBy(accessibility = "LiveStationProfileViewController-CollectionView-UICollectionView")
        public WebElement livestationView;



        @AndroidFindBy(id="com.clearchannel.iheartradio.controller.debug:id/player_ad_close_button")
        public WebElement fullPlayerDismiss;

        @AndroidFindBy(id="com.clearchannel.iheartradio.controller.debug:id/player_ad_view_holder")  //@AndroidFindBy(id="com.clearchannel.iheartradio.controller.debug:id/aw0")
        public WebElement fullPlayerTriggerAd;


        @AndroidFindBy(id="com.clearchannel.iheartradio.controller.debug:id/action_search")
        public WebElement searchAction;

        @AndroidFindBy(id="com.clearchannel.iheartradio.controller.debug:id/searchInputEditText")
        public WebElement searchTextBox;

        @AndroidFindBy(id="com.clearchannel.iheartradio.controller.debug:id/list_item_tile_with_text") //(id="com.clearchannel.iheartradio.controller.debug:id/station_logo")
        public WebElement liveStation;

        public  WebElement gettabRadio(){
            return tabRadio;
        }

        @AndroidFindBy(id = "com.clearchannel.iheartradio.controller.debug:id/menu_playlists")
        private AndroidElement playlistTab;

        public  boolean isDisplayed(){
                return playlistTab.isDisplayed();
        }

        public  void clickPlaylistatb(){
                playlistTab.click();
        }

        // @iOSXCUITFindBy(accessibility="AnimationOnboardingViewController-LoginButton-UIButton")
        //public static WebElement ioSLogin;

    }


