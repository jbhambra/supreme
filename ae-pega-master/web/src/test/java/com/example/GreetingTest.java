package com.example;

import org.junit.*;
import static org.junit.Assert.*;

public class GreetingTest {

    @Test
    public void should_have_a_default_message() {

      // Arrange
      Greeting g = new Greeting();

      // Assert
      assertEquals( "Hello, World" , g.toString() );
    }
}

