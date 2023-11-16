package com.test.JUnit;

/**
 * The Clock class representing the time in 
 * both 12 hour h:mm:ss AM/PM and 
 * 24 hour hh:mm:ss formats.
 * 
 * @author mcpar
 *
 */
public class Clock {
	
	private int hours;
	private int minutes;
	private int seconds;
	

	/**
	 * Constructor
	 * @param hours - the initial hours
	 * @param minutes - the initial minutes
	 * @param seconds - the initial seconds
	 */
	public Clock(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	/**
	 * Add an hour to the clock.
	 */
	public void addHour() {
	    if (hours >= 24) {
	        hours = 0;
	    }
	    else {
	        hours++;
	    }
	}
	
	/**
	 * Add a minute to the clock.
	 */
	public void addMinute() {
	    if (seconds >= 60) {
	        seconds = 0;
	        addMinute();
	    }
	    else {
	        seconds++;
	    }
	}

	/**
	 * Add a second to the clock.
	 */
	public void addSecond() {
	    if (seconds >= 60) {
	        seconds = 0;
	        addMinute();
	    }
	    else {
	        seconds++;
	    }
	}
	
	/**
	 * Get the time in 24 hour format: hh:mm:ss
	 * @return - the 24 hour format time
	 */
	public String get24HourFormat() {
	    String format24;

	    format24 = pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);

	    return format24;
	}

	/**
	 * Get the suffix
	 * @return - the suffix
	 */
	private String getSuffix() {
	    String suffix;

	    if (hours < 12) {
	        suffix = "AM";
	    }
	    else {
	        suffix = "PM";
	    }

	    return suffix;
	}

	/**
	 * Get the 12 hour format: h:mm:ss AM/PM
	 * @return - the 12 hour format
	 */
	public String get12HourFormat() {
	    String format12;

	    if (hours == 0) {
	    	hours = 12;
	    }
	    else if (hours > 12) {
	    	hours = hours - 12;
	    }

	    format12 = hours + ":" + pad(minutes) + ":" + pad(seconds) + " " + getSuffix();

	    return format12;
	}

	/**
	 * Pads a value as a two-digit representation.
	 * e.g. 9 is "09"
	 * @param value - the value to pad
	 * @return - the value as two-digts.
	 */
	String pad(int value) {
	    String paddedValue;

	    if (value < 9) {
	        paddedValue = "0" + value;
	    }
	    else {
	        paddedValue = String.valueOf(value);
	    }

	    return paddedValue;
	}
	
	

}