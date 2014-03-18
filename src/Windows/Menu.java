package Windows;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
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
	private static Login login;
	

	public Menu(){
		setTitle("Welcome! "+login.name);
		setVisible(true);
		createWindow();
	}
	
	//create window Menu
	public void createWindow(){
		
		panel = new JPanel();
		vRegistration = new JButton("  New Vehicle Registration ");
		transaction = new JButton("         Auto Transaction        ");
		lRegistration = new JButton("Driver Licence Registration");
		record = new JButton("          Violation Record         ");
		search = new JButton("           Search Engine           ");
		vRegistration.addActionListener(this);
		transaction.addActionListener(this);
		lRegistration.addActionListener(this);
		record.addActionListener(this);
		search.addActionListener(this);
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
	
	public static void main(String args[]) {
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	login = new Login();
            	login.setVisible(true);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == vRegistration){
			new NewVehicleRegistration();
		}
		else if(e.getSource() == transaction){
			new AutoTransaction();
		}
		else if(e.getSource() == lRegistration){
			new DriverLicenceRegistration();
		}
		else if(e.getSource() == record){
			new ViolationRecord();
		}
		else if(e.getSource() == search){
			new SearchEngine();
		}
	}
}
