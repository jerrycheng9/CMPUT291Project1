package Windows;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

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

import Controller.DataBase;

public class Menu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton vRegistration;
	private JButton transaction;
	private JButton lRegistration;
	private JButton record;
	private JButton search;
	private static Login login;
	private static DataBase db;
	private Statement stmt;
	
	private String serial_no;
	private String owner_id;
	

	public Menu(){
		db = new DataBase();
		try {
			stmt = login.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Welcome! "+login.name);
		setVisible(true);
		createWindow();
	}
	
	//create window Menu
	public void createWindow(){
		
		JPanel panel = new JPanel();
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
				serial_no = nvrserialtext.getText();
				String nvrgetmaker = nvrmakertext.getText();
				String nvrgetmodel = nvrmodeltext.getText();
				String nvrgetyear = nvryeartext.getText();
				String nvrgetcolor = nvrcolortext.getText();
				String nvrgetid = nvridtext.getText();
				if("".equals(nvrgetid) || "".equals(nvrgetcolor) || "".equals(nvrgetyear) || "".equals(nvrgetmodel) || "".equals(nvrgetmaker) || "".equals(serial_no)){
					 JOptionPane.showMessageDialog(nvr, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				} else {
					AddOwner();
					nvr.setVisible(false);
				}
			}
		});
		nvr.setTitle("New Vehicle Registration");
		nvr.add(nvrpanel);
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
			                    .addComponent(nvrmakertext)
			                    .addComponent(nvrmodeltext)
			                    .addComponent(nvryeartext)
			                    .addComponent(nvrcolortext)
			                    .addComponent(nvridtext))
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
        nvr.pack();
        nvr.setVisible(true);
	}
	
	public void AddOwner(){
		final JFrame ao = new JFrame();
		ao.setTitle("Primar Owner");
		ao.setVisible(true);
		JPanel aopanel = new JPanel();
		JLabel po = new JLabel("Primary owner id:");
		final JTextField pot = new JTextField();
		JButton poadd = new JButton("Add");
		poadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				owner_id = pot.getText();
				if ("".equals(owner_id)){
					JOptionPane.showMessageDialog(ao, "Information Missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					AddSOwner();
					ao.setVisible(false);
				}
			}
		});
		ao.add(aopanel);
		aopanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		GroupLayout aolayout = new GroupLayout(aopanel);
		aopanel.setLayout(aolayout);
		aolayout.setHorizontalGroup(
				aolayout.createSequentialGroup()
					.addComponent(po)
					.addGap(18)
					.addComponent(pot,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(poadd)
					.addGap(0, 65, Short.MAX_VALUE)
		);
		aolayout.setVerticalGroup(
				aolayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(po)
					.addComponent(pot)
					.addComponent(poadd)
		);
		ao.pack();
	}
	
	public void AddSOwner(){
		final JFrame aso = new JFrame();
		aso.setTitle("Secondary Owner");
		aso.setVisible(true);
		JPanel apipanel = new JPanel();
		JLabel so = new JLabel("Secondary owner id:");
		JLabel note = new JLabel("Note: Just close window if doesnt have secondary owner");
		final JTextField sot = new JTextField();
		JButton soadd = new JButton("Add");
		soadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sowner_id = sot.getText();
				if ("".equals(sowner_id)){
					JOptionPane.showMessageDialog(aso, "Information Missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					aso.setVisible(false);
					AddSOwner();
				}
			}
		});
		GroupLayout apilayout = new GroupLayout(apipanel);
		apipanel.setLayout(apilayout);
		apilayout.setHorizontalGroup(
				apilayout.createSequentialGroup()
					.addComponent(so)
					.addGap(18)
					.addComponent(sot,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(soadd)
					.addGap(0, 65, Short.MAX_VALUE)
		);
		apilayout.setVerticalGroup(
				apilayout.createSequentialGroup()
					.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(so)
							.addComponent(sot)
							.addComponent(soadd))
		);
		JPanel apipanel2 = new JPanel();
		apipanel2.add(note);
		aso.add(apipanel,BorderLayout.NORTH);
		aso.add(apipanel2,BorderLayout.SOUTH);
		aso.pack();
	}
	
	public void AddPersonInfo(){
		final JFrame api = new JFrame();
		JPanel apipanel = new JPanel();
		JLabel apiname = new JLabel("Name");
		JLabel apihei = new JLabel("Height");
		JLabel apiwei = new JLabel("Weight");
		JLabel apieye = new JLabel("Eyecolor");
		JLabel apihair = new JLabel("haircolor");
		JLabel apiaddr = new JLabel("Address");
		JLabel apigen = new JLabel("Gender");
		JLabel apibir = new JLabel("Birthday");
		final JTextField apinamet = new JTextField();
		final JTextField apiheit = new JTextField();
		final JTextField apiweit = new JTextField();
		final JTextField apieyet = new JTextField();
		final JTextField apihairt = new JTextField();
		final JTextField apiaddrt = new JTextField();
		final JTextField apigent = new JTextField();
		final JTextField apibirt = new JTextField();
		JButton apiadd = new JButton("Add");
		apiadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String apigetname = apinamet.getText();
				String apigethei = apiheit.getText();
				String apigetwei = apiweit.getText();
				String apigeteye = apieyet.getText();
				String apigethair = apihairt.getText();
				String apigetaddr = apiaddrt.getText();
				String apigetgen = apigent.getText();
				String apigetbir = apibirt.getText();
				if("".equals(apigetaddr) || "".equals(apigetbir) || "".equals(apigeteye) || "".equals(apigetgen) || "".equals(apigethair) || "".equals(apigetwei) || "".equals(apigetname) || "".equals(apigethei)){
					 JOptionPane.showMessageDialog(api, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		api.setTitle("Add Person Infomation");
		api.setVisible(true);
		api.add(apipanel);
		apipanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		GroupLayout apilayout = new GroupLayout(apipanel);
		apipanel.setLayout(apilayout);
		apilayout.setHorizontalGroup(
				apilayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addGroup(apilayout.createSequentialGroup()
	    				.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    						.addComponent(apiname)
			                    .addComponent(apihei)
			                    .addComponent(apiwei)
			                    .addComponent(apieye)
			                    .addComponent(apihair)
			                    .addComponent(apiaddr)
			                    .addComponent(apigen)
			                    .addComponent(apibir))
			            .addPreferredGap(ComponentPlacement.RELATED)
			            .addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
			                    .addComponent(apinamet,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(apiheit)
			                    .addComponent(apiweit)
			                    .addComponent(apieyet)
			                    .addComponent(apihairt)
			                    .addComponent(apiaddrt)
			                    .addComponent(apigent)
			                    .addComponent(apibirt))
			            .addGap(18, 18, 18)
			            .addComponent(apiadd)
			            .addGap(0, 65, Short.MAX_VALUE))
	    );
		apilayout.setVerticalGroup(
				apilayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(apilayout.createSequentialGroup()
	            		.addContainerGap()
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apiname)
	            				.addComponent(apinamet))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apihei)
	            				.addComponent(apiheit))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apiwei)
	            				.addComponent(apiweit))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apieye)
	            				.addComponent(apieyet))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apihair)
	            				.addComponent(apihairt))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apiaddr)
	            				.addComponent(apiaddrt))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apigen)
	            				.addComponent(apigent))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(apibir)
	            				.addComponent(apibirt)
	            				.addComponent(apiadd))
	            				.addContainerGap(15, Short.MAX_VALUE))
	        );
		api.pack();
		
	}
	
	public void AutoTransaction(){
		JFrame at = new JFrame();
		at.setTitle("Auto Transaction");
		JPanel atpanel = new JPanel();
		JLabel ats = new JLabel("Seller");
		JLabel atb = new JLabel("Buyer");
		JTextField atst = new JTextField();
		JTextField atbt = new JTextField();
		JButton atadd = new JButton();
		JLabel atnote = new JLabel("Primary Seller and Buyer");
		GroupLayout atlayout = new GroupLayout(atpanel);
		atpanel.setLayout(atlayout);
		atlayout.setHorizontalGroup(
				atlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(atlayout.createSequentialGroup()
	                .addGroup(atlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(ats)
	                    .addComponent(atb))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(atlayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                    .addComponent(atst)
	                    .addComponent(atbt, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addComponent(atadd)
	                .addGap(0, 65, Short.MAX_VALUE))
	        );
		atlayout.setVerticalGroup(
				atlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(atlayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(atlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(ats)
	                    .addComponent(atst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(atlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(atb)
	                    .addComponent(atbt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(atadd))
	                .addContainerGap(15, Short.MAX_VALUE))
	        );
		JPanel atpanel2 = new JPanel();
		atpanel2.add(atnote);
		at.add(atpanel,BorderLayout.NORTH);
		at.add(atpanel2,BorderLayout.CENTER);
		at.pack();
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
