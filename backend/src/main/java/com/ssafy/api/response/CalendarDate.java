package com.ssafy.api.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

public interface CalendarDate {
    LocalDate getDate();
    Integer getCount();
}
