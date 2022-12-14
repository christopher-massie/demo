package com.example.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.List;

@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  @ConditionalOnProperty()
  @Order(1)
  public CommandLineRunner insertData(CustomerInsertService customerInsertService){
    System.out.println("SAVING FROM CSV ==============================================");
    return args -> {
      List<CustomerDto> allCustomers = customerInsertService.getAllCustomers("src/main/resources/production.csv");
      customerInsertService.saveAllCustomers(allCustomers);
      System.out.println("Saved: " + allCustomers.size());
    };
  }

  @Bean
  @Order(2)
  public CommandLineRunner selectData(CustomerRepository customerRepository){
    System.out.println("RETURNING SAVED RECORDS ==============================================");
    return args -> customerRepository.findAll().forEach(System.out::println);
  }
}