/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package qlsksv_vku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class statisticsForm extends javax.swing.JFrame {

    public int currentYear;
    
    public statisticsForm() {
        initComponents();
        setLocationRelativeTo(null);
        
        genderDistributionSummary();      
        avgStatistic();
        BloodGroupStatistics();
        HealthStatusStatistics();
        
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        genderDistributionLabel.setText("GENDER DISTRIBUTION SUMMARY - " + currentYear);
        avgIndexStatisticLabel.setText("AVERAGE INDEX STATISTICS - " + currentYear);
        bloodTypeDistributionLabel.setText("BLOOD TYPE DISTRIBUTION SUMMARY - " + currentYear);
        healthDistributionLabel.setText("HEALTH DISTRIBUTION SUMMARY - " + currentYear);
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateCurrentTimeLabel(currentTimeLabel1);
                updateCurrentTimeLabel(currentTimeLabel2);
                updateCurrentTimeLabel(currentTimeLabel3);
                updateCurrentTimeLabel(currentTimeLabel4);
            }
        }, 0, 1000);
    }
   
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    
    String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";

    public void genderDistributionSummary() {
        String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";
        int maleCount = 0, femaleCount = 0, sumGender = 0;
        double malePercent = 0, femalePercent = 0;
        try {
            conn = DriverManager.getConnection(dbURL);
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT gender, COUNT(*) as count FROM details GROUP BY gender")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                int count = resultSet.getInt("count");

                if ("Male".equalsIgnoreCase(gender)) {
                    maleCount = count;
                } else if ("Female".equalsIgnoreCase(gender)) {
                    femaleCount = count;
                }
            }
            sumGender = maleCount + femaleCount;
            malePercent = (maleCount*100)/sumGender;
            femalePercent = 100 - malePercent;
            maleTotal.setText(String.valueOf(maleCount));
            femaleTotal.setText(String.valueOf(femaleCount));
            malePercentLabel.setText(String.valueOf(malePercent));
            femalePercentLabel.setText(String.valueOf(femalePercent));
        }
        } catch (SQLException ex) {
            Logger.getLogger(statisticsForm.class.getName()).log(Level.SEVERE, "Error connecting to the database", ex);
            ex.printStackTrace();
        }

    }
    
    public void avgStatistic() {
        String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";

        try {
            conn = DriverManager.getConnection(dbURL);
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT height, weight, bmi FROM healths")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            double totalHeight = 0.0;
            double totalWeight = 0.0;
            double totalBMI = 0.0;
            while (resultSet.next()) {
                double height = resultSet.getDouble("height");
                double weight = resultSet.getDouble("weight");
                double bmi = resultSet.getDouble("bmi");
                
                totalHeight += height;
                totalWeight += weight;
                totalBMI += bmi;
                
                count++;
            }
            double averageHeight = count > 0 ? totalHeight / count : 0.0;
            double averageWeight = count > 0 ? totalWeight / count : 0.0;
            double averageBMI = count > 0 ? totalBMI / count : 0.0;
            
            String averageHeight_text = decimalFormat.format(averageHeight);
            String averageWeight_text = decimalFormat.format(averageWeight);
            String averageBMI_text = decimalFormat.format(averageBMI);
            
            totalStudentLabel.setText(String.valueOf(count));
            totalStudentLabel1.setText(String.valueOf(count));
            totalStudentLabel2.setText(String.valueOf(count));
            avgHeightLabel.setText(averageHeight_text);
            avgWeightLabel.setText(averageWeight_text);
            avgBMILabel.setText(averageBMI_text);
            
            resultSet.close();
            preparedStatement.close();
            conn.close();
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(statisticsForm.class.getName()).log(Level.SEVERE, "Error connecting to the database", ex);
            ex.printStackTrace();
        }
    }

    public void BloodGroupStatistics() {
        String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";

        try {
            conn = DriverManager.getConnection(dbURL);
            int grACount = 0, grBCount = 0, grABCount = 0, grOCount = 0;
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT bloodType, COUNT(*) as count FROM healths GROUP BY bloodType")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String bloodType = resultSet.getString("bloodType");
                int count = resultSet.getInt("count");
                
                switch (bloodType.toUpperCase()) {
                        case "GROUP A":
                            grACount = count;
                            break;
                        case "GROUP B":
                            grBCount = count;
                            break;
                        case "GROUP AB":
                            grABCount = count;
                            break;
                        case "GROUP O":
                            grOCount = count;
                            break;
                    }
            }
                groupALabel.setText(String.valueOf(grACount));
                groupBLabel.setText(String.valueOf(grBCount));
                groupABLabel.setText(String.valueOf(grABCount));
                groupOLabel.setText(String.valueOf(grOCount));
                
                resultSet.close();
            
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(statisticsForm.class.getName()).log(Level.SEVERE, "Error connecting to the database", ex);
            ex.printStackTrace();
        }
    }
    
    public void HealthStatusStatistics(){
        String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLSKSV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";

        try {
            conn = DriverManager.getConnection(dbURL);
            int ExceCount = 0, GoodCount = 0, FairCount = 0, AveCount = 0, PoorCount = 0, veryPoorCount = 0;
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT healthStatus, COUNT(*) as count FROM healths GROUP BY healthStatus")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String healthStatus = resultSet.getString("healthStatus");
                int count = resultSet.getInt("count");
                
                switch (healthStatus) {
                        case "Sức khỏe rất tốt":
                            ExceCount = count;
                            break;
                        case "Sức khỏe tốt":
                            GoodCount = count;
                            break;
                        case "Sức khỏe khá":
                            FairCount = count;
                            break;
                        case "Sức khỏe trung bình":
                            AveCount = count;
                            break;
                        case "Sức khỏe kém":
                            PoorCount = count;
                            break;
                        case "Sức khỏe rất kém":
                            veryPoorCount = count;
                            break;
                    }
            }
                ExceLabel.setText(String.valueOf(ExceCount));
                GoodLabel.setText(String.valueOf(GoodCount));
                AveLabel.setText(String.valueOf(AveCount));
                fairLabel.setText(String.valueOf(FairCount));
                PoorLabel.setText(String.valueOf(PoorCount));
                veryPoorLabel.setText(String.valueOf(veryPoorCount));
                
                resultSet.close();
            
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(statisticsForm.class.getName()).log(Level.SEVERE, "Error connecting to the database", ex);
            ex.printStackTrace();
        }
    }
    
    private void updateCurrentTimeLabel(JLabel currentTimeLabel) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentTime = timeFormat.format(new Date());
        currentTimeLabel1.setText("Current Time: " + currentTime);
        currentTimeLabel2.setText("Current Time: " + currentTime);
        currentTimeLabel3.setText("Current Time: " + currentTime);
        currentTimeLabel4.setText("Current Time: " + currentTime);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        currentTimeLabel1 = new javax.swing.JLabel();
        genderDistributionLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        maleTotal = new javax.swing.JLabel();
        femaleTotal = new javax.swing.JLabel();
        malePercentLabel = new javax.swing.JLabel();
        femalePercentLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        avgIndexStatisticLabel = new javax.swing.JLabel();
        currentTimeLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        avgBMILabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        totalStudentLabel = new javax.swing.JLabel();
        avgHeightLabel = new javax.swing.JLabel();
        avgWeightLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bloodTypeDistributionLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        currentTimeLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        groupBLabel = new javax.swing.JLabel();
        groupABLabel = new javax.swing.JLabel();
        groupOLabel = new javax.swing.JLabel();
        groupALabel = new javax.swing.JLabel();
        totalStudentLabel1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        healthDistributionLabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        ExceLabel = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        GoodLabel = new javax.swing.JLabel();
        fairLabel = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        veryPoorLabel = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        AveLabel = new javax.swing.JLabel();
        PoorLabel = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        totalStudentLabel2 = new javax.swing.JLabel();
        currentTimeLabel4 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        currentTimeLabel1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        currentTimeLabel1.setText("CurrentTime");
        jPanel1.add(currentTimeLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 250, 20));

        genderDistributionLabel.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        genderDistributionLabel.setText("GENDER DISTRIBUTION SUMMARY - 2024");
        jPanel1.add(genderDistributionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gender");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 102, 150, 30));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total Individuals");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 102, 150, 30));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Percentage (%)");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 102, 150, 30));

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Male");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 150, 40));

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Female");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 150, 40));

        maleTotal.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        maleTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maleTotal.setText(".");
        maleTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(maleTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 150, 40));

        femaleTotal.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        femaleTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        femaleTotal.setText(".");
        femaleTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.add(femaleTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 150, 40));

        malePercentLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        malePercentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        malePercentLabel.setText(".");
        malePercentLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.add(malePercentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 150, 40));

        femalePercentLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        femalePercentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        femalePercentLabel.setText(".");
        femalePercentLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel1.add(femalePercentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 150, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/bgr_Statistics3.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 340));

        jTabbedPane1.addTab("Gender ratio", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        avgIndexStatisticLabel.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        avgIndexStatisticLabel.setText("AVERAGE INDEX STATISTICS - ");
        jPanel2.add(avgIndexStatisticLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        currentTimeLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        currentTimeLabel2.setText("CurrentTime");
        jPanel2.add(currentTimeLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 250, 20));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Statistics");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 190, 40));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("\tAverage Value");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 190, 40));

        jLabel9.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Average BMI");
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 190, 30));

        avgBMILabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        avgBMILabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avgBMILabel.setText(".");
        avgBMILabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel2.add(avgBMILabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 190, 30));

        jLabel11.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Average Height (cm)");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 190, 30));

        jLabel12.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Average Weight (kg)");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 190, 30));

        jLabel13.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total Students");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 190, 30));

        totalStudentLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        totalStudentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalStudentLabel.setText(".");
        totalStudentLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel2.add(totalStudentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 190, 30));

        avgHeightLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        avgHeightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avgHeightLabel.setText(".");
        avgHeightLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel2.add(avgHeightLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 190, 30));

        avgWeightLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        avgWeightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avgWeightLabel.setText(".");
        avgWeightLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel2.add(avgWeightLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 190, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/bgr_Statistics3.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 340));

        jTabbedPane1.addTab(" Average statistics", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bloodTypeDistributionLabel.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        bloodTypeDistributionLabel.setText(" BLOOD TYPE DISTRIBUTION SUMMARY - ");
        jPanel3.add(bloodTypeDistributionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Statistics");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 190, 40));

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("\tAverage Value");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 190, 40));

        jLabel16.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("A");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 190, 30));

        jLabel17.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("B");
        jLabel17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 190, 30));

        currentTimeLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        currentTimeLabel3.setText("CurrentTime");
        jPanel3.add(currentTimeLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 250, 20));

        jLabel18.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("O");
        jLabel18.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 190, 30));

        jLabel19.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("AB");
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 190, 30));

        jLabel20.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Total");
        jLabel20.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 190, 30));

        groupBLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        groupBLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        groupBLabel.setText(".");
        groupBLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel3.add(groupBLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 190, 30));

        groupABLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        groupABLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        groupABLabel.setText(".");
        groupABLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel3.add(groupABLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 190, 30));

        groupOLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        groupOLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        groupOLabel.setText(".");
        groupOLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel3.add(groupOLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 190, 30));

        groupALabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        groupALabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        groupALabel.setText(".");
        groupALabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel3.add(groupALabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 190, 30));

        totalStudentLabel1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        totalStudentLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalStudentLabel1.setText(".");
        totalStudentLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel3.add(totalStudentLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 190, 30));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/bgr_Statistics3.jpg"))); // NOI18N
        jLabel21.setText("jLabel21");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 340));

        jTabbedPane1.addTab("Blood group statistics", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        healthDistributionLabel.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        healthDistributionLabel.setText(" HEALTH DISTRIBUTION SUMMARY - ");
        jPanel4.add(healthDistributionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Health Category");
        jLabel22.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 190, 40));

        jLabel23.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Number of Students");
        jLabel23.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 190, 40));

        ExceLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        ExceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExceLabel.setText(".");
        ExceLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(ExceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 190, 30));

        jLabel24.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Rất Tốt");
        jLabel24.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 190, 30));

        jLabel25.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Tốt");
        jLabel25.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 190, 30));

        GoodLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        GoodLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GoodLabel.setText(".");
        GoodLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(GoodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 190, 30));

        fairLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        fairLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fairLabel.setText(".");
        fairLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(fairLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 190, 30));

        jLabel26.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Khá");
        jLabel26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 190, 30));

        jLabel27.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Rất Kém");
        jLabel27.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 190, 30));

        veryPoorLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        veryPoorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        veryPoorLabel.setText(".");
        veryPoorLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(veryPoorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 190, 30));

        jLabel28.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Trung Bình");
        jLabel28.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 190, 30));

        jLabel29.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Kém");
        jLabel29.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 190, 30));

        AveLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        AveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AveLabel.setText(".");
        AveLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(AveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 190, 30));

        PoorLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        PoorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PoorLabel.setText(".");
        PoorLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(PoorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 190, 30));

        jLabel30.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Total");
        jLabel30.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 190, 30));

        totalStudentLabel2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        totalStudentLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalStudentLabel2.setText(".");
        totalStudentLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        jPanel4.add(totalStudentLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 190, 30));

        currentTimeLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        currentTimeLabel4.setText("CurrentTime");
        jPanel4.add(currentTimeLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 250, 20));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlsksv_vku/bgr_Statistics3.jpg"))); // NOI18N
        jLabel31.setText("jLabel31");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 340));

        jTabbedPane1.addTab("Health status statistics", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AveLabel;
    private javax.swing.JLabel ExceLabel;
    private javax.swing.JLabel GoodLabel;
    private javax.swing.JLabel PoorLabel;
    private javax.swing.JLabel avgBMILabel;
    private javax.swing.JLabel avgHeightLabel;
    private javax.swing.JLabel avgIndexStatisticLabel;
    private javax.swing.JLabel avgWeightLabel;
    private javax.swing.JLabel bloodTypeDistributionLabel;
    private javax.swing.JLabel currentTimeLabel1;
    private javax.swing.JLabel currentTimeLabel2;
    private javax.swing.JLabel currentTimeLabel3;
    private javax.swing.JLabel currentTimeLabel4;
    private javax.swing.JLabel fairLabel;
    private javax.swing.JLabel femalePercentLabel;
    private javax.swing.JLabel femaleTotal;
    private javax.swing.JLabel genderDistributionLabel;
    private javax.swing.JLabel groupABLabel;
    private javax.swing.JLabel groupALabel;
    private javax.swing.JLabel groupBLabel;
    private javax.swing.JLabel groupOLabel;
    private javax.swing.JLabel healthDistributionLabel;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel malePercentLabel;
    private javax.swing.JLabel maleTotal;
    private javax.swing.JLabel totalStudentLabel;
    private javax.swing.JLabel totalStudentLabel1;
    private javax.swing.JLabel totalStudentLabel2;
    private javax.swing.JLabel veryPoorLabel;
    // End of variables declaration//GEN-END:variables
}
