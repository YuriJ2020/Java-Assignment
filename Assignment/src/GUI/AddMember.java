/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Agent;
import Classes.Family;
import Classes.Members;
import Classes.Single;
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
    public static ArrayList<Agent> agentList  = new ArrayList<>();
    
    //Buttons
    private JButton btnAdd, btnClear, btnExit;

    //TextFields
    private JTextField txfFirst, txfLast, txfEmail, txfPhone;
    private JTextField txfAddress, txfSuburb, txfPostcode, txfNoMember;

    //Radio Buttons
    private JRadioButton rbtMale, rbtFemale;
    private JRadioButton rbtSingle, rbtFamily;

    //Combo Box
    private JComboBox cboStateLoad;
    private JComboBox cboPackLoad;
    private JComboBox cboAgentLoad;

    //Labels
    private JLabel lblHeading, lblFirst, lblLast, lblGender, lblEmail, lblPhone;
    private JLabel lblPack, lblNoMember;
    
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
    public static String stateLoad, packLoad, agentLoad;
    private int agentID;

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
	
    public AddMember(MainMenu menu, ArrayList<Members> memberArray) 
    {  	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Add a new Member");
        this.setVisible(true);
        this.setBounds(550,100,485,625);
        
        //set default font
        setUIFont(new FontUIResource(new Font("Helvetica", 0, 16))); 
        
        //re-create the main menu when this frame is closed
    	parentMenu = menu;
        list = memberArray;
        
        //create panel for Heading
        JPanel pnlHeading = new JPanel();
        pnlHeading.setBackground(myColor3);
        pnlHeading.add(lblHeading = new JLabel());
        lblHeading.setForeground(Color.white);
        lblHeading.setFont(f1);
        lblHeading.setText("Add a new Member");

        //Create ButtonGroup for the radio buttons
        //create panal for Gender
        JPanel pnlGender = new JPanel();
        pnlGender.setLayout(new GridLayout(1, 2));
        pnlGender.setBackground(myColor1);
        pnlGender.add(rbtMale = new JRadioButton("Male"));
        pnlGender.add(rbtFemale = new JRadioButton("Female"));
        
        //Make only one radio button can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtMale);
        genderGroup.add(rbtFemale);
        
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
        
        //get agent name from database to Combobox
        cboAgentLoad = new JComboBox();
        Utilities.DataAccessLayer.getAgentToCombobox(cboAgentLoad);
        
    	JPanel pnlType = new JPanel();
        pnlType.setBackground(myColor1);
        pnlType.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlType.setLayout(new GridLayout(0,2));
        pnlType.add(new JLabel("Agent name    ", SwingConstants.RIGHT));
    	pnlType.add(cboAgentLoad);
        pnlType.add(new JLabel("Type     ", SwingConstants.RIGHT));
        pnlType.add(pnlTypeRadio);
        pnlType.add(lblPack = new JLabel("Package     ", SwingConstants.RIGHT));
    	pnlType.add(cboPackLoad);
        lblPack.setVisible(false);
        cboPackLoad.setVisible(false);
        pnlType.add(lblNoMember = new JLabel("No. of Family Member ", SwingConstants.RIGHT));
        pnlType.add(txfNoMember = new JTextField());
        System.out.println(txfNoMember);
        lblNoMember.setVisible(false);
        txfNoMember.setVisible(false);
        
        //Panel for the buttons
    	JPanel pnlButtons = new JPanel();
        pnlButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlButtons.add(btnExit = new JButton("Main menu"));
    	pnlButtons.add(btnAdd = new JButton("Add"));
    	pnlButtons.add(btnClear = new JButton("Clear"));

        //Create panal for combine all panel
        JPanel pnlCenter = new JPanel();
        pnlCenter.setLayout(new GridLayout(3, 0));
        pnlCenter.add(pnlData);
        pnlCenter.add(pnlAddress);
        pnlCenter.add(pnlType);
        
        //Add panels to the JFrame container
    	Container c = this.getContentPane();
    	c.add(pnlHeading, BorderLayout.NORTH);
        c.add(pnlCenter, BorderLayout.CENTER);
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
            lblPack.setVisible(true);
            cboPackLoad.setVisible(true);
            lblNoMember.setVisible(false);
            txfNoMember.setVisible(false);
        }
        if (e.getSource() == rbtFamily)
        {
            type = "Family"; //retrieve value from RadioButtons
            lblNoMember.setVisible(true);
            txfNoMember.setVisible(true);
            lblPack.setVisible(false);
            cboPackLoad.setVisible(false);
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
            System.out.println("Agent: " + agentLoad);
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
        int id, noMember;
        String first, last, email, phone, address, suburb, postcode;
        String output = "<html>";
        
        id = Integer.parseInt(txfID.getText());

        //get data from TextFields and ComboBox
        first = txfFirst.getText();
        last = txfLast.getText();
        email = txfEmail.getText();
        phone = txfPhone.getText();
        address = txfAddress.getText();
        suburb = txfSuburb.getText();
        postcode = txfPostcode.getText();

        validate = true;
        
        if(Utilities.Validation.CheckNull(first, last, gender, email, phone, address, suburb, postcode, type) ||
            agentLoad == null || agentLoad.equals("Make a Selection") || stateLoad == null || stateLoad.equals("Make a Selection")) 
        {
            output += "Please complete all options on the form<br>";
            validate = false;
        }
        else{
            if(!Utilities.Validation.isString(first, last)){
            output += "-  First name and Last name cannot contains numeric<br>";
            validate = false;    
            }
            if(!Utilities.Validation.checkEmail(email)){
                output += "-  Invalid Email<br>";
                validate = false;
            }
            if(!Utilities.Validation.checkPhone(phone)){
                output += "-  Invalid Phone number<br>";
                validate = false;
            }
            if(!Utilities.Validation.checkPost(postcode)){
                output += "-  Post codes must be in the range of 2000 – 9999<br>";
                validate = false;
            }
            
        }
        if(validate){
            //get agent ID from comboBox
            agentID = Utilities.DataAccessLayer.getAgentID();
            System.out.println(agentID);
            if(type.equals("Single")){ //type: Single
                if(!(packLoad.equals("Make a Selection") || packLoad == null)){
                    //add to ArrayList
                    System.out.println(id+"\t"+first+"\t"+last+"\t"+gender+"\t"+email+"\t"+phone+"\t"+address+"\t"+suburb+"\t"+stateLoad+"\t"+postcode+"\t"+BASE_FEE+"\t"+packLoad+"\t"+type+"\t"+ agentID);
                    list.add(new Single(id, first, last, gender, email, phone, address, suburb, stateLoad, postcode, BASE_FEE, packLoad, type, agentID));
                   
                    JOptionPane.showMessageDialog(null, "Old Fee: " + list.get(list.size()-1).getFee());
                    System.out.println("Size: " + list.size());
                    System.out.println("CalcFee: " + list.get(list.size()-1));
                    list.get(list.size()-1).calcFees(); //update the BASE_FEE($50) for this type of member
                    JOptionPane.showMessageDialog(null, "New Fee: " + list.get(list.size()-1).getFee());
                    
                    Single s = new Single(id, first, last, gender, email, phone, address, suburb, stateLoad, postcode, BASE_FEE, packLoad, type, agentID);
                    Utilities.DataAccessLayer.addSingleToDatabase(s);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please complete all fields");
                }
            }
            
            else //type: Family
            {
                if(!(txfNoMember.equals("")))
                {
                    noMember = Integer.parseInt(txfNoMember.getText());
                    //add to ArrayList
                    //list.add(new Family(id, first, last, gender, email, phone, address, suburb, stateLoad, postcode, BASE_FEE, noMember, type, agentID));

                    //list.get(id).calcFees(); //update the BASE_FEE($50) for this type of member

                    Family f = new Family(id, first, last, gender, email, phone, address, suburb, stateLoad, postcode, BASE_FEE, noMember, type, agentID);
                    Utilities.DataAccessLayer.addFamilyToDatabase(f);
                    JOptionPane.showMessageDialog(null, "Add Family");
                } 
                else{// txfNoMember is blank
                    JOptionPane.showMessageDialog(null, "Please complete all fields");
                }
            }
            JOptionPane.showMessageDialog(null, "Member Record successfully added"); 			
            clearForm();  //clear Frame for next record  
        } else {
            JOptionPane.showMessageDialog(null, output);
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
    
    // close addMember frame and return to main menu
    public void mainMenu() 
    {
        parentMenu.setVisible(true);	
        this.dispose(); 
    }
}
