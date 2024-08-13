package io.p4r53c.telran.calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class CalendarTest {

    private Calendar calendar;
    private MonthYear monthYear;

    @BeforeEach
    void setUp() throws Exception {
        // Initializing with a sample month and year
        monthYear = new MonthYear(2, 2024); // February 2024
        calendar = new Calendar(monthYear, "mon");
    }

    @Test
    @DisplayName("Test first day of February 2024 is Thursday")
    void testFirstDayOfMonthIndex() {
        LocalDate firstDayOfMonth = LocalDate.of(monthYear.year(), monthYear.month(), 1);
        DayOfWeek actualFirstDay = firstDayOfMonth.getDayOfWeek();
        assertEquals(DayOfWeek.THURSDAY, actualFirstDay);
    }

    @Test
    @DisplayName("Test last day of February 2024 is 29 (Leap Year)")
    void testLastDayOfMonth() {
        int lastDay = calendar.getLastDayOfMonth();
        assertEquals(29, lastDay, "February 2024 should have 29 days.");
    }

    @Test
    @DisplayName("Test first day of the week index with Monday start")
    void testFirstDayOfWeekIndexMonday() throws Exception {
        Calendar mondayStartCalendar = new Calendar(new MonthYear(2, 2024), "mon");
        int firstDayIndex = mondayStartCalendar.getFirstDayOfMonthIndex();
        assertEquals(3, firstDayIndex, "February 1, 2024 should be a Thursday with index 3 if starting on Monday.");
    }

    @Test
    @DisplayName("Test first day of the week index with Sunday start")
    void testFirstDayOfWeekIndexSunday() throws Exception {
        Calendar sundayStartCalendar = new Calendar(new MonthYear(2, 2024), "sun");
        int firstDayIndex = sundayStartCalendar.getFirstDayOfMonthIndex();
        assertEquals(4, firstDayIndex, "February 1, 2024 should be a Thursday with index 4 if starting on Sunday.");
    }
}
