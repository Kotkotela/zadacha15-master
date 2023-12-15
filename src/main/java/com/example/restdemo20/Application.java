package com.example.restdemo20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Autowired
	private PersonRepository repository;

	@GetMapping("/persons")
	public Iterable<Person> getPersons() {
		return repository.findAll();
	}
	@GetMapping("/persons/{id}")
	public Optional<Person> findPersonById(@PathVariable int id) {
		return repository.findById(id);
	}
	@PostMapping("/persons")
	public Person addPerson(@RequestBody Person person) {
		repository.save(person);
		return person;
	}
	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
		HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
		return new ResponseEntity(repository.save(person), status);
	}
	@DeleteMapping("/persons/{id}")
	public void deletePerson(@PathVariable int id) {
		repository.deleteById(id);
	}

}
