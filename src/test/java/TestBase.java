import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    public static AppiumDriver driver;


    public static void Android_Setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("deviceName", "A12");
        caps.setCapability("appPackage", "com.sbk.yas.app.stage");
        caps.setCapability("appActivity", "com.yaslife.app.MainActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    public static void iOS_Setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("platformVersion", "14.7.1");
        caps.setCapability("deviceName", "Mostafa’s iPhone");
        caps.setCapability("udid", "1a40c3f273c7a6f42727fb8ca5fc06a3a624fbc2");
        caps.setCapability("xcodeOrgId", "mmostafa886@gmail.com");
        caps.setCapability("xcodeSigningId", "Apple Development");
        caps.setCapability("derivedDataPath", "/Users/yaslife/Library/Developer/Xcode/DerivedData/WebDriverAgent-ciegwgvxzxdrqthilmrmczmqvrgu");
        // caps.setCapability("useNewWDA" , true);
        caps.setCapability("useNewWDA", false);

        caps.setCapability("bundleId", "com.sbk.yas.app.test");
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    public void tearDown() {
        if (null != driver) {
            driver.resetApp();
            driver.quit();
        }

    }
}
