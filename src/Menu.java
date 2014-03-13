import javax.swing.*;
public class Menu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton vRegistration;
	private JButton transaction;
	private JButton lRegistration;
	private JButton record;
	private JButton search;
	

	public Menu(){
		setTitle("Menu");
		setVisible(true);
		createWindow();
	}
	
	public void createWindow(){
		
		panel = new JPanel();
		vRegistration = new JButton("  New Vehicle Registration  ");
		transaction = new JButton("       Auto Transaction        ");
		lRegistration = new JButton("Driver Licence Registration");
		record = new JButton("Violation Record");
		search = new JButton("Search Engine");
		add(panel);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
		BoxLayout panelLayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(panelLayout);
		panel.add(vRegistration);
		panel.add(transaction);
		panel.add(lRegistration);
		panel.add(record);
		panel.add(search);
		pack();
	}
}
