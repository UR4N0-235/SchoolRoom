package com.ur4n0.schoolroom.teacher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherModel> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<TeacherModel> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Optional<TeacherModel> getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public TeacherModel createTeacher(TeacherModel teacher) {
        return teacherRepository.save(teacher);
    }

    public TeacherModel updateTeacher(Long id, TeacherModel updatedTeacher) {
        Optional<TeacherModel> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isPresent()) {
            TeacherModel existingTeacher = optionalTeacher.get();
            existingTeacher.setName(updatedTeacher.getName());
            existingTeacher.setEmail(updatedTeacher.getEmail());
            existingTeacher.setPassword(updatedTeacher.getPassword());
            return teacherRepository.save(existingTeacher);
        } else {
            throw new IllegalArgumentException("Professor não encontrado com o ID fornecido.");
        }
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
