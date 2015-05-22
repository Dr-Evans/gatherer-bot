package core;
import gui.MinerConfigFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "abotter", info = "Coal Miner", name = "AMiner", version = 0.3, logo = "")
public class MinerScript extends Script{
	MinerConfigFrame configFrame;
	Miner selectedMiner = new MiningGuildMiner(this);

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
			selectedMiner.execute();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return random(200, 300);
	}
	
	@Override
	public void onExit() {
		log("AMINER EXITED");
		configFrame.dispatchEvent(new WindowEvent(configFrame, WindowEvent.WINDOW_CLOSING));
	}
	
	@Override
	public void onPaint(Graphics2D g){
		final String aMiner = "AMiner";
		final String runningTime = "Time Running = " + LocalTime.ofSecondOfDay(TimeUnit.MILLISECONDS.toSeconds(experienceTracker.getElapsed(Skill.MINING))).toString();
		final String totalGainedXP = "Total EXP Gained = " + experienceTracker.getGainedXP(Skill.MINING) + " XP (" + experienceTracker.getGainedLevels(Skill.MINING) + " levels)";
		final String gainedXPPerHour = "EXP/Hour = " + experienceTracker.getGainedXPPerHour(Skill.MINING);
		final String timeToLevel = "Time to Level = " + LocalTime.ofSecondOfDay(TimeUnit.MILLISECONDS.toSeconds(experienceTracker.getTimeToLevel(Skill.MINING))).toString();
		
		g.setColor(Color.CYAN);
		g.setFont(new Font("Courier New", Font.PLAIN, 12));
		g.drawString(aMiner, 7, 28);
		g.drawString(runningTime, 7, 40); 
		g.drawString(totalGainedXP, 7, 52);
		g.drawString(gainedXPPerHour, 7, 64);
		g.drawString(timeToLevel, 7, 76);
		super.onPaint(g);
	}
}
