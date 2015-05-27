package gui;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import mining.Miner;
import utils.Scriptable;
import core.GathererScript;
import fishing.Fisher;


public class GathererConfigFrame extends JFrame implements Scriptable {
	private static final long serialVersionUID = -830332177405013117L;
	private GathererScript script;
	
	public GathererConfigFrame(GathererScript script) {
		this.script = script;
		initUI();
	}
	
	public GathererScript getScript() {
		return script;
	}
	
	private void initUI() {
		setTitle("AGatherer Configuration");
		setSize(600, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		JTabbedPane jtp = new JTabbedPane();
		setTabs(jtp);
		getContentPane().add(jtp);
	}
	
	private void setTabs(JTabbedPane jtp) {
		jtp.addTab("Fishing", new GathererPanel<Fisher>(getScript().getFishers()));
		jtp.addTab("Mining", new GathererPanel<Miner>(getScript().getMiners()));
		
//		jtp.addTab("Smither", new GathererPanel<Smither>(getScript().getMiners()));
//		jtp.addTab("Woodcutter", new GathererPanel<Woodcutter>(getScript().getMiners()));
//		jtp.addTab("Misc", new GathererPanel<Miscer>(getScript().getMiners()));
	}
}
