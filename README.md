# Console Calendar

![screenshot](/img/tc.png "screenshot")

## HWs/CWs

* HW 17 - Terminal Calendar with POSIX-compliant CLI and ANSI coloring.

Features:

* Highlight today's date.
* Print calendar for the current month and year.
* Print calendar for a given month and year.
* First day of the week offset (can be set with `-f` or `--firstday`)
* Month and year with `-m` or `--month` and `-y` or `--year`.
* Print help information with `-h` or `--help`.

## Usage Examples

* Run `java -jar terminal-calendar-1.0.jar` or `java -jar terminal-calendar-1.0.jar -h` to print help information:

```powershell
PS C:\Calendar> java -jar terminal-calendar.jar
Usage: terminal-calendar.jar [-hnV] [-f=<firstDay>] [-m=<month>] [-y=<year>]
                             [COMMAND]
Prints a calendar for a given month and year.
  -f, --firstday=<firstDay>
                        First day of the week (mon, tue, wed, thu, fri, sat,
                          sun).
  -h, --help            Show this help message and exit.
  -m, --month=<month>   Month number (1-12).
  -n, --now             Print calendar for the current month and year.
  -V, --version         Print version information and exit.
  -y, --year=<year>     Year number.
Commands:
  help  Display help information about the specified command.
```

* Run `java -jar terminal-calendar-1.0.jar -n` or `java -jar terminal-calendar-1.0.jar --now` to print current month and year calendar:

```powershell
PS C:\Calendar> java -jar .\terminal-calendar.jar -n

        August 2024

 MON TUE WED THU FRI SAT SUN
 ---------------------------
               1   2   3   4
   5   6   7   8   9  10  11
  12  13  14  15  16  17  18
  19  20  21  22  23  24  25
  26  27  28  29  30  31
```

* Run `java -jar terminal-calendar-1.0.jar -nf sun` or `java -jar terminal-calendar-1.0.jar --now --firstday=sun` to print current month and year (week starting on sunday):

```powershell
PS C:\Calendar> java -jar .\terminal-calendar.jar -nf sun

        August 2024

 SUN MON TUE WED THU FRI SAT
 ---------------------------
                   1   2   3
   4   5   6   7   8   9  10
  11  12  13  14  15  16  17
  18  19  20  21  22  23  24
  25  26  27  28  29  30  31
```

* Other usage examples:

```powershell

// February 2024
PS C:\Calendar> java -jar .\terminal-calendar.jar --month=2 --year=2024

        February 2024

 MON TUE WED THU FRI SAT SUN
 ---------------------------
               1   2   3   4
   5   6   7   8   9  10  11
  12  13  14  15  16  17  18
  19  20  21  22  23  24  25
  26  27  28  29
```

```powershell
// February 2023
PS C:\Calendar> java -jar .\terminal-calendar.jar --month=2 --year=2023

        February 2023

 MON TUE WED THU FRI SAT SUN
 ---------------------------
           1   2   3   4   5
   6   7   8   9  10  11  12
  13  14  15  16  17  18  19
  20  21  22  23  24  25  26
  27  28
```

```powershell
// February 2023 (week starting on sunday)
PS C:\Calendar> java -jar .\terminal-calendar.jar --month=2 --year=2023 --firstday=sun

        February 2023

 SUN MON TUE WED THU FRI SAT
 ---------------------------
               1   2   3   4
   5   6   7   8   9  10  11
  12  13  14  15  16  17  18
  19  20  21  22  23  24  25
  26  27  28
```

## Contributing

It is a study project and does not require any contributions.

## License

It is study project and does not require any license.
