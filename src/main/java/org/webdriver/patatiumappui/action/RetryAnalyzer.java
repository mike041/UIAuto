package org.webdriver.patatiumappui.action;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetry  implements IRetryAnalyzer {
    @Override
    public boolean retry(ITestResult result) {
        return false;
    }
}
