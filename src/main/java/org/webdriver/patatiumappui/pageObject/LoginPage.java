package org.webdriver.patatiumappui.pageObject;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.patatiumappui.utils.BaseAction;
import org.webdriver.patatiumappui.utils.Locator;
import org.webdriver.patatiumappui.pageObjectConfig.PageObjectAutoCodeForYaml;//微信App登录页面_对象库类
public class LoginPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/patatiumappui/pageObjectConfig/UILibrary.yaml";
 public   LoginPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
setLocatorMap();
}
/***
* com.tencent.mm:id/b9i
* @return
* @throws IOException
*/
public  Locator 使用其他方式登录() throws IOException
 {
   Locator locator=getLocator("使用其他方式登录");
   return locator;
 }

/***
* //android.widget.EditText[@text='QQ号/微信号/Email']
* @return
* @throws IOException
*/
public  Locator 账号输入框() throws IOException
 {
   Locator locator=getLocator("账号输入框");
   return locator;
 }

/***
* //android.widget.EditText[@NAF='1']
* @return
* @throws IOException
*/
public  Locator 密码输入框() throws IOException
 {
   Locator locator=getLocator("密码输入框");
   return locator;
 }

/***
* com.tencent.mm:id/b8z
* @return
* @throws IOException
*/
public  Locator 登录按钮() throws IOException
 {
   Locator locator=getLocator("登录按钮");
   return locator;
 }

/***
* com.tencent.mm:id/bl3
* @return
* @throws IOException
*/
public  Locator 登录失败提示信息() throws IOException
 {
   Locator locator=getLocator("登录失败提示信息");
   return locator;
 }

/***
* com.tencent.mm:id/a_r
* @return
* @throws IOException
*/
public  Locator 登录失败确认按钮() throws IOException
 {
   Locator locator=getLocator("登录失败确认按钮");
   return locator;
 }
}