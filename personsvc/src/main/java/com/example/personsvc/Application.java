package com.example.personsvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
// import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication
@RestController
public class Application implements CommandLineRunner {

    @Autowired
	private PersonRepo repository;

    @RequestMapping("/")
    public String home() {
        return "Hello world from product-svc!!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		this.repository.deleteAll();

		// save a couple of customers
		this.repository.save(new Person("Alice", "Smith"));
		this.repository.save(new Person("Bob", "Smith"));

		// fetch all customers
		System.out.println("Products found with findAll():");
		System.out.println("-------------------------------");
		for (Person customer : this.repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Person customer : this.repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}
}