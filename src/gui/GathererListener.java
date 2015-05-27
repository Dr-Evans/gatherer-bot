package gui;

import core.Gatherer;

public interface GathererListener {
	public void onGathererStart(Gatherer g);
	public void onGathererStop(Gatherer g);
}
