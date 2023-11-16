package com.test.JUnit;

public class TimeFormatter {

    /**
     * Formats time in 12-hour format.
     *
     * @param hours   the hours to format
     * @param minutes the minutes to format
     * @param seconds the seconds to format
     * @return the formatted time string
     */
    public static String format12Hour(int hours, int minutes, int seconds) {
        int formattedHours = hours;
        if (formattedHours == 0) {
            formattedHours = 12;
        } else if (formattedHours > 12) {
            formattedHours -= 12;
        }
        return pad(formattedHours) + ":" + pad(minutes) + ":" + pad(seconds) + " " + getSuffix(hours);
    }

    /**
     * Formats time in 24-hour format.
     *
     * @param hours   the hours to format
     * @param minutes the minutes to format
     * @param seconds the seconds to format
     * @return the formatted time string
     */
    public static String format24Hour(int hours, int minutes, int seconds) {
        return pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);
    }

    /**
     * Pads a single digit number with a leading zero.
     *
     * @param value the value to pad
     * @return the padded string
     */
    private static String pad(int value) {
        return (value < 10) ? "0" + value : String.valueOf(value);
    }

    /**
     * Determines the AM or PM suffix for 12-hour format.
     *
     * @param hours the hours to check
     * @return "AM" or "PM"
     */
    private static String getSuffix(int hours) {
        return (hours < 12) ? "AM" : "PM";
    }
}
