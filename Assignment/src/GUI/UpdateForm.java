/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Classes.Family;
import Classes.Members;
import Classes.Single;
import Utilities.MemberTableModel;
import java.util.ArrayList;
import javax.swing.ButtonGroup;

/**
 *
 * @author ppunme
 */
public class UpdateForm extends javax.swing.JFrame {
    
    SearchForm parent;
    MemberTableModel memberModel;
    
    private ArrayList<Members> list = new ArrayList<>();
    public static int agentID;
    private String agentName;
    private String stateLoad;
    
    /** Creates new form Update */
    public UpdateForm(SearchForm menu, MemberTableModel model, Members m) {
        initComponents();
        this.setVisible(true);
        this.setBounds(400, 100, 594, 495);
        
        parent = menu;
        memberModel = model;
        
        //get Agent name ComboBox
        Utilities.DataAccessLayer.getAgentToCombobox(cboAgent);
        
        //get Selected agent name from m.getAgentID()
        agentID = m.getAgentID();
        agentName = Utilities.DataAccessLayer.getAgentName();
        System.out.println(agentName);
        
        //make only one radio button can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtMale);
        genderGroup.add(rbtFemale);
        
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(rbtSingle);
        typeGroup.add(rbtFamily);
        
        txfID.setText(Integer.toString(m.getId()));
        txfFirst.setText(m.getName());
        txfLast.setText(m.getLast());
        txfEmail.setText(m.getEmail());
        txfPhone.setText(m.getPhone());
        txfAddress.setText(m.getAddress());
        txfSuburb.setText(m.getSuburb());
        cboState.setSelectedItem(m.getState());
        txfPostcode.setText(m.getPostcode());
        cboAgent.setSelectedItem(agentName);
        
        if(m.getGender().equalsIgnoreCase("Male")){
            rbtMale.setSelected(true);
            rbtFemale.setSelected(false);
        } else {
            rbtFemale.setSelected(true);
            rbtMale.setSelected(false);
        }
        
        if (m instanceof Single) {
            rbtSingle.setSelected(true);
            cboPack.setSelectedItem(((Single) m).getPackLoad());
            lblNoMember.setVisible(false);
            txfNoMember.setVisible(false);
        } 
        
        if (m instanceof Family) {
            rbtFamily.setSelected(true);
            txfNoMember.setText(Integer.toString(((Family) m).getNoMembers()));
            lblPack.setVisible(false);
            cboPack.setVisible(false);
        }
        
        //cannot change the primary key
        txfID.setEnabled(false);
        txfID.setToolTipText("CANNOT CHANGE PRIMARY KEY");
        
        stateLoad = (String) cboState.getSelectedItem();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblHead = new javax.swing.JLabel();
        lblLast = new javax.swing.JLabel();
        lblFirst = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        txfLast = new javax.swing.JTextField();
        txfFirst = new javax.swing.JTextField();
        txfEmail = new javax.swing.JTextField();
        rbtMale = new javax.swing.JRadioButton();
        txfPhone = new javax.swing.JTextField();
        rbtFemale = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        txfAddress = new javax.swing.JTextField();
        lblSuburb = new javax.swing.JLabel();
        lblState = new javax.swing.JLabel();
        cboState = new javax.swing.JComboBox<>();
        txfSuburb = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        txfID = new javax.swing.JTextField();
        lblPostcode = new javax.swing.JLabel();
        txfPostcode = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblAgentName = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        lblPack = new javax.swing.JLabel();
        cboPack = new javax.swing.JComboBox<>();
        txfNoMember = new javax.swing.JTextField();
        lblNoMember = new javax.swing.JLabel();
        rbtFamily = new javax.swing.JRadioButton();
        rbtSingle = new javax.swing.JRadioButton();
        cboAgent = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(246, 246, 246));

        jPanel2.setBackground(new java.awt.Color(133, 160, 168));

        lblHead.setBackground(new java.awt.Color(133, 160, 168));
        lblHead.setFont(new java.awt.Font("Andale Mono", 0, 24)); // NOI18N
        lblHead.setForeground(new java.awt.Color(255, 255, 255));
        lblHead.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHead.setText("Update Member");
        lblHead.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(lblHead, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        lblLast.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblLast.setForeground(new java.awt.Color(51, 51, 51));
        lblLast.setText("Last Name");

        lblFirst.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblFirst.setForeground(new java.awt.Color(51, 51, 51));
        lblFirst.setText("First Name");

        lblGender.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblGender.setForeground(new java.awt.Color(51, 51, 51));
        lblGender.setText("Gender");

        lblEmail.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");

        lblPhone.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblPhone.setForeground(new java.awt.Color(51, 51, 51));
        lblPhone.setText("Phone");

        txfLast.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        txfFirst.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        txfEmail.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        rbtMale.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        rbtMale.setForeground(new java.awt.Color(51, 51, 51));
        rbtMale.setText("Male");

        txfPhone.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        rbtFemale.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        rbtFemale.setForeground(new java.awt.Color(51, 51, 51));
        rbtFemale.setText("Female");

        jLabel7.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("ADDRESS");

        lblAddress.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(51, 51, 51));
        lblAddress.setText("Address");

        txfAddress.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        lblSuburb.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblSuburb.setForeground(new java.awt.Color(51, 51, 51));
        lblSuburb.setText("Suburb");

        lblState.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblState.setForeground(new java.awt.Color(51, 51, 51));
        lblState.setText("State");

        cboState.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        cboState.setForeground(new java.awt.Color(51, 51, 51));
        cboState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New South Wales", "Western Australia", "Queensland", "South Australia", "Victoria", "Tasmania" }));

        txfSuburb.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        lblID.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(51, 51, 51));
        lblID.setText("Member ID");

        txfID.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        lblPostcode.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblPostcode.setForeground(new java.awt.Color(51, 51, 51));
        lblPostcode.setText("Postcode");

        txfPostcode.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        btnUpdate.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblAgentName.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblAgentName.setForeground(new java.awt.Color(51, 51, 51));
        lblAgentName.setText("Agent name");

        lblType.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblType.setForeground(new java.awt.Color(51, 51, 51));
        lblType.setText("Type");

        lblPack.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblPack.setForeground(new java.awt.Color(51, 51, 51));
        lblPack.setText("Package");

        cboPack.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        cboPack.setForeground(new java.awt.Color(51, 51, 51));
        cboPack.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saver", "Bronze", "Ultimate" }));

        txfNoMember.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        lblNoMember.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblNoMember.setForeground(new java.awt.Color(51, 51, 51));
        lblNoMember.setText("No. of Family member");

        rbtFamily.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        rbtFamily.setForeground(new java.awt.Color(51, 51, 51));
        rbtFamily.setText("Family");

        rbtSingle.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        rbtSingle.setForeground(new java.awt.Color(51, 51, 51));
        rbtSingle.setText("Single");

        cboAgent.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        cboAgent.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblNoMember, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfNoMember, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFirst, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblID, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGender)
                                    .addComponent(lblLast)
                                    .addComponent(lblEmail)
                                    .addComponent(lblPhone)))
                            .addGap(12, 12, 12)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(rbtMale)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbtFemale))
                                .addComponent(txfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addComponent(txfLast)
                                .addComponent(txfID)
                                .addComponent(txfFirst)
                                .addComponent(txfPhone)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rbtSingle)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbtFamily))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblAgentName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cboAgent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblPack, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cboPack, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPostcode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfPostcode))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSuburb)
                            .addComponent(lblState))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboState, 0, 185, Short.MAX_VALUE)
                            .addComponent(txfSuburb)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirst)
                    .addComponent(lblAddress)
                    .addComponent(txfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLast, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSuburb)
                    .addComponent(txfSuburb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtMale)
                    .addComponent(rbtFemale)
                    .addComponent(lblGender)
                    .addComponent(lblState)
                    .addComponent(cboState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail)
                    .addComponent(lblPostcode)
                    .addComponent(txfPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgentName)
                    .addComponent(cboAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtSingle)
                        .addComponent(rbtFamily))
                    .addComponent(lblType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPack)
                    .addComponent(cboPack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfNoMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoMember)
                    .addComponent(btnUpdate)
                    .addComponent(btnBack))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();             //close this frame
        parent.setVisible(true);    //make parent visible again
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateMember();
    }//GEN-LAST:event_btnUpdateActionPerformed

    public void updateMember(){
        Utilities.DataAccessLayer.updateMember();
        memberModel.getDataFromDatabase();
        memberModel.fireTableDataChanged();
        
        this.dispose();             //close this frame
        parent.setVisible(true);    //make parent visible again
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Update().setVisible(true);
            }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateForm().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private static javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboAgent;
    private javax.swing.JComboBox<String> cboPack;
    private javax.swing.JComboBox<String> cboState;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAgentName;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirst;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHead;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLast;
    private javax.swing.JLabel lblNoMember;
    private javax.swing.JLabel lblPack;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPostcode;
    private javax.swing.JLabel lblState;
    private javax.swing.JLabel lblSuburb;
    private javax.swing.JLabel lblType;
    private javax.swing.JRadioButton rbtFamily;
    private javax.swing.JRadioButton rbtFemale;
    private javax.swing.JRadioButton rbtMale;
    private javax.swing.JRadioButton rbtSingle;
    public static javax.swing.JTextField txfAddress;
    public static javax.swing.JTextField txfEmail;
    public static javax.swing.JTextField txfFirst;
    public static javax.swing.JTextField txfID;
    public static javax.swing.JTextField txfLast;
    public static javax.swing.JTextField txfNoMember;
    public static javax.swing.JTextField txfPhone;
    public static javax.swing.JTextField txfPostcode;
    public static javax.swing.JTextField txfSuburb;
    // End of variables declaration//GEN-END:variables

}
