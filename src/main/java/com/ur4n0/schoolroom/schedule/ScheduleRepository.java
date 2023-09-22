package com.ur4n0.schoolroom.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import com.ur4n0.schoolroom.teacher.TeacherModel;
import com.ur4n0.schoolroom.room.RoomModel;



public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long>{
    List<ScheduleModel> findByTeacher(TeacherModel teacher);
    List<ScheduleModel> findByRoom(RoomModel room);
    List<ScheduleModel> findByStartDateBetween(LocalDateTime startOf, LocalDateTime endOf);
    List<ScheduleModel> findByStartDateBetweenAndEndDateBetween(LocalDateTime start, LocalDateTime end, LocalDateTime start2, LocalDateTime end2);
    List<ScheduleModel> findByTeacherAndRoom(TeacherModel teacher, RoomModel room);
    @Query("SELECT s FROM ScheduleModel s WHERE s.room.id = :roomId " +
            "AND (:startDate BETWEEN s.startDate AND s.endDate " +
            "OR :endDate BETWEEN s.startDate AND s.endDate)")
    List<ScheduleModel> findConflictingSchedules(@Param("roomId") Long roomId,
                                                 @Param("startDate") LocalDateTime startDate,
                                                 @Param("endDate") LocalDateTime endDate);
}
