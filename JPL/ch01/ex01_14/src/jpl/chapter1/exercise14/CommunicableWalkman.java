package jpl.chapter1.exercise14;

public class CommunicableWalkman extends TwoPlugsWalkman {

    Voice voice;

    public void talk(Voice voice) {
	this.voice = voice;
    }

    public Voice listen() {
	return this.voice;
    }

    public static class Voice {
    }
}
