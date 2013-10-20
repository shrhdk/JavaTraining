package jpl.chapter1.exercise14;

public class Walkman {
    Plug plug;
    Cassette cassette;
    State state = State.STOP;
    
    public void connectPlug(final Plug plug) {
	this.plug = plug;
    }

    public void disconnectPlug(final Plug plug) {
	this.plug = null;
    }

    public void insertCassette(final Cassette cassette) {
	this.cassette = cassette;
    }
    
    public void ejectCassette(){
	this.cassette = null;
    }

    public void startMusic() {
	this.state = State.START;
    }

    public void stopMusic() {
	this.state = State.STOP;
    }

    public void pauseMusic() {
	state = State.PAUSE;
    }

    public enum State {
	START,
	STOP,
	PAUSE
    }
    
    public static class Plug {
    }

    public static interface Cassette {
    }
}
