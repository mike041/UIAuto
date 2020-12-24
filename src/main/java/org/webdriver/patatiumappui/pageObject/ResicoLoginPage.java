package org.webdriver.patatiumappui.pageObject;

import java.io.IOException;

import org.webdriver.patatiumappui.utils.BaseAction;
import org.webdriver.patatiumappui.utils.Locator;

public class ResicoLoginPage extends BaseAction {
    //用于eclipse工程内运行查找对象库文件路径
    private String path = "src/main/java/org/webdriver/patatiumappui/pageObjectConfig/UILibrary.yaml";

    public ResicoLoginPage() {
//工程内读取对象库文件
        setXmlObjectPath(path);
        setLocatorMap();
    }

    /***
     * //*[@id="app"]/div/div/div[2]/div/form/div[1]/div/div/input
     * @return
     * @throws IOException
     */
    public Locator 用户名() throws IOException {
        Locator locator = getLocator("用户名");
        return locator;
    }

    /***
     * //*[@id="app"]/div/div/div[2]/div/form/div[2]/div/div/input
     * @return
     * @throws IOException
     */
    public Locator 密码() throws IOException {
        Locator locator = getLocator("密码");
        return locator;
    }

    /***
     * forget
     * @return
     * @throws IOException
     */
    public Locator 忘记密码() throws IOException {
        Locator locator = getLocator("忘记密码");
        return locator;
    }

    /***
     * button
     * @return
     * @throws IOException
     */
    public Locator 立即登录() throws IOException {
        Locator locator = getLocator("立即登录");
        return locator;
    }

    /***
     * //*[@id="app"]/div/div/div[2]/div/div[1]/div[3]/i
     * @return
     * @throws IOException
     */
    public Locator 二维码() throws IOException {
        Locator locator = getLocator("二维码");
        return locator;
    }

    /***
     * el-form-item__error
     * @return
     * @throws IOException
     */
    public Locator 失败提示信息() throws IOException {
        Locator locator = getLocator("失败提示信息");
        return locator;
    }
}