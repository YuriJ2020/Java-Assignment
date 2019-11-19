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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author ppunme
 */
public class UpdateForm extends javax.swing.JFrame implements ActionListener{
    
    SearchForm parent;
    MemberTableModel memberModel;
    
    private ArrayList<Members> list = new ArrayList<>();
    
    public static String agentName;
    public static int agentID;
    public static String gender;
    public static String type;
    public static String stateLoad;
    public static String agentLoad;
    public static String packLoad;
    private int indexPack, indexAgent;
    
    /** Creates new form Update */
    public UpdateForm(SearchForm menu, MemberTableModel model, Members m) {
        initComponents();
        this.setVisible(true);
        this.setBounds(400, 100, 400, 531); // (x,y,width,height)
        
        parent = menu;
        memberModel = model;
        
        //get Agent name ComboBox
        Utilities.DataAccessLayer.getAgentToCombobox(cboAgent);
        
        //get Selected agent name from m.getAgentID()
        agentID = m.getAgentID();
        agentLoad = Utilities.DataAccessLayer.getAgentName();
        
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
        cboAgent.setSelectedItem(agentLoad);
        
        if(m.getGender().equalsIgnoreCase("Male")){
            rbtMale.setSelected(true);
            rbtFemale.setSelected(false);
            gender = "Male";
        } else {
            rbtFemale.setSelected(true);
            rbtMale.setSelected(false);
            gender = "Female";
        }
        
        if (m instanceof Single) {
            rbtSingle.setSelected(true);
            cboPack.setSelectedItem(((Single) m).getPackLoad());
            lblNoMember.setVisible(false);
            txfNoMember.setVisible(false);
            type = "Single";
            packLoad = ((Single) m).getPackLoad();
            System.out.println("Pack " + packLoad);
        } 
        if (m instanceof Family) {
            rbtFamily.setSelected(true);
            txfNoMember.setText(Integer.toString(((Family) m).getNoMembers()));
            lblPack.setVisible(false);
            cboPack.setVisible(false);
            type = "Family";
        }
        System.out.println("Pack " + packLoad);
        //cannot change the primary key
        txfID.setEnabled(false);
        txfID.setToolTipText("CANNOT CHANGE PRIMARY KEY");
        
        rbtMale.addActionListener(this);
        rbtFemale.addActionListener(this);
        rbtSingle.addActionListener(this);
        rbtFamily.addActionListener(this);
        cboPack.addActionListener(this);
        cboAgent.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnBack.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e)
    {
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
            cboPack.setVisible(true);
            lblNoMember.setVisible(false);
            txfNoMember.setVisible(false);
        }
        if (e.getSource() == rbtFamily)
        {
            type = "Family"; //retrieve value from RadioButtons
            lblNoMember.setVisible(true);
            txfNoMember.setVisible(true);
            lblPack.setVisible(false);
            cboPack.setVisible(false);
        }
        if(e.getSource() == cboPack)
        {
            packLoad = (String) cboPack.getSelectedItem();
        }
        if(e.getSource() == cboAgent)
        {
            //if agent name was changed
            agentName = (String) cboAgent.getSelectedItem();
            System.out.println("Agent: " + agentName);
            agentID = Utilities.DataAccessLayer.getAgentIDupdate();
            System.out.println("New Agent ID: " + agentID);
        }
    }
    
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource()== cboPack)
        {
            indexPack = cboPack.getSelectedIndex();
        }
        if(e.getSource()== cboAgent)
        {
            indexAgent = cboAgent.getSelectedIndex();
        }
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
        lblID = new javax.swing.JLabel();
        txfID = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblAgentName = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        lblPack = new javax.swing.JLabel();
        cboPack = new javax.swing.JComboBox<>();
        lblNoMember = new javax.swing.JLabel();
        rbtFamily = new javax.swing.JRadioButton();
        rbtSingle = new javax.swing.JRadioButton();
        cboAgent = new javax.swing.JComboBox<>();
        txfNoMember = new javax.swing.JTextField();

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

        lblID.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(51, 51, 51));
        lblID.setText("Member ID");

        txfID.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

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

        txfNoMember.setFont(new java.awt.Font(".SF NS Text", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblAgentName, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(cboAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rbtSingle)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbtFamily)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnBack)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnUpdate))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblNoMember, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txfNoMember))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblPack, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboPack, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addContainerGap(46, Short.MAX_VALUE)))))
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
                            .addComponent(txfPhone))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLast, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtMale)
                    .addComponent(rbtFemale)
                    .addComponent(lblGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgentName)
                    .addComponent(cboAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblType)
                    .addComponent(rbtSingle)
                    .addComponent(rbtFamily))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPack)
                    .addComponent(cboPack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoMember)
                    .addComponent(txfNoMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnBack))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        int id, noMember;
        double totalFee;
        String first, last, email, phone, address, suburb, postcode;
        String output = "<html>";
        first = txfFirst.getText();
        last = txfLast.getText();
        email = txfEmail.getText();
        phone = txfPhone.getText();

        boolean validate = true;
        
        if(Utilities.Validation.CheckNull(first, last, gender, email, phone, type) ||
            agentLoad == null || agentLoad.equals("Make a Selection") ) 
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
        }
        
        if(validate == true){
            Utilities.DataAccessLayer.updateMember();
            memberModel.getDataFromDatabase(); //get datafrom database again
            memberModel.fireTableDataChanged(); //refresh table
            this.dispose();             
            parent.setVisible(true); 
        }
        else{
            JOptionPane.showMessageDialog(null, output);
        }
        
        
        
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JLabel lblType;
    private javax.swing.JRadioButton rbtFamily;
    private javax.swing.JRadioButton rbtFemale;
    private javax.swing.JRadioButton rbtMale;
    private javax.swing.JRadioButton rbtSingle;
    public static javax.swing.JTextField txfEmail;
    public static javax.swing.JTextField txfFirst;
    public static javax.swing.JTextField txfID;
    public static javax.swing.JTextField txfLast;
    public static javax.swing.JTextField txfNoMember;
    public static javax.swing.JTextField txfPhone;
    // End of variables declaration//GEN-END:variables

}
