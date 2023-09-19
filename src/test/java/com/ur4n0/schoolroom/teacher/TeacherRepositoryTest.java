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
    public void testFindTeacherByRmfindByRm() {
        TeacherModel teacher = new TeacherModel("John Doe", "09380", "password123");
        entityManager.persistAndFlush(teacher);

        Optional<TeacherModel> foundTeacher = teacherRepository.findByRm("09380");

        assertThat(foundTeacher).isPresent();
        assertThat(foundTeacher.get().getName()).isEqualTo(teacher.getName());
        assertThat(foundTeacher.get().getRm()).isEqualTo(teacher.getRm());
        assertThat(foundTeacher.get().getPassword()).isEqualTo(teacher.getPassword());
    }

    @Test
    public void testFindTeacherByRmfindByRmNotFound() {
        Optional<TeacherModel> foundTeacher = teacherRepository.findByRm("1111");

        assertThat(foundTeacher).isNotPresent();
    }
}
