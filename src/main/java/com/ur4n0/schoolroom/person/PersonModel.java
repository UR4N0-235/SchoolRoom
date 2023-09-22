package com.ur4n0.schoolroom.person;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ur4n0.schoolroom.schedule.ScheduleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class PersonModel {
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

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PersonType type = PersonType.TEACHER;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<ScheduleModel> schedules = new ArrayList<>();

    public PersonModel(String name, String rm, String password) {
        this.name = name;
        this.rm = rm;
        this.password = password;
    }

    public PersonModel(Long id, String name, String rm, String password) {
        this.id = id;
        this.name = name;
        this.rm = rm;
        this.password = password;
    }
}
