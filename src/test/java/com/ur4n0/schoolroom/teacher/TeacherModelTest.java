package com.ur4n0.schoolroom.teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TeacherModelTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testTeacherModelPersistence() {
        TeacherModel teacher = new TeacherModel("John Doe", "johndoe@example.com", "password123");

        entityManager.persistAndFlush(teacher);
        TeacherModel foundTeacher = entityManager.find(TeacherModel.class, teacher.getId());

        assertThat(foundTeacher.getName()).isEqualTo(teacher.getName());
        assertThat(foundTeacher.getRm()).isEqualTo(teacher.getRm());
        assertThat(foundTeacher.getPassword()).isEqualTo(teacher.getPassword());
    }
}