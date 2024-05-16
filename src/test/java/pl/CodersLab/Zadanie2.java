package pl.CodersLab;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class Zadanie2 {
    @Test
    public void shouldFindSearchPhrase() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://mystore-testlab.coderslab.pl.");
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.id("field-email")).sendKeys("annaannakow.1985@gmail.com");
        driver.findElement(By.id("field-password")).sendKeys("Malinowysok");
        driver.findElement(By.id("submit-login")).click();

        WebElement searchInput = driver.findElement(By.className("ui-autocomplete-input"));
        String searchPhrase = "Hummingbird Printed Sweater";
        searchInput.sendKeys(searchPhrase + Keys.ENTER);

        driver.findElement(By.className("thumbnail-top")).click();

        WebElement sizeInput = driver.findElement(By.id("group_1"));
        String sizePhrase = "M";
        sizeInput.sendKeys(sizePhrase + Keys.ENTER);

        WebElement quantity = driver.findElement(By.id("quantity_wanted"));
        quantity.sendKeys("5");
        quantity.sendKeys(Keys.END);
        quantity.sendKeys(Keys.BACK_SPACE);

        driver.findElement(By.cssSelector(".btn.btn-primary.add-to-cart")).click();

        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=cart&action=show");
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=order");
        driver.findElement(By.name("confirm-addresses")).click();
        driver.findElement(By.name("confirmDeliveryOption")).click();
        driver.findElement(By.name("payment-option")).click();
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.cssSelector(".btn.btn-primary.center-block")).click();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        Path path = Paths.get("screenshot.png");
        Files.copy(scrFile.toPath(), path);
    }
}
