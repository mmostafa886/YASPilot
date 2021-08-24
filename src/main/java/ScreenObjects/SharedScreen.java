package ScreenObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SharedScreen extends ScreenBase{
    public SharedScreen(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    //@AndroidFindBy(xpath = "//*/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.View")
    @AndroidFindBy(xpath = "(//android.view.View)[1]")
    @iOSXCUITFindBy(xpath = "//*[@x = '0' and @width = '414' and @height = '44']")
    public MobileElement screenHeader;


    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'DashboardStack')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'Dashboard')]")
    MobileElement dashBoardTab;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'RewardStack')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'Reward')]")
    MobileElement rewardsTab;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'ChallengeStack')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'Challenge')]")
    MobileElement challengesTab;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'TeamCompetitionStack')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'TeamComp')]")
    MobileElement teamCompTab;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'SettingsStack')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'Settings')]")
    MobileElement settingsTab;

    @AndroidFindBy(xpath = "//android.widget.ProgressBar")
    public MobileElement progressBar;


}
