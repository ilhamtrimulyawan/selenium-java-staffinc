package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class SearchPage {
    WebDriver driver;

    By search_button = By.xpath("//*[text()='Mau kerja dimana?']");
    By search_field = By.id("search-input--desktop");
    By provinsi_text = By.cssSelector(".ant-select.ant-select-lg.ant-select-borderless.ant-select-in-form-item." +
            "css-acm2ia.ant-select-multiple.ant-select-disabled.ant-select-show-search");
    By kota_text = By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) " +
            "> div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > " +
            "div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)");
    //By provinsi_text = By.xpath("//*[text()='Cari Provinsi']");
    //By kota_text = By.xpath("//*[text()='Cari Kota']");
    By simpan_Button = By.cssSelector("body > div:nth-child(1) > div:nth-child(1) >" +
            " div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > " +
            "div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > " +
            "div:nth-child(1) > div:nth-child(1) > button:nth-child(1) > " +
            "div:nth-child(1)");
    By search_provinsi_placeholder = By.xpath("//*[@placeholder='Cari provinsi...']");
    By search_kota_placeholder = By.xpath("//*[@placeholder='Cari kota...']");
    By simpan_setPreferensiKerja_Button = By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > " +
            "div:nth-child(1) > div:nth-child(1) > div:nth-child(3) >" +
            " div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > " +
            "div:nth-child(1) > button:nth-child(1) > div:nth-child(1)");
    By notifsuksespreferensif = By.xpath("//*[text()='Preferensi Diterapkan']");
    By lihatSemuaButton = By.xpath("//div[@class='ant-space-item']" +
            "//div[@class='ant-space-item']//span[@class='ant-typography css-acm2ia']" +
            "[normalize-space()='Lihat Semua']");
    By listLocationAfterFilterText = By.xpath("//*[@data-testid='testid-molecule-job-card-location-icon']/parent::*/following-sibling::*/span");


    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickSearchButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(search_button));
        element.click();
    }

    public void choosePreferensiKerja(String preferensikerja) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String preferensiKerjaTemp = String.format("//*[text()='%s']", preferensikerja);
        By preferensiKerjaXpath = By.xpath(preferensiKerjaTemp);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(preferensiKerjaXpath));
        element.click();
    }

    public void chooseProvinsi(String provinsi) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(provinsi_text));
        driver.findElement(provinsi_text).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(search_provinsi_placeholder).isEnabled();
        driver.findElement(search_provinsi_placeholder).sendKeys(provinsi);
        String provinsiTemp = String.format("//*[text()='%s' and @class = 'ant-typography css-acm2ia']", provinsi);
        By provinsiXpath = By.xpath(provinsiTemp);
        driver.findElement(provinsiXpath).click();
    }

    public void chooseKota(String kota) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(kota_text));
        driver.findElement(kota_text).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(search_kota_placeholder).isEnabled();
        driver.findElement(search_kota_placeholder).sendKeys(kota);
        String resultKotaTemp = String.format("//*[contains(text(), '%s')]/parent::*/preceding-sibling::*/input",kota);
        By resultKotaXpath = By.xpath(resultKotaTemp);
        List<WebElement> checkboxes = driver.findElements(resultKotaXpath);

        // Iterate through the checkboxes and select them if not already selected
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        driver.findElement(simpan_Button).click();
    }

    public void clickSimpanPreferensiKerja() {
        driver.findElement(simpan_setPreferensiKerja_Button).click();
    }

    public void getNotifSuccessFilter(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(notifsuksespreferensif));
        assertTrue(element.isDisplayed(), "Element is not visible");
    }

    public void clickLihatSemua(){
        driver.findElement(lihatSemuaButton).click();
    }

    public void validateSearchResultMatched() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(listLocationAfterFilterText));
        // Find all elements with the specific locator (modify the locator as needed)
        List<WebElement> locationElements = driver.findElements(listLocationAfterFilterText); // replace with actual locator

        // Create a list to store the location names
        List<String> locations = new ArrayList<>();

        // Iterate through the elements and add the text to the list
        for (WebElement element : locationElements) {
            locations.add(element.getText());
        }

        // Print out the locations
        for (String location : locations) {
            System.out.println(location);
        }
    }

    public List<WebElement> getResultSearch(){
        List<WebElement> searchResult = driver.findElements(listLocationAfterFilterText);
        return searchResult;
    }
}
