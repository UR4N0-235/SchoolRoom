package com.ur4n0.schoolroom.teacher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherModel>> getAllTeachers() {
        List<TeacherModel> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherModel> getTeacherById(@PathVariable Long id) {
        Optional<TeacherModel> teacher = teacherService.getTeacherById(id);
        return teacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/rm/{rm}")
    public ResponseEntity<TeacherModel> getTeacherByRm(@PathVariable String rm) {
        Optional<TeacherModel> teacher = teacherService.getTeacherByRm(rm);
        return teacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherModel teacher) {
        if (teacherService.getTeacherByRm(teacher.getRm()).isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um professor com esse rm");
        if (teacher.getId() != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é permitido a escolha de id");
        
        TeacherModel createdTeacher = teacherService.createTeacher(teacher);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherModel> updateTeacher(@PathVariable Long id, @RequestBody TeacherModel updatedTeacher) {
        try {
            TeacherModel teacher = teacherService.updateTeacher(id, updatedTeacher);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
