package pl.CodersLab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Objects;

public class Zadanie1 {

}

    @Given("user is logged in and is on the page with the form")
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
            driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    WebElement loginField = driver.findElement(By.id("field-email"));
    WebElement passwordField = driver.findElement(By.id("field-password"));
            loginField.sendKeys("annaannakow.1985@gmail.com");
            passwordField.sendKeys("Malinowysok");
            driver.findElement(By.id("submit-login")).click();
            driver.findElement(By.id("addresses-link")).click();
            driver.findElement(By.cssSelector(".addresses-footer a")).click();

}
@When("user fills out a form with data {string} {string} {string} {string} {string} {string} {string} {string}")
public void user_fills_form(String alias, String company, String vat,
                            String address, String addressComplement, String city,
                            String zip, String phone) {
    WebDriver driver = new ChromeDriver();

    driver.findElement(By.id("field-alias")).sendKeys(alias);
    driver.findElement(By.id("field-company")).sendKeys(company);
    if (vat.length() == 9 && vat.matches("\\d+")) {
        driver.findElement(By.id("field-vat_number")).sendKeys(vat);
    }
    else
    {
        System.out.println("VAT number is not valid");
        System.exit(0);
    }
    driver.findElement(By.id("field-address1")).sendKeys(address);
    driver.findElement(By.id("field-address2")).sendKeys(addressComplement);
    driver.findElement(By.id("field-city")).sendKeys(city);
    driver.findElement(By.id("field-postcode")).sendKeys(zip);

    {
        System.out.println("ERROR: Check your address");
        System.exit(0);
    }
    if (phone.length() <= 15 && phone.matches("\\d+")) {
        driver.findElement(By.id("field-phone")).sendKeys(phone);
    }
    else
    {
        System.out.println("Phone number is not valid");
        System.exit(0);
    }
}


@And("user confirms form")
public void user_confirms_form() {
    WebDriver driver = new ChromeDriver();
    Duration timeout = Duration.ofSeconds(10);
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    driver.findElement(By.cssSelector(".btn.btn-primary.form-control-submit.float-xs-right")).click();
    driver.get("https://mystore-testlab.coderslab.pl/index.php?id_product=22&id_product_attribute=51&rewrite=brown-bear-printed-sweater&controller=product#/1-size-s");
    driver.findElement(By.cssSelector(".btn.btn-primary.add-to-cart")).click();
    WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("row")));
    addToCartButton.click();
    driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=cart&action=show");
    driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=order");
    driver.findElement(By.cssSelector("a.delete-address")).click();
    boolean doesElementExist = !driver.findElements(By.cssSelector(".alert.alert-success")).isEmpty();
    if (doesElementExist) {
        System.out.println("Address is successfully deleted");
    }
    else {
        System.out.println("Address is not deleted");
    }
}