package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.Gatherer;

public class GathererPanel<G extends Gatherer> extends JPanel {
	private static final long serialVersionUID = -9003066035669073228L;
	private static Border border = BorderFactory.createLineBorder(new Color(50, 50, 50));
	private JList<G> gathererList;
	private JPanel optionsPanel;
	private JPanel gathererOptionsPanel;
	private JPanel controlPanel;
	private JButton startButton;
	private JButton stopButton;
	private G[] gatherers;
	
	GathererPanel(G[] gatherers) {
		this.gatherers = gatherers;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		gathererList = new JList<G>(gatherers);
		gathererList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gathererList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		gathererList.setBorder(BorderFactory.createTitledBorder(border, "List"));
		gathererList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					onGathererListChange(gathererList.getSelectedValue());
				}
			}
		});
		//list.setSelectedIndex(0);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.25;
		add(gathererList, c);
		
		optionsPanel = new JPanel(new BorderLayout());
		optionsPanel.setBorder(BorderFactory.createTitledBorder(border, "Options"));
		
		controlPanel = new JPanel();
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GathererSubject.notifyStop(gathererList.getSelectedValue());
			}
		});
		controlPanel.add(stopButton);
		
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GathererSubject.notifyStart(gathererList.getSelectedValue());
			}
		});
		controlPanel.add(startButton);
		
		optionsPanel.add(BorderLayout.SOUTH, controlPanel);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.75;
		add(optionsPanel, c);
	}

	protected JList<G> getGathererList() {
		return gathererList;
	}
	
	protected G[] getGatherers() {
		return gatherers;
	}
	
	protected void onGathererListChange(G newGatherer) {
		JPanel newOptions = new JPanel();
		
		for (Entry<String, Boolean> entry : newGatherer.getConfiguration().entrySet()){
			String key = entry.getKey();
			Boolean value = entry.getValue();
			
			JCheckBox jcb = new JCheckBox(key);
			jcb.setSelected(value);
			
			newOptions.add(jcb);
		}
		
		setGathererOptionsPanel(newOptions);
	}
	
	protected void setGathererOptionsPanel(JPanel newPanel) {
		if (gathererOptionsPanel != null) optionsPanel.remove(gathererOptionsPanel);
		
		gathererOptionsPanel = newPanel;

		optionsPanel.add(BorderLayout.CENTER, newPanel);
		optionsPanel.revalidate();
	}
}
