package com.example;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.io.*;

import org.dalesbred.*;
import com.google.gson.*;

public class GreetingServlet extends HttpServlet {

  Gson gson = new Gson();

  protected Database getDatabase() {

    try {
      Class.forName("org.h2.Driver");
    } catch ( ClassNotFoundException e ) {
        e.printStackTrace();
    }

    return Database.forUrlAndCredentials(
      "jdbc:h2:tcp://localhost:9092/~/acme;" +
      "INIT=CREATE TABLE IF NOT EXISTS GREETINGS( id VARCHAR(255), text VARCHAR(255) );"
      , "sa", "sa" );
  }

  protected void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
    Database db = getDatabase();
    List<Greeting> greetings = db.findAll( Greeting.class, "select * from greetings" );

    res.setContentType( "application/json" );
    res.getWriter().write( gson.toJson( greetings ) );
  }

  protected void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
    Greeting greeting = gson.fromJson( req.getReader(), Greeting.class );
    Database db = getDatabase();
    db.update( "insert into greetings(id, text) values( ?, ? )", greeting.id, greeting.text );
    res.setContentType( "application/json" );
    res.setStatus( HttpServletResponse.SC_CREATED );
    res.getWriter().write( "{ \"message\": \"done\" }" );
  }
}

