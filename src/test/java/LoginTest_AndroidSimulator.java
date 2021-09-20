import ScreenObjects.LoginScreen;
import ScreenObjects.ScreenBase;
import ScreenObjects.SharedScreen;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.JsonValidLogin;

import java.io.IOException;


public class LoginTest_AndroidSimulator extends TestBase{

    LoginScreen lgnScrn;
    SharedScreen shrdScrn;
    ScreenBase sBase;


    @BeforeTest
    public void driverSetup() throws IOException {
        AndroidEmulator_setup();
    }

    @DataProvider(name = "LoginData")
    public Object[][] passData() throws Exception {
        return JsonValidLogin.getJsonData
                (System.getProperty("user.dir")+"/data/ValidLoginData.json",
                        "Login Data",3);
    }

    @Test(dataProvider = "LoginData")
    public void login(String eMail, String pWord, String hText) {
        lgnScrn = new LoginScreen(driver);
        shrdScrn = new SharedScreen(driver);
        sBase = new ScreenBase(driver);
        lgnScrn.clickLogin01();
        lgnScrn.enterEMail(eMail);
        lgnScrn.enterPassWord(pWord);
        lgnScrn.clickLogin02();
        shrdScrn.waitForinVisibility(shrdScrn.progressBar);

          /* //Take a screenshot for the screen header in case the retrieved text is not as expected
        if(!(shrdScrn.getAttribute(shrdScrn.screenHeader, "text").contains(hText))) {
            sBase.takeElementScreenShot(shrdScrn.screenHeader, "Android/Failures");
        }*/
        Assert.assertTrue((shrdScrn.getAttribute(shrdScrn.screenHeader, "text").contains(hText)));
    }

   // @AfterMethod
    //To be removed in the future after adding practical TCs if we don't need it
    public void takeScreenShotOnFailure(ITestResult iTestResult) throws Exception {
       sBase = new ScreenBase(driver);
       // sBase.takeScreenShot("Android"); //To take screenshot whatever the test result is

        //The following Try & Catch are for taking screenshot in case of failure
        try {
            if (iTestResult.getStatus() == 2) { // 2 = failed , 3 = skipped, 1 = success
                if (driver != null) {
                    sBase.takeScreenShot("Android/Failures");
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
