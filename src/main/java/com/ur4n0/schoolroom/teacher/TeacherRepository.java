package com.ur4n0.schoolroom.teacher;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {
 Optional<TeacherModel> findByEmail(String email);   
}