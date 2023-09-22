package com.ur4n0.schoolroom.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonModelTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testPersonModelPersistence() {
        PersonModel person = new PersonModel("John Doe", "johndoe@example.com", "password123");

        entityManager.persistAndFlush(person);
        PersonModel foundPerson = entityManager.find(PersonModel.class, person.getId());

        assertThat(foundPerson.getName()).isEqualTo(person.getName());
        assertThat(foundPerson.getRm()).isEqualTo(person.getRm());
        assertThat(foundPerson.getPassword()).isEqualTo(person.getPassword());
    }
}