package com.ur4n0.schoolroom.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ur4n0.schoolroom.person.PersonModel;
import com.ur4n0.schoolroom.person.PersonRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testFindPersonByRmfindByRm() {
        PersonModel person = new PersonModel("John Doe", "09380", "password123");
        entityManager.persistAndFlush(person);

        Optional<PersonModel> foundPerson = personRepository.findByRm("09380");

        assertThat(foundPerson).isPresent();
        assertThat(foundPerson.get().getName()).isEqualTo(person.getName());
        assertThat(foundPerson.get().getRm()).isEqualTo(person.getRm());
        assertThat(foundPerson.get().getPassword()).isEqualTo(person.getPassword());
    }

    @Test
    public void testFindPersonByRmfindByRmNotFound() {
        Optional<PersonModel> foundPerson = personRepository.findByRm("1111");

        assertThat(foundPerson).isNotPresent();
    }
}
