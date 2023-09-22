package com.ur4n0.schoolroom.person;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonModel> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<PersonModel> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Optional<PersonModel> getPersonByRm(String rm) {
        return personRepository.findByRm(rm);
    }

    public PersonModel createPerson(PersonModel person) {
        return personRepository.save(person);
    }

    public PersonModel updatePerson(Long id, PersonModel updatedPerson) {
        Optional<PersonModel> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            PersonModel existingPerson = optionalPerson.get();
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setRm(updatedPerson.getRm());
            existingPerson.setPassword(updatedPerson.getPassword());
            return personRepository.save(existingPerson);
        } else {
            throw new IllegalArgumentException("Professor não encontrado com o ID fornecido.");
        }
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
