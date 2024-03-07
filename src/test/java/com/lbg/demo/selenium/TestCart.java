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

public class TestCart {

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
	void testCreateCart() throws InterruptedException {
		this.driver.get("http://localhost:3000/cart");

		WebElement createdCart = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > div > div"));
		Assertions.assertEquals(("Adele"), createdCart.getText());
	}

	@Test
	@Order(2)
	void editCart() {
		this.driver.get("http://localhost:3000/cart");

		WebElement editCart = this.driver.findElement(By
				.cssSelector("#root > div > div > div > div > div > div > div > div > button.btn.btn-warning.btn-sm"));
		editCart.click();

		WebElement newName = this.driver.findElement(By.cssSelector("#name"));
		newName.sendKeys(" + Temi");

		WebElement submitCart = this.driver.findElement(By.cssSelector("#root > div > div > form > button"));
		submitCart.click();

		WebElement newCart = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > div > div"));
		Assertions.assertEquals(("Adele + Temi"), newCart.getText());

	}

}
