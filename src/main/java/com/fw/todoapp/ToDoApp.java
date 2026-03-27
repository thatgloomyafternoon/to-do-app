package com.fw.todoapp;

import com.fw.todoapp.configurations.SpringBootStartup;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoApp {

  public static void main(String[] args) {
    SpringBootStartup.run(ToDoApp.class, args);
  }

}
