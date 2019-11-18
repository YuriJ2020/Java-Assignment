/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Agent;
import Classes.Members;
import Classes.Single;
import Utilities.ConnectionDetails;
import Utilities.MemberTableModel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ppunme
 */
public class SearchForm extends javax.swing.JFrame {

    /**
     * Creates new form SearchForm
     */
    MainMenu parentMenu;
    
    Color myColor1 = new Color(45,81,142);
    
    String searchLoad;
    int indexSearch;
    private int selectedRow;
    private boolean found;
    
    MemberTableModel memberModel = new MemberTableModel();
    ArrayList<Members> list = new ArrayList<>();
    ArrayList<Agent> agentList = new ArrayList<>();
    
    public SearchForm(MainMenu menu) {
        initComponents();
   
        this.setTitle("Search Form");
        this.setVisible(true);
        this.setBounds(400, 100, 609, 600); // (x,y,width,height)
     
        parentMenu = menu;
        
        searchLoad = (String) cboSearch.getSelectedItem();
        System.out.println(searchLoad);
        
        memberModel = new MemberTableModel();
        resultTable.setModel(memberModel);
        
        resultTable.setSelectionMode(selectedRow);
        //the following uses an anonymous inner class
        ListSelectionModel rowSM = resultTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                selectedRow = lsm.getMinSelectionIndex();
            }
        });
        
        //all columns displayed at the preferred widths
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
    private void SearchFromAll() {  
        ArrayList<Members> searchList = new ArrayList<>();     
        Utilities.DataAccessLayer.getDataFromDatabase(list);
        
        found = false;
        String search = txfSearch.getText();

        for(Members m : list) {
            //convert number to String
            String memberIDString = Integer.toString(m.getId());
            String feeString = Double.toString(m.getFee());
            String agentIDString = Integer.toString(m.getAgentID());
            
            if(memberIDString.equalsIgnoreCase(search) || m.getName().equalsIgnoreCase(search) || m.getLast().equalsIgnoreCase(search)||
                m.getGender().equalsIgnoreCase(search) || m.getEmail().equalsIgnoreCase(search) || m.getPhone().equalsIgnoreCase(search)||
                m.getEmail().equalsIgnoreCase(search) || m.getAddress().equalsIgnoreCase(search) || m.getSuburb().equalsIgnoreCase(search)||
                m.getState().equalsIgnoreCase(search) || m.getPostcode().equalsIgnoreCase(search) || feeString.equalsIgnoreCase(search) ||
                agentIDString.equalsIgnoreCase(search)){
        
                found = true;
                searchList.add(m);
            }      
        }
        
        if(found == true) {
            memberModel = new MemberTableModel(searchList);
            resultTable.setModel(memberModel);
        } else {
            JOptionPane.showMessageDialog(null, "This Member does not exist");
        }           
    }
    
    private void SearchFromID() {
        ArrayList<Members> searchList = new ArrayList<>();     
        Utilities.DataAccessLayer.getDataFromDatabase(list);

        found = false;
        
        for(Members m : list) {
            try{
                if(m.getId() == (Integer.parseInt(txfSearch.getText()))){
                    found = true;
                    searchList.add(m);
                }
            } catch (NumberFormatException nEx) { //if enter String
                nEx.getMessage();
            }
        }

        if(found == true) {
            memberModel = new MemberTableModel(searchList);
            resultTable.setModel(memberModel);
        } else {
            JOptionPane.showMessageDialog(null, "This member does not exist");
        }
    }
    
    private void SearchFromName() {
        ArrayList<Members> searchList = new ArrayList<>();     
        Utilities.DataAccessLayer.getDataFromDatabase(list);
        
        found = false;

        for(Members m : list) {
            if(m.getName().equalsIgnoreCase(txfSearch.getText())){
                found = true;
                searchList.add(m);
            }
        }
        
        if(found == true) {
            memberModel = new MemberTableModel(searchList);
            resultTable.setModel(memberModel);
        } else {
            JOptionPane.showMessageDialog(null, "This member does not exist");
        }
    }
    
    private void SearchFromAgent() {
        ArrayList<Members> searchList = new ArrayList<>();     
        Utilities.DataAccessLayer.getDataFromDatabase(list);

        found = false;
        
        for(Members m : list) {
            try{
                if(m.getAgentID() == (Integer.parseInt(txfSearch.getText()))){
                    found = true;
                    searchList.add(m);
                }
            } catch (NumberFormatException nEx) { //if enter String
                nEx.getMessage();
            }
        }

        if(found == true) {
            memberModel = new MemberTableModel(searchList);
            resultTable.setModel(memberModel);
        } else {
            JOptionPane.showMessageDialog(null, "This member does not exist");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        pnlAll = new javax.swing.JPanel();
        pnlSearch = new javax.swing.JPanel();
        lblSearchBy = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JLabel();
        btnSearch = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txfSearch = new javax.swing.JTextField();
        cboSearch = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane(resultTable);
        resultTable = new javax.swing.JTable();
        lblHead = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlAll.setBackground(new java.awt.Color(34, 70, 96));

        pnlSearch.setBackground(new java.awt.Color(255, 255, 255));

        lblSearchBy.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblSearchBy.setText("Search from");

        btnBack.setBackground(new java.awt.Color(136, 132, 130));
        btnBack.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBack.setText("Back");
        btnBack.setOpaque(true);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackMousePressed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(220, 161, 56));
        btnDelete.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setText("Delete");
        btnDelete.setOpaque(true);
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(220, 161, 56));
        btnUpdate.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdate.setText("Update");
        btnUpdate.setOpaque(true);
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpdateMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpdateMousePressed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(34, 70, 96));
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSearchMousePressed(evt);
            }
        });

        lblSearch.setFont(new java.awt.Font(".SF NS Text", 0, 20)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearch.setText("Search");

        javax.swing.GroupLayout btnSearchLayout = new javax.swing.GroupLayout(btnSearch);
        btnSearch.setLayout(btnSearchLayout);
        btnSearchLayout.setHorizontalGroup(
            btnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );
        btnSearchLayout.setVerticalGroup(
            btnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSearchLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        cboSearch.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Member ID", "First Name", "Sales Agent" }));
        cboSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSearchItemStateChanged(evt);
            }
        });
        cboSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSearchActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        resultTable.setFont(new java.awt.Font(".SF NS Text", 0, 12)); // NOI18N
        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Membere ID", "First Name", "Last Name", "Gender", "Email", "Phone", "Address"
            }
        ));
        resultTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                                .addComponent(lblSearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addComponent(txfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(txfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(lblSearchBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        lblHead.setBackground(new java.awt.Color(255, 255, 255));
        lblHead.setFont(new java.awt.Font("Andale Mono", 0, 40)); // NOI18N
        lblHead.setForeground(new java.awt.Color(255, 255, 255));
        lblHead.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHead.setText("Search Form");

        javax.swing.GroupLayout pnlAllLayout = new javax.swing.GroupLayout(pnlAll);
        pnlAll.setLayout(pnlAllLayout);
        pnlAllLayout.setHorizontalGroup(
            pnlAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAllLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAllLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHead)
                .addGap(170, 170, 170))
        );
        pnlAllLayout.setVerticalGroup(
            pnlAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAllLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblHead, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMousePressed
        
        if(txfSearch.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter data to search");
            memberModel = new MemberTableModel();
            resultTable.setModel(memberModel);
        }
        else if (searchLoad == "All") {
            SearchFromAll();
        }
        else if (searchLoad == "Member ID") {
            SearchFromID();
        }
        else if (searchLoad == "First Name") {
            SearchFromName();
        }
        else if (searchLoad == "Sales Agent") {
            SearchFromAgent();
        }
        
    }//GEN-LAST:event_btnSearchMousePressed

    private void cboSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSearchActionPerformed
        if(evt.getSource() == cboSearch){
            searchLoad = (String) cboSearch.getSelectedItem();
        }
    }//GEN-LAST:event_cboSearchActionPerformed

    private void cboSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSearchItemStateChanged
        if(evt.getSource() == cboSearch){
            indexSearch = cboSearch.getSelectedIndex();
            System.out.println("index: " + indexSearch);
        }
    }//GEN-LAST:event_cboSearchItemStateChanged

    private void btnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMousePressed
        parentMenu.setVisible(true);	
        this.dispose(); 
    }//GEN-LAST:event_btnBackMousePressed

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed
        Members m = memberModel.getRow(selectedRow);
        Utilities.DataAccessLayer.deleteMember(m);
        
        //refresh table
        memberModel.fireTableRowsDeleted(selectedRow, selectedRow); //check
        
        memberModel = new MemberTableModel();
        resultTable.setModel(memberModel);
    }//GEN-LAST:event_btnDeleteMousePressed

    private void btnUpdateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMousePressed
        Members m = memberModel.getRow(selectedRow);
        UpdateForm updateFrame = new UpdateForm(this,memberModel,m); 
        this.setVisible(false);
    }//GEN-LAST:event_btnUpdateMousePressed

    private void btnSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseEntered
        btnSearch.setBackground(new java.awt.Color(102,102,102)); 
    }//GEN-LAST:event_btnSearchMouseEntered

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        setColor(btnBack);
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        setColor(btnDelete);
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        setColor(btnUpdate);
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseExited
        btnSearch.setBackground(new java.awt.Color(34,70,96));
    }//GEN-LAST:event_btnSearchMouseExited

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        btnBack.setBackground(new java.awt.Color(136,132,130));
    }//GEN-LAST:event_btnBackMouseExited

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        btnDelete.setBackground(new java.awt.Color(220,161,56)); 
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseExited
        btnUpdate.setBackground(new java.awt.Color(220,161,56));
    }//GEN-LAST:event_btnUpdateMouseExited

    public void setColor(JLabel label){
        label.setBackground(new java.awt.Color(102,102,102)); 
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        
        

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchForm().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JPanel btnSearch;
    private javax.swing.JLabel btnUpdate;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHead;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearchBy;
    private javax.swing.JPanel pnlAll;
    private javax.swing.JPanel pnlSearch;
    private static javax.swing.JTable resultTable;
    private javax.swing.JTextField txfSearch;
    // End of variables declaration//GEN-END:variables
}
