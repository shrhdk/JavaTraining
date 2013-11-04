package jpl.chapter3.exercise2;

import java.util.ArrayList;
import java.util.Arrays;

public class Observer {

    public static class Log {
	public final int step;
	public final String event;
	public final int xMask;
	public final int yMask;
	public final int fullMask;

	public Log(int step, String event, int xMask, int yMask, int fullMask) {
	    this.step = step;
	    this.event = event;
	    this.xMask = xMask;
	    this.yMask = yMask;
	    this.fullMask = fullMask;
	}

	public static String getHeaderString() {
	    return String.format("%-4s %-20s %-6s %-6s %-6s", "step", "event", "xMask", "yMask", "fullMask");
	}

	private static String separatorString;

	public static String getSeparatorString() {
	    if (separatorString == null) {
		int headerWidth = getHeaderString().length();
		char[] separatorChar = new char[headerWidth];
		Arrays.fill(separatorChar, '-');
		return new String(separatorChar);
	    } else {
		return separatorString;
	    }
	}

	@Override
	public String toString() {
	    return String.format("%4d %-20s 0x%04x 0x%04x 0x%04x", step, event, xMask, yMask, fullMask);
	}
    }

    private static int step = 0;
    private static ArrayList<Log> logArrayList = new ArrayList<Log>();

    public static void clear() {
	step = 0;
	logArrayList.clear();
    }

    public static void add(String event, int xMask, int yMask, int fullMask) {
	logArrayList.add(new Log(step++, event, xMask, yMask, fullMask));
    }

    public static Log[] getLogArray() {
	return (Log[]) logArrayList.toArray(new Log[] {});
    }
}
