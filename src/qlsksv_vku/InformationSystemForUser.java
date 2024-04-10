package qlsksv_vku;


import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quang
 */
public class InformationSystemForUser extends javax.swing.JFrame {

    /**
     * Creates new form InformationSystem
     */
    public String studentCode, fullName, birth, gender, CCCD, height, weight, bmi, bloodType, healthCode, healthStatus, note;
    public String fullNameContact, relationshipContact, phoneContact, addressContact, URLImageStudent, roleUser;
    
    public InformationSystemForUser(String userName, String role) {
        initComponents();
        show_table();
        setLocationRelativeTo(null);
        
        this.userName = userName;
        userLoginLabel.setText(userName);
        this.roleUser = role;
        userLoginLabel.setText(userName);
        roleLabel.setText(roleUser);
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateCurrentTimeLabel(currentTimeLabel);
            }
        }, 0, 1000);
    }
    
    public String userName;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    
    private void show_table(){
        String searchStudentCodeText = searchStudentCode.getText().trim();
        String searchFullNameText = searchFullName.getText().trim();

        int CC;
        
        try {
            String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
            conn = DriverManager.getConnection(dbURL);
//            preparedStatement = conn.prepareStatement("SELECT * FROM details");
            if (!searchStudentCodeText.isEmpty() && !searchFullNameText.isEmpty()) {
                preparedStatement = conn.prepareStatement("SELECT * FROM details WHERE studentCode LIKE ? AND fullName LIKE ?");
                preparedStatement.setString(1, "%" + searchStudentCodeText + "%");
                preparedStatement.setString(2, "%" + searchFullNameText + "%");
            } else if (!searchStudentCodeText.isEmpty()) {
                preparedStatement = conn.prepareStatement("SELECT * FROM details WHERE studentCode LIKE ?");
                preparedStatement.setString(1, "%" + searchStudentCodeText + "%");
            } else if (!searchFullNameText.isEmpty()) {
                preparedStatement = conn.prepareStatement("SELECT * FROM details WHERE fullName LIKE ?");
                preparedStatement.setString(1, "%" + searchFullNameText + "%");
            } else {
                preparedStatement = conn.prepareStatement("SELECT * FROM details");
            }
            ResultSet Rs = preparedStatement.executeQuery();
            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) studentInfoTable.getModel();
            
            DFT.setRowCount(0);
            while (Rs.next()){
                
                Vector v2 = new Vector();
                
                for (int i = 1; i <= CC; i++){
                    v2.add(Rs.getString("studentCode"));
                    v2.add(Rs.getString("fullName"));
                    v2.add(Rs.getString("birth"));
                    v2.add(Rs.getString("gender"));
                    v2.add(Rs.getString("CCCD"));
                    
                }
                DFT.addRow(v2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InformationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        studentInfoTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchStudentCode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        searchFullName = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        currentTimeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        userLoginLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        logOutButton = new javax.swing.JButton();
        detailsButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Information System");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        studentInfoTable.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        studentInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Code", "Full Name", "Birth", "Gender", "CCCD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentInfoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentInfoTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studentInfoTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 90, 830, 367));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("STUDENT INFORMATION SYSTEM");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 12, -1, -1));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel2.setText("Student Code:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 49, -1, -1));
        getContentPane().add(searchStudentCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 48, 150, -1));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel4.setText("Full Name:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 49, -1, -1));
        getContentPane().add(searchFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 48, 200, -1));

        findButton.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        findButton.setText("Find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });
        getContentPane().add(findButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(761, 47, 90, -1));

        refreshButton.setBackground(new java.awt.Color(204, 255, 255));
        refreshButton.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/refresh-page-option.png"))); // NOI18N
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        getContentPane().add(refreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 130, 40));

        cancelButton.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 47, 90, -1));

        currentTimeLabel.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        currentTimeLabel.setText("CurrentTime");
        getContentPane().add(currentTimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 250, -1));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/icons8-user-100.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 150, 100));

        userLoginLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        userLoginLabel.setForeground(new java.awt.Color(255, 255, 255));
        userLoginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLoginLabel.setText("...");
        jPanel1.add(userLoginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 113, 138, -1));

        roleLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        roleLabel.setForeground(new java.awt.Color(255, 51, 0));
        roleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roleLabel.setText("...");
        jPanel1.add(roleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 144, 138, -1));

        logOutButton.setBackground(new java.awt.Color(204, 204, 204));
        logOutButton.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        logOutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/logout.png"))); // NOI18N
        logOutButton.setText("Log out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });
        jPanel1.add(logOutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 433, 138, 35));

        detailsButton.setBackground(new java.awt.Color(255, 255, 0));
        detailsButton.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        detailsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/resume.png"))); // NOI18N
        detailsButton.setText("SEE DETAILS");
        detailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsButtonActionPerformed(evt);
            }
        });
        jPanel1.add(detailsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 213, 137, 35));

        updateButton.setBackground(new java.awt.Color(51, 204, 255));
        updateButton.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/graph.png"))); // NOI18N
        updateButton.setText("STATISTICS");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        jPanel1.add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 296, 137, 35));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/bgr1.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        jLabel8.setText("Vietnam - Korea University of Information and Communication Technology");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 463, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/10135315_18129294 (2).jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsButtonActionPerformed
       int selectedRow = studentInfoTable.getSelectedRow();
       
       if (selectedRow != -1) {
           studentCode = studentInfoTable.getValueAt(selectedRow, 0).toString();
           new DetailsStudentForUser(studentCode).setVisible(true);
       } else {
           JOptionPane.showMessageDialog(this, "Please select a row for see details.");
       }
    }//GEN-LAST:event_detailsButtonActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        show_table();
    }//GEN-LAST:event_findButtonActionPerformed

    private void studentInfoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentInfoTableMouseClicked

    }//GEN-LAST:event_studentInfoTableMouseClicked

    private void updateCurrentTimeLabel(JLabel currentTimeLabel) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentTime = timeFormat.format(new Date());
        currentTimeLabel.setText("Current Time: " + currentTime);
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        searchStudentCode.setText("");
        searchFullName.setText("");
        show_table();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        new statisticsForm().setVisible(true);
    }//GEN-LAST:event_updateButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        searchStudentCode.setText("");
        searchFullName.setText("");
        show_table();
    }//GEN-LAST:event_refreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel currentTimeLabel;
    private javax.swing.JButton detailsButton;
    private javax.swing.JButton findButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JTextField searchFullName;
    private javax.swing.JTextField searchStudentCode;
    private javax.swing.JTable studentInfoTable;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel userLoginLabel;
    // End of variables declaration//GEN-END:variables
}
