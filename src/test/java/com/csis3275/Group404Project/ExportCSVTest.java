package com.csis3275.Group404Project;

import com.csis3275.Group404Project.dao.expenseDAO;
import com.csis3275.Group404Project.dao.userDAO;
import com.csis3275.Group404Project.model.Expense;
import com.csis3275.Group404Project.model.USER_404_PROJECT;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExportCSVTest {
    private ChromeDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    public expenseDAO expenseDao;
    public userDAO userDAO;
    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> settings = new HashMap<String, Object>();
        settings.put("download.prompt_for_download", false);
        settings.put("download.default_directory", "c:/tmp");
        chromeOptions.setExperimentalOption("prefs", settings);
        System.setProperty("webdriver.chrome.driver", "c:/tmp/chromedriver.exe");
        driver = new  ChromeDriver(chromeOptions);
        js = (JavascriptExecutor) driver;
        //this will allow it to wait for responses before throwing errors
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void editBudgetTest() {
        driver.get("http://localhost:8080/login");
        driver.manage().window().setSize(new Dimension(1593, 1040));
        driver.findElement(By.id("username")).sendKeys("Gregory");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("export")).click();
        try {
            byte[] csvFile = Files.readAllBytes(Paths.get("c:/tmp/data.csv"));
            byte[] comparer = comparerFileData();
            Boolean equal = Arrays.equals(comparer,csvFile);
            assertThat(equal ,is (true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public byte[] comparerFileData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        USER_404_PROJECT user = userDAO.getUserByUserName(currentPrincipalName).get(0);

        if(user.getUserType().equals("admin")) {
            List<Expense> expenses = expenseDao.getExpenseByStatus("Approved");
            File csvFile = csvHelper.getCSVFile(expenses);
            byte[] fileBytes = null;
            try {
                fileBytes = Files.readAllBytes(Paths.get(csvFile.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileBytes;
        }
        else {
            List<Expense> expenses = expenseDao.getExpensesByUserAndStatus(currentPrincipalName, "Approved");
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
}

