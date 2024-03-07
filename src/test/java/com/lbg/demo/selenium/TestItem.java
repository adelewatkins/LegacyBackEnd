package com.lbg.demo.selenium;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:shop-schema.sql",
		"classpath:shop-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TestItem {

	private RemoteWebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();

		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	@Order(1)
	void testGetItems() {
		this.driver.get("http://localhost:3000/Items");

		WebElement itemAppears = this.driver.findElement(By.cssSelector(null));
		Assertions.assertEquals("Poca Hontas", itemAppears.getText());
	}

	@Test
	@Order(2)
	void testCreateItem() {
		this.driver.get("http://localhost:3000/item");
		String name = "Banana";
		String type = "Fruits";
		String price = "5.00";

		WebElement nameNew = this.driver.findElement(By.id("nm"));
		nameNew.sendKeys(name);

		WebElement priceNew = this.driver.findElement(By.id("pr"));
		priceNew.sendKeys("5.00");

		WebElement typeNew = this.driver.findElement(By.id("ty"));
		typeNew.sendKeys(type);

		WebElement submitItem = this.driver.findElement(By.id("submitItem"));
		submitItem.submit();

		WebElement addItem = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > div > p"));
		addItem.click();
		WebElement createdItem = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > div > p > img"));

		WebElement createdItemPrice = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div:nth-child(1) > div > p"));

		WebElement createdItemType = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > div > p > img"));

		Assertions.assertEquals((name + " " + name), createdItem.getText());
		Assertions.assertEquals((price), createdItemPrice.getText());
		Assertions.assertEquals((type), createdItemType.getText());

	}

	@AfterEach
	public void tearDown() {
	}

}
