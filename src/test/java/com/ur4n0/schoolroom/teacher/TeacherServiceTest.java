package com.ur4n0.schoolroom.teacher;

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
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void setUp() {
        TeacherModel teacher = new TeacherModel(1L, "John Doe", "johndoe@example.com", "password123");

        when(teacherRepository.findAll()).thenReturn(Arrays.asList(teacher));
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(teacher));
    }

    @Test
    public void testGetAllTeachers() {
        List<TeacherModel> teachers = teacherService.getAllTeachers();
        assertThat(teachers).hasSize(1);
    }

    @Test
    public void testGetTeacherById() {
        Optional<TeacherModel> teacher = teacherService.getTeacherById(1L);
        assertThat(teacher).isPresent();
    }

    @Test
    public void testGetTeacherByEmail() {
        Optional<TeacherModel> teacher = teacherService.getTeacherByEmail("johndoe@example.com");
        assertThat(teacher).isPresent();
    }

    @Test
    public void testCreateTeacher() {
        TeacherModel newTeacher = new TeacherModel("Jane Smith", "janesmith@example.com", "password456");
        teacherService.createTeacher(newTeacher);

        verify(teacherRepository, times(1)).save(newTeacher);
    }

    @Test
    public void testUpdateTeacher() {
        TeacherModel updatedTeacher = new TeacherModel(1L, "Updated Name", "updated@example.com", "newpassword");

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(updatedTeacher));

        teacherService.updateTeacher(1L, updatedTeacher);

        verify(teacherRepository, times(1)).save(updatedTeacher);
    }

    @Test
    public void testDeleteTeacher() {
        teacherService.deleteTeacher(1L);

        verify(teacherRepository, times(1)).deleteById(1L);
    }
}