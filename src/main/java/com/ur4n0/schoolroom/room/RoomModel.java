package com.ur4n0.schoolroom.room;

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
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @OneToMany(mappedBy = "room")
    private List<ScheduleModel> schedules = new ArrayList<>();
}
