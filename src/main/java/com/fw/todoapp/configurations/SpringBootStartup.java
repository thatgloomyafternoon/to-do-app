package com.fw.todoapp.configurations;

import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public class SpringBootStartup {

  private static final Logger LOG = LoggerFactory.getLogger(SpringBootStartup.class);

  public static void run(Class<?> mainClass, String[] args) {
    /**
     * sets the timezone of this service to UTC,
     * so all ZonedDateTime.now and LocalDateTime.now
     * will be based on UTC.
     */
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    SpringApplication springApplication = new SpringApplication(mainClass);
    Environment environment = springApplication.run(args).getEnvironment();
    logStartup(environment);
  }

  private static void logStartup(Environment env) {
    LOG.info("\n---------------------------------------------------------------\n" +
             "  APPLICATION IS INITIALIZED AND RUNNING!\n" +
             "  Application Name : {}\n" +
             "  Port             : {}\n" +
             "  Active Profiles  : {}\n" +
             "---------------------------------------------------------------",
             env.getProperty("spring.application.name"),
             env.getProperty("server.port"),
             env.getActiveProfiles());
  }

}
