package com.ur4n0.schoolroom.schedule;

import lombok.Data;

@Data
public class ScheduleDTO {
    private Long startDate;
    private Long endDate;
    private Long personId;
    private Long roomId;
}
