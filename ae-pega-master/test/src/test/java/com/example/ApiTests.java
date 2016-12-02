package com.example;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.net.*;

import org.apache.http.entity.*;
import org.apache.http.client.fluent.*;

import org.h2.tools.*;
import java.sql.*;

public class ApiTests {

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
  public void before() throws SQLException {
    runScript( "api-setup.sql" );
  }

  @After
  public void after() throws SQLException {
    runScript( "api-teardown.sql" );
  }

  @Test
  public void should_return_a_greeting() throws IOException {
    String content = Request.Get( "http://localhost:8080/api/greetings" )
      .connectTimeout( 1000 )
      .socketTimeout( 1000 )
      .execute().returnContent().asString();

    assertEquals( "[{\"id\":\"1\",\"text\":\"hello, world\"}]", content );
  }
}
