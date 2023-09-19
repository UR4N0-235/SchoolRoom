package com.ur4n0.schoolroom.teacher;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.ur4n0.schoolroom.schedule.ScheduleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    @NotBlank(message = "Insira o nome do professor")
    private String name;

    @Column(name = "rm", unique = true, nullable = false)
    @NotBlank(message = "Insira o rm do professor")
    private String rm;

    @Column(name = "password", unique = false, nullable = false)
    @NotBlank(message = "Insira a senha temporaria do professor")
    private String password;

    @OneToMany(mappedBy = "teacher")
    private List<ScheduleModel> schedules = new ArrayList<>();

    public TeacherModel(String name, String rm, String password) {
        this.name = name;
        this.rm = rm;
        this.password = password;
    }

    public TeacherModel(Long id, String name, String rm, String password) {
        this.id = id;
        this.name = name;
        this.rm = rm;
        this.password = password;
    }
}
