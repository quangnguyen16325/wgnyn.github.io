package qlsksv_vku;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author quang
 */

public class LoginForm extends javax.swing.JFrame {

    public String userLog, roleUserLog;
    
    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        emailUserLabel = new javax.swing.JLabel();
        emailUserField = new javax.swing.JTextField();
        passwordUserLabel = new javax.swing.JLabel();
        passwordUserField = new javax.swing.JPasswordField();
        hidePassToggle = new javax.swing.JToggleButton();
        loginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        regisButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login account");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginLabel.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        loginLabel.setText("LOGIN");
        getContentPane().add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 24, -1, -1));

        emailUserLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        emailUserLabel.setText("Email ");
        getContentPane().add(emailUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 59, 50, -1));

        emailUserField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(emailUserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 84, 250, -1));

        passwordUserLabel.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        passwordUserLabel.setText("Password");
        getContentPane().add(passwordUserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 114, 77, -1));

        passwordUserField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(passwordUserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 139, 250, -1));

        hidePassToggle.setText("Show");
        hidePassToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hidePassToggleActionPerformed(evt);
            }
        });
        getContentPane().add(hidePassToggle, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 140, -1, -1));

        loginButton.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 175, 250, 35));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 13)); // NOI18N
        jLabel1.setText("Do not have account? Create a new account.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 216, 250, -1));

        regisButton.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        regisButton.setText("Sign up");
        regisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regisButtonActionPerformed(evt);
            }
        });
        getContentPane().add(regisButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 238, 250, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/10135315_18129294 (1).jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-260, 0, 660, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String email = emailUserField.getText();
        String password = String.valueOf(passwordUserField.getPassword());
        
        if (email.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter Email","Try again",JOptionPane.ERROR_MESSAGE);
        } else if (password.isEmpty()){
             JOptionPane.showMessageDialog(this, "Please enter Password","Try again",JOptionPane.ERROR_MESSAGE);
        } else { 
        user = getAuthenticatedUser(email, password);
        }
        if (user != null){
            System.out.println("Login successful");
            dispose();
            if (roleUserLog.equalsIgnoreCase("admin")){
                new InformationSystem(userLog, roleUserLog).setVisible(true);
            } else if (roleUserLog.equalsIgnoreCase("user")){
                new InformationSystemForUser(userLog, roleUserLog).setVisible(true);
            } else if (roleUserLog.equalsIgnoreCase("master")) {
                new userManagementForMaster(userLog, "MASTER").setVisible(true);
            }
        } else if (user == null && !email.isEmpty() && !password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email or Password Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void hidePassToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hidePassToggleActionPerformed
        togglePasswordVisibility();
    }//GEN-LAST:event_hidePassToggleActionPerformed

    private void regisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regisButtonActionPerformed
        dispose();
        new SignInForm().setVisible(true);
    }//GEN-LAST:event_regisButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
    
    private void togglePasswordVisibility(){
        if (hidePassToggle.isSelected()){
            passwordUserField.setEchoChar((char) 0);
            hidePassToggle.setText("Hide");
        } else {
            passwordUserField.setEchoChar('*');
            hidePassToggle.setText("Show");
        }
    } 
    
    public Users user;
    private Users getAuthenticatedUser(String email, String password){
        user = null;
        String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
        
        try {
            Connection conn = DriverManager.getConnection(dbURL);
            
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email COLLATE SQL_Latin1_General_CP1_CS_AS = ? AND password COLLATE SQL_Latin1_General_CP1_CS_AS = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
		user = new Users();
		user.firstName = resultSet.getString("firstname");
		user.lastName = resultSet.getString("lastName");
		user.email = resultSet.getString("email");
		user.password = resultSet.getString("password");
                user.roleUser = resultSet.getString("roleUser");
                
                if (user.firstName == null){
                    user.firstName = "";
                    userLog = user.lastName;
                } else { 
                    userLog = user.firstName + " " + user.lastName;
                }
                
                if(user.roleUser == null) {
                    user.roleUser = "USER";
                }
                
                roleUserLog = user.roleUser;
            }
			
            stmt.close();
            conn.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailUserField;
    private javax.swing.JLabel emailUserLabel;
    private javax.swing.JToggleButton hidePassToggle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordUserField;
    private javax.swing.JLabel passwordUserLabel;
    private javax.swing.JButton regisButton;
    // End of variables declaration//GEN-END:variables
}
