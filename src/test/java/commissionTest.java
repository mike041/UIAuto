import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.webdriver.patatiumappui.action.LoginAction;
import org.webdriver.patatiumappui.action.RetryAnalyzer;
import org.webdriver.patatiumappui.pageObject.CommissionPage;
import org.webdriver.patatiumappui.pageObject.LoginPage;
import org.webdriver.patatiumappui.pageObject.ResicoLoginPage;
import org.webdriver.patatiumappui.utils.Assertion;
import org.webdriver.patatiumappui.utils.ElementAction;
import org.webdriver.patatiumappui.utils.ExcelReadUtil;
import org.webdriver.patatiumappui.utils.TestBaseCase;

import java.io.IOException;

/**
 * @author mikezhou
 *
 */
public class commissionTest extends TestBaseCase {
    ElementAction action = new ElementAction();
    ResicoLoginPage loginPage = new ResicoLoginPage();

    @BeforeClass
    public void beforeclass() throws IOException {
        webDriver.get("http://sso.test.ustax.tech/");

        action.type(loginPage.用户名(), "15902379217");
        action.type(loginPage.密码(), "resico888");
        action.click(loginPage.立即登录());
        action.sleep(2);
    }

    @Test(description = "登录测试", retryAnalyzer = RetryAnalyzer.class)
    public void checkCommissionPage() throws IOException {
        //调用登录方法(需填写正确的用户名和密码)
        CommissionPage commissionPage = new CommissionPage();
        action.click(commissionPage.园区奖励());


        action.sleep(5);
        //设置检查点
        Assertion.VerityTextPresent("通讯录", "验证是否登录成功！");
        //设置断言 。判断用例是否失败
        Assertion.VerityError();
    }


    //数据驱动案例--start
    @DataProvider(name = "longinData")
    public Object[][] loginData() {
        //读取登录用例测试数据
        String filePath = "src/main/resources/data/loginData.xls";
        //读取第一个sheet，第2行到第5行-第2到第4列之间的数据
        return ExcelReadUtil.case_data_excel(0, 1, 2, 1, 3, filePath);
    }




}
