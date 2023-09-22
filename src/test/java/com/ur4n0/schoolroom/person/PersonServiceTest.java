package com.ur4n0.schoolroom.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        PersonModel person = new PersonModel(1L, "John Doe", "09890", "password123");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person));
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.findByRm("09890")).thenReturn(Optional.of(person));
    }

    @Test
    public void testGetAllPersons() {
        List<PersonModel> persons = personService.getAllPersons();
        assertThat(persons).hasSize(1);
    }

    @Test
    public void testGetPersonById() {
        Optional<PersonModel> person = personService.getPersonById(1L);
        assertThat(person).isPresent();
    }

    @Test
    public void testGetPersonByRmfindByRm() {
        Optional<PersonModel> person = personService.getPersonByRm("09890");
        assertThat(person).isPresent();
    }

    @Test
    public void testCreatePerson() {
        PersonModel newPerson = new PersonModel("Jane Smith", "0930", "password456");
        personService.createPerson(newPerson);

        verify(personRepository, times(1)).save(newPerson);
    }

    @Test
    public void testUpdatePerson() {
        PersonModel updatedPerson = new PersonModel(1L, "Updated Name", "0328", "newpassword");

        when(personRepository.findById(1L)).thenReturn(Optional.of(updatedPerson));

        personService.updatePerson(1L, updatedPerson);

        verify(personRepository, times(1)).save(updatedPerson);
    }

    @Test
    public void testDeletePerson() {
        personService.deletePerson(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }
}
