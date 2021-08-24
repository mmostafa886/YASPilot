package ScreenObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DashboardScreen extends ScreenBase{
    public DashboardScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    @AndroidFindBy(xpath = "//android.widget.TextView")
    MobileElement lgnBtn01;

}
