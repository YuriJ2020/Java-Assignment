/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Family;
import Classes.Members;
import Classes.Single;
import Utilities.ConnectionDetails;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author ppunme
 */
public class AddMember extends JFrame implements ActionListener, ItemListener
{
    public static ArrayList<Members> list;
    
    //Buttons
    private JButton btnAdd, btnClear, btnExit;

    //TextFields
    private JTextField txfID, txfFirst, txfLast, txfEmail, txfPhone;
    private JTextField txfAddress, txfSuburb, txfPostcode;

    //Radio Buttons
    private JRadioButton rbtMale, rbtFemale;
    private JRadioButton rbtSingle, rbtFamily;

    //Combo Box
    private JComboBox cboStateLoad;
    private JComboBox cboPackLoad;
    private JComboBox cboAgentLoad;

    //Labels
    private JLabel lblHeading, lblID, lblFirst, lblLast, lblGender, lblEmail, lblPhone;
    
    //Image
    private JLabel lblImage;
    private ImageIcon image;
    
    //set a color object using RGB
    Color myColor1 = new Color(255, 255, 255); //white
    Color myColor2 = new Color(234, 235, 237); //grey
    Color myColor3 = new Color(45,81,142);     //blue
    
    Font f1 = new Font("Helvetica", Font.BOLD,30);
    Font f2 = new Font("Helvetica", Font.PLAIN,16);
    
    private boolean validate = true;
    private final static double BASE_FEE = 50.00; 
    private int nextAvailableID;
    
    private String gender;
    private String type;
    private int indexState, indexPack, indexAgent;
    private String stateLoad, packLoad, agentLoad;

    MainMenu parentMenu;
    
    //set default font
    public static void setUIFont(FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }
	
    public AddMember(MainMenu menu, ArrayList<Members> membersArray) 
    {  	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Add a new Member");
        this.setVisible(true);
        this.setBounds(550,100,485,625);
        
        //set default font
        setUIFont(new FontUIResource(new Font("Helvetica", 0, 16))); 
        
        //re-create the main menu when this frame is closed
    	parentMenu = menu;
        list = membersArray;

        nextAvailableID = list.size() + 1;
        
        //create panel for Heading
        JPanel pnlHeading = new JPanel();
        pnlHeading.setBackground(myColor3);
        pnlHeading.add(lblHeading = new JLabel());
        lblHeading.setForeground(Color.white);
        lblHeading.setFont(f1);
        lblHeading.setText("Add a new Member");

        //Create ButtonGroup for the radio buttons
        //Make only one radio button can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtMale);
        genderGroup.add(rbtFemale);
        
        //create panal for Gender
        JPanel pnlGender = new JPanel();
        pnlGender.setLayout(new GridLayout(1, 2));
        pnlGender.setBackground(myColor1);
        pnlGender.add(rbtMale = new JRadioButton("Male"));
        pnlGender.add(rbtFemale = new JRadioButton("Female"));
        
        //create panel for type of membership
        JPanel pnlTypeRadio = new JPanel();
        pnlTypeRadio.setLayout(new GridLayout(1, 2));
        pnlTypeRadio.setBackground(myColor1);
        pnlTypeRadio.add(rbtSingle = new JRadioButton("Single"));
        pnlTypeRadio.add(rbtFamily = new JRadioButton("Family"));
        
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(rbtSingle);
        typeGroup.add(rbtFamily);

        //create panel for the Main
    	JPanel pnlData = new JPanel(new GridBagLayout());
        pnlData.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding
        pnlData.setBackground(myColor1);
        GridBagConstraints g = new GridBagConstraints();
        g.weightx = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        
        lblID = new JLabel("ID  ", SwingConstants.RIGHT);
        g.gridx = 0;
        g.gridy = 0;
        pnlData.add(lblID, g);
        
        g.gridx = 1;
        g.gridy = 0;
    	pnlData.add(txfID = new JTextField(Integer.toString(nextAvailableID)), g);
    	txfID.setEnabled(false); //make this field not interactive
        
        g.gridx = 0;
        g.gridy = 1;
    	pnlData.add(lblFirst = new JLabel("First name  ", SwingConstants.RIGHT), g);
        
        g.gridx = 1;
        g.gridy = 1;
    	pnlData.add(txfFirst = new JTextField(), g);
        
        g.gridx = 0;
        g.gridy = 2;
    	pnlData.add(lblLast = new JLabel("Last name  ", SwingConstants.RIGHT), g);
        
        g.gridx = 1;
        g.gridy = 2;
        pnlData.add(txfLast = new JTextField(), g);
        
        g.gridx = 0;
        g.gridy = 3;
    	pnlData.add(lblGender = new JLabel("Gender  ", SwingConstants.RIGHT), g);
        
        g.gridx = 1;
        g.gridy = 3;
        pnlData.add(pnlGender, g);
        
        g.gridx = 0;
        g.gridy = 4;
        pnlData.add(lblEmail = new JLabel("Email  ", SwingConstants.RIGHT), g);
        
        g.gridx = 1;
        g.gridy = 4;
        pnlData.add(txfEmail = new JTextField(10), g);
        
        g.gridx = 0;
        g.gridy = 5;
        pnlData.add(lblPhone = new JLabel("Phone  ", SwingConstants.RIGHT), g);
        
        g.gridx = 1;
        g.gridy = 5;
        pnlData.add(txfPhone = new JTextField(10), g);
        
        //Create panal for Address
        //Combo box
        String sLoad[] = {"New South Wales", "Western Australia", "Queensland", "South Australia", "Victoria", "Tasmania", "Make a Selection"};
        cboStateLoad = new JComboBox(sLoad);
        cboStateLoad.setSelectedItem("Make a Selection");
        
        //Panel
        JPanel pnlAddress = new JPanel(new GridLayout(0,2,5,5)); // row,col,hgap,vgap
        pnlAddress.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding
        pnlAddress.setBackground(myColor2);

        pnlAddress.add(new JLabel());
        pnlAddress.add(new JLabel("ADDRESS", SwingConstants.CENTER));
        pnlAddress.add(new JLabel("Address   ", SwingConstants.RIGHT));
        pnlAddress.add(txfAddress = new JTextField());    
        pnlAddress.add(new JLabel("Suburb   ", SwingConstants.RIGHT));
        pnlAddress.add(txfSuburb = new JTextField());
        pnlAddress.add(new JLabel("State   ", SwingConstants.RIGHT));
        pnlAddress.add(cboStateLoad);
        pnlAddress.add(new JLabel("Postcode   ", SwingConstants.RIGHT));
        pnlAddress.add(txfPostcode = new JTextField());
        
        //Create panel for the type detail
        String pLoad[] = {"Saver", "Bronze", "Ultimate", "Make a Selection"};
        cboPackLoad = new JComboBox(pLoad);
        cboPackLoad.setSelectedItem("Make a Selection");
        
        String aLoad[] = {"Make a Selection"};
        cboAgentLoad = new JComboBox(aLoad);
        cboAgentLoad.setSelectedItem("Maka a Selection");
        
    	JPanel pnlType = new JPanel();
        pnlType.setBackground(myColor1);
        pnlType.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlType.setLayout(new GridLayout(0,2));
        pnlType.add(new JLabel("Type     ", SwingConstants.RIGHT));
        pnlType.add(pnlTypeRadio);
        pnlType.add(new JLabel("Package     ", SwingConstants.RIGHT));
    	pnlType.add(cboPackLoad);
        pnlType.add(new JLabel("Agent     ", SwingConstants.RIGHT));
    	pnlType.add(cboAgentLoad);
        
        //Create panel for the buttons
    	JPanel pnlButtons = new JPanel();
        pnlButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlButtons.add(btnExit = new JButton("Main menu"));
    	pnlButtons.add(btnAdd = new JButton("Add"));
    	pnlButtons.add(btnClear = new JButton("Clear"));
        
        //Create panal for combine all panel
        JPanel pnlAll = new JPanel();
        pnlAll.setLayout(new GridLayout(3, 0));
        pnlAll.add(pnlData);
        pnlAll.add(pnlAddress);
        pnlAll.add(pnlType);
        
        //Add panels to the JFrame container
    	Container c = this.getContentPane();
    	c.add(pnlHeading, BorderLayout.NORTH);
        c.add(pnlAll, BorderLayout.CENTER);
    	c.add(pnlButtons, BorderLayout.SOUTH);
        
        //register buttons to accept events
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);
        rbtMale.addActionListener(this);
        rbtFemale.addActionListener(this);
        rbtSingle.addActionListener(this);
        rbtFamily.addActionListener(this);
        cboStateLoad.addActionListener(this);
        cboPackLoad.addActionListener(this);
        cboAgentLoad.addActionListener(this);
        //addItemListener
        cboStateLoad.addItemListener(this);
        cboPackLoad.addItemListener(this);
        cboAgentLoad.addItemListener(this);
    }	
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnAdd)
        {
            addNewMember();
        }
        if(e.getSource() == btnClear)
        {
            clearForm();
        }
        if(e.getSource() == btnExit)
        {
            mainMenu();
        }
        if (e.getSource() == rbtMale)
        {
            gender = "Male"; //retrieve value from RadioButtons
        }
        if (e.getSource() == rbtFemale)
        {
            gender = "Female"; //retrieve value from RadioButtons
        }
        if (e.getSource() == rbtSingle)
        {
            type = "Single"; //retrieve value from RadioButtons
        }
        if (e.getSource() == rbtFamily)
        {
            type = "Family"; //retrieve value from RadioButtons
        }
        if(e.getSource() == cboStateLoad)
        {
            stateLoad = (String) cboStateLoad.getSelectedItem();
        }
        if(e.getSource() == cboPackLoad)
        {
            packLoad = (String) cboPackLoad.getSelectedItem();
        }
        if(e.getSource() == cboAgentLoad)
        {
            agentLoad = (String) cboAgentLoad.getSelectedItem();
        }
    }
	
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource()==cboStateLoad)
        {
            indexState = cboStateLoad.getSelectedIndex();
        }
        if(e.getSource()== cboPackLoad)
        {
            indexPack = cboPackLoad.getSelectedIndex();
        }
        if(e.getSource()== cboAgentLoad)
        {
            indexAgent = cboAgentLoad.getSelectedIndex();
        }
    }
	
    //add Single member to the array
    public void addNewMember()
    {
        //insert into arrayList
        int id;
        String first, last, email, phone, address, suburb, postcode;

        id = list.size()+1;

        //get data from TextFields and ComboBox
        first = txfFirst.getText();
        last = txfLast.getText();
        email = txfEmail.getText();
        phone = txfPhone.getText();
        address = txfAddress.getText();
        suburb = txfSuburb.getText();
        postcode = txfPostcode.getText();

        validate = true;

        System.out.println("State: " + stateLoad + "\nPackage: " + packLoad + "\nAgent: " + agentLoad + "\nType: " + type + "\ngender: " + gender);
        
        //check to see if each TextField have data
        if(!(first.equals("") || last.equals("") || email.equals("") || phone.equals("") || address.equals("")
                || suburb.equals("") || postcode.equals("")))
        {
            System.out.println("Done1");
            if(!(stateLoad.equals("Make a Selection")))
            {
                System.out.println("Done2");
                if(!(packLoad.equals("Make a Selection")))
                {
                    System.out.println("Done3");
                    //if(!(agentLoad.equals("Make a Selection")))
                    //{
                        if(type.equals("Single")){ //type: Single
                            //add to ArrayList
                            list.add(new Single(id, first, last, gender, email, phone, address, suburb, stateLoad, postcode, BASE_FEE, packLoad));
                       

                            nextAvailableID++;
                            list.get(id-1).calcFees(); //update the BASE_FEE($50) for this type of member

                            validate = false; //all data valid
                            addToDatabase();
                        }
                        else //type: Family
                        {
                            //add to ArrayList
                            list.add(new Family(id, first, last, gender, email, phone, address, suburb, stateLoad, postcode, BASE_FEE, id));
                            

                            nextAvailableID++;
                            list.get(id-1).calcFees(); //update the BASE_FEE($50) for this type of member

                            JOptionPane.showMessageDialog(null, "Add Family");
                            validate = false; //all data valid
                        }
                        
                    //}                  
                }
            }
        }

        if(validate)
        {
            JOptionPane.showMessageDialog(null, "Please complete all options on the form");		
        }
        else
        {   			   			
            JOptionPane.showMessageDialog(null, "Member Record successfully added"); 			
            clearForm();  //clear Frame for next record
        }
    }
   
	
    // clear the Frame
    private void clearForm()  
    {
        //re-set all components
        txfID.setText(Integer.toString(nextAvailableID));
        txfFirst.setText("");	
        txfLast.setText("");		
        rbtMale.setSelected(false);
        rbtFemale.setSelected(false);
        txfEmail.setText("");
        txfPhone.setText("");
        txfAddress.setText("");
        txfSuburb.setText("");
        cboStateLoad.setSelectedItem("Make a Selection");	
        txfPostcode.setText("");
        rbtSingle.setSelected(false);
        rbtFamily.setSelected(false);
        cboPackLoad.setSelectedItem("Make a Selection");	
        cboAgentLoad.setSelectedItem("Make a Selection");	
    }
 
    public void addToDatabase(){
        //Insert into database
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        
        int MYSQL_DUPLICATE_PK = 1062; //1062 error code for duplicate primary key
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            System.out.println("Connected to the database");
            
            //create table if it not exist
            //create tblMember
            String tblMember = "CREATE TABLE if not exists tblMember(" 
                    + "memberId int not null primary key, "
                    + "first varchar(50), "
                    + "last varchar(50),"
                    + "gender varchar(20),"
                    + "email varchar(50),"
                    + "phone varchar(20))"; 
            System.out.println(tblMember);        
            stmt.executeUpdate(tblMember);
            System.out.println("tblEmployees has been created");
            
            //create tblAddress
            String tblAddress = "CREATE TABLE if not exists tblAddress("
                    + "addressID int not null AUTO_INCREMENT,"
                    + "address varchar(50)," 
                    + "suburb varchar(50),"
                    + "state varchar(50),"
                    + "postcode int, " 
                    + "memberID int, "
                    + "PRIMARY KEY (addressID),"
                    + "FOREIGN KEY (memberId) References tblMember(memberId))";
            System.out.println(tblAddress);        
            stmt.executeUpdate(tblAddress);
            System.out.println("tblAddress has been created");
            
            //create tblSingle
            String tblSingle = "CREATE TABLE if not exists tblSingle("
                    + "singleID int not null AUTO_INCREMENT,"
                    + "type varchar(50), " 
                    + "baseFee double,"
                    + "memberID int,"
                    + "PRIMARY KEY (singleID),"
                    + "FOREIGN KEY (memberId) References tblMember(memberId))";
            System.out.println(tblSingle);        
            stmt.executeUpdate(tblSingle);
            System.out.println("tblSingle has been created");
            
            //create tblFamily
            String tblFamily = "CREATE TABLE if not exists tblFamily("
                    + "familyID int not null AUTO_INCREMENT,"
                    + "noMember int, " 
                    + "memberID int,"
                    + "PRIMARY KEY (familyID),"
                    + "FOREIGN KEY (memberId) References tblMember(memberId))";
            System.out.println(tblFamily);        
            stmt.executeUpdate(tblFamily);
            System.out.println("tblFamily has been created");
            
            //check memberID in database
            String sql = "SELECT * from tblMember where memberID=" + txfID.getText();
            r = stmt.executeQuery(sql);
            System.out.println(r);
            
            if(r.next())
            { //found this member id in database
                JOptionPane.showMessageDialog(null, "This member id is already exist");
            }
            else
            {
                //insert data to member table
                sql = "INSERT INTO tblMember (memberID, first, last, gender, email, phone) values"
                        + "('" + txfID.getText() + "','" + txfFirst.getText() + "','" + txfLast.getText() 
                        + "','" + gender + "','" + txfEmail.getText() + "','" + txfPhone.getText() + "')";
                stmt.executeUpdate(sql);

                //insert data to address table
                sql = "INSERT INTO tblAddress (address, suburb, state, postcode, memberID) values ('"+ txfAddress.getText()
                        + "','" + txfSuburb.getText() + "','" + stateLoad + "','" + txfPostcode.getText() + "','" + txfID.getText() + "')";
                stmt.executeUpdate(sql);
                
                //insert data to single table
                sql = "INSERT INTO tblSingle (type, baseFee, memberID) values ('"+ packLoad + "','" + BASE_FEE + "','" + txfID.getText() + "')";
                stmt.executeUpdate(sql);
                System.out.println("Member details have been added to database");
            }
            
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println("ERROR: " + sqlE.getMessage());
            if(sqlE.getErrorCode() == MYSQL_DUPLICATE_PK){ //duplicate primary key
                JOptionPane.showMessageDialog(null, "Duplicate Member ID");
                txfID.requestFocusInWindow();
            }
        } finally {
            
        }
        
        try {
            if(stmt != null) {
                stmt.close();
            }
            System.out.println("Statement close");
        } catch (SQLException sqlE) {
            System.out.println("Error closing statement");
        }
        
        try{
            if (con != null) {
                con.close();
            }
            System.out.println("Connection close");
        } catch (SQLException sqlE) {
            System.out.println("Error Closing connection");
        }
    }
    
    // close addSingle frame and return to main menu
    public void mainMenu() 
    {
        parentMenu.setVisible(true);	
        this.dispose(); 
    }
}
