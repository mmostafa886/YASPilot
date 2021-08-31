import ScreenObjects.ScreenBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;


public class TestListener extends TestBase  implements ITestListener {

    ScreenBase scrnBase;

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        scrnBase = new ScreenBase(driver);
        String name = arg0.getInstanceName()+"-"+arg0.getName();
        try {
            scrnBase.takeScreenShot(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The TC: "+ arg0.getName()+" has been Failed");
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("The TC: "+ arg0.getName()+" Skipped");

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        scrnBase = new ScreenBase(driver);
        System.out.println("The TC: "+ arg0.getName()+" has been Started");

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("The TC: "+ arg0.getName()+" has been Passed");

    }
}
