package Windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import Controller.DataBase;
import DataPicker.DatePicker;

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
				int nvrgetyear = Integer.parseInt(nvryeartext.getText());
				String nvrgetcolor = nvrcolortext.getText();
				int nvrgetid = Integer.parseInt(nvridtext.getText());
				if("".equals(nvrgetid) || "".equals(nvrgetcolor) || "".equals(nvrgetyear) || "".equals(nvrgetmodel) || "".equals(nvrgetmaker) || "".equals(serial_no)){
					 JOptionPane.showMessageDialog(nvr, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						if(db.find_vehicle(stmt, serial_no)){
							JOptionPane.showMessageDialog(nvr, "Car has Already exist", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
						}else{
							db.add_vehicle(login.con, serial_no, nvrgetmaker, nvrgetmodel, nvrgetyear, nvrgetcolor, nvrgetid);
							AddOwner();
							nvr.setVisible(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

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
					try {
						if(db.find_people(stmt, owner_id)){
							db.add_owner(login.con, owner_id, serial_no);
							AddSOwner();
						}else{
							AddPersonInfo();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					try {
						if(db.find_people(stmt, sowner_id)){
							db.add_secondary_owner(login.con, sowner_id, serial_no);
						}else{
							AddPersonInfo();
							db.add_secondary_owner(login.con, sowner_id, serial_no);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
		JButton apica = new JButton("calander");
		apica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				apibirt.setText(new DatePicker(api).setPickedDate());
			}
		});
		apiadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String apigetname = apinamet.getText();
				int apigethei = Integer.parseInt(apiheit.getText());
				int apigetwei = Integer.parseInt(apiweit.getText());
				String apigeteye = apieyet.getText();
				String apigethair = apihairt.getText();
				String apigetaddr = apiaddrt.getText();
				String apigetgen = apigent.getText();
				Date apigetbir = null;
				try {
					apigetbir = getdate(apibirt.getText());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if("".equals(apigetaddr) || "".equals(apigetbir) || "".equals(apigeteye) || "".equals(apigetgen) || "".equals(apigethair) || "".equals(apigetwei) || "".equals(apigetname) || "".equals(apigethei)){
					 JOptionPane.showMessageDialog(api, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						db.add_people(login.con, owner_id, apigetname, apigethei, apigetwei, apigeteye, apigethair, apigetaddr, apigetgen, apigetbir);
						db.add_owner(login.con, owner_id, serial_no);
						AddSOwner();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			                    .addComponent(apigen))
			            .addPreferredGap(ComponentPlacement.RELATED)
			            .addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
			                    .addComponent(apinamet,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(apiheit)
			                    .addComponent(apiweit)
			                    .addComponent(apieyet)
			                    .addComponent(apihairt)
			                    .addComponent(apiaddrt)
			                    .addComponent(apigent))
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
	            				.addComponent(apigent)
	            				.addComponent(apiadd))
	            				.addContainerGap(15, Short.MAX_VALUE))
	        );
	    JPanel apipanel2 = new JPanel();
		GroupLayout apilayout2 = new GroupLayout(apipanel2);
		apipanel2.setLayout(apilayout2);
		apilayout2.setHorizontalGroup(
				apilayout2.createSequentialGroup()
					.addComponent(apibir)
					.addGap(18)
					.addComponent(apibirt,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addComponent(apica)
		);
		apilayout2.setVerticalGroup(
				apilayout2.createSequentialGroup()
					.addGroup(apilayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(apibir)
							.addComponent(apibirt)
							.addComponent(apica))
		);
		api.add(apipanel,BorderLayout.NORTH);
		api.add(apipanel2,BorderLayout.CENTER);
		api.pack();
		
	}
	
	public void AutoTransaction(){
		final JFrame at = new JFrame();
		at.setTitle("Auto Transaction");
		JPanel atpanel = new JPanel();
		JLabel ats = new JLabel("Seller");
		JLabel atb = new JLabel("Buyer");
		final JTextField atst = new JTextField();
		final JTextField atbt = new JTextField();
		JButton atadd = new JButton("Add");
		atadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String atgets = atst.getText();
				String atgetb = atbt.getText();
				if ("".equals(atgetb) || "".equals(atgets)){
					 JOptionPane.showMessageDialog(at, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						if(db.find_people(stmt, atgetb) || db.find_people(stmt, atgets)){
							if(db.find_owner_a(stmt, atgets)){
								db.delect_owner(login.con, atgets);
								ResultSet re = db.give_vehicleno(stmt, atgets);
								while(re.next()){
									db.add_owner(login.con, atgetb, re.getString("vehicle_id"));
								}
							}else{
								JOptionPane.showMessageDialog(at, "Seller doesnt own a car", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(at, "Owner or seller does not exist", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					at.setVisible(false);
					secondarybs();
				}
			}
		});
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
	
	public void secondarybs(){
		final JFrame bs = new JFrame();
		bs.setTitle("Secondary Buyer/Seller Registraion");
		JPanel bspanel = new JPanel();
		JLabel bss = new JLabel("Seller");
		JLabel bsb = new JLabel("Buyer");
		final JTextField bsst = new JTextField();
		final JTextField bsbt = new JTextField();
		JButton bsadd = new JButton("Add");
		bsadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String atgets = bsst.getText();
				String atgetb = bsbt.getText();
				if ("".equals(atgetb) || "".equals(atgets)){
					 JOptionPane.showMessageDialog(bs, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						if(db.find_people(stmt, atgetb) || db.find_people(stmt, atgets)){
							if(db.find_owner_b(stmt, atgets)){
								db.delect_secondary_owner(login.con, atgets);
								db.add_secondary_owner(login.con, atgetb, serial_no);
							}else{
								JOptionPane.showMessageDialog(bs, "Seller doesn't own a car", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(bs, "Seller or Owner Des not exist", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					bs.setVisible(false);
					secondarybs();
				}
			}
		});
		JLabel bsnote = new JLabel("Seondary Buyer/Seller(Optional)");
		GroupLayout bslayout = new GroupLayout(bspanel);
		bspanel.setLayout(bslayout);
		bslayout.setHorizontalGroup(
				bslayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(bslayout.createSequentialGroup()
	                .addGroup(bslayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(bss)
	                    .addComponent(bsb))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(bslayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                    .addComponent(bsst)
	                    .addComponent(bsbt, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addComponent(bsadd)
	                .addGap(0, 65, Short.MAX_VALUE))
	        );
		bslayout.setVerticalGroup(
				bslayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(bslayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(bslayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(bss)
	                    .addComponent(bsst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(bslayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(bsb)
	                    .addComponent(bsbt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(bsadd))
	                .addContainerGap(15, Short.MAX_VALUE))
	        );
		JPanel bspanel2 = new JPanel();
		bspanel2.add(bsnote);
		bs.add(bspanel,BorderLayout.NORTH);
		bs.add(bspanel2,BorderLayout.CENTER);
		bs.pack();
		bs.setVisible(true);
	}
	
	public void DriverLicenceRegistration(){
		final JFrame dlr = new JFrame();
		dlr.setTitle("Driver Licence Registration");
		JPanel dlrpanel = new JPanel();
		JLabel dlrsin = new JLabel("Sin");
		JLabel dlrlicence = new JLabel("Licence_no");
		JLabel dlrclass = new JLabel("Class");
		JLabel dlrphoto = new JLabel("Photo");
		JLabel dlrida = new JLabel("Issuing_Date");
		JLabel dlreda = new JLabel("Expireing_Data");
		final JTextField dlrsint = new JTextField();
		final JTextField dlrlicencet = new JTextField();
		final JTextField dlrclasst = new JTextField();
		final JTextField dlrphotot = new JTextField();
		final JTextField dlridat = new JTextField();
		final JTextField dlredat  = new JTextField();
		JButton dlradd = new JButton("Add");
		dlradd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dlrgetsin = dlrsint.getText();
				String dlrgetlicence = dlrlicencet.getText();
				String dlrgetclass = dlrclasst.getText();
				String dlrgetphoto = dlrphotot.getText();
				Date dlrgetida = null;
				Date dlrgeteda = null;
				try {
					dlrgetida = getdate(dlridat.getText());
					dlrgeteda = getdate(dlredat.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if("".equals(dlrgetsin) || "".equals(dlrgetlicence) || "".equals(dlrgetclass) || "".equals(dlrgetphoto) || "".equals(dlrgetida) || "".equals(dlrgeteda)){
					 JOptionPane.showMessageDialog(dlr, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						if (!(db.find_people(stmt, dlrgetsin))){
							AddPersonInfo2();
						}else{
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		JButton dlrica = new JButton("Calander");
		JButton dlreca = new JButton("Calander");
		dlrica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dlridat.setText(new DatePicker(dlr).setPickedDate());
			}
		});
		dlreca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dlredat.setText(new DatePicker(dlr).setPickedDate());
			}
		});
		GroupLayout dlrlayout = new GroupLayout(dlrpanel);
		dlrpanel.setLayout(dlrlayout);
	    dlrlayout.setHorizontalGroup(
	    		dlrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addGroup(dlrlayout.createSequentialGroup()
	    				.addGroup(dlrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    						.addComponent(dlrsin)
			                    .addComponent(dlrlicence)
			                    .addComponent(dlrclass)
			                    .addComponent(dlrphoto))
			            .addPreferredGap(ComponentPlacement.RELATED)
			            .addGroup(dlrlayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	    						.addComponent(dlrsint,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(dlrlicencet)
			                    .addComponent(dlrclasst)
			                    .addComponent(dlrphotot))
			            .addGap(18, 18, 18)
			            .addComponent(dlradd)
			            .addGap(0, 65, Short.MAX_VALUE))
	    );
	    dlrlayout.setVerticalGroup(
	    		dlrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(dlrlayout.createSequentialGroup()
	            		.addContainerGap()
	            		.addGroup(dlrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(dlrsin)
	            				.addComponent(dlrsint))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(dlrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(dlrlicence)
	            				.addComponent(dlrlicencet))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(dlrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(dlrclass)
	            				.addComponent(dlrclasst))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(dlrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(dlrphoto)
	            				.addComponent(dlrphotot)
	            				.addComponent(dlradd))
	            				.addContainerGap(15, Short.MAX_VALUE))
	        );
	    JPanel dlrpanel2 = new JPanel();
	    GroupLayout dlrlayout2 = new GroupLayout(dlrpanel2);
		dlrpanel2.setLayout(dlrlayout2);
		dlrlayout2.setHorizontalGroup(
				dlrlayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(dlrlayout2.createSequentialGroup()
	                .addGroup(dlrlayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(dlrida)
	                    .addComponent(dlreda))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(dlrlayout2.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                    .addComponent(dlridat)
	                    .addComponent(dlredat, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addGroup(dlrlayout2.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                		.addComponent(dlrica)
	                		.addComponent(dlreca)))
	        );
		dlrlayout2.setVerticalGroup(
				dlrlayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(dlrlayout2.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(dlrlayout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(dlrida)
	                    .addComponent(dlridat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(dlrica))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(dlrlayout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(dlreda)
	                    .addComponent(dlredat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(dlreca)))
	        );
	    dlr.add(dlrpanel,BorderLayout.NORTH);
	    dlr.add(dlrpanel2,BorderLayout.CENTER);
	    dlr.pack();
		dlr.setVisible(true);
	}
	
	public void AddPersonInfo2(){
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
		JButton apica = new JButton("calander");
		apica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				apibirt.setText(new DatePicker(api).setPickedDate());
			}
		});
		apiadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String apigetname = apinamet.getText();
				int apigethei = Integer.parseInt(apiheit.getText());
				int apigetwei = Integer.parseInt(apiweit.getText());
				String apigeteye = apieyet.getText();
				String apigethair = apihairt.getText();
				String apigetaddr = apiaddrt.getText();
				String apigetgen = apigent.getText();
				Date apigetbir = null;
				try {
					apigetbir = getdate(apibirt.getText());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if("".equals(apigetaddr) || "".equals(apigetbir) || "".equals(apigeteye) || "".equals(apigetgen) || "".equals(apigethair) || "".equals(apigetwei) || "".equals(apigetname) || "".equals(apigethei)){
					 JOptionPane.showMessageDialog(api, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					/*try {
						db.add_people(login.con, owner_id, apigetname, apigethei, apigetwei, apigeteye, apigethair, apigetaddr, apigetgen, apigetbir);
						api.setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}*/
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
			                    .addComponent(apigen))
			            .addPreferredGap(ComponentPlacement.RELATED)
			            .addGroup(apilayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
			                    .addComponent(apinamet,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(apiheit)
			                    .addComponent(apiweit)
			                    .addComponent(apieyet)
			                    .addComponent(apihairt)
			                    .addComponent(apiaddrt)
			                    .addComponent(apigent))
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
	            				.addComponent(apigent)
	            				.addComponent(apiadd))
	            				.addContainerGap(15, Short.MAX_VALUE))
	        );
	    JPanel apipanel2 = new JPanel();
		GroupLayout apilayout2 = new GroupLayout(apipanel2);
		apipanel2.setLayout(apilayout2);
		apilayout2.setHorizontalGroup(
				apilayout2.createSequentialGroup()
					.addComponent(apibir)
					.addGap(18)
					.addComponent(apibirt,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addComponent(apica)
		);
		apilayout2.setVerticalGroup(
				apilayout2.createSequentialGroup()
					.addGroup(apilayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(apibir)
							.addComponent(apibirt)
							.addComponent(apica))
		);
		api.add(apipanel,BorderLayout.NORTH);
		api.add(apipanel2,BorderLayout.CENTER);
		api.pack();
		
	}
	
	public void ViolationRecord(){
		final JFrame vr = new JFrame();
		vr.setTitle("Violation Record");
		JPanel vrpanel = new JPanel();
		JLabel vrtno = new JLabel("Ticket_no");
		JLabel vrvno = new JLabel("Violator_no");
		JLabel vrveno = new JLabel("Vehicle_no");
		JLabel vrono = new JLabel("Office_no");
		JLabel vrty = new JLabel("Type");
		JLabel vrda =new JLabel("Date");
		JLabel vrp = new JLabel("Place");
		JLabel vrd = new JLabel("Description");
		final JTextField vrtnot = new JTextField();
		final JTextField vrvnot = new JTextField();
		final JTextField vrvenot = new JTextField();
		final JTextField vronot = new JTextField();
		final JTextField vrtyt = new JTextField();
		final JTextField vrdat = new JTextField();
		final JTextField vrpt = new JTextField();
		final JTextField vrdt = new JTextField();
		JButton vradd = new JButton("Add");
		vradd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int vrgettno = Integer.parseInt(vrtnot.getText());
				String vrgetvno = vrvnot.getText();
				String vrgetveno = vrvenot.getText();
				String vrgetono = vronot.getText();
				String vrgetty = vrtyt.getText();
				String vrgetd = vrdat.getText();
				String vrgetp = vrpt.getText();
				if("".equals(vrgetvno) || "".equals(vrgetveno) || "".equals(vrgetono) || "".equals(vrgetty) || "".equals(vrdat) || "".equals(vrgetp) || "".equals(vrgetd) || "".equals(vrgettno)){
					 JOptionPane.showMessageDialog(vr, "Some information missed", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						if (!db.find_vehicle(stmt, vrgetveno) || !db.find_people(stmt, vrgetvno) || !db.find_people(stmt, vrgetono)){
							JOptionPane.showMessageDialog(vr, "Does not Exist", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		JButton vrca = new JButton("Calendar");
		vrca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vrdat.setText(new DatePicker(vr).setPickedDate());
			}
		});
		GroupLayout vrlayout = new GroupLayout(vrpanel);
		vrpanel.setLayout(vrlayout);
	    vrlayout.setHorizontalGroup(
	    		vrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addGroup(vrlayout.createSequentialGroup()
	    				.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    						.addComponent(vrtno)
			                    .addComponent(vrvno)
			                    .addComponent(vrveno)
			                    .addComponent(vrono)
			                    .addComponent(vrty)
			                    .addComponent(vrp)
			                    .addComponent(vrd))
			            .addPreferredGap(ComponentPlacement.RELATED)
			            .addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	    						.addComponent(vrtnot,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
			                    .addComponent(vrvnot)
			                    .addComponent(vrvenot)
			                    .addComponent(vronot)
			                    .addComponent(vrtyt)
			                    .addComponent(vrpt)
			                    .addComponent(vrdt))
			            .addGap(18, 18, 18)
			            .addComponent(vradd)
			            .addGap(0, 65, Short.MAX_VALUE))
	    );
	    vrlayout.setVerticalGroup(
	            vrlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(vrlayout.createSequentialGroup()
	            		.addContainerGap()
	            		.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(vrtno)
	            				.addComponent(vrtnot))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(vrvno)
	            				.addComponent(vrvnot))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(vrveno)
	            				.addComponent(vrvenot))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(vrono)
	            				.addComponent(vronot))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(vrty)
	            				.addComponent(vrtyt))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(vrp)
	            				.addComponent(vrpt))
	            		.addPreferredGap(ComponentPlacement.RELATED)
	            		.addGroup(vrlayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	            				.addComponent(vrd)
	            				.addComponent(vrdt)
	            				.addComponent(vradd))
	            				.addContainerGap(15, Short.MAX_VALUE))
	        );
	    JPanel vrpanel2 = new JPanel();
		GroupLayout vrlayout2 = new GroupLayout(vrpanel2);
		vrpanel2.setLayout(vrlayout2);
		vrlayout2.setHorizontalGroup(
				vrlayout2.createSequentialGroup()
					.addComponent(vrda)
					.addGap(18)
					.addComponent(vrdat,GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addComponent(vrca)
		);
		vrlayout2.setVerticalGroup(
				vrlayout2.createSequentialGroup()
					.addGroup(vrlayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(vrda)
							.addComponent(vrdat)
							.addComponent(vrca))
		);
		
		vr.add(vrpanel,BorderLayout.NORTH);
		vr.add(vrpanel2,BorderLayout.CENTER);
		vr.pack();
		vr.setVisible(true);
	}
	
	public void SearchEngine(){
		JFrame se = new JFrame();
		se.setTitle("Search Engine");
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JPanel buttonRow = new JPanel();
        //Use default FlowLayout.
        buttonRow.add(SearchPeople(true));
        tabbedPane.addTab("Search A Person", buttonRow);
        
        JPanel lol = new JPanel();
        lol.add(SearchSin(true));
        tabbedPane.addTab("Search Sin", lol);
        
        JPanel labelAndComponent = new JPanel();
        //Use default FlowLayout.
        labelAndComponent.add(Searchvr(true));
        tabbedPane.addTab("Search Violation Record", labelAndComponent);
 
        JPanel buttonAndComponent = new JPanel();
        //Use default FlowLayout.
        buttonAndComponent.add(VehicleHistory(true));
        tabbedPane.addTab("Search Vehicle History", buttonAndComponent);
        
        //Add tabbedPane to this panel.
        se.add(tabbedPane, BorderLayout.CENTER);
        se.pack();
		se.setVisible(true);
	}
	
	 protected JPanel SearchPeople(boolean changeAlignment) {
	        JPanel pane = new JPanel();
	        JLabel label = new JLabel("Enter a Name");
	        final JTextField text = new JTextField();
	        JButton search = new JButton("Search");
	        search.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String name = text.getText();
					try {
						ResultSet rs = db.find_people_by_name(stmt, name);
						while(rs.next()){
							System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getInt(3)+","+rs.getInt(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	        pane.add(label);
	        pane.add(text);
	        pane.add(search);
	        return pane;
	    }
	 
	 protected JPanel Searchvr(boolean doItRight) {
	        JPanel pane = new JPanel();
	        JLabel label = new JLabel("Enter a Serial_no");
	        final JTextField text = new JTextField();
	        JButton search = new JButton("Search");
	        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	        pane.add(label);
	        pane.add(text);
	        pane.add(search);
	        return pane;
	 }
	 
	 protected JPanel VehicleHistory(boolean doItRight) {
	        JPanel pane = new JPanel();
	        JLabel label = new JLabel("Enter a Vehicle");
	        final JTextField text = new JTextField();
	        JButton search = new JButton("Search");
	        search.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String name = text.getText();
					try {
						ResultSet rs = db.search_history(stmt, name);
						while(rs.next()){
							for (int i = 1; i <= 6; i++){
								Object o = rs.getObject(i);
								if (o != null){
									String value = o.toString();
									System.out.println(value);
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	        pane.add(label);
	        pane.add(text);
	        pane.add(search);
	        return pane;
	 }
	 
	 protected JPanel SearchSin(boolean hehe){
		 JPanel pane = new JPanel();
	     JLabel label = new JLabel("Enter Sin");
	     final JTextField text = new JTextField();
	     JButton search = new JButton("Search");
	        search.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String name = text.getText();
					try {
						ResultSet rs = db.search_people(stmt, "0", name);
						while(rs.next()){
							System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getInt(3)+","+rs.getInt(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getDate(9));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	     pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	     pane.add(label);
	     pane.add(text);
	     pane.add(search);
	     return pane;
		 
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
	
	public Date getdate(String haha) throws ParseException{
		SimpleDateFormat hehe = new SimpleDateFormat("dd-Mm-yyyy");
		java.util.Date date = hehe.parse(haha);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		return sqlStartDate;
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
