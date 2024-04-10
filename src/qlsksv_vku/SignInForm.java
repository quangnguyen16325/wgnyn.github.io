package qlsksv_vku;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author quang
 */
public class SignInForm extends javax.swing.JFrame {
    
    private final String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
    
    public SignInForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        firstnameLabel = new javax.swing.JLabel();
        firstnameField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        emailUserLabel = new javax.swing.JLabel();
        emailUserField = new javax.swing.JTextField();
        passwordUserLabel = new javax.swing.JLabel();
        verifyPassUserLabel = new javax.swing.JLabel();
        passwordUserField = new javax.swing.JPasswordField();
        hidePassToggle = new javax.swing.JToggleButton();
        verifyPassField = new javax.swing.JPasswordField();
        signInButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create account");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginLabel.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        loginLabel.setText("SIGN UP");
        getContentPane().add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 14, -1, -1));

        firstnameLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        firstnameLabel.setText("First Name");
        getContentPane().add(firstnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 49, -1, -1));

        firstnameField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        firstnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(firstnameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 74, 240, -1));

        lastNameLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        lastNameLabel.setText("Last Name");
        getContentPane().add(lastNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 104, 83, -1));

        lastNameField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(lastNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 129, 240, -1));

        emailUserLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        emailUserLabel.setText("Email");
        getContentPane().add(emailUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 159, 83, -1));

        emailUserField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(emailUserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 184, 240, -1));

        passwordUserLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        passwordUserLabel.setText("Password");
        getContentPane().add(passwordUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 214, 83, -1));

        verifyPassUserLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        verifyPassUserLabel.setText("Verify Passowrd");
        getContentPane().add(verifyPassUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 269, 125, -1));

        passwordUserField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(passwordUserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 239, 240, -1));

        hidePassToggle.setText("Show");
        hidePassToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hidePassToggleActionPerformed(evt);
            }
        });
        getContentPane().add(hidePassToggle, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 240, -1, -1));

        verifyPassField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(verifyPassField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 294, 240, -1));

        signInButton.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        signInButton.setText("Sign up");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });
        getContentPane().add(signInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 336, 240, 32));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        jLabel1.setText("Already have an account? Log in now.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 374, 240, -1));

        jButton2.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 396, 240, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/10120975_1440 (1).jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnameFieldActionPerformed

    private void hidePassToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hidePassToggleActionPerformed
        togglePasswordVisibility();
    }//GEN-LAST:event_hidePassToggleActionPerformed

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed

        registerUser();
    }//GEN-LAST:event_signInButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void togglePasswordVisibility(){
        if (hidePassToggle.isSelected()){
            passwordUserField.setEchoChar((char) 0);
            hidePassToggle.setText("Hide");
        } else {
            passwordUserField.setEchoChar('*');
            hidePassToggle.setText("Show");
        }
    }
    
    private void registerUser(){
        String firstName = firstnameField.getText();
        String lastName = lastNameField.getText();
        String email = emailUserField.getText();
        String password = String.valueOf(passwordUserField.getPassword());
        String verifyPass = String.valueOf(verifyPassField.getPassword());
        
        if (firstName.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter First Name","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        } else if (lastName.isEmpty()){
             JOptionPane.showMessageDialog(this, "Please enter Last Name","Try again",JOptionPane.ERROR_MESSAGE);
             return;
        } else if (email.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Please enter Email","Try again",JOptionPane.ERROR_MESSAGE);
             return;
        } else if (password.isEmpty()){
             JOptionPane.showMessageDialog(this, "Please enter Password","Try again",JOptionPane.ERROR_MESSAGE);
             return;
        } else if (verifyPass.isEmpty()){
             JOptionPane.showMessageDialog(this, "Please enter Verify Password","Try again",JOptionPane.ERROR_MESSAGE);
        }
        
        if (!password.equals(verifyPass)) {
            JOptionPane.showMessageDialog(this,"Verify Password does not match","Try again", JOptionPane.ERROR_MESSAGE);
        }
        
        if (isEmailAlreadyExist(email)) {
            JOptionPane.showMessageDialog(this, "Email is already registered", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (password.equals(verifyPass)){
            user = addUserToDatabase(firstName, lastName, email, password);
            if (user != null & !password.isEmpty()){
                JOptionPane.showMessageDialog(this, "Account registration successful!");
            }
        }
   }
    
    public Users user;
    private Users addUserToDatabase(String firstName, String lastName, String email, String password){
        user = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hashed = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String sha3Hex = bytesToHex(hashed);
            try (Connection conn = DriverManager.getConnection(dbURL)){
//                Connection conn = DriverManager.getConnection(dbURL);
//                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO users (firstName, lastName, email, password)" +
                        "VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, sha3Hex);

                    int addedRows = preparedStatement.executeUpdate();
                    if (addedRows > 0) {
                        user = new Users();
                        user.firstName = firstName;
                        user.lastName = lastName;
                        user.email = email;
                        user.password = password;
                    }
                }
            } 
        } catch (NoSuchAlgorithmException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }

    //                stmt.close();
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            return user;
    }
    
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
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private boolean isCorrectEmail(String email) {
        
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailUserField;
    private javax.swing.JLabel emailUserLabel;
    private javax.swing.JTextField firstnameField;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JToggleButton hidePassToggle;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordUserField;
    private javax.swing.JLabel passwordUserLabel;
    private javax.swing.JButton signInButton;
    private javax.swing.JPasswordField verifyPassField;
    private javax.swing.JLabel verifyPassUserLabel;
    // End of variables declaration//GEN-END:variables
}
