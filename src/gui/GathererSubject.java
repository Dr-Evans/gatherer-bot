package gui;

import java.util.ArrayList;

import core.Gatherer;

public class GathererSubject {
	static ArrayList<GathererListener> gls = new ArrayList<GathererListener>();
	
	public static void addGathererListener(GathererListener gl) {
		gls.add(gl);
	}
	
	public static void removeGathererListener(GathererListener gl){
		gls.remove(gl);
	}
	
	public static void notifyStart(Gatherer g) {
		for (GathererListener gl : gls) {
			gl.onGathererStart(g);
		}
	}
	
	public static void notifyStop(Gatherer g) {
		for (GathererListener gl : gls) {
			gl.onGathererStop(g);
		}
	}
}
