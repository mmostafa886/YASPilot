package ScreenObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import utils.GetTimeStamp;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Base64;

public class ScreenBase {

    public static AppiumDriver driver;
    public static final long WAIT = 8;
    GetTimeStamp gtStamp;

    public ScreenBase(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        driver = appiumDriver;
    }


    public void waitForVisibility(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT, 150);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Element is not Visible");
        }
    }


    public void waitForinVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT, 150);
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            System.out.println("The Element is not present");
        }

    }

    public void clear(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public void click(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendText(MobileElement element, String txt) {
        waitForVisibility(element);
        element.sendKeys(txt);
    }

    public String getAttribute(MobileElement element, String attr) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT, 150);
        try {

            //The next 2 lines are left for learning purposes
            //Working iOS //wait .until(ExpectedConditions.refreshed(ExpectedConditions.not(ExpectedConditions.visibilityOf(element))));
            //working Android    // wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(element, "enabled", "true")));
            return element.getAttribute(attr);
        } catch (Exception e) {
            return "The Element is not present to get its Attribute";
        }

    }

    public void takeScreenShot(String osName) throws IOException {
        gtStamp = new GetTimeStamp();
        String destPath = System.getProperty("user.dir") +
                "/ScreenShots/" + osName+"/"+ gtStamp.getCurrentTimeStamp() + ".jpg";
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String base64img1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        File destFile = new File(destPath);
        FileUtils.copyFile(file, destFile);
     /*   Reporter.log("<a href='" + destFile.getAbsolutePath() + "'>>>" + osName + "<<</a><br><br><br><br>");
        Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='600' width='300'/> </a>");
*/
        Reporter.log(" <br><img src='data:image/png;base64," + base64img1 + "' height='600' width='300'/>");
    }

    public void takeElementScreenShot(MobileElement element, String osName) throws IOException {
        gtStamp = new GetTimeStamp();
        File scrFile = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +
                "/ScreenShots/" + osName + "/Element" + gtStamp.getCurrentTimeStamp() + ".png"));

        /*    // Get entire page screenshot
    File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    BufferedImage fullImg = ImageIO.read(screenshot);

// Get the location of element on the page
    Point point = element.getLocation();

// Get width and height of the element
    int eleWidth = element.getSize().getWidth();
    int eleHeight = element.getSize().getHeight();

// Crop the entire page screenshot to get only element screenshot
    BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
            eleWidth, eleHeight);
    ImageIO.write(eleScreenshot, "png", screenshot);

// Copy the element screenshot to disk
    FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+
            "/ScreenShots/"+osName+"/Elements"+gtStamp.getCurrentTimeStamp()+".png"));*/
    }


}
