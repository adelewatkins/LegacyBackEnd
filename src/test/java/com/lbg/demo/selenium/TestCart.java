package com.lbg.demo.selenium;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:shop-schema.sql",
		"classpath:shop-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class TestCart {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test
	@Order(1)
	void testGetCart() {
		this.driver.get("http://localhost:3000/Carts");
	}

	@Test
	@Order(2)
	void testCreateCart() {
		this.driver.get("http://localhost:3000/Carts");

		String name = "banana";
		Double price = 5.00;
		String type = "fruits";

		WebElement nameNew = this.driver.findElement(By.id("nm"));
		nameNew.sendKeys(name);

		WebElement priceNew = this.driver.findElement(By.id("pr"));
		priceNew.sendKeys("5.00");

		WebElement typeNew = this.driver.findElement(By.id("ty"));
		typeNew.sendKeys(type);

		WebElement submitCart = this.driver.findElement(By.id("submitCart"));
		submitCart.submit();

	}

	@AfterEach
	public void tearDown() {

	}

}