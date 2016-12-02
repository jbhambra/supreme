package com.example;

import org.junit.*;
import static org.junit.Assert.*;

import cucumber.api.java.en.*;
import cucumber.api.*;

public class SimpleSteps {

  Greeting greeting;

  @Given("^I have a standard greeting$")
  public void i_have_a_standard_greeting() throws Throwable {
    greeting = new Greeting();
  }

  @Then("^I should see \"([^\"]*)\"$")
  public void i_should_see(String arg1) throws Throwable {
    assertEquals( greeting.text, arg1 );
  }
}
