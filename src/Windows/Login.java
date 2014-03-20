package Windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;


public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JLabel userNameLabel;
	private JTextField userName;
	private JLabel passwordLabel;
	private JPasswordField password;
	private JButton button;
	public String name;
	public Statement stmt;
	
	
	public Login(){
		setTitle("Login");
		initComponents();
	}
	
	private void initComponents(){
		
		mainPanel = new JPanel();
		userNameLabel = new JLabel("User name:");
		userName = new JTextField();
		passwordLabel = new JLabel("Password:");
		password = new JPasswordField();
		button = new JButton("Log in");
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		add(mainPanel);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
		
		 GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
	        mainPanel.setLayout(mainPanelLayout);
	        mainPanelLayout.setHorizontalGroup(
	            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(mainPanelLayout.createSequentialGroup()
	                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(userNameLabel)
	                    .addComponent(passwordLabel))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                    .addComponent(userName)
	                    .addComponent(password, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addComponent(button)
	                .addGap(0, 65, Short.MAX_VALUE))
	        );
	        mainPanelLayout.setVerticalGroup(
	            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(mainPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(userNameLabel)
	                    .addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(passwordLabel)
	                    .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(button))
	                .addContainerGap(15, Short.MAX_VALUE))
	        );
	        pack();
	    }
	
	private void buttonActionPerformed(ActionEvent evt){
		name = userName.getText();
		String pass = String.valueOf(password.getPassword());
		
		if("".equals(pass) || "".equals(name)){
            JOptionPane.showMessageDialog(this, "Name or password is empty!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
		}else{
			login(name,pass);
		}
            
	}
	
	private void login(String name, String pass){
		String m_url="jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
        String m_driverName="oracle.jdbc.driver.OracleDriver";
        try{
            @SuppressWarnings({ "unused", "rawtypes" })
			Class drvClass = Class.forName(m_driverName);
            Connection con = DriverManager.getConnection(m_url,name,pass);
            stmt  = con.createStatement();
            setVisible(false);
            new Menu();
        }catch(Exception e){
        	JOptionPane.showMessageDialog(this, "Invalid Username/Password", "Login Denied", JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        }
        
	}
}
