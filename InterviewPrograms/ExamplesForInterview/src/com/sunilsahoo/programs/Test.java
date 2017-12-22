package com.sunilsahoo.programs;

public class Test {
	static double findAngle(int hours, int minutes) throws Exception {
		// validate the input
		if (!isValid(hours, minutes)) {
			throw new Exception("Invalid input");
		}

		if (hours == 12)
			hours = 0;
		if (minutes == 60)
			minutes = 0;

		int hourAngle = (int) (0.5 * (hours * 60 + minutes));
		int minuteAngle = (int) (6 * minutes);

		int angle = Math.abs(hourAngle - minuteAngle);

		// angle = Math.min(360-angle, angle);

		return angle;
	}

	static boolean isValid(int hours, int minutes) {
		return !(hours < 0 || minutes < 0 || hours > 12 || minutes > 60);
	}

	// Driver program
	public static void main(String[] args) throws Exception {
		System.out.println(findAngle(9, 60) + " degree");
		System.out.println(findAngle(3, 30) + " degree");
	}
}
