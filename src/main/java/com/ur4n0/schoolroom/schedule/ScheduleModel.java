package com.ur4n0.schoolroom.schedule;

import java.time.LocalDateTime;

import com.ur4n0.schoolroom.room.RoomModel;
import com.ur4n0.schoolroom.teacher.TeacherModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class ScheduleModel {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STARTDATE")
    private LocalDateTime startDate;
    
    @Column(name = "ENDDATE")
    private LocalDateTime endDate;    

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherModel teacher;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomModel room;

    public ScheduleModel() {
    }
}
