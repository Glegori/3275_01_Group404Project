package com.csis3275.Group404Project;
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
public class ReviewBudgetTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	  System.setProperty("webdriver.chrome.driver", "c:/tmp/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void reviewBudgetTest() {
    driver.get("http://localhost:8080/login");
    driver.manage().window().setSize(new Dimension(1593, 1040));
    driver.findElement(By.id("username")).sendKeys("Francis");
    driver.findElement(By.id("password")).sendKeys("12345");
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector(".sideButton:nth-child(3)")).click();
    driver.findElement(By.linkText("Review Budget")).click();
    driver.findElement(By.id("type")).click();
    {
      WebElement dropdown = driver.findElement(By.id("type"));
      dropdown.findElement(By.xpath("//option[. = 'FOOD']")).click();
    }
    driver.findElement(By.id("type")).click();
    driver.findElement(By.id("Expense")).click();
    driver.findElement(By.id("budget")).sendKeys("1000");
    driver.findElement(By.id("Expense")).click();
    driver.findElement(By.id("save")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
    driver.findElement(By.cssSelector(".sideButton:nth-child(3)")).click();
    driver.findElement(By.linkText("Add An Expense")).click();
    driver.findElement(By.id("expenseName")).click();
    driver.findElement(By.id("expenseName")).sendKeys("Burger King");
    driver.findElement(By.id("expenseCost")).sendKeys("1500");
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).sendKeys("2020-12-03");
    driver.findElement(By.id("expenseType")).click();
    {
      WebElement dropdown = driver.findElement(By.id("expenseType"));
      dropdown.findElement(By.xpath("//option[. = 'Food']")).click();
    }
    driver.findElement(By.id("expenseType")).click();
    driver.findElement(By.cssSelector(".col-md-offset-3 > button")).click();
    assertThat(driver.findElement(By.xpath("(//tr)[last()]/td[5]")).getText(), is("Denied"));
  }
}

