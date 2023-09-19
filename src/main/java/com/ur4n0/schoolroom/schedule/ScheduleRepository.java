package com.ur4n0.schoolroom.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import com.ur4n0.schoolroom.teacher.TeacherModel;
import com.ur4n0.schoolroom.room.RoomModel;



public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long>{
    List<ScheduleModel> findByTeacher(TeacherModel teacher);
    List<ScheduleModel> findByRoom(RoomModel room);
    List<ScheduleModel> findByDateBetween(LocalDateTime startOf, LocalDateTime endOf);

}
