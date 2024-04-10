
package qlsksv_vku;


import java.sql.Connection;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author quang
 */
public class DetailsStudentForUser extends javax.swing.JFrame {

    /**
     * Creates new form DetailsStudent
     */
    public DetailsStudentForUser(String studentCode) {
        initComponents();
        takeStudentCode = studentCode;
        show_info();
        setLocationRelativeTo(null);
        
        Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateCurrentTimeLabel(currentTimeLabel);
            }
        }, 0, 1000);
    }
    
    public String takeStudentCode, studentCode, fullName, birth, gender, CCCD, height, weight, bmi, bloodType, healthCode, healthStatus, note;
    public String fullNameContact, relationshipContact, phoneContact, addressContact, URLImageStudent;
    
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    
    
    private void show_info() {
    String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
    try (Connection conn = DriverManager.getConnection(dbURL)) {
        String detailsQuery = "SELECT * FROM details WHERE studentCode = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(detailsQuery)) {
            preparedStatement.setString(1, takeStudentCode);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    URLImageStudent = rs.getString("URL");
                    studentCodeField.setText(rs.getString("studentCode"));
                    fullNameField.setText(rs.getString("fullName"));
                    birthField.setText(rs.getString("birth"));
                    genderField.setSelectedItem(rs.getString("gender"));
                    CCCDField.setText(rs.getString("CCCD"));
                    URLImageField.setText(rs.getString("URL"));

                    ImageIcon originalIcon = new ImageIcon(URLImageStudent);

                    int labelWidth = imageStudentLabel.getWidth();
                    int labelHeight = imageStudentLabel.getHeight();

                    if (originalIcon.getIconWidth() != labelWidth || originalIcon.getIconHeight() != labelHeight) {
                        Image originalImage = originalIcon.getImage();
                        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        imageStudentLabel.setIcon(scaledIcon);
                    } else {
                        imageStudentLabel.setIcon(originalIcon);
                    }
                }
            }
        }

        String healthsQuery = "SELECT * FROM healths WHERE studentCode = ?";
        try (PreparedStatement preparedStatementHealths = conn.prepareStatement(healthsQuery)) {
            preparedStatementHealths.setString(1, takeStudentCode);
            try (ResultSet rsHealths = preparedStatementHealths.executeQuery()) {
                if (rsHealths.next()) {
                    studentCode = rsHealths.getString("studentCode");
                    height = rsHealths.getString("height");
                    weight = rsHealths.getString("weight");
                    bmi = rsHealths.getString("bmi");
                    bloodType = rsHealths.getString("bloodType");
                    healthCode = rsHealths.getString("healthCode");
                    healthStatus = rsHealths.getString("healthStatus");
                    note = rsHealths.getString("note");

                    heightField.setText(height);
                    weightField.setText(weight);
                    BMIField.setText(bmi);
                    bloodTypeField.setSelectedItem(bloodType);
                    healthCodeField.setText(healthCode);
                    healthStatusField.setSelectedItem(healthStatus);
                    noteField.setText(note);
                }
            }
        }
        
        String emergencyContactQuery = "SELECT * FROM emergencyContact where studentCode = ?";
        try (PreparedStatement preparedStatementHealths = conn.prepareStatement(emergencyContactQuery)) {
            preparedStatementHealths.setString(1, takeStudentCode);
            try (ResultSet rsEmergency = preparedStatementHealths.executeQuery()) {
                if (rsEmergency.next()){
                    studentCode = rsEmergency.getString("studentCode");
                    fullNameContact = rsEmergency.getString("fullNameContact");
                    relationshipContact = rsEmergency.getString("relationshipContact");
                    phoneContact = rsEmergency.getString("phoneContact");
                    addressContact = rsEmergency.getString("addressContact");
                    
                    fullNameContactField.setText(fullNameContact);
                    relationshipContactFiled.setText(relationshipContact);
                    phoneContactField.setText(phoneContact);
                    addressContactField.setText(addressContact);
                }
            }
        }

    } catch (SQLException ex) {
        Logger.getLogger(InformationSystem.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void updateCurrentTimeLabel(JLabel currentTimeLabel) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentTime = timeFormat.format(new Date());
        currentTimeLabel.setText("Current Time: " + currentTime);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        studentCodeField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fullNameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        birthField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        genderField = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        CCCDField = new javax.swing.JTextField();
        currentTimeLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        imageStudentLabel = new javax.swing.JLabel();
        URLImageField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        healthCodeField = new javax.swing.JTextField();
        BMIField = new javax.swing.JTextField();
        weightField = new javax.swing.JTextField();
        heightField = new javax.swing.JTextField();
        bloodTypeField = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        noteField = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        healthStatusField = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        fullNameContactField = new javax.swing.JTextField();
        relationshipContactFiled = new javax.swing.JTextField();
        phoneContactField = new javax.swing.JTextField();
        addressContactField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Details Information");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel1.setText("Personal Information");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, 730, -1));

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel3.setText("Student Code:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 40, 89, -1));

        studentCodeField.setEditable(false);
        studentCodeField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel1.add(studentCodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 37, 250, -1));

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel4.setText("Full Name:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 73, 89, -1));

        fullNameField.setEditable(false);
        fullNameField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel1.add(fullNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 73, 250, -1));

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel5.setText("Birth:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 112, 89, -1));

        birthField.setEditable(false);
        birthField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel1.add(birthField, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 109, 250, -1));

        jLabel6.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel6.setText("Gender:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 148, -1, -1));

        genderField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        genderField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        genderField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderFieldActionPerformed(evt);
            }
        });
        jPanel1.add(genderField, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 145, -1, -1));

        jLabel7.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel7.setText("ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 184, -1, -1));

        CCCDField.setEditable(false);
        CCCDField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel1.add(CCCDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 181, 250, -1));

        currentTimeLabel.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        currentTimeLabel.setText("CurrentTime");
        jPanel1.add(currentTimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 280, -1));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel8.setText("Vietnam - Korea University of Information");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 330, -1, -1));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel9.setText("and Communication Technology");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 358, -1, -1));

        jLabel26.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel26.setText("URL Image:");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 223, -1, -1));

        imageStudentLabel.setBackground(new java.awt.Color(255, 255, 255));
        imageStudentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageStudentLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("3x4"));
        jPanel1.add(imageStudentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 31, 246, 321));

        URLImageField.setEditable(false);
        URLImageField.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jPanel1.add(URLImageField, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 247, 357, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/10135315_18129294 (2).jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 430));

        jTabbedPane1.addTab("General Information", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel10.setText("Personal Health Information");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 6, 214, -1));

        jLabel11.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel11.setText("Height:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 46, -1, -1));

        jLabel12.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel12.setText("Weight:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 82, -1, -1));

        jLabel13.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel13.setText("BMI:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 118, -1, -1));

        jLabel14.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel14.setText("Blood type:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 154, -1, -1));

        jLabel15.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel15.setText("Current health status:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 226, -1, -1));

        jLabel16.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel16.setText("Health insurance code:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 190, -1, -1));

        jLabel17.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel17.setText("NOTE :");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 259, -1, -1));

        healthCodeField.setEditable(false);
        healthCodeField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel2.add(healthCodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 187, 237, -1));

        BMIField.setEditable(false);
        BMIField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel2.add(BMIField, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 115, 237, -1));

        weightField.setEditable(false);
        weightField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel2.add(weightField, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 79, 237, -1));

        heightField.setEditable(false);
        heightField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel2.add(heightField, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 43, 237, -1));

        bloodTypeField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        bloodTypeField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Group A", "Group B", "Group AB", "Group O" }));
        jPanel2.add(bloodTypeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 151, -1, -1));

        noteField.setEditable(false);
        noteField.setColumns(20);
        noteField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        noteField.setRows(5);
        jScrollPane2.setViewportView(noteField);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 289, 457, -1));

        jLabel18.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel18.setText("(kg)");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 82, -1, -1));

        jLabel19.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel19.setText("(cm)");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 46, -1, -1));

        jLabel20.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 115, -1, -1));

        healthStatusField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        healthStatusField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sức khỏe rất tốt", "Sức khỏe tốt", "Sức khỏe khá", "Sức khỏe trung bình", "Sức khỏe kém", "Sức khỏe rất kém" }));
        jPanel2.add(healthStatusField, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 223, 237, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/10135315_18129294 (2).jpg"))); // NOI18N
        jLabel27.setText("jLabel27");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 420));

        jTabbedPane1.addTab("Health Condition", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel21.setText("Emergency Contact Information");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 6, -1, -1));

        jLabel22.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel22.setText("Full name:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 46, -1, -1));

        jLabel23.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel23.setText("Relationship:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 82, -1, -1));

        jLabel24.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel24.setText("Phone number:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 118, -1, -1));

        jLabel25.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel25.setText("Address:");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 154, -1, -1));

        fullNameContactField.setEditable(false);
        fullNameContactField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel3.add(fullNameContactField, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 43, 280, -1));

        relationshipContactFiled.setEditable(false);
        relationshipContactFiled.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        relationshipContactFiled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationshipContactFiledActionPerformed(evt);
            }
        });
        jPanel3.add(relationshipContactFiled, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 79, 280, -1));

        phoneContactField.setEditable(false);
        phoneContactField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        phoneContactField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneContactFieldActionPerformed(evt);
            }
        });
        jPanel3.add(phoneContactField, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 115, 280, -1));

        addressContactField.setEditable(false);
        addressContactField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jPanel3.add(addressContactField, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 151, 280, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/10135315_18129294 (2).jpg"))); // NOI18N
        jLabel28.setText("jLabel28");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 420));

        jTabbedPane1.addTab("Emergency Contact", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("General Information");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void genderFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderFieldActionPerformed

    private void relationshipContactFiledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationshipContactFiledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_relationshipContactFiledActionPerformed

    private void phoneContactFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneContactFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneContactFieldActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DetailsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DetailsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DetailsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DetailsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DetailsStudent().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BMIField;
    private javax.swing.JTextField CCCDField;
    private javax.swing.JTextField URLImageField;
    private javax.swing.JTextField addressContactField;
    private javax.swing.JTextField birthField;
    private javax.swing.JComboBox<String> bloodTypeField;
    private javax.swing.JLabel currentTimeLabel;
    private javax.swing.JTextField fullNameContactField;
    private javax.swing.JTextField fullNameField;
    private javax.swing.JComboBox<String> genderField;
    private javax.swing.JTextField healthCodeField;
    private javax.swing.JComboBox<String> healthStatusField;
    private javax.swing.JTextField heightField;
    private javax.swing.JLabel imageStudentLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea noteField;
    private javax.swing.JTextField phoneContactField;
    private javax.swing.JTextField relationshipContactFiled;
    private javax.swing.JTextField studentCodeField;
    private javax.swing.JTextField weightField;
    // End of variables declaration//GEN-END:variables
}
