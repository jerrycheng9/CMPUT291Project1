package Windows;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

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
	
	public void NewVehicleRegistration(){
		final JFrame nvr = new JFrame();
		JPanel nvrpanel = new JPanel();
		JLabel nvrserial = new JLabel("Serial_no");
		JLabel nvrmaker = new JLabel("Maker");
		JLabel nvrmodel = new JLabel("Model");
		JLabel nvryear = new JLabel("Year");
		JLabel nvrcolor = new JLabel("Color");
		JLabel nvrid = new JLabel("Type_id");
		final JTextField nvrserialtext = new JTextField();
		final JTextField nvrmakertext = new JTextField();
		final JTextField nvrmodeltext = new JTextField();
		final JTextField nvryeartext = new JTextField();
		final JTextField nvrcolortext = new JTextField();
		final JTextField nvridtext = new JTextField();
		JButton nvradd = new JButton("Add");
		nvradd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nvrgetserial = nvrserialtext.getText();
				String nvrgetmaker = nvrmakertext.getText();
				String nvrgetmodel = nvrmodeltext.getText();
				String nvrgetyear = nvryeartext.getText();
				String nvrgetcolor = nvrcolortext.getText();
				String nvrgetid = nvridtext.getText();
				if("".equals(nvrgetid) || "".equals(nvrgetcolor) || "".equals(nvrgetyear) || "".equals(nvrgetmodel) || "".equals(nvrgetmaker) || "".equals(nvrgetserial)){
					 JOptionPane.showMessageDialog(nvr, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					SearchEngine();
				}
			}
		});
		nvr.setTitle("New Vehicle Registration");
		nvr.setVisible(true);
		nvrpanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		GroupLayout nvrlayout = new GroupLayout(nvrpanel);
		nvrpanel.setLayout(nvrlayout);
	    nvrlayout.setHorizontalGroup(
	    		nvrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addGroup(nvrlayout.createSequentialGroup()
	    				.addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    						.addComponent(nvrserial)
			                    .addComponent(nvrmaker)
			                    .addComponent(nvrmodel)
			                    .addComponent(nvryear)
			                    .addComponent(nvrcolor)
			                    .addComponent(nvrid))
			            .addPreferredGap(ComponentPlacement.RELATED)
			            .addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
			                    .addComponent(nvrserialtext,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(nvrmakertext,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(nvrmodeltext,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(nvryeartext,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(nvrcolortext,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(nvridtext,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
			            .addGap(18, 18, 18)
			            .addComponent(nvradd)
			            .addGap(0, 65, Short.MAX_VALUE))
	    );
	    nvrlayout.setVerticalGroup(
	            nvrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(nvrlayout.createSequentialGroup()
	            		.addContainerGap()
	            		.addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(nvrserial)
	            				.addComponent(nvrserialtext))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(nvrmaker)
	            				.addComponent(nvrmakertext))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(nvrmodel)
	            				.addComponent(nvrmodeltext))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(nvryear)
	            				.addComponent(nvryeartext))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(nvrcolor)
	            				.addComponent(nvrcolortext))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(nvrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(nvrid)
	            				.addComponent(nvridtext)
	            				.addComponent(nvradd))
	            				.addContainerGap(15, Short.MAX_VALUE))
	        );
        GroupLayout nvrreallayout = new GroupLayout(nvr.getContentPane());
        nvr.getContentPane().setLayout(nvrreallayout);
        nvrreallayout.setHorizontalGroup(
            nvrreallayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(nvrpanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        nvrreallayout.setVerticalGroup(
            nvrreallayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(nvrpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        nvr.pack();
	}
	
	public void AutoTransaction(){
		JFrame at = new JFrame();
		at.setTitle("Auto Transaction");
		at.setVisible(true);
	}
	
	public void DriverLicenceRegistration(){
		JFrame dlr = new JFrame();
		dlr.setTitle("Driver Licence Registration");
		dlr.setVisible(true);
	}
	
	public void ViolationRecord(){
		JFrame vr = new JFrame();
		vr.setTitle("Violation Record");
		vr.setVisible(true);
	}
	
	public void SearchEngine(){
		JFrame se = new JFrame();
		se.setTitle("Search Engine");
		se.setVisible(true);
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
			NewVehicleRegistration();
		}
		else if(e.getSource() == transaction){
			AutoTransaction();
		}
		else if(e.getSource() == lRegistration){
			DriverLicenceRegistration();
		}
		else if(e.getSource() == record){
			ViolationRecord();
		}
		else if(e.getSource() == search){
			SearchEngine();
		}
	}
}
