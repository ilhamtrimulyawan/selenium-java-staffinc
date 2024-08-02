package StepDefinitions.ui;

import PageObjects.SearchPage;
import Utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SearchSteps {
    protected WebDriver driver = Driver.getDriver();
    SearchPage SearchPage = new SearchPage(driver);
    
    @Given("user visits the Staffinc website")
    public void visit_website(){
        driver.get("https://jobs.staffinc.co/");
    }

    @When("user click the search button")
    public void click_search_button() {
        SearchPage.clickSearchButton();
    }

    @And("user search with choose Preferensi Kerja {string}")
    public void choosePreferensiKerja(String keyword) {
        SearchPage.choosePreferensiKerja(keyword);
    }

    @And("user choose Provinsi {string}")
    public void chooseProvinsi(String args) {
        SearchPage.chooseProvinsi(args);
    }

    @And("user choose Kota {string}")
    public void chooseKota(String args) {
        SearchPage.chooseKota(args);
    }

    @And("user click Simpan Preferensi Kerja")
    public void clickSimpanPreferensiKerja() {
        SearchPage.clickSimpanPreferensiKerja();
    }

    @Then("user verify that get notification Success Filter")
    public void getNotifSuccessFilter() {
        SearchPage.getNotifSuccessFilter();
    }

    @And("user click Lihat Semua Button")
    public void clickLihatSemua() {
        SearchPage.clickLihatSemua();
    }

    @Then("user verify that search result matched")
    public void validateSearchResultMatched() {
        SearchPage.validateSearchResultMatched();
        driver.close();
        driver.quit();
    }

    @Then("user verify that search result contains {string} appears")
    public void verifysearch(String keyword) throws InterruptedException {
        Thread.sleep(5000); // handle race condition
        if(SearchPage.getResultSearch().size() != 0) {
            for(WebElement result : SearchPage.getResultSearch()) {
                System.out.println(result.getAttribute("innerHTML"));
                Assert.assertTrue(result.getAttribute("innerHTML").toLowerCase().contains(keyword.toLowerCase()));
            }
        }
        driver.close();
        driver.quit();
    }

//    @Then("article contains that {string} appears")
//    public void use_logiaan(String keyword) throws InterruptedException {
//        Thread.sleep(5000); // handle race condition
//        if(SearchPage.getTitleArticle().size() != 0) {
//            for(WebElement result : SearchPage.getTitleArticle()) {
//                System.out.println(result.getAttribute("innerHTML"));
//                Assert.assertTrue(result.getAttribute("innerHTML").toLowerCase().contains(keyword.toLowerCase()));
//            }
//        }
//
//    }
}
