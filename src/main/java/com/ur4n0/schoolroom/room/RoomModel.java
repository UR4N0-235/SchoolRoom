package com.ur4n0.schoolroom.room;

import java.util.ArrayList;
import java.util.List;

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
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoomType type;

    @Column(name = "number", nullable = false)
    private int number;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<ScheduleModel> schedules = new ArrayList<>();

    public RoomModel(Long id, int number) {
        this.id = id;
        this.number = number;
        this.type = RoomType.SALA_DE_AULA; // default
    }

    public RoomModel(Long id, int number, RoomType type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

}
