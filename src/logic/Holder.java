package logic;

import sharedObject.Tickable;

public interface Holder extends Tickable , MouseInteractable{
	
	
	boolean isFinalized();
	
}
