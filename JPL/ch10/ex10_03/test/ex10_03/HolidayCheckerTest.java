package ex10_03;

import static org.junit.Assert.*;

import org.junit.Test;

import ex10_03.HolidayChecker.Days;

public class HolidayCheckerTest {

	@Test
	public void testIsHolidayImplementedWithIfElse() {
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithIfElse(Days.MONDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithIfElse(Days.TUESDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithIfElse(Days.WEDNESDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithIfElse(Days.THURSDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithIfElse(Days.FRIDAY));
		assertEquals(true,
				HolidayChecker.isHolidayImplementedWithIfElse(Days.SATURDAY));
		assertEquals(true,
				HolidayChecker.isHolidayImplementedWithIfElse(Days.SUNDAY));
	}

	@Test
	public void testIsHolidayImplementedWithSwitch() {
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithSwitch(Days.MONDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithSwitch(Days.TUESDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithSwitch(Days.WEDNESDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithSwitch(Days.THURSDAY));
		assertEquals(false,
				HolidayChecker.isHolidayImplementedWithSwitch(Days.FRIDAY));
		assertEquals(true,
				HolidayChecker.isHolidayImplementedWithSwitch(Days.SATURDAY));
		assertEquals(true,
				HolidayChecker.isHolidayImplementedWithSwitch(Days.SUNDAY));
	}

}
