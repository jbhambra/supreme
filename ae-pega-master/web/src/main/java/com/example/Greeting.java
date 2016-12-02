package com.example;

public class Greeting {
  public String id;
  public String text;

  public Greeting() {
    this.text = "Hello, World";
  }

  public Greeting( String id, String text ) {
    this.id = id;
    this.text = text;
  }

  @Override
  public String toString() {
    return this.text;
  }
}
