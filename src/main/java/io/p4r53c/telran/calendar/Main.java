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
    /**
     * The main method of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         * The CommandLine.execute() method executes the command specified by the
         * args parameter and returns the exit code. The exit code is then passed
         * to the System.exit() method to terminate the application.
         */
        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}
