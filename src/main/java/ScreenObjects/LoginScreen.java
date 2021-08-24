package ScreenObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginScreen extends ScreenBase{
    public LoginScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[contains(@name,'Login') or contains(@name,'Anmelden')])[26]")
    MobileElement lgnBtn01;

    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    MobileElement eMail;

    @AndroidFindBy(xpath = "//android.widget.EditText[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField")
    MobileElement psWord;

    @AndroidFindBy(xpath = "//*[@class='android.widget.TextView'][2]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText)[2]")
    MobileElement psWordLabel;


    @AndroidFindBy(xpath = "//android.view.ViewGroup[3]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='auth.login.loginButton']")
    MobileElement lgnBtn02;


    public void clickLogin01(){
        click(lgnBtn01);
    }

    public void enterEMail(String emailText){
        eMail.clear();
        sendText(eMail, emailText);
        click(psWordLabel);
    }

    public void enterPassWord(String passWordText){
        psWord.clear();
        sendText(psWord, passWordText);
        click(psWordLabel);
    }

    public void clickLogin02(){
        click(lgnBtn02);
    }
}
