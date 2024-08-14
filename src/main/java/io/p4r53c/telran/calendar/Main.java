package io.p4r53c.telran.calendar;

import picocli.CommandLine;

/**
 * Main class for the Terminal Calendar.
 *
 * @author p4r53c
 * @version 1.0
 * 
 */
public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}
