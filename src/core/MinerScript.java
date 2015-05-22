package core;
import gui.MinerConfigFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "abotter", info = "Coal Miner", name = "AMiner", version = 0.2, logo = "")
public class MinerScript extends Script{
	MinerConfigFrame configFrame;
	Miner selectedMiner = new MiningGuildMiner(this);

	@Override
	public void onStart() {
		log("AMINER STARTED");
		
		EventQueue.invokeLater(new Runnable() {
	        
            @Override
            public void run() {
            	configFrame = new MinerConfigFrame();
            	configFrame.setVisible(true);
            }
        });
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
		g.setColor(Color.BLUE);
		g.drawString("TEST STRING", 7, 40);
		super.onPaint(g);
	}

}
