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
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quang
 */
public class userManagementForMaster extends javax.swing.JFrame {

    public String firstName, lastName, email, password, roleUser;
    
    public userManagementForMaster(String userName, String role) {
        initComponents();
        show_table();
        setLocationRelativeTo(null);
        
        this.userName = userName;
        this.roleUser = role;
        userLoginLabel.setText(userName);
        roleLabel.setText(roleUser);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        userLoginLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        passwordField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        roleField = new javax.swing.JComboBox<>();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        nextPageButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/master.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(646, 6, 89, -1));

        userLoginLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        userLoginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLoginLabel.setText("...");
        getContentPane().add(userLoginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 6, 120, -1));

        roleLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        roleLabel.setForeground(new java.awt.Color(255, 51, 0));
        roleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roleLabel.setText("...");
        getContentPane().add(roleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 31, 120, -1));

        jLabel1.setFont(new java.awt.Font("Perpetua", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("USER MANAGEMENT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 6, -1, 50));

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "First Name", "Last Name", "Email", "Password", "Role"
            }
        ));
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 593, 330));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/master.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 6, 89, -1));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel2.setText("First Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 80, -1, -1));

        firstNameField.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        getContentPane().add(firstNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 101, 270, -1));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel3.setText("Last Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 135, -1, -1));

        lastNameField.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        getContentPane().add(lastNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 156, 270, -1));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 190, 58, -1));

        emailField.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        getContentPane().add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 211, 270, -1));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel5.setText("Password");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 245, 58, -1));

        passwordField.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 266, 270, -1));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel8.setText("Role");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 298, 47, -1));

        roleField.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        roleField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN" }));
        roleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleFieldActionPerformed(evt);
            }
        });
        getContentPane().add(roleField, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 294, 192, -1));

        updateButton.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/changes.png"))); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 120, -1));

        deleteButton.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/delete-user.png"))); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 120, -1));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel9.setText("Name :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 9, -1, -1));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel10.setText("Role :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 34, 37, -1));

        addButton.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/add-user.png"))); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 120, -1));

        clearButton.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        clearButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\quang\\Downloads\\broom.png")); // NOI18N
        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 120, -1));

        logoutButton.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/logout.png"))); // NOI18N
        logoutButton.setText("LOG OUT");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        getContentPane().add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, 40));

        nextPageButton.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        nextPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/send.png"))); // NOI18N
        nextPageButton.setText("STUDENT INFORMATION");
        nextPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPageButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nextPageButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 220, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/3320389_9213 (2).jpg"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleFieldActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        email = emailField.getText();
        password = passwordField.getText();
        roleUser = roleField.getSelectedItem().toString();
        
        if (lastName.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter Last Name.");
        } else if (email.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Email.");
        } else if (password.equals("")) {
             JOptionPane.showMessageDialog(null, "Please enter Password.");
        } else {
            try {
                String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
                conn = DriverManager.getConnection(dbURL);
                String updateQuery = "UPDATE users SET firstName=?, lastName=?, email=?, password=?, roleUser=? WHERE email=?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, roleUser);
                preparedStatement.setString(6, email);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "User updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update user.");
                }
                
                conn.close();
                show_table();
                
            }   catch (SQLException ex) {
                   Logger.getLogger(InformationSystem.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow != -1) {
            firstName = getValueAt(selectedRow, 0);
            lastName = getValueAt(selectedRow, 1);
            email = getValueAt(selectedRow, 2);
            password = getValueAt(selectedRow, 3);
            roleUser = getValueAt(selectedRow, 4);
            
            if (roleUser == null){
                roleUser = "USER";
            }
            
            firstNameField.setText(firstName);
            lastNameField.setText(lastName);
            emailField.setText(email);
            passwordField.setText(password);
            roleField.setSelectedItem(roleUser);
        }
    }//GEN-LAST:event_usersTableMouseClicked

    private boolean isEmailAlreadyExist(String email) {
    String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
    
    try (Connection conn = DriverManager.getConnection(dbURL)) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        roleField.setSelectedItem("USER");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        email = emailField.getText();
        password = passwordField.getText();
        roleUser = roleField.getSelectedItem().toString();
        
        if (lastName.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter Last Name.");
        } else if (email.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Email.");
        } else if (password.equals("")) {
             JOptionPane.showMessageDialog(null, "Please enter Password.");
        }else if (isEmailAlreadyExist(email)) {
            JOptionPane.showMessageDialog(this, "Email is already registered", "Try again", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
                conn = DriverManager.getConnection(dbURL);
                String insertUser = "INSERT INTO users (firstName, lastName, email, password, roleUser) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(insertUser);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, roleUser);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "User added successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add user.");
                }
                
                conn.close();
                show_table();
                
            }   catch (SQLException ex) {
                   Logger.getLogger(InformationSystem.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = usersTable.getSelectedRow();
       
        if (selectedRow != -1) {
           email = usersTable.getValueAt(selectedRow, 2).toString();
           int result = JOptionPane.showConfirmDialog(
                null, 
                "Do you want to continue deleting?",
                "Confirm",  
                JOptionPane.YES_NO_OPTION);  

        if (result == JOptionPane.YES_OPTION) {
            try {
                String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
                conn = DriverManager.getConnection(dbURL);
                deleteFromTable(conn, "users", email);
                
                conn.close();
                firstNameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                roleField.setSelectedItem("USER");
                show_table();
                
            }   catch (SQLException ex) {
                   Logger.getLogger(InformationSystem.class.getName()).log(Level.SEVERE, null, ex);
               }
        } else if (result == JOptionPane.NO_OPTION) {
            this.dispose();
        }
       } else {
           JOptionPane.showMessageDialog(this, "Please select a row for Delete.");
       }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void nextPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageButtonActionPerformed
        this.dispose();
        new InformationSystem(userName, "master").setVisible(true);
    }//GEN-LAST:event_nextPageButtonActionPerformed
    
    private static void deleteFromTable(Connection connection, String tableName, String studentCode) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE email = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setString(1, studentCode);

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
    }
    
    private String getValueAt(int row, int column) {
        Object value = usersTable.getValueAt(row, column);
        return (value != null) ? value.toString() : null;
    }

    public String userName, roleUser_log;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    private void show_table(){
        
        int CC;
        
        try {
            String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
            conn = DriverManager.getConnection(dbURL);
            preparedStatement = conn.prepareStatement("SELECT * FROM users");
            ResultSet Rs = preparedStatement.executeQuery();
            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) usersTable.getModel();
            
            DFT.setRowCount(0);
            while (Rs.next()){
                
                Vector v2 = new Vector();
                
                for (int i = 1; i <= CC; i++){
                    v2.add(Rs.getString("firstName"));
                    v2.add(Rs.getString("lastName"));
                    v2.add(Rs.getString("email"));
                    v2.add(Rs.getString("password"));
                    v2.add(Rs.getString("roleUser"));
                    
                }
                DFT.addRow(v2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InformationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton nextPageButton;
    private javax.swing.JTextField passwordField;
    private javax.swing.JComboBox<String> roleField;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel userLoginLabel;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
