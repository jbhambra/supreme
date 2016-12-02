package com.example;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import org.dalesbred.*;

public class MainServlet extends HttpServlet {

  public void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
    String main = "/WEB-INF/main.jsp";
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher( main );
    Greeting greeting = new Greeting();
    req.setAttribute( "greeting",  greeting.toString() );
    dispatcher.forward( req, res );
  }
}

