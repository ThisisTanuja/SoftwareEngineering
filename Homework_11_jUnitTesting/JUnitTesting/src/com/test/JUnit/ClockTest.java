package com.test.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ClockTest {
	@Test
	void test12HourFormat() {
		Clock clock = new Clock(13, 14, 15);
		assertEquals("01:14:15 PM", clock.get12HourFormat());
	}
	
	@Test
	void test24HourFormat() {
		Clock clock = new Clock(13, 14, 15);
		assertEquals("13:14:15", clock.get24HourFormat());
	}
	
	@Test
	void testAddHourBeforeMidnightFormat12() {
		Clock clock = new Clock(23, 30, 30);
		clock.addHour();
		assertEquals("12:30:30 AM", clock.get12HourFormat());
	}
	
	@Test
	void testAddHourBeforeMidnightFormat24() {
		Clock clock = new Clock(23, 30, 30);
		clock.addHour();
		assertEquals("00:30:30", clock.get24HourFormat());
	}
	
	@Test
	void testAddHourDuringMorningFormat12() {
		Clock clock = new Clock(10, 00, 00);
		clock.addHour();
		assertEquals("11:00:00 AM", clock.get12HourFormat());
	}
	
	@Test
	void testAddHourDuringMorningFormat24() {
		Clock clock = new Clock(10, 00, 00);
		clock.addHour();
		assertEquals("11:00:00", clock.get24HourFormat());
	}
	
	@Test
	void testAddHourDuringEveningFormat12() {
		Clock clock = new Clock(22, 00, 00);
		clock.addHour();
		assertEquals("11:00:00 PM", clock.get12HourFormat());
	}
	
	@Test
	void testAddHourDuringEveningFormat24() {
		Clock clock = new Clock(22, 00, 00);
		clock.addHour();
		assertEquals("23:00:00", clock.get24HourFormat());
	}
	
	@Test
	void testAddHourMultipleTimesFormat12() {
		Clock clock = new Clock(10, 00, 00);
		clock.addHour();
		clock.addHour();
		clock.addHour();
		assertEquals("01:00:00 PM", clock.get12HourFormat());
	}
	
	@Test
	void testAddHourMultipleTimesFormat24() {
		Clock clock = new Clock(10, 00, 00);
		clock.addHour();
		clock.addHour();
		clock.addHour();
		assertEquals("13:00:00", clock.get24HourFormat());
	}
	
	@Test
    void testInvalidHourInitialization() {
		// Test for hours less than 0
        assertThrows(IllegalArgumentException.class, () -> {
        	new Clock(-1, 20, 30);
        }, "Hour must be between 0 and 23");
        
        // Test for hours greater than 23
        assertThrows(IllegalArgumentException.class, () -> {
            new Clock(24, 20, 30);
        }, "Hour must be between 0 and 23");
    }
	
	@Test
	void testAddMinuteNormalBehavior() {
		Clock clock = new Clock(10, 30, 50);
		clock.addMinute();
		assertEquals("10:31:50", clock.get24HourFormat());
	}
	
	@Test
	void testAddMinuteRolloverToNextHour() {
		Clock clock = new Clock(23, 59, 45);
		clock.addMinute();
		assertEquals("00:00:45", clock.get24HourFormat());
	}
	
	@Test
	void testAddMinuteContinuousRollover() {
		Clock clock = new Clock(10, 58, 59);
		clock.addMinute();
		clock.addMinute();
		assertEquals("11:00:59", clock.get24HourFormat());
	}
	
	@Test
    void testInvalidMinuteInitialization() {
		// Test for minutes less than 0
        assertThrows(IllegalArgumentException.class, () -> {
        	new Clock(1, -1, 30);
        }, "Minute must be between 0 and 23");
        
        // Test for minutes greater than 59
        assertThrows(IllegalArgumentException.class, () -> {
            new Clock(1, 60, 30);
        }, "Minute must be between 0 and 23");
    }
	
	@Test
	void testAddSecondNormalBehavior() {
		Clock clock = new Clock(10, 30, 30);
		clock.addSecond();
		assertEquals("10:30:31", clock.get24HourFormat());
	}
	
	@Test
	void testAddSecondRolloverToNextMinute() {
		Clock clock = new Clock(10, 58, 59);
		clock.addSecond();
		assertEquals("10:59:00", clock.get24HourFormat());
	}
	
	@Test
	void testAddSecondRolloverToNextHour() {
		Clock clock = new Clock(10, 59, 59);
		clock.addSecond();
		assertEquals("11:00:00", clock.get24HourFormat());
	}
	
	@Test
	void testAddSecondContinousRollover() {
		Clock clock = new Clock(23, 59, 58);
		clock.addSecond();
		clock.addSecond();
		assertEquals("00:00:00", clock.get24HourFormat());
	}
	
	@Test
	void testInvalidSecondInitialization() {
		// Test for seconds less than 0
        assertThrows(IllegalArgumentException.class, () -> {
        	new Clock(01, 01, -1);
        }, "Second must be between 0 and 23");
        
        // Test for seconds greater than 59
        assertThrows(IllegalArgumentException.class, () -> {
            new Clock(01, 59, 60);
        }, "Second must be between 0 and 23");
    }
	
	@Test
	void testPadSingleDigit() {
		Clock clock = new Clock(0, 9, 0);
		assertEquals("00", clock.pad(0));
		assertEquals("09", clock.pad(9));
	}
	
	@Test
	void testPadDoubleDigit() {
		Clock clock = new Clock(10, 59, 0);
		assertEquals("10", clock.pad(10));
		assertEquals("59", clock.pad(59));
	}
	
	@Test
	void testEdgeCases() {
		Clock clock = new Clock(23, 59, 59);
		clock.addSecond();
		assertEquals("00:00:00", clock.get24HourFormat());
		assertEquals("12:00:00 AM", clock.get12HourFormat());
	}
	

}
