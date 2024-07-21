package com.aarshinkov.datetimecalculator.main;

import com.aarshinkov.datetimecalculator.TimeCalculator;
import com.aarshinkov.datetimecalculator.domain.Time;

public class Main {

    public static void main(String[] args) {
        TimeCalculator tc = new TimeCalculator();

        Time t1 = new Time("1:34:23");
        // 03:08:46
        System.out.println(tc.multiplyTime(t1, 2));
    }

    public static void main2(String[] args) {
        TimeCalculator tc = new TimeCalculator();

        Time t1 = new Time("225:32:56");
        Time t2 = new Time("222:48:23");
        Time t3 = new Time("36:28:15");

        Time total = tc.addTimes(t1, t2, t3);
        System.out.println(total.toString());

        Time t4 = new Time("12:35:19");
        Time t5 = new Time("302:14:28");
        Time t6 = new Time("169:59:47");

        Time total2 = tc.addTimes(t4, t5, t6);
        System.out.println(total2.toString());

        System.out.println("Are times the same? " + (total.equals(total2) ? "Yes" : "No"));

        System.out.println(tc.addTimes(new Time("24:59:59"), new Time("182:22:55"), new Time("18:10:02")).toString());
    }

    public static void main3(String[] args) {
        TimeCalculator tc = new TimeCalculator();

        Time pilotResult = new Time(151L, 45L, 9L);

        System.out.printf("%-20.30s  %-20.30s%n", "Pilot: ", pilotResult);
//        System.out.println("Pilot: " + pilotResult);

        Time controllerS1 = new Time(142L, 6L, 6L);
        Time controllerS2 = new Time(49L, 57L, 48L);

        Time controllerResult = tc.addTimes(controllerS1, controllerS2);
        System.out.printf("%-20.30s  %-20.30s%n", "Controller: ", controllerResult);

        Time atisS1 = new Time(141L, 15L, 6L);
        Time atisS2 = new Time(49L, 36L, 33L);
        Time atisResult = tc.addTimes(atisS1, atisS2);
        System.out.printf("%-20.30s  %-20.30s%n", "ATIS: ", atisResult);

        Time obsOBS = new Time(12L, 35L, 19L);
        Time obsS1 = new Time(18L, 53L, 16L);
        Time obsS2 = new Time(3L, 6L, 14L);
        Time obsResult = tc.addTimes(obsOBS, obsS1, obsS2);
        System.out.printf("%-20.30s  %-20.30s%n", "OBS: ", obsResult);

        System.out.println("----------------------------------------------");
        Time totalTime = tc.addTimes(pilotResult, controllerResult, atisResult, obsResult);
        System.out.printf("%-20.30s  %-20.30s%n", "Total: ", totalTime);
    }

    public static void main4(String[] args) {
        TimeCalculator tc = new TimeCalculator();

        System.out.println(tc.addTimes(new Time("01:16:01"),
                new Time("04:01:12"),
                new Time("01:45:17"),
                new Time("116:17:43"),
                new Time("03:48:03"),
                new Time("07:15:47"),
                new Time("01:03:48"),
                new Time("02:38:17"),
                new Time("01:55:53"),
                new Time("03:26:49"),
                new Time("02:27:39"),
                new Time("01:14:11"),
                new Time("05:22:13"),
                new Time("01:11:44"),
                new Time("00:00:10"),
                new Time("00:22:15"),
                new Time("02:30:35")
        ).toString());
    }
}
