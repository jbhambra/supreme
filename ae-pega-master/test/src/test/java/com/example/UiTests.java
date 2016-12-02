package com.example;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.net.*;

import org.apache.http.entity.*;
import org.apache.http.client.fluent.*;

import org.h2.tools.*;
import java.sql.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UiTests {

  private WebDriver driver;
  private String baseUrl;

  private Connection getConnection() throws SQLException {
    Connection connection = null;

    try {
      Class.forName("org.h2.Driver");
    } catch ( ClassNotFoundException e ) {
      e.printStackTrace();
    }

    connection = DriverManager.getConnection(
      "jdbc:h2:tcp://localhost:9092/~/acme", "sa", "sa" );

    return connection;
  }

  private void runScript( String fileName ) throws SQLException {
    Reader script = new InputStreamReader(
      this.getClass().getResourceAsStream( fileName ) );
    RunScript.execute( this.getConnection(), script );
  }

  @Before
  public void before() throws SQLException, MalformedURLException {
    baseUrl = "http://localhost:8080";
    runScript( "ui-setup.sql" );

    System.setProperty("webdriver.chrome.driver", "./chromedriver");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @After
  public void after() throws SQLException {
    runScript( "ui-teardown.sql" );
    driver.quit();
  }

  @Test
  public void should_have_default_title() {
    driver.get( baseUrl + "/index.html" );
    assertEquals( "Hello", driver.getTitle() );
  }

  @Test
  public void can_add_new_greeting() throws InterruptedException {
    driver.get( baseUrl + "/index.html" );
    driver.findElement(By.id("text")).clear();
    driver.findElement(By.id("text")).sendKeys("hello");
    driver.findElement(By.cssSelector("button")).click();
    assertEquals("hello", driver.findElement(By.cssSelector("li")).getText());
  }
}
