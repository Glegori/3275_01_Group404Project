package com.csis3275.Group404Project;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class importCSVfileTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void importCSVfileTest() {
    driver.get("http://localhost:8080/login");
    driver.manage().window().setSize(new Dimension(741, 727));
    driver.findElement(By.id("username")).sendKeys("Alfredo");
    driver.findElement(By.id("password")).sendKeys("12345");
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.cssSelector(".sideButton:nth-child(3)")).click();
    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
    WebElement chooseFile = driver.findElement(By.id("billImage"));
    chooseFile.sendKeys("C:\\Users\\Alfredo Morales\\Desktop\\testImportExpensse.csv");
    driver.findElement(By.cssSelector(".col-md-offset-3 > button")).click();
    driver.findElement(By.cssSelector("tr:nth-child(59) > td:nth-child(1)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(59) > td:nth-child(1)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("tr:nth-child(59) > td:nth-child(1)"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(71) > td:nth-child(2)")).getText(), is("250.0"));
  }
}