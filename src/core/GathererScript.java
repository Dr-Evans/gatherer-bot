package core;
import gui.GathererConfigFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import mining.*;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "abotter", info = "Coal Miner", name = "AMiner", version = 0.5, logo = "")
public class GathererScript extends Script{
	private static final int cursorDimension = 12;
	private static final int cursorValue = cursorDimension / 2;
	GathererConfigFrame configFrame;
	Gatherer gatherer = new MiningGuildMiner(this);

	@Override
	public void onStart() {
		log("AMINER STARTED");
		
		experienceTracker.start(Skill.MINING);
		
//		EventQueue.invokeLater(new Runnable() {
//	        
//            @Override
//            public void run() {
//            	configFrame = new MinerConfigFrame();
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
		log("AMINER EXITED");
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
		final String aMiner = "AMiner - " + gatherer;
		final String state = "State = " + gatherer.getState();
		final String runningTime = "Time Running = " + LocalTime.ofSecondOfDay(TimeUnit.MILLISECONDS.toSeconds(experienceTracker.getElapsed(Skill.MINING))).toString();
		final String totalGainedXP = "Total EXP Gained = " + experienceTracker.getGainedXP(Skill.MINING) + " XP (" + experienceTracker.getGainedLevels(Skill.MINING) + " levels)";
		final String gainedXPPerHour = "EXP/Hour = " + experienceTracker.getGainedXPPerHour(Skill.MINING);
		final String timeToLevel = "Time to Level = " + LocalTime.ofSecondOfDay(TimeUnit.MILLISECONDS.toSeconds(experienceTracker.getTimeToLevel(Skill.MINING))).toString();
		
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
