package com.example.personsvc;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepo extends MongoRepository<Person, String> {

	Person findByFirstName(String firstName);

	List<Person> findByLastName(String lastName);

}