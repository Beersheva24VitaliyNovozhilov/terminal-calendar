package io.p4r53c.telran.calendar;

import picocli.CommandLine.Option;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

import java.time.LocalDate;
import java.util.concurrent.Callable;

/**
 * CLI class generates POSIX compliant CLI for Terminal Calendar.
 * 
 * @author p4r53c
 * @version 1.0
 */
@Command(name = "terminal-calendar.jar", mixinStandardHelpOptions = true, version = "Terminal Calendar 1.0 (c) p4r53c", description = "Prints a calendar for a given month and year.", subcommands = {
        HelpCommand.class })
public class CLI implements Callable<Integer> {

    private int statusCode;
    private MonthYear monthYear;

    @Option(names = { "-m", "--month" }, description = "Month number (1-12).", defaultValue = "0")
    private int month;

    @Option(names = { "-y", "--year" }, description = "Year number.", defaultValue = "0")
    private int year;

    @Option(names = { "-f",
            "--firstday" }, description = "First day of the week (mon, tue, wed, thu, fri, sat, sun).", defaultValue = "mon")
    private String firstDay;

    @Option(names = { "-n", "--now" }, description = "Print calendar for the current month and year.")
    private boolean now;

    /**
     * Overrides the call method from the Callable interface. This method is
     * responsible for outputting a calendar based on the provided input.
     * 
     * Due to its specificity, this method is a bit complicated and has more than
     * one return statement.
     *
     * @return an integer representing the exit code of the program. 0 indicates
     *         success, 1 indicates an error occurred.
     */
    @Override
    public Integer call() {
        try {
            if (now) {
                LocalDate currentDate = LocalDate.now();
                monthYear = new MonthYear(currentDate.getMonthValue(), currentDate.getYear());

                // Despite the exception handling in the Calendar constructor, this check is not
                // redundant, since there is no other way to check for missing arguments and
                // invoke usage().
            } else if (month == 0 || year == 0) {
                CommandLine.usage(this, System.out);
                System.exit(0);
            } else {
                monthYear = new MonthYear(month, year);
            }
            Calendar calendar = new Calendar(monthYear, firstDay);
            calendar.printCalendar();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            statusCode = 1;
        }

        return statusCode;
    }
}
