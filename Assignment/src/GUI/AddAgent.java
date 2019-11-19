/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Agent;
import static GUI.AddMember.agentLoad;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author ppunme
 */
public class AddAgent extends JFrame implements ActionListener
{
    public static ArrayList<Agent> agentList;
    
    //buttons
    private JButton btnAdd, btnClear, btnExit;

    //TextFields
    private JTextField txfID, txfFirst, txfLast, txfPhone;

    //Labels
    private JLabel lblHeading, lblID, lblFirst, lblLast, lblPhone;

    //set a color object using RGB
    Color myColor1 = new Color(255, 255, 255); //white
    Color myColor2 = new Color(234, 235, 237); //grey
    Color myColor3 = new Color(45,81,142);     //blue
    
    Font f1 = new Font("Helvetica", Font.BOLD,30);
    Font f2 = new Font("Helvetica", Font.PLAIN,16);
    
    private boolean validate = true;

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
	
    public AddAgent(MainMenu menu, ArrayList<Agent> list) 
    {  	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Add Sales Agent");
        this.setVisible(true);
        this.setBounds(600,100,325,400);
        
        //set default font
        setUIFont(new FontUIResource(new Font("Helvetica", 0, 16))); 
        
        //re-create the main menu when this frame is closed
    	parentMenu = menu;
        agentList = list;
  
        //create panel for Heading
        JPanel pnlHeading = new JPanel();
        pnlHeading.setBackground(myColor3);
        pnlHeading.add(lblHeading = new JLabel());
        lblHeading.setForeground(Color.white);
        lblHeading.setFont(f1);
        lblHeading.setText("Add new Agent");

        //create panel for the Main
    	JPanel pnlData = new JPanel(new GridBagLayout());
        pnlData.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding
        pnlData.setBackground(myColor1);
        GridBagConstraints g = new GridBagConstraints();
        g.weightx = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        
        lblID = new JLabel("Agent ID  ", SwingConstants.RIGHT);
        g.gridx = 0;
        g.gridy = 0;
        pnlData.add(lblID, g);
        
        g.gridx = 1;
        g.gridy = 0;
    	pnlData.add(txfID = new JTextField(), g);
        
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
        g.gridy = 4;
        pnlData.add(lblPhone = new JLabel("Phone  ", SwingConstants.RIGHT), g);
        
        g.gridx = 1;
        g.gridy = 4;
        pnlData.add(txfPhone = new JTextField(10), g);
        
        //Create panel for the buttons
    	JPanel pnlButtons = new JPanel();
        pnlButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlButtons.add(btnExit = new JButton("Main menu"));
    	pnlButtons.add(btnClear = new JButton("Clear"));
        pnlButtons.add(btnAdd = new JButton("Add"));
    	
        //Add panels to the JFrame container
    	Container c = this.getContentPane();
    	c.add(pnlHeading, BorderLayout.NORTH);
        c.add(pnlData, BorderLayout.CENTER);
    	c.add(pnlButtons, BorderLayout.SOUTH);
        
        //register buttons to accept events
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);      
    }	
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnAdd)
        {
            addAgent();
        }
        if(e.getSource() == btnClear)
        {
            clearForm();
        }
        if(e.getSource() == btnExit)
        {
            mainMenu();
        }
    }
	
    //add Local student to the array
    public void addAgent()
    {
        int id;
        String first, last, email, phone, address, suburb, postcode;
        String output = "<html>";

        //get data from TextFields and ComboBox
        id = Integer.parseInt(txfID.getText());
        first = txfFirst.getText();
        last = txfLast.getText();
        phone = txfPhone.getText();

        validate = true;
        
        if((first.equals("")|| last.equals("")|| phone.equals("") || id == 0))
        {
            output += "Please complete all options on the form<br>";
            validate = false;
        }
        else{
            if(!Utilities.Validation.isString(first, last)){
            output += "-  First name and Last name cannot contains numeric<br>";
            validate = false;    
            }
            if(!Utilities.Validation.checkPhone(phone)){
                output += "-  Invalid Phone number<br>";
                validate = false;
            }
        }

        if(validate)
        {
            //add to ArrayList
            agentList.add(new Agent(id,first,last,phone));
            
            //create an object to stored the details
            Agent a = new Agent(id,first,last,phone);
            Utilities.DataAccessLayer.addAgentToDatabase(a);
        }
        else
        {   			   			
            JOptionPane.showMessageDialog(null, output);			
        }		
    }
    
    
    // clear the Frame
    private void clearForm()  
    {
        //re-set all components
        txfID.setText("");
        txfFirst.setText("");	
        txfLast.setText("");
        txfPhone.setText("");
    }
	
    // close addProduct frame and return to main menu
    public void  mainMenu() 
    {
        parentMenu.setVisible(true);	
        this.dispose(); //release resources back to the OS
    }
    
 
}
