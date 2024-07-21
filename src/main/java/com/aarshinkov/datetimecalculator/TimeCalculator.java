package com.aarshinkov.datetimecalculator;

import com.aarshinkov.datetimecalculator.domain.Time;

public class TimeCalculator {

    public Time addTimes(Time... times) {

        long secondsResult = 0;
        long minutesResult = 0;
        long hoursResult = 0;

        for (Time time : times) {
            secondsResult += time.getSeconds();

            if (secondsResult >= 60) {

                long dividedSecondsRounded = secondsResult / 60;
                long secondsForSubtraction = dividedSecondsRounded * 60;
                secondsResult -= secondsForSubtraction;
                minutesResult += dividedSecondsRounded;
            }

            minutesResult += time.getMinutes();

            if (minutesResult >= 60) {

                long dividedMinutesRounded = minutesResult / 60;
                long minutesForSubtraction = dividedMinutesRounded * 60;
                minutesResult -= minutesForSubtraction;
                hoursResult += dividedMinutesRounded;
            }

            hoursResult += time.getHours();
        }

        return new Time(hoursResult, minutesResult, secondsResult);
    }

    public Time multiplyTime(Time time, int multiplier) {

        // Умножаваме часове, минути и секунди по множителя
        long totalSeconds = (time.getHours() * 3600 + time.getMinutes() * 60 + time.getSeconds()) * multiplier;

        return secondsToTime(totalSeconds);
    }

    public Time divideTime(Time time, int divider) {

        if (time == null || time.equals(new Time(0L, 0L, 0L))) {
            return null;
        }

        long timeInSeconds = time.toSeconds();

        long timeDividedInSeconds = timeInSeconds / divider;

        return secondsToTime(timeDividedInSeconds);
    }

    public Time secondsToTime(long seconds) {

        long hours = seconds / 3600;
        seconds %= 3600;

        long minutes = seconds / 60;
        seconds %= 60;

        return new Time(hours, minutes, seconds);
    }

    public Time minutesToTime(long minutes) {

        long hours = minutes / 60;
        minutes %= 60;

        return new Time(hours, minutes, 0L);
    }
}
