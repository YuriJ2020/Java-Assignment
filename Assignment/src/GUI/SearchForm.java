/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Members;
import static GUI.AddSingle.list;
import static GUI.AddSingle.setUIFont;
import Utilities.ConnectionDetails;
import Utilities.MemberTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author ppunme
 */
public class SearchForm extends JFrame implements ActionListener {
    
    private int selectedRow;
    
    private JTextField txfFirst = new JTextField();
    private JTextField txfLast = new JTextField();
    
    private JLabel lblHeading;
    private JLabel lblFirst = new JLabel("First Name  " ,SwingConstants.RIGHT);
    private JLabel lblLast = new JLabel("Last Name  " ,SwingConstants.RIGHT);
    
    //Buttons
    JButton btnSearch = new JButton("Search");
    JButton btnDel = new JButton("Delete");
    JButton btnUpdate = new JButton("Update");
    JButton btnMenu = new JButton("Main Menu");
    
    
    JTable tblMember = new JTable();
    //add the table to the scroll bar constructor
    JScrollPane scroll = new JScrollPane(tblMember,
    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    //set a color object using RGB
    Color myColor1 = new Color(255, 255, 255); //white
    Color myColor2 = new Color(234, 235, 237); //grey
    Color myColor3 = new Color(163, 186, 195); //green
    
    Font f1 = new Font("Helvetica", Font.BOLD,30);
    Font f2 = new Font("Helvetica", Font.PLAIN,16);
    
    Container con = getContentPane();
    MemberTableModel memModel;
    
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
    
    public SearchForm(MainMenu menu, ArrayList<Members> membersArray){
        
        //set default font
        setUIFont(new FontUIResource(new Font("Helvetica", 0, 16))); 
        
        this.setTitle("Search Form");
        this.setVisible(true);
        this.setBounds(530, 100, 700, 560);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        parentMenu = menu;
        list = membersArray;

        //Head Panel
        JPanel pnlHeading = new JPanel();
        pnlHeading.setBackground(myColor3);
        pnlHeading.add(lblHeading = new JLabel());
        lblHeading.setForeground(Color.white);
        lblHeading.setFont(f1);
        lblHeading.setText("Search for Members");
        
        //Search Panel
        JPanel pnlSearch = new JPanel();
        pnlSearch.setLayout(new GridLayout(0,2));
        pnlSearch.add(lblFirst);
        pnlSearch.add(txfFirst);
        pnlSearch.add(lblLast);
        pnlSearch.add(txfLast);
        pnlSearch.add(new JLabel(""));
        pnlSearch.add(btnSearch);
 
        //Button Panel
        JPanel pnlButton = new JPanel();
        pnlButton.add(btnDel);
        pnlButton.add(btnUpdate);
        pnlButton.add(btnMenu);
    
        //Table
        memModel = new MemberTableModel();
        tblMember.setModel(memModel);
        
        //code to only allow to select one row at a time
        tblMember.setSelectionMode(selectedRow);
        
        //the following uses an anonymous inner class
        ListSelectionModel rowSM = tblMember.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                selectedRow = lsm.getMinSelectionIndex();
            }
        });
        
        
        JPanel pnlAll = new JPanel();
        pnlAll.setLayout(new BorderLayout());
        pnlAll.add(pnlSearch, BorderLayout.NORTH);
        pnlAll.add(scroll, BorderLayout.CENTER);
        
        //Add Panel to Container
        con.add(pnlHeading, BorderLayout.NORTH);
        con.add(pnlAll, BorderLayout.CENTER);
        con.add(pnlButton, BorderLayout.SOUTH);
        
        //register the buttons
        btnSearch.addActionListener(this);
        btnDel.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnMenu.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnDel){
            deleteRecord();
        }
        if(e.getSource() == btnUpdate){
            Members m = memModel.getRow(selectedRow);
            //UpdateStudent updateFrame = new UpdateStudent(this,studModel,s);
            this.setVisible(false);
        }
        if(e.getSource() == btnMenu){
            parentMenu.setVisible(true);	
            this.dispose(); 
        }
    }
    
    public void deleteRecord(){
        Members m = memModel.getRow(selectedRow);
        System.out.print(m.getId());
        
        Connection con = null;
        Statement stmt = null;
        
        try{
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            String sql = "Delete from member where memberID=" + m.getId();
            System.out.println(sql);
            
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            //put code here
        }
        memModel.getDataFromDatabase();
        memModel.fireTableRowsDeleted(selectedRow, selectedRow); //check
        
        
    } 
    
}
