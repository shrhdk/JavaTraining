package jpl.chapter1.exercise14;

public class TwoPlugsWalkman extends Walkman {
    Plug subPlug;
    
    public void connectSubPlug(final Plug subPlug){
	this.subPlug = subPlug;
    }
    
    public void connectSubPlug(){
	this.subPlug = null;
    }
}
