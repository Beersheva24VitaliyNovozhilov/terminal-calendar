package io.p4r53c.telran.calendar;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

import io.p4r53c.telran.calendar.exceptions.CalendarException;

/**
 * Represents a terminal calendar.
 *
 * @author p4r53c
 * @version 1.0
 * 
 */
public class Calendar {
    private final MonthYear monthYear;
    private final DayOfWeek startDay;

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String RED = "\u001B[31m";

    private static final int MINIMUM_YEAR = 1;
    private static final int MINIMUM_MONTH = 1;
    private static final int MAXIMUM_MONTH = 12;

    public Calendar(MonthYear monthYear, String firstDay) throws CalendarException {
        if (monthYear.year() < MINIMUM_YEAR) {
            throw new CalendarException("Year must be at least " + MINIMUM_YEAR + ".");
        }

        if (monthYear.month() < MINIMUM_MONTH || monthYear.month() > MAXIMUM_MONTH) {
            throw new CalendarException(
                    "Month must be between " + MINIMUM_MONTH + " and " + MAXIMUM_MONTH + ".");
        }

        this.monthYear = monthYear;
        this.startDay = getFirstDayOfWeek(firstDay);
    }

    /**
     * Prints a calendar for the specified month and year.
     */
    public void printCalendar() {
        printTitle();
        printWeekDays();
        printDates();
    }

    /**
     * Prints the title of the calendar, including the month and year.
     */
    private void printTitle() {
        Month monthEnum = Month.of(monthYear.month());

        // "%n\t%s %d%n%n" producing the platform-specific line separator
        System.out.printf("%n\t%s %d%n%n",
                monthEnum.name().substring(0, 1) +
                        monthEnum.name().substring(1).toLowerCase(),
                monthYear.year());
    }

    /**
     * Prints the days of the week for the calendar.
     */
    private void printWeekDays() {
        DayOfWeek[] daysOfWeek = DayOfWeek.values();
        int startIndex = startDay.ordinal();

        for (int i = 0; i < daysOfWeek.length; i++) {
            String color;
            int dayIndex = (startIndex + i) % daysOfWeek.length;

            if (i == daysOfWeek.length - 2 || i == daysOfWeek.length - 1) {
                color = MAGENTA;
            } else {
                color = CYAN;
            }

            System.out.printf("%s%4s%s", color, daysOfWeek[dayIndex].name().substring(0, 3), RESET);
        }

        System.out.println("\n ---------------------------");
    }

    /**
     * Prints the dates of the month in a calendar format.
     */
    private void printDates() {
        int firstDayOfMonthIndex = getFirstDayOfMonthIndex();
        int lastDay = getLastDayOfMonth();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < firstDayOfMonthIndex; i++) {
            System.out.print("    ");
        }
        for (int day = 1; day <= lastDay; day++) {
            String color = RESET;

            if (today.getDayOfMonth() == day && today.getMonthValue() == monthYear.month()
                    && today.getYear() == monthYear.year()) {
                color = RED;
            }

            System.out.printf("%s%4d%s", color, day, RESET);

            if ((day + firstDayOfMonthIndex) % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }

    /**
     * Returns the DayOfWeek enum value corresponding to the given first day of the
     * week.
     *
     * @param firstDay the first day of the week (case-insensitive)
     * @return the corresponding DayOfWeek enum value
     * @throws CalendarException if the first day of the week is invalid
     */
    private DayOfWeek getFirstDayOfWeek(String firstDay) throws CalendarException {
        return switch (firstDay.toLowerCase()) {
            case "mon" -> DayOfWeek.MONDAY;
            case "tue" -> DayOfWeek.TUESDAY;
            case "wed" -> DayOfWeek.WEDNESDAY;
            case "thu" -> DayOfWeek.THURSDAY;
            case "fri" -> DayOfWeek.FRIDAY;
            case "sat" -> DayOfWeek.SATURDAY;
            case "sun" -> DayOfWeek.SUNDAY;
            default -> throw new CalendarException(
                    "Invalid first day of the week. Must be one of: mon, tue, wed, thu, fri, sat, sun.");
        };
    }

    /**
     * Calculates the index of the first day of the month relative to the start day.
     * 
     * Method Protected is for testing purposes.
     *
     * @return the index of the first day of the month, where 0 represents the start
     *         day
     */
    protected int getFirstDayOfMonthIndex() {
        LocalDate firstDayOfMonth = LocalDate.of(monthYear.year(), monthYear.month(), 1);

        int dayOfWeekIndex = firstDayOfMonth.getDayOfWeek().getValue();
        int startIndex = startDay.getValue();

        return (dayOfWeekIndex - startIndex + 7) % 7;
    }

    /**
     * Returns the last day of the month.
     * 
     * Method Protected is for testing purposes.
     *
     * @return the last day of the month
     */
    protected int getLastDayOfMonth() {
        Month monthEnum = Month.of(monthYear.month());
        return monthEnum.length(Year.isLeap(monthYear.year()));
    }
}
