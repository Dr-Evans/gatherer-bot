package gui;
import javax.swing.JFrame;


public class MinerConfigFrame extends JFrame{
	private static final long serialVersionUID = 1L;
//	private JButton applyButton;

	public MinerConfigFrame() {
		initUI();
	}
	
	private void initUI() {
		setTitle("AMiner Configuration");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
//		applyButton = new JButton("Apply");
//		applyButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				//TODO: Apply changes
//			}
//		});
	}
}
