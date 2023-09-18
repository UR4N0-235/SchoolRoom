package com.ur4n0.schoolroom.teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TeacherRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testFindTeacherByEmail() {
        TeacherModel teacher = new TeacherModel("John Doe", "johndoe@example.com", "password123");
        entityManager.persistAndFlush(teacher);

        Optional<TeacherModel> foundTeacher = teacherRepository.findByEmail("johndoe@example.com");

        assertThat(foundTeacher).isPresent();
        assertThat(foundTeacher.get().getName()).isEqualTo(teacher.getName());
        assertThat(foundTeacher.get().getEmail()).isEqualTo(teacher.getEmail());
        assertThat(foundTeacher.get().getPassword()).isEqualTo(teacher.getPassword());
    }

    @Test
    public void testFindTeacherByEmailNotFound() {
        Optional<TeacherModel> foundTeacher = teacherRepository.findByEmail("nonexistent@example.com");

        assertThat(foundTeacher).isNotPresent();
    }
}
