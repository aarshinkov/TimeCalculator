package com.aarshinkov.datetimecalculator.domain;

import com.aarshinkov.datetimecalculator.enums.TimeUnits;

import java.io.Serializable;
import java.util.Objects;

public class Time implements Serializable {

    private Long hours;
    private Long minutes;
    private Long seconds;

    public Time() {
        this.hours = 0L;
        this.minutes = 0L;
        this.seconds = 0L;
    }

    public Time(Long totalSeconds) {
        // Calculate hours
        this.hours = totalSeconds / 3600; // 1 hour = 3600 seconds
        totalSeconds %= 3600; // Remaining seconds after calculating hours

        // Calculate minutes
        this.minutes = totalSeconds / 60; // 1 minute = 60 seconds
        totalSeconds %= 60; // Remaining seconds after calculating minutes

        // Remaining seconds
        this.seconds = totalSeconds;
    }

    public Time(Long hours, Long minutes, Long seconds) {

        this.seconds = 0L;
        this.minutes = 0L;

        long minutesToAdd = 0L;
        long hoursToAdd = 0L;

        if (seconds != null) {
            if (seconds > 60) {
                this.seconds = seconds % 60;
                minutesToAdd = seconds / 60;
            } else {
                this.seconds = (seconds > 0) ? seconds : 0;
            }
        }

        if (minutes != null) {
            if (minutes > 60) {
                this.minutes = minutes % 60;
                hoursToAdd = minutes / 60;
            } else {
                this.minutes = (minutes > 0) ? minutes : 0;
            }
        }

        this.minutes += minutesToAdd;
        this.hours = ((hours != null && hours > 0) ? hours : 0) + hoursToAdd;
    }

    //    01:00:05
    public Time(String fullTime) {
        String[] split = fullTime.split(":");

        String hours = split[0];
        this.hours = getValue(hours);

        String minutes = split[1];
        this.minutes = getValue(minutes);

        String seconds = split[2];
        this.seconds = getValue(seconds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(hours, time.hours) && Objects.equals(minutes, time.minutes) && Objects.equals(seconds, time.seconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(addTrailingZeroIfAvailable(this.hours));

//        if (hasUnit(TimeUnits.HOUR.getUnitNamePlural())) {
            builder.append(":");
//        }

        builder.append(addTrailingZeroIfAvailable(this.minutes));

//        if (hasUnit(TimeUnits.HOUR.getUnitNamePlural()) || hasUnit(TimeUnits.SECOND.getUnitNamePlural())) {
            builder.append(":");
//        }

        builder.append(addTrailingZeroIfAvailable(this.seconds));

        return String.valueOf(builder);
    }

    public String printFullTime(boolean showHoursIfZero, boolean showMinutesIfZero, boolean showSecondsIfZero) {

        // 08:34:00
        StringBuilder builder = new StringBuilder();

        boolean isHoursPrinted = false;
        boolean isMinutesPrinted = false;

        if (showHoursIfZero) {
            builder.append(addTrailingZeroIfAvailable(this.hours));
            isHoursPrinted = true;
        } else {
            if (this.hours > 0) {
                builder.append(addTrailingZeroIfAvailable(this.hours));
                isHoursPrinted = true;
            }
        }

        if (showMinutesIfZero) {
            if (isHoursPrinted) {
                builder.append(":");
            }
            isMinutesPrinted = true;
            builder.append(addTrailingZeroIfAvailable(this.minutes));
        } else {
            if (this.minutes > 0) {
                if (isHoursPrinted) {
                    builder.append(":");
                }
                builder.append(addTrailingZeroIfAvailable(this.minutes));
                isMinutesPrinted = true;
            }
        }

        if (showSecondsIfZero) {
            if (isMinutesPrinted) {
                builder.append(":");
            }
            builder.append(addTrailingZeroIfAvailable(this.seconds));
        } else {
            if (this.seconds > 0) {
                if (isMinutesPrinted) {
                    builder.append(":");
                }
                builder.append(addTrailingZeroIfAvailable(this.seconds));
            }
        }

        return String.valueOf(builder);
    }

    public String printTime() {

        StringBuilder builder = new StringBuilder();

        builder.append(getTimeUnitText(this.hours, TimeUnits.HOUR.getUnitNameSingular()));
        if (this.hours != null && this.hours > 0 && (hasUnit(TimeUnits.MINUTE.getUnitNamePlural()) || hasUnit(TimeUnits.SECOND.getUnitNamePlural()))) {
            builder.append(", ");
        }
        builder.append(getTimeUnitText(this.minutes, TimeUnits.MINUTE.getUnitNameSingular()));
        if (this.minutes != null && this.minutes > 0 && hasUnit(TimeUnits.SECOND.getUnitNamePlural())) {
            builder.append(", ");
        }
        builder.append(getTimeUnitText(this.seconds, TimeUnits.SECOND.getUnitNameSingular()));

        return String.valueOf(builder);
    }

    public long toSeconds() {

        if (isTimeEmptyOrNull()) {
            return 0L;
        }

        long calculatedSeconds = 0L;

        if (hours != null && hours > 0) {
            calculatedSeconds += hours * 3600;
        }

        if (minutes != null && minutes > 0) {
            calculatedSeconds += minutes * 60;
        }

        if (seconds != null && seconds > 0) {
            calculatedSeconds += seconds;
        }

        return calculatedSeconds;
    }

    public boolean isTimeEmptyOrNull() {

        if (hours != null && hours > 0) {
            return false;
        }

        if (minutes != null && minutes > 0) {
            return false;
        }

        return seconds == null || seconds <= 0;
    }

    private long getValue(String value) {

        String[] valueSplit = value.split("");
        long result;

        if (valueSplit.length == 1) {
            result = Long.parseLong(valueSplit[0]);
        } else if (valueSplit.length == 2) {
            if (!valueSplit[0].equalsIgnoreCase("0")) {
                result = Long.parseLong(value);
            } else {
                result = Long.parseLong(valueSplit[1]);
            }
        } else {
            result = Long.parseLong(value);
        }

        return result;
    }

    private boolean hasUnit(String unit) {

        if (unit != null && !unit.trim().isEmpty()) {
            if (unit.equalsIgnoreCase(TimeUnits.HOUR.getUnitNameSingular()) || unit.equalsIgnoreCase(TimeUnits.HOUR.getUnitNamePlural())) {
                return this.hours != null && this.hours > 0;
            } else if (unit.equalsIgnoreCase(TimeUnits.MINUTE.getUnitNameSingular()) || unit.equalsIgnoreCase(TimeUnits.MINUTE.getUnitNamePlural())) {
                return this.minutes != null && this.minutes > 0;
            } else if (unit.equalsIgnoreCase(TimeUnits.SECOND.getUnitNameSingular()) || unit.equalsIgnoreCase(TimeUnits.SECOND.getUnitNamePlural())) {
                return this.seconds != null && this.seconds > 0;
            }
        }

        return false;
    }

    private String addTrailingZeroIfAvailable(long unit) {

        if (unit == 0) {
            return "00";
        }

        if (unit > 0) {
            if (unit <= 9) {
                return "0" + unit;
            }

            return "" + unit;
        }
        return "";
    }

    private StringBuilder getTimeUnitText(long unit, String unitName) {

        StringBuilder builder = new StringBuilder();

        if (unit > 0) {
            builder.append(unit).append(" ").append(unitName);

            if (unit > 1) {
                builder.append("s");
            }
        }

        return builder;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}
