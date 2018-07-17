package com.qa.sg;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GlueCode {

    WebDriver driver;

    @Before
    public void beforemethod(Scenario scenario ) {
        System.out.println("scenario name is :  " + scenario.getName());


        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", false);
        driver = new FirefoxDriver(options);


    }



    @After
    public void aftermethod(Scenario scn){

        if(scn.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scn.embed(screenshot, "image/png");
        }
        // driver.quit();
    }




    @Given("^open the url \"([^\"]*)\"$")
    public void open_the_url(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        driver.get(url);
    }

    @Then("^click on the menu text vacancies$")
    public void click_on_the_menu_text_vacancies() throws Throwable {


        driver.findElement(By.cssSelector(".site-nav--menu__link")).click();
    }

    @Then("^navigate into the open vacancied area$")
    public void navigate_into_the_open_vacancied_area() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver , 120);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".product__header"))));
        String Actual_Res = driver.findElement(By.cssSelector(".product__header")).getText();
        Assert.assertEquals("This is check for Vacacience page","Vacancies",Actual_Res);


    }
    @Then("^Verify default search results are disaplyed$")
    public void verify_default_search_results_are_disaplyed() throws Throwable {


        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@class='srJobList']"))))  ;
        int rowCount = driver.findElements(By.xpath("//table[@class='srJobList']/tbody/tr")).size();
        System.out.println("rowCount = " + rowCount);
        Assert.assertTrue( rowCount > 1);


    }


    @Then("^user enter \"([^\"]*)\" and hits Enter in keyboard$")
    public void user_enter_and_hits_Enter_in_keyboard(String keyword) throws Throwable {

        System.out.println("keyword is : " + keyword);

         driver.findElement(By.xpath("//input[@class='srSearchInput']")).sendKeys(keyword);
         driver.findElement(By.xpath("//input[@class='srSearchInput']")).sendKeys(Keys.ENTER);

    }

    @Then("^Verify search results \"([^\"]*)\"$")
    public void verify_search_results_are(String status) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@class='srJobList']"))))  ;
        int rowCount = driver.findElements(By.xpath("//table[@class='srJobList']/tbody/tr")).size();
        System.out.println("rowCount = " + rowCount);
        if(status.equalsIgnoreCase("Successful")){
            Assert.assertTrue( rowCount > 1);

        }
        else {
            Assert.assertTrue( rowCount == 1);
        }


    }






}
