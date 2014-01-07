package ex10_03;

public class HolidayChecker {

	public static boolean isHolidayImplementedWithIfElse(Days day) {
		if (day == Days.MONDAY) {
			return false;
		} else if (day == Days.TUESDAY) {
			return false;
		} else if (day == Days.WEDNESDAY) {
			return false;
		} else if (day == Days.THURSDAY) {
			return false;
		} else if (day == Days.FRIDAY) {
			return false;
		} else if (day == Days.SATURDAY) {
			return true;
		} else if (day == Days.SUNDAY) {
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static boolean isHolidayImplementedWithSwitch(Days day) {
		switch (day) {
		case MONDAY:	// FALLTHROUGH
		case TUESDAY:	// FALLTHROUGH
		case WEDNESDAY:	// FALLTHROUGH
		case THURSDAY:	// FALLTHROUGH
		case FRIDAY:
			return false;
		case SATURDAY:	// FALLTHROUGH
		case SUNDAY:
			return true;
		default:
			throw new IllegalArgumentException();
		}
	}

	public enum Days {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}
}
