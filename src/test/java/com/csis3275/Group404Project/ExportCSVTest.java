package com.csis3275.Group404Project;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.model.Expense;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class ExportCSVTest {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Autowired
    public expenseDAO dao;
    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> settings = new HashMap<String, Object>();
        settings.put("download.prompt_for_download", false);
        settings.put("browser.helperApps.neverAsk.saveToDisk", "application/csv");
        settings.put("browser.download.folderList", 2);
        settings.put("download.default_directory", "c:\\tmp");
        chromeOptions.setExperimentalOption("prefs", settings);
        System.setProperty("webdriver.chrome.driver", "c:/tmp/chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        js = (JavascriptExecutor) driver;
        //this will allow it to wait for responses before throwing errors
        driver.manage().timeouts().implicitlyWait(750, TimeUnit.MILLISECONDS);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void editBudgetTest() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        driver.manage().window().setSize(new Dimension(1593, 1040));
        driver.findElement(By.id("username")).sendKeys("Gregory");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("export")).click();
        Thread.sleep(4000);
        //byte[] csvFile = Files.readAllBytes(Paths.get("c:/tmp/data.csv"));
        //byte[] comparer = comparerFileData();
        //byte[] comparer = Files.readAllBytes(Paths.get("c:/tmp/data.csv"));
        //Boolean equal = Arrays.equals(comparer,csvFile);
        assertThat(Files.exists(Paths.get("c:/tmp/data.csv")) ,is (true));
    }
    public byte[] comparerFileData(){
        List<Expense> expenses = dao.getExpenseByStatus("Approved");
        File csvFile = csvHelper.getCSVFile(expenses);
        byte[] fileBytes = null;
        try {
            fileBytes = Files.readAllBytes(Paths.get(csvFile.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBytes;
    }
}

