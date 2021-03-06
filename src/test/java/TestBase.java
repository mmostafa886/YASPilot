import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.JACProcessor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestBase{

    public static AppiumDriver driver;
    public static String appCenterDownloadURL;
    public static AppiumDriverLocalService service;


    public String getDownloadURL(String urlCall) throws IOException {
        // Create a neat value object to hold the URL
        URL url = new URL(urlCall);
        // Open a connection(?) on the URL(?) and cast the response(??)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-API-Token", "cd83fe1ee71031be591aca48f1808fa45ea4f0a8");
        //This the API Token for a user privileged to download apps from the app center, must have the download privilege only
        // This line makes the request
        InputStream responseStream = connection.getInputStream();
        // Manually converting the response body InputStream to APOD using Jackson
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JACProcessor parcedJson = mapper.readValue(responseStream, JACProcessor.class);
        // Finally we have the response
        System.out.println(parcedJson.download_url);
        //Return the download_url from the app center api response which can be used as a desired capability to download the app & start the driver
        return parcedJson.download_url;
    }


    public void AndroidEmulator_setup() throws IOException {
      appCenterDownloadURL = getDownloadURL("https://api.appcenter.ms/v0.1/sdk/apps/a5e5ecd6-cb6f-4a06-9ab2-3c29a1edfe9b/releases/private/latest");
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
/*        builder.withIPAddress("0.0.0.0");
        builder.usingPort(4723);
        builder.withEnvironment(System.getenv());*/
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        service = AppiumDriverLocalService.buildService(builder
                .withAppiumJS(new File("/home/yaslife/.nvm/versions/node/v16.11.1/lib/node_modules/appium/build/lib/main.js"))
                    .usingDriverExecutable(new File("/home/yaslife/.nvm/versions/node/v16.11.1/bin/node")));
        service.start();
        //////////////

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("deviceName", "Android11");
        caps.setCapability("isHeadless", true);
       caps.setCapability("app" , appCenterDownloadURL);
/*        caps.setCapability("appWaitDuration" , 100000);
        caps.setCapability("deviceReadyTimeout" , 10000);
        caps.setCapability("androidDeviceReadyTimeout" , 10000);
        caps.setCapability("androidInstallTimeout" , 100000);
        caps.setCapability("avdLaunchTimeout" , 200000);
        caps.setCapability("avdReadyTimeout" , 200000);
        caps.setCapability("androidInstallTimeout" , 1000000);
        caps.setCapability("adbExecTimeout" , 1000000);*/
        //caps.setCapability("uiautomator2ServerInstallTimeout" , 1000000);
       caps.setCapability("app" , System.getProperty("user.dir")+"/apps/app-sbk-releaseStaging.apk");
       driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),caps);
    }

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
        caps.setCapability("deviceName", "Mostafa???s iPhone");
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
            driver.quit();
        }

    }
}
