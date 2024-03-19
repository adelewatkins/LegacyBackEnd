package com.lbg.demo.selenium;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)

@Sql(scripts = { "classpath:shopSchema.sql",
		"classpath:shopData.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class TestItem {

	private RemoteWebDriver driver;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test
	@Order(1)
	void testCreateItem() throws InterruptedException {
		this.driver.get("http://localhost:3000/items");

		String itemName = "Apple";
		String itemPrice = "0.99";

		WebElement name = this.driver.findElement(By.cssSelector("#name"));
		name.sendKeys(itemName);

		WebElement price = this.driver.findElement(By.cssSelector("#price"));
		price.sendKeys(itemPrice);

		WebElement addItem = this.driver.findElement(By.cssSelector("#root > div > div > form > button"));
		addItem.click();

		WebElement createdItem = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > div > div"));

		Assertions.assertEquals((itemName), createdItem.getText());

	}

	@Test
	@Order(2)
	void testAddItemBasket() {
		this.driver.get("http://localhost:3000/items");

		String itemName = "Banana";
		String itemPrice = "1.99";

		WebElement name = this.driver.findElement(By.cssSelector("#name"));
		name.sendKeys(itemName);

		WebElement price = this.driver.findElement(By.cssSelector("#price"));
		price.sendKeys(itemPrice);

		WebElement addItem = this.driver.findElement(By.cssSelector("#root > div > div > form > button"));
		addItem.click();

		WebElement addToCart = this.driver.findElement(
				By.cssSelector("#root > div > div > div > div > div > div > div > div > button.btn.btn-success"));
		addToCart.click();

		this.driver.get("http://localhost:3000/cart/1");

		WebElement addedItem = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div.card-title.h5"));
		Assertions.assertEquals((itemName), addedItem.getText());

	}
}
