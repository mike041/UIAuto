package org.webdriver.patatiumappui.pageObject;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.patatiumappui.utils.BaseAction;
import org.webdriver.patatiumappui.utils.Locator;
import org.webdriver.patatiumappui.pageObjectConfig.PageObjectAutoCodeForYaml;//员工提成页面_对象库类
public class CommissionPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/patatiumappui/pageObjectConfig/UILibrary.yaml";
 public   CommissionPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* b
* @return
* @throws IOException
*/
public  Locator 员工提成奖励() throws IOException
 {
   Locator locator=getLocator("员工提成奖励");
   return locator;
 }

/***
* tab-0
* @return
* @throws IOException
*/
public  Locator 服务费() throws IOException
 {
   Locator locator=getLocator("服务费");
   return locator;
 }

/***
* tab-1
* @return
* @throws IOException
*/
public  Locator 园区奖励() throws IOException
 {
   Locator locator=getLocator("园区奖励");
   return locator;
 }

/***
* /html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div[1]/span/button
* @return
* @throws IOException
*/
public  Locator 导出() throws IOException
 {
   Locator locator=getLocator("导出");
   return locator;
 }

/***
* /html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div[1]/span/a
* @return
* @throws IOException
*/
public  Locator 我发放的() throws IOException
 {
   Locator locator=getLocator("我发放的");
   return locator;
 }

/***
* /html/body/div[1]/div/div[2]/div[2]/div/div[2]/div/div/div/div[4]/div[1]/div[2]/table/thead/tr
* @return
* @throws IOException
*/
public  Locator 列表() throws IOException
 {
   Locator locator=getLocator("列表");
   return locator;
 }
}