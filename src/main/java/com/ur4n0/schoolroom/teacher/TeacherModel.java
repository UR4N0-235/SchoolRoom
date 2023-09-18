package com.ur4n0.schoolroom.teacher;
import java.util.ArrayList;
import java.util.List;

import com.ur4n0.schoolroom.schedule.ScheduleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class TeacherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @OneToMany(mappedBy = "teacher")
    private List<ScheduleModel> schedules = new ArrayList<>();

    public TeacherModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public TeacherModel(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
