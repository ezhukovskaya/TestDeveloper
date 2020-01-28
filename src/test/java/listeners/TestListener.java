package listeners;

import framework.enums.TestResult;
import framework.utils.TestRailUtils;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger log = Logger.getLogger(TestListener.class);
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        TestRailUtils.addResult(iTestResult, TestResult.SUCCESS.getValue(), String.format("Test %s ", TestResult.SUCCESS));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        TestRailUtils.addResult(iTestResult, TestResult.FAILURE.getValue(), String.format("Test %s ", TestResult.FAILURE));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        TestRailUtils.addResult(iTestResult, TestResult.SKIPPED.getValue(), String.format("Test %s ", TestResult.SKIPPED));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("I am in onStart method " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("I am in onFinish method " + iTestContext.getName());
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
