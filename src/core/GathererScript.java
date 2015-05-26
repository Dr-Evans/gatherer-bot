package core;
import fishing.KaramjaLobsterFisher;
import gui.GathererConfigFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "abotter", info = "Gathering script", name = "AGatherer", version = 0.5, logo = "")
public class GathererScript extends Script {
	private static final int cursorDimension = 12;
	private static final int cursorValue = cursorDimension / 2;
	GathererConfigFrame configFrame;
	Gatherer gatherer = new KaramjaLobsterFisher(this);

	@Override
	public void onStart() {
		log("AGatherer started");
		
		experienceTracker.start(gatherer.getSkill());
		
//		EventQueue.invokeLater(new Runnable() {
//	        
//            @Override
//            public void run() {
//            	configFrame = new GathererConfigFrame();
//            	configFrame.setVisible(true);
//            }
//        });
	}
	
	@Override
	public int onLoop() {
		try {
			gatherer.execute();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return random(500, 800);
	}
	
	@Override
	public void onExit() {
		log("AGatherer exited.");
		configFrame.dispatchEvent(new WindowEvent(configFrame, WindowEvent.WINDOW_CLOSING));
	}
	
	@Override
	public void onPaint(Graphics2D g){
		configGraphics(g);
		drawInfo(g);
		drawCursor(g);
		
		super.onPaint(g);
	}
	
	private void configGraphics(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.setFont(new Font("Courier New", Font.PLAIN, 12));
	}
	
	private void drawInfo(Graphics2D g) {
		final String aMiner = "AGatherer - " + gatherer;
		final String state = "State = " + gatherer.getState();
		final String runningTime = "Time Running = " + LocalTime.ofSecondOfDay(TimeUnit.MILLISECONDS.toSeconds(experienceTracker.getElapsed(gatherer.getSkill()))).toString();
		final String totalGainedXP = "Total EXP Gained = " + experienceTracker.getGainedXP(gatherer.getSkill()) + " XP (" + experienceTracker.getGainedLevels(gatherer.getSkill()) + " levels)";
		final String gainedXPPerHour = "EXP/Hour = " + experienceTracker.getGainedXPPerHour(gatherer.getSkill());
		final String timeToLevel = "Time to Level = " + LocalTime.ofSecondOfDay(TimeUnit.MILLISECONDS.toSeconds(experienceTracker.getTimeToLevel(gatherer.getSkill()))).toString();
		
		g.drawString(aMiner, 7, 28);
		g.drawString(state, 7, 40);
		g.drawString(runningTime, 7, 52); 
		g.drawString(totalGainedXP, 7, 64);
		g.drawString(gainedXPPerHour, 7, 76);
		g.drawString(timeToLevel, 7, 88);
	}
	
	private void drawCursor(Graphics2D g) {
		Point p = mouse.getPosition();
		final int mouseX = (int) p.getX();
		final int mouseY = (int) p.getY();
		final int xSub = mouseX - cursorValue;
		final int xAdd = mouseX + cursorValue;
		final int ySub = mouseY - cursorValue;
		final int yAdd = mouseY + cursorValue;
		
		g.drawLine(xSub, ySub, xAdd, yAdd);
		g.drawLine(xSub, yAdd, xAdd, ySub);
	}
}
