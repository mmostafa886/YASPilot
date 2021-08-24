import ScreenObjects.LoginScreen;
import ScreenObjects.ScreenBase;
import ScreenObjects.SharedScreen;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JsonValidLogin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;


public class Trials extends TestBase{

    LoginScreen lgnScrn;
    SharedScreen shrdScrn;
    ScreenBase sBase;
    IOSTouchAction actions;


    @Test
    public void login() throws IOException {
        iOS_Setup();
        lgnScrn = new LoginScreen(driver);
        shrdScrn = new SharedScreen(driver);
        sBase = new ScreenBase(driver);
        lgnScrn.clickLogin01();
        lgnScrn.enterEMail("mohamed+11sbk@yas.life");
        lgnScrn.enterPassWord("Test@12312");
        lgnScrn.clickLogin02();
        shrdScrn.waitForinVisibility(shrdScrn.progressBar);
        System.out.println((shrdScrn.getAttribute(shrdScrn.screenHeader, "label")));
        if(!(shrdScrn.getAttribute(shrdScrn.screenHeader, "label").contains("Anmelden"))) {
            sBase.takeElementScreenShot(shrdScrn.screenHeader, "iOS/Failures");
        }
        Assert.assertTrue((shrdScrn.getAttribute(shrdScrn.screenHeader, "label").contains("Anmelden")));
    }


    @Test
    public void login_android() throws IOException {
        Android_Setup();
        lgnScrn = new LoginScreen(driver);
        shrdScrn = new SharedScreen(driver);
        sBase = new ScreenBase(driver);
        lgnScrn.clickLogin01();
        lgnScrn.enterEMail("mohamed+11sbk@yas.life");
        lgnScrn.enterPassWord("Test@12312");
        lgnScrn.clickLogin02();
        shrdScrn.waitForinVisibility(shrdScrn.progressBar);
        System.out.println((shrdScrn.getAttribute(shrdScrn.screenHeader, "text")));
        if(!(shrdScrn.getAttribute(shrdScrn.screenHeader, "text").contains("Login"))) {
            sBase.takeElementScreenShot(shrdScrn.screenHeader, "Android/Failures");
        }
        Assert.assertTrue((shrdScrn.getAttribute(shrdScrn.screenHeader, "text").contains("Login")));
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult iTestResult) throws IOException {
        sBase = new ScreenBase(driver);
        // sBase.takeScreenShot("Android"); //To take screenshot whatever the test result is

        //The following Try & Catch are for taking screenshot in case of failure
        try {
            if (iTestResult.getStatus() == 2) { // 2 = failed , 3 = skipped, 1 = success
                if (driver != null) {
                    sBase.takeScreenShot("iOS/Failures");
                }
            }
        } catch (Exception e) {
        }

        tearDown();

    }
    @AfterTest
    public void quitDriver(){
        if (null != driver) {
            driver.quit();
        }
    }


}
