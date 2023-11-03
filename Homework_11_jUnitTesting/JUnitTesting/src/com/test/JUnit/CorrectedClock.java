package com.test.JUnit;

/**
 * The CorrectedClock class represents a clock capable of displaying time
 * in both 12-hour and 24-hour formats.
 */
public class CorrectedClock {
		
		// Instance variables for hours, minutes, and seconds
	    private int hours;
	    private int minutes;
	    private int seconds;
	    
	    
	    /**
	     * Constructs a CorrectedClock object with initial time set to the provided hours, minutes, and seconds.
	     * 
	     * @param hours the initial hours
	     * @param minutes the initial minutes
	     * @param seconds the initial seconds
	     */
	    public CorrectedClock(int hours, int minutes, int seconds) {
	    	setHours(hours);
	        setMinutes(minutes);
	        setSeconds(seconds);
	    }
	    
	    /**
	     * Sets the hours of the clock, ensuring the value is within 0-23.
	     * Throws IllegalArgumentException if the value is out of range.
	     * 
	     * @param hours the hours to set
	     */
	    public void setHours(int hours) {
			if(hours >= 0 && hours < 24) {
				this.hours = hours;
			} else {
				throw new IllegalArgumentException("Hour must be between 0 and 23");
			}
		}
	    
	    /**
	     * Sets the minutes of the clock, ensuring the value is within 0-59.
	     * Throws IllegalArgumentException if the value is out of range.
	     * 
	     * @param minutes the minutes to set
	     */
	    public void setMinutes(int minutes) {
			if(minutes >= 0 && minutes < 60) {
				this.minutes = minutes;
			} else {
				throw new IllegalArgumentException("Minute must be between 0 and 59");
			}
		}
	    
	    /**
	     * Sets the seconds of the clock, ensuring the value is within 0-59.
	     * Throws IllegalArgumentException if the value is out of range.
	     * 
	     * @param seconds the seconds to set
	     */
	    public void setSeconds(int seconds) {
			if(seconds >= 0 && seconds < 60) {
				this.seconds = seconds;
			} else {
				throw new IllegalArgumentException("Second must be between 0 and 59");
			}
		}
	    
	    /**
	     * Adds one hour to the current time, rolling over to 0 if it surpasses 23 hours.
	     */
	    public void addHour() {
	    	hours++;
	        if (hours >= 24) {
	        	hours = 0;
	        }
	    }

	    /**
	     * Adds one minute to the current time, rolling over to 0 and adding an hour if it surpasses 59 minutes.
	     */
	    public void addMinute() {
	        if (minutes >= 59) {
	            minutes = 0;
	            addHour();
	        } else {
	            minutes++;
	        }
	    }

	    /**
	     * Adds one second to the current time, rolling over to 0 and adding a minute if it surpasses 59 seconds.
	     */
	    public void addSecond() {
	        if (seconds >= 59) {
	            seconds = 0;
	            addMinute();
	        } else {
	            seconds++;
	        }
	    }

	    /**
	     * Returns the time in 24-hour format as a string (hh:mm:ss).
	     * 
	     * @return the time in 24-hour format
	     */
	    public String get24HourFormat() {
	    	String format24;

		    format24 = pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);

		    return format24;
	    }

	    /**
	     * Determines the AM or PM suffix for 12-hour time format based on the hours.
	     * 
	     * @return "AM" or "PM"
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
	     * Returns the time in 12-hour format as a string (h:mm:ss AM/PM).
	     * 
	     * @return the time in 12-hour format
	     */
	    public String get12HourFormat() {
	    	String format12;
	        int inputtedHours = hours;
	        
	        if (inputtedHours == 0) {
	            inputtedHours = 12;
	        } 
	        else if (inputtedHours > 12) {
	            inputtedHours = inputtedHours - 12;
	        }
	        
	        format12 = pad(inputtedHours) + ":" + pad(minutes) + ":" + pad(seconds) + " " + getSuffix();
	        
	        return format12;
	    }

	    /**
	     * Pads a single digit number with a leading zero for display purposes and
	     * returns it as a two-character string.
	     * 
	     * @param value the value to pad if necessary
	     * @return the padded string representation of the value
	     */
	    public String pad(int value) {
		    String paddedValue;

		    if (value <= 9) {
		        paddedValue = "0" + value;
		    }
		    else {
		        paddedValue = String.valueOf(value);
		    }

		    return paddedValue;
		}
}
