package com.ur4n0.schoolroom.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ur4n0.schoolroom.person.PersonModel;
import com.ur4n0.schoolroom.room.RoomModel;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public boolean isUnoccupiedRoom(ScheduleModel schedule) {
        return scheduleRepository
                .findConflictingSchedules(
                        schedule.getRoom().getId(),
                        schedule.getStartDate(),
                        schedule.getEndDate())
                .isEmpty();
    }

    public ScheduleModel createSchedule(ScheduleModel schedule) {
        if(isUnoccupiedRoom(schedule))
            return scheduleRepository.save(schedule);
        else
            throw new IllegalArgumentException("room not empty in this datetime");
    }

    public Optional<ScheduleModel> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public ScheduleModel updateSchedule(Long id, ScheduleModel updatedSchedule) {
        Optional<ScheduleModel> existingSchedule = scheduleRepository.findById(id);

        if (!existingSchedule.isPresent())
            throw new IllegalArgumentException("Schedule with ID " + id + " not found.");

        ScheduleModel scheduleToUpdate = existingSchedule.get();
        scheduleToUpdate.setStartDate(updatedSchedule.getStartDate());
        scheduleToUpdate.setEndDate(updatedSchedule.getEndDate());

        scheduleToUpdate.setPerson(updatedSchedule.getPerson());
        scheduleToUpdate.setRoom(updatedSchedule.getRoom());

        return scheduleRepository.save(scheduleToUpdate);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<ScheduleModel> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleModel> getSchedulesByPerson(PersonModel person) {
        return scheduleRepository.findByPerson(person);
    }

    public List<ScheduleModel> getSchedulesByRoom(RoomModel room) {
        return scheduleRepository.findByRoom(room);
    }

    public List<ScheduleModel> getSchedulesByPersonAndRoom(PersonModel person, RoomModel room) {
        return scheduleRepository.findByPersonAndRoom(person, room);
    }

    public List<ScheduleModel> getSchedulesByDay(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return scheduleRepository.findByStartDateBetween(startOfDay, endOfDay);
    }

    public List<ScheduleModel> getSchedulesByDayRange(LocalDate startDateRange, LocalDate endDateRange) {
        LocalDateTime startOfDay = startDateRange.atStartOfDay();
        LocalDateTime endOfDay = endDateRange.atTime(23, 59, 59);
        return scheduleRepository.findByStartDateBetween(startOfDay, endOfDay);
    }

    public List<ScheduleModel> getSchedulesByDateAndTimeRange(LocalDateTime startDateRange,
            LocalDateTime endDateRange) {
        return scheduleRepository.findByStartDateBetween(startDateRange, endDateRange);
    }
}
