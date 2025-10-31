package com.gom.amigo_secreto.service;

import com.gom.amigo_secreto.model.Person;
import com.gom.amigo_secreto.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
    }

    public Person createPerson(Person person) {
        Person newPerson = person;

        return personRepository.save(newPerson);

    }
}
