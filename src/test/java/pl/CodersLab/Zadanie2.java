package pl.CodersLab;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie2 {
    @Test
    public void shouldFindSearchPhrase() {
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

        WebElement quantity = driver.findElement(By.id("Quantity_wanted"));
        quantity.sendKeys("5");
        quantity.sendKeys(Keys.END);
        quantity.sendKeys(Keys.BACK_SPACE);
    }
}
