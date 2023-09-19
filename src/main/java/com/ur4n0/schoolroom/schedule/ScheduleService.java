package com.ur4n0.schoolroom.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ur4n0.schoolroom.room.RoomModel;
import com.ur4n0.schoolroom.teacher.TeacherModel;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleModel createSchedule(ScheduleModel schedule) {
        return scheduleRepository.save(schedule);
    }

    public Optional<ScheduleModel> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public ScheduleModel updateSchedule(Long id, ScheduleModel updatedSchedule) {
        Optional<ScheduleModel> existingSchedule = scheduleRepository.findById(id);

        if (existingSchedule.isPresent()) {
            ScheduleModel scheduleToUpdate = existingSchedule.get();
            scheduleToUpdate.setDate(updatedSchedule.getDate());

            scheduleToUpdate.setTeacher(updatedSchedule.getTeacher());
            scheduleToUpdate.setRoom(updatedSchedule.getRoom());

            return scheduleRepository.save(scheduleToUpdate);
        } else {
            throw new IllegalArgumentException("Schedule with ID " + id + " not found.");
        }
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<ScheduleModel> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleModel> getSchedulesByTeacher(TeacherModel teacher) {
        return scheduleRepository.findByTeacher(teacher);
    }

    public List<ScheduleModel> getSchedulesByRoom(RoomModel room) {
        return scheduleRepository.findByRoom(room);
    }

    public List<ScheduleModel> getSchedulesByDay(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return scheduleRepository.findByDateBetween(startOfDay, endOfDay);
    }
    
    public List<ScheduleModel> getSchedulesByDayRange(LocalDate startDateRange, LocalDate endDateRange) {
        LocalDateTime startOfDay = startDateRange.atStartOfDay();
        LocalDateTime endOfDay = endDateRange.atTime(23, 59, 59);
        return scheduleRepository.findByDateBetween(startOfDay, endOfDay);
    }

    public List<ScheduleModel> getSchedulesByDateAndTimeRange(LocalDateTime startDateRange, LocalDateTime endDateRange) {
        return scheduleRepository.findByDateBetween(startDateRange, endDateRange);
    }
}

