package org.webdriver.patatiumappui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBaseCase {
    public static WebDriver driver;
    //方法描述
    public static String description;
    public Log log = new Log(this.getClass().getSuperclass());

    @BeforeSuite
    @Parameters({"remote", "browserName", "nodeURL", "version", "platformName"})
    public void setup(String remote, String browserName, String nodeURL, String version, String platformName) {
        log.info("------------------开始执行测试---------------");
        if (remote == "true") {
            driver = this.setRemoteDriver(browserName, nodeURL, version, platformName);
            return;
        }
        System.setProperty("webdriver.chrome.driver", "F:\\软件安装包\\chromedriver_win32_87\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // 启动时自动最大化窗口
        options.addArguments("--disable-popup-blocking"); // 禁用阻止弹出窗口
        options.addArguments("no-sandbox"); // 启动无沙盒模式运行
        options.addArguments("disable-extensions"); // 禁用扩展
        options.addArguments("no-default-browser-check"); // 默认浏览器检查
        Map<String, Object> prefs = new HashMap();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);// 禁用保存密码提示框
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() {
        this.driver.quit();
        // ElementAction action = new ElementAction();
        log.info("关闭server");
        //action.executeCmd("taskkill /f/im cmd.exe");
        log.info("-------------结束测试，并关闭退出driver及自动化 server-------------");
    }

    /**
     * @param browserName  浏览器类型
     * @param nodeURL      远程url
     * @param version      浏览器版本
     * @param platformName 平台(window、linux、mac等)
     * @return
     * @throws MalformedURLException
     */
    private WebDriver setRemoteDriver(String browserName, String nodeURL, String version, String platformName) {

        Platform platform = Platform.fromString(platformName);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        switch (browserName) {
            case "ChromeDriver":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized"); // 启动时自动最大化窗口
                options.addArguments("--disable-popup-blocking"); // 禁用阻止弹出窗口
                options.addArguments("no-sandbox"); // 启动无沙盒模式运行
                options.addArguments("disable-extensions"); // 禁用扩展
                options.addArguments("no-default-browser-check"); // 默认浏览器检查
                Map<String, Object> prefs = new HashMap();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);// 禁用保存密码提示框

                //设置自动化相关参数
                desiredCapabilities.setBrowserName(browserName);
                desiredCapabilities.setPlatform(platform);
                desiredCapabilities.setVersion(version);
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                //初始化driver
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), desiredCapabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                //设置自动化相关参数
                desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                //初始化driver
                driver = new ChromeDriver(desiredCapabilities);
                break;
        }
        return driver;
    }

    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver", "F:\\软件安装包\\chromedriver_win32_87\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://sso.test.ustax.tech/");
        System.out.println(driver.getTitle().contains("登录"));
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("15902379217");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/form/div[2]/div/div/input")).sendKeys("resico888");
        driver.findElement(By.tagName("button")).click();
    }


}
