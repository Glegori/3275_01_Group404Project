package com.csis3275.Group404Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
public class EditBudgetTest {
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
  public void editBudgetTest() {
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
      dropdown.findElement(By.xpath("//option[. = 'TRAVEL']")).click();
    }
    driver.findElement(By.id("type")).click();
    driver.findElement(By.id("Expense")).click();
    driver.findElement(By.id("Expense")).click();
    driver.findElement(By.id("save")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
    driver.findElement(By.cssSelector(".sideButton:nth-child(3)")).click();
    driver.findElement(By.linkText("Add An Expense")).click();
    driver.findElement(By.id("expenseName")).click();
    driver.findElement(By.id("expenseName")).sendKeys("Hawaii");
    driver.findElement(By.id("expenseCost")).sendKeys("1500");
    driver.findElement(By.id("date")).sendKeys("2020-12-03");
    driver.findElement(By.id("expenseType")).click();
    {
      WebElement dropdown = driver.findElement(By.id("expenseType"));
      dropdown.findElement(By.xpath("//option[. = 'Travel']")).click();
    }
    driver.findElement(By.id("expenseType")).click();
    driver.findElement(By.cssSelector(".col-md-offset-3 > button")).click();
    assertThat(driver.findElement(By.xpath("//tr[13]/td[5]")).getText(), is("Denied"));
  }
}

