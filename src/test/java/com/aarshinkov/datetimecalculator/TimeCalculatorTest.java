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
    public void test_totalSecondsConstructor() {
        Time t = new Time(3662L);
        assertEquals(new Time(1L, 1L, 2L), t);

        t = new Time(52L);
        assertEquals(new Time(0L, 0L, 52L), t);

        t = new Time(85L);
        assertEquals(new Time(0L, 1L, 25L), t);
    }

    @Test
    public void test_TimeToString() {

        Time t = new Time();

        t = new Time(178L);
        assertEquals("00:02:58", t.toString());

        t = new Time(58L);
        assertEquals("00:00:58", t.toString());
    }

    @Test
    public void test_printTime() {
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

    @Test
    public void test2() {
        Time s1 = new Time("172:12:00");
        Time s2 = new Time("427:54:00");
        Time s3 = new Time("387:16:12");
        Time c1 = new Time("705:27:36");
        Time total = tc.addTimes(s1, s2, s3, c1);
        System.out.println(total);
    }

    @Test
    public void test_times() {
        Time atanasTime = getAtanasTime();
        Time andreiTime = getAndreiTime();

        System.out.println(atanasTime);
        System.out.println(andreiTime);
    }

    @Test
    public void testFullTime() {

        Time t1 = new Time(0L);
        assertEquals("00:00:00", t1.printFullTime(true, true, true));

        t1 = new Time(0L);
        assertEquals("00:00", t1.printFullTime(false, true, true));

        t1 = new Time(0L);
        assertEquals("00", t1.printFullTime(false, false, true));

        t1 = new Time(0L);
        assertEquals("", t1.printFullTime(false, false, false));

        t1 = new Time(3660L);
        assertEquals("01:01:00", t1.printFullTime(true, true, true));

        t1 = new Time(62L);
        assertEquals("00:01:02", t1.printFullTime(true, true, true));

        t1 = new Time(320L);
        assertEquals("05:20", t1.printFullTime(false, true, true));

        t1 = new Time(320L);
        assertEquals("00:05:20", t1.printFullTime(true, true, true));

        t1 = new Time(1020L);
        assertEquals("00:17", t1.printFullTime(true, true, false));

        t1 = new Time(1020L);
        assertEquals("00:17:00", t1.printFullTime(true, true, true));

        t1 = new Time(1020L);
        assertEquals("17:00", t1.printFullTime(false, true, true));
    }

    private Time getAtanasTime() {
        Time pilot = new Time("213:08:40");
        Time controller = new Time("367:17:19");
        Time atis = new Time("323:15:24");
        Time observer = new Time("41:58:41");
        return tc.addTimes(pilot, controller, atis, observer);
    }

    private Time getKristianTime() {
        Time pilot = new Time("735:29:30");
        Time controller = new Time("169:03:24");
        Time atis = new Time("164:06:16");
        Time observer = new Time("22:38:01");
        return tc.addTimes(pilot, controller, atis, observer);
    }

    private Time getAndreiTime() {
        Time pilot = new Time("297:37:14");
        Time controller = new Time("347:50:45");
        Time atis = new Time("315:04:00");
        Time observer = new Time("09:03:25");
        return tc.addTimes(pilot, controller, atis, observer);
    }
}