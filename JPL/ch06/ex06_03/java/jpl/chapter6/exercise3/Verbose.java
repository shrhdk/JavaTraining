package jpl.chapter6.exercise3;

public interface Verbose {
	enum Level {
		SILENT, TERSE, NORMAL, VERBOSE
	}
	
	void setVerbosity(Level level);
	Level getVerbosity();
}
