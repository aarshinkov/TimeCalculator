package com.aarshinkov.datetimecalculator;

import com.aarshinkov.datetimecalculator.domain.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeCalculatorTest {

    private TimeCalculator tc;

    @BeforeEach
    public void setUp() {
//        System.out.println("Initializing time calculator.");
        tc = new TimeCalculator();
    }

    @Test
    public void test_TimeToString() {
        Time t = new Time();

        assertEquals("", t.printTime());

        t = new Time();
        t.setHours(1);
        assertEquals("1 hour", t.printTime());

        t = new Time();
        t.setHours(2);
        assertEquals("2 hours", t.printTime());

        t = new Time();
        t.setHours(1);
        t.setMinutes(5);
        t.setSeconds(30);
        assertEquals("1 hour, 5 minutes, 30 seconds", t.printTime());

        t = new Time();
        t.setHours(4);
        t.setMinutes(1);
        t.setSeconds(22);
        assertEquals("4 hours, 1 minute, 22 seconds", t.printTime());

        t = new Time();
        t.setHours(1);
        t.setSeconds(30);
        assertEquals("1 hour, 30 seconds", t.printTime());

        t = new Time();
        t.setHours(8);
        t.setSeconds(1);
        assertEquals("8 hours, 1 second", t.printTime());

        t = new Time();
        t.setMinutes(5);
        t.setSeconds(30);
        assertEquals("5 minutes, 30 seconds", t.printTime());

        t = new Time();
        t.setMinutes(1);
        t.setSeconds(1);
        assertEquals("1 minute, 1 second", t.printTime());

        t = new Time();
        t.setHours(2);
        t.setMinutes(5);
        assertEquals("2 hours, 5 minutes", t.printTime());
    }

    @Test
    public void test_CompareEmptyTimes_Equals() {
        Time time1 = new Time();
        Time time2 = new Time();

        assertEquals(time1, time2);
    }

    @Test
    public void test_CompareTimes_Equals() {
        Time time1 = new Time(1L, 2L, 5L);
        Time time2 = new Time(1L, 2L, 5L);

        assertEquals(time1, time2);
    }

    @Test
    public void test_CompareTimesOnlyHours_Equals() {
        Time time1 = new Time();
        time1.setHours(2);
        Time time2 = new Time();
        time2.setHours(2);

        assertEquals(time1, time2);
    }

    @Test
    public void test_CompareTimesOnlyHoursAndMinutes_Equals() {
        Time time1 = new Time();
        time1.setHours(6);
        time1.setMinutes(30);
        Time time2 = new Time();
        time2.setHours(6);
        time2.setMinutes(30);

        assertEquals(time1, time2);
    }

    @Test
    public void test_AddTimes() {
        assertEquals(new Time(2L, 5L, 15L).printTime(),
                tc.addTimes(new Time(1L, 2L, 5L),
                        new Time(1L, 3L, 10L)).printTime());

        assertEquals(new Time(11L, 6L, 20L).printTime(),
                tc.addTimes(new Time(4L, 20L, 45L),
                        new Time(6L, 45L, 35L)).printTime());

        assertEquals(new Time(8L, 32L, 35L).printTime(),
                tc.addTimes(new Time(4L, 55L, 120L),
                        new Time(3L, 35L, 35L)).printTime());

        assertEquals(new Time(12L, 48L, 10L).printTime(),
                tc.addTimes(new Time(8L, 78L, 450L),
                        new Time(2L, 77L, 340L)).printTime());
    }

    @Test
    public void test_toSeconds() {

        Time time = new Time();

        assertEquals(0L, time.toSeconds());

        time = new Time(1L, 0L, 0L);

        assertEquals(3600L, time.toSeconds());

        time = new Time(1L, 46L, 0L);

        assertEquals(6360L, time.toSeconds());

        time = new Time(1L, 87L, 0L);

        assertEquals(8820L, time.toSeconds());

        time = new Time(2L, 30L, 26L);

        assertEquals(9026L, time.toSeconds());
    }

    @Test
    public void test_secondsToTime() {

        long seconds = 3600;

        assertEquals(new Time(1L, 0L, 0L), tc.secondsToTime(seconds));

        seconds = 3601;

        assertEquals(new Time(1L, 0L, 1L), tc.secondsToTime(seconds));

        seconds = 3599;

        assertEquals(new Time(0L, 59L, 59L), tc.secondsToTime(seconds));

        seconds = 60;

        assertEquals(new Time(0L, 1L, 0L), tc.secondsToTime(seconds));

        seconds = 59;

        assertEquals(new Time(0L, 0L, 59L), tc.secondsToTime(seconds));

        seconds = 5400;

        assertEquals(new Time(1L, 30L, 0L), tc.secondsToTime(seconds));

        seconds = 5398;

        assertEquals(new Time(1L, 29L, 58L), tc.secondsToTime(seconds));

        seconds = 12182;

        assertEquals(new Time(3L, 23L, 2L), tc.secondsToTime(seconds));
    }

    @Test
    public void test_multiplyTime() {

        Time time = new Time(1L, 25L, 27L);
        assertEquals(new Time(2L, 50L, 54L), tc.multiplyTime(time, 2));

        time = new Time(2L, 10L, 30L);
        assertEquals(new Time(4L, 21L, 0L), tc.multiplyTime(time, 2));

        time = new Time(0L, 45L, 59L);
        assertEquals(new Time(1L, 31L, 58L), tc.multiplyTime(time, 2));

        time = new Time(3L, 1L, 0L);
        assertEquals(new Time(6L, 2L, 0L), tc.multiplyTime(time, 2));

        time = new Time(5L, 30L, 30L);
        assertEquals(new Time(16L, 31L, 30L), tc.multiplyTime(time, 3));
    }

    @Test
    public void test_divideTime() {

        assertEquals(null, tc.divideTime(null, 2));

        Time time = new Time(1L, 0L, 0L);

        assertEquals(new Time(0L, 30L, 0L), tc.divideTime(time, 2));

        time = new Time(1L, 30L, 0L);

        assertEquals(new Time(0L, 45L, 0L), tc.divideTime(time, 2));

        time = new Time(0L, 0L, 27L);

        assertEquals(new Time(0L, 0L, 13L), tc.divideTime(time, 2));

        time = new Time(0L, 25L, 27L);

        assertEquals(new Time(0L, 12L, 43L), tc.divideTime(time, 2));

        time = new Time(1L, 25L, 27L);

        assertEquals(new Time(0L, 42L, 43L), tc.divideTime(time, 2));

        time = new Time(2L, 10L, 30L);

        assertEquals(new Time(1L, 5L, 15L), tc.divideTime(time, 2));

        time = new Time(0L, 45L, 59L);

        assertEquals(new Time(0L, 15L, 19L), tc.divideTime(time, 3));

        time = new Time(3L, 1L, 0L);

        assertEquals(new Time(0L, 45L, 15L), tc.divideTime(time, 4));

        time = new Time(5L, 30L, 30L);

        assertEquals(new Time(1L, 6L, 6L), tc.divideTime(time, 5));

    }

    @Test
    public void test_minutesToTime() {

        long minutes = 40;

        assertEquals(new Time(0L, 40L, 0L), tc.minutesToTime(minutes));

        minutes = 60;

        assertEquals(new Time(1L, 0L, 0L), tc.minutesToTime(minutes));

        minutes = 61;

        assertEquals(new Time(1L, 1L, 0L), tc.minutesToTime(minutes));

        minutes = 119;

        assertEquals(new Time(1L, 59L, 0L), tc.minutesToTime(minutes));

        minutes = 120;

        assertEquals(new Time(2L, 0L, 0L), tc.minutesToTime(minutes));

        minutes = 121;

        assertEquals(new Time(2L, 1L, 0L), tc.minutesToTime(minutes));

        minutes = 0;

        assertEquals(new Time(0L, 0L, 0L), tc.minutesToTime(minutes));

        minutes = -1;

        assertEquals(new Time(0L, 0L, 0L), tc.minutesToTime(minutes));
    }
}