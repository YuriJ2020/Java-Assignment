/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Members;
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
public class AddSingle extends JFrame implements ActionListener, ItemListener
{
    //buttons
    private JButton btnAdd, btnClear, btnExit;

    //TextFields
    private JTextField txfID, txfFirst, txfLast, txfEmail, txfPhone;
    private JTextField txfStreetNo, txfStreetName, txfSuburb, txfPostcode;

    //Radio Buttons
    private JRadioButton rbtMale, rbtFemale;
    
    //Combo Box
    private JComboBox cboStateLoad;
    private JComboBox cboTypeLoad;

    //Labels
    private JLabel lblHeading, lblID, lblFirst, lblLast, lblGender, lblEmail, lblPhone;

    //Combo Box
    private String[] sLoad = {"Part-Time","Full-Time","Make a Selection"};
    private JComboBox cboStudyLoad = new JComboBox(sLoad);

    private JLabel lblImage;
    private ImageIcon image;
    
    //set a color object using RGB
    Color myColor1 = new Color(255, 255, 255); //white
    Color myColor2 = new Color(234, 235, 237); //grey
    Color myColor3 = new Color(163, 186, 195); //green
    
    Font f1 = new Font("Helvetica", Font.BOLD,30);
    Font f2 = new Font("Helvetica", Font.PLAIN,16);
    
    public static ArrayList<Members> list;	

    private boolean validate = true;
    private final static double BASE_FEE = 1000.00; 
    private String gender;
    private int indexState;
    private String stateLoad;
    private int indexType;
    private String typeLoad;
    private int nextAvailableID;

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
	
    public AddSingle(MainMenu menu, ArrayList<Members> membersArray) 
    {  	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Single Membership");
        this.setVisible(true);
        this.setBounds(550,100,485,625);
        
        
        //set default font
        setUIFont(new FontUIResource(new Font("Helvetica", 0, 16))); 
        
        //re-create the main menu when this frame is closed
    	parentMenu = menu;
        
        //generate Member ID
        list = membersArray;
        
        nextAvailableID = 1;
        /*f(list.size() == 0){
            nextAvailableID = 1;
        }
        else{
            nextAvailableID = list.size() + 1;
        }*/
        
        //create panel for Heading
        JPanel pnlHeading = new JPanel();
        pnlHeading.setBackground(myColor3);
        pnlHeading.add(lblHeading = new JLabel());
        lblHeading.setForeground(Color.white);
        lblHeading.setFont(f1);
        lblHeading.setText("Add Single Membership");

        //create ButtonGroup for the radio buttons
        JPanel pnlRadio = new JPanel();
        pnlRadio.setLayout(new GridLayout(1, 2));
        pnlRadio.setBackground(myColor1);
        pnlRadio.add(rbtMale = new JRadioButton("Male"));
        pnlRadio.add(rbtFemale = new JRadioButton("Female"));
        
        //only one radio button can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtMale);
        genderGroup.add(rbtFemale);

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
        pnlData.add(pnlRadio, g);
        
        g.gridx = 0;
        g.gridy = 4;
        pnlData.add(lblGender = new JLabel("Email  ", SwingConstants.RIGHT), g);
        
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
        pnlAddress.add(new JLabel("Street No   ", SwingConstants.RIGHT));
        pnlAddress.add(txfStreetNo = new JTextField());
        pnlAddress.add(new JLabel("Street Name   ", SwingConstants.RIGHT));
        pnlAddress.add(txfStreetName = new JTextField());    
        pnlAddress.add(new JLabel("Suburb   ", SwingConstants.RIGHT));
        pnlAddress.add(txfSuburb = new JTextField());
        pnlAddress.add(new JLabel("State   ", SwingConstants.RIGHT));
        pnlAddress.add(cboStateLoad);
        pnlAddress.add(new JLabel("Postcode   ", SwingConstants.RIGHT));
        pnlAddress.add(txfPostcode = new JTextField());
        
        //Create panel for the type detail
        String tLoad[] = {"Active Saver ($85 per month)", "Bronze Plus ($100 per month)", "Ultimate ($120)", "Make a Selection"};
        cboTypeLoad = new JComboBox(tLoad);
        cboTypeLoad.setSelectedItem("Make a Selection");
        
    	JPanel pnlType = new JPanel();
        pnlType.setBackground(myColor1);
        pnlType.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlType.setLayout(new GridLayout(0,3));
        pnlType.add(new JLabel("Member Type:     ", SwingConstants.LEFT));
    	pnlType.add(cboTypeLoad);
        pnlType.add(new JLabel(""));
        
        //Create panel for the buttons
    	JPanel pnlButtons = new JPanel();
        pnlButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
    	pnlButtons.add(btnAdd = new JButton("Add"));
    	pnlButtons.add(btnClear = new JButton("Clear"));
    	pnlButtons.add(btnExit = new JButton("Main menu"));
        
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
        cboStateLoad.addActionListener(this);
        cboTypeLoad.addActionListener(this);
        //addItemListener
        cboStateLoad.addItemListener(this);
        cboTypeLoad.addItemListener(this);
        
    }	
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnAdd)
        {
            addStudent();
        }
        if(e.getSource() == btnClear)
        {
            clearForm();
        }
        if (e.getSource() == rbtMale)
        {
            gender = "Male"; //retrieve value from RadioButtons
        }
        if (e.getSource() == rbtFemale)
        {
            gender = "Female"; //retrieve value from RadioButtons
        }
        if(e.getSource() == cboStateLoad)
        {
            stateLoad = (String) cboStateLoad.getSelectedItem();
        }
        if(e.getSource() == cboTypeLoad)
        {
            typeLoad = (String) cboTypeLoad.getSelectedItem();
        }
        if(e.getSource() == btnExit)
        {
            mainMenu();
        }
    }
	
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource()==cboStateLoad)
        {
            indexState = cboStateLoad.getSelectedIndex();
        }
        if(e.getSource()== cboTypeLoad)
        {
            indexType = cboTypeLoad.getSelectedIndex();
        }
    }
	
    //add Local student to the array
    public void addStudent()
    {
        int id;
        String first, last, email, phone, streetNo, streetName, suburb, postcode;

        id = list.size()+1;

        //get data from TextFields and ComboBox
        first = txfFirst.getText();
        last = txfLast.getText();
        email = txfEmail.getText();
        phone = txfEmail.getText();
        streetNo = txfStreetNo.getText();
        streetName = txfStreetName.getText();
        suburb = txfSuburb.getText();
        indexState = cboStateLoad.getSelectedIndex();
        postcode = txfPostcode.getText();
        indexType = cboTypeLoad.getSelectedIndex();

        validate = true;

        //check to see if each TextField have data
        if(!(first.equals("")|| last.equals("")))
        {
            JOptionPane.showMessageDialog(null, id + " " + first + " " + last + " " + gender + " " + email + " " + phone + " " + streetNo + streetName + suburb + stateLoad + postcode + " " + typeLoad);
            //add to ArrayList
            //classList.add(new Single(id, fName, lName, BASE_FEE, course, studyLoad));
            //JOptionPane.showMessageDialog(null, list);
            nextAvailableID++;
            //list.get(id-1).calcFees(); //update the BASE_FEE($1000) for this type of student

            validate = false; //all data valid	
           
        }

        if(validate)
        {
            JOptionPane.showMessageDialog(null, "Please complete all options on the form");		
        }
        else
        {   			   			
            JOptionPane.showMessageDialog(null, "Student Record successfully added"); 			
            //clear Frame for next record
            clearForm(); 
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
        cboStudyLoad.setSelectedItem("Make a Selection");						
    }
	
    // close addProduct frame and return to main menu
    public void  mainMenu() 
    {
        parentMenu.setVisible(true);	
        this.dispose(); //release resources back to the OS
    }
    
    /*public static void main(String[] args) {

        AddSingle test = new AddSingle(list);
    }*/ 
    
 
}
