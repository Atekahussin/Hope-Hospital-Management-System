/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_project;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
/**
 *
 * @author AMAL
 */
public class Book extends javax.swing.JFrame {

    /**
     * Creates new form Book
     */
    Connection con=null;
    public Book() {
        initComponents();
        
         try 
        {
    
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hope?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , 
            "root", "1234");
        }
        catch ( java.sql.SQLException sqlException ) 
        {
            System.out.println(sqlException.getMessage() );
        }
    }
private String getClinicByDoctor(String doctorFullName) {
    String clinic = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Ensure the connection is established
        if (con == null || con.isClosed()) {
            throw new SQLException("Database connection is not available.");
        }

        // SQL Query to fetch Clinic based on Doctor's full name
        String query = "SELECT clinic FROM doctors WHERE full_name = ?";
        stmt = con.prepareStatement(query);
        stmt.setString(1, doctorFullName);

        // Execute the query
        rs = stmt.executeQuery();
        if (rs.next()) {
            clinic = rs.getString("clinic"); // Match the "clinic" column from the table
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return clinic;
}

// Method to fetch user_id from users table using the username
private int getUserIdFromUsername(String username) {
    int userId = -1;
    try {
        String query = "SELECT UserID FROM users WHERE username = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("UserID");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return userId;
}

// Method to check if the appointment already exists
private boolean appointmentExists(String doctor, String day, String time, String date) {
    try {
        // Query to check if the appointment already exists
        String query = "SELECT * FROM appointments WHERE doctor_name = ? AND day = ? AND time = ? AND date = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, doctor);
            stmt.setString(2, day);
            stmt.setString(3, time);
            stmt.setString(4, date);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If the query returns a result, the appointment already exists
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // No matching appointment found
}



private void insertAppointmentIntoDatabase(String doctor, String day, String time, String date, String clinic, String availability) {
    PreparedStatement stmt = null;

    try {
        // SQL Query to insert the appointment into the appointments table
        String query = "INSERT INTO appointments (doctor_name, day, time, date, clinic, availability) VALUES ( ?, ?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(query);

        // Set values for the query
        stmt.setString(1, doctor);
        stmt.setString(2, day);
        stmt.setString(3, time);
        stmt.setString(4, date);
        stmt.setString(5, clinic);
        stmt.setString(6, availability);
     

        stmt.executeUpdate(); // Execute the query

        // Success message
        JOptionPane.showMessageDialog(this, "Appointment added to the database successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


// Method to check if the user has an appointment at the same time with a different doctor
private boolean userHasAppointmentAtSameTimeWithDifferentDoctor(String doctor, String day, String time, String date) {
    boolean hasAppointment = false;
    String query = "SELECT * FROM appointments WHERE day = ? AND time = ? AND date = ? AND doctor_name != ?";

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, day);
        stmt.setString(2, time);
        stmt.setString(3, date);
        stmt.setString(4, doctor); // Exclude the selected doctor from the search
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                hasAppointment = true;  // The user already has an appointment for the same day, time, and date with another doctor
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return hasAppointment;
}
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Doctors = new javax.swing.JButton();
        Services = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        Hope_Label = new javax.swing.JLabel();
        AboutUs = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Services1 = new javax.swing.JButton();
        Logout1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        doctorComboBox = new javax.swing.JComboBox<>();
        dayComboBox = new javax.swing.JComboBox<>();
        timeComboBox = new javax.swing.JComboBox<>();
        confirm = new javax.swing.JButton();
        dateComboBox = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        doctorComboBox1 = new javax.swing.JComboBox<>();
        dayComboBox1 = new javax.swing.JComboBox<>();
        timeComboBox1 = new javax.swing.JComboBox<>();
        confirm1 = new javax.swing.JButton();
        dateComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        doctorComboBox2 = new javax.swing.JComboBox<>();
        dayComboBox2 = new javax.swing.JComboBox<>();
        timeComboBox2 = new javax.swing.JComboBox<>();
        confirm2 = new javax.swing.JButton();
        dateComboBox2 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        doctorComboBox3 = new javax.swing.JComboBox<>();
        dayComboBox3 = new javax.swing.JComboBox<>();
        timeComboBox3 = new javax.swing.JComboBox<>();
        confirm3 = new javax.swing.JButton();
        dateComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1300, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1300, 700));

        jPanel2.setBackground(new java.awt.Color(3, 28, 54));

        Doctors.setBackground(new java.awt.Color(3, 28, 54));
        Doctors.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Doctors.setForeground(new java.awt.Color(255, 255, 255));
        Doctors.setText("Our Doctors");
        Doctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoctorsActionPerformed(evt);
            }
        });

        Services.setBackground(new java.awt.Color(3, 28, 54));
        Services.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Services.setForeground(new java.awt.Color(255, 255, 255));
        Services.setText("Patient Services");
        Services.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServicesActionPerformed(evt);
            }
        });

        Logout.setBackground(new java.awt.Color(3, 28, 54));
        Logout.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        Hope_Label.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Hope_Label.setForeground(new java.awt.Color(255, 255, 255));
        Hope_Label.setText("HopeHospital");

        AboutUs.setBackground(new java.awt.Color(3, 28, 54));
        AboutUs.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        AboutUs.setForeground(new java.awt.Color(255, 255, 255));
        AboutUs.setText("About Us");
        AboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutUsActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/hop1.png"))); // NOI18N

        Services1.setBackground(new java.awt.Color(3, 28, 54));
        Services1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Services1.setForeground(new java.awt.Color(255, 255, 255));
        Services1.setText("Contact Us");
        Services1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Services1ActionPerformed(evt);
            }
        });

        Logout1.setBackground(new java.awt.Color(3, 28, 54));
        Logout1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Logout1.setForeground(new java.awt.Color(255, 255, 255));
        Logout1.setText("Return");
        Logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Hope_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addComponent(AboutUs)
                .addGap(18, 18, 18)
                .addComponent(Doctors)
                .addGap(18, 18, 18)
                .addComponent(Services)
                .addGap(18, 18, 18)
                .addComponent(Services1)
                .addGap(120, 120, 120)
                .addComponent(Logout1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Logout)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Doctors)
                            .addComponent(Services)
                            .addComponent(AboutUs)
                            .addComponent(Services1)
                            .addComponent(Logout)
                            .addComponent(Logout1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Hope_Label)
                            .addComponent(jLabel2))))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(243, 242, 237));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setForeground(new java.awt.Color(3, 28, 54));

        doctorComboBox.setMaximumRowCount(3);
        doctorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Doctor ", "Dr. Afaf Naji", "Dr. Fatima Al-Beik", "Dr. Loai Al Mubarak", "Dr. Saleh Al Khalaf" }));
        doctorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorComboBoxActionPerformed(evt);
            }
        });

        dayComboBox.setMaximumRowCount(4);
        dayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        dayComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayComboBoxActionPerformed(evt);
            }
        });

        timeComboBox.setMaximumRowCount(4);
        timeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a time", "08:30AM", "09:00AM", "09:30AM", "10:00AM", "10:30AM", "11:00AM", "11:30AM", "01:40PM", "03:30PM", "04:00PM", "05:00PM", "06:00PM", "06:30PM" }));
        timeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeComboBoxActionPerformed(evt);
            }
        });

        confirm.setText("Confirm");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        dateComboBox.setMaximumRowCount(4);
        dateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Date", "15 December 2024", "16 December 2024", "17 December 2024", "18 December 2024", "19 December 2024", "20 December 2024", "21 December 2024", "23 December 2024", "27 December 2024", "30 December 2024" }));
        dateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(doctorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dayComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeComboBox, 0, 130, Short.MAX_VALUE)
                    .addComponent(dateComboBox, 0, 130, Short.MAX_VALUE))
                .addGap(35, 35, 35))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(confirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(confirm)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Dental ", jPanel3);

        doctorComboBox1.setMaximumRowCount(3);
        doctorComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Doctor ", "Dr. Hisham Khalil", "Dr. Fadi Alreefi", " " }));
        doctorComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorComboBox1ActionPerformed(evt);
            }
        });

        dayComboBox1.setMaximumRowCount(4);
        dayComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        dayComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayComboBox1ActionPerformed(evt);
            }
        });

        timeComboBox1.setMaximumRowCount(4);
        timeComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a time", "08:30AM", "09:00AM", "09:30AM", "10:00AM", "10:30AM", "11:00AM", "11:30AM", "01:40PM", "03:30PM", "04:00PM", "05:00PM", "06:00PM", "06:30PM" }));
        timeComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeComboBox1ActionPerformed(evt);
            }
        });

        confirm1.setText("Confirm");
        confirm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm1ActionPerformed(evt);
            }
        });

        dateComboBox1.setMaximumRowCount(4);
        dateComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Date", "15 December 2024", "16 December 2024", "17 December 2024", "18 December 2024", "19 December 2024", "20 December 2024", "21 December 2024", "23 December 2024", "27 December 2024", "30 December 2024" }));
        dateComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doctorComboBox1, 0, 177, Short.MAX_VALUE)
                    .addComponent(dayComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateComboBox1, 0, 157, Short.MAX_VALUE)
                    .addComponent(timeComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(confirm1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(confirm1)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Cardiology ", jPanel5);

        doctorComboBox2.setMaximumRowCount(3);
        doctorComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Doctor ", "Dr. Mohammed Alfalah", "Dr. Sari Soukarieh", "Dr. Omar Al-Abdulsalam" }));
        doctorComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorComboBox2ActionPerformed(evt);
            }
        });

        dayComboBox2.setMaximumRowCount(4);
        dayComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        dayComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayComboBox2ActionPerformed(evt);
            }
        });

        timeComboBox2.setMaximumRowCount(4);
        timeComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a time", "08:30AM", "09:00AM", "09:30AM", "10:00AM", "10:30AM", "11:00AM", "11:30AM", "01:40PM", "03:30PM", "04:00PM", "05:00PM", "06:00PM", "06:30PM" }));
        timeComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeComboBox2ActionPerformed(evt);
            }
        });

        confirm2.setText("Confirm");
        confirm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm2ActionPerformed(evt);
            }
        });

        dateComboBox2.setMaximumRowCount(4);
        dateComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Date", "15 December 2024", "16 December 2024", "17 December 2024", "18 December 2024", "19 December 2024", "20 December 2024", "21 December 2024", "23 December 2024", "27 December 2024", "30 December 2024" }));
        dateComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(confirm2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(doctorComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dayComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateComboBox2, 0, 157, Short.MAX_VALUE)
                    .addComponent(timeComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(confirm2)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Ophthalmology ", jPanel4);

        doctorComboBox3.setMaximumRowCount(3);
        doctorComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Doctor ", "Dr. Basmah Aldhafer", "Dr. Basil Bedir" }));
        doctorComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorComboBox3ActionPerformed(evt);
            }
        });

        dayComboBox3.setMaximumRowCount(4);
        dayComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        dayComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayComboBox3ActionPerformed(evt);
            }
        });

        timeComboBox3.setMaximumRowCount(4);
        timeComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a time", "08:30AM", "09:00AM", "09:30AM", "10:00AM", "10:30AM", "11:00AM", "11:30AM", "01:40PM", "03:30PM", "04:00PM", "05:00PM", "06:00PM", "06:30PM" }));
        timeComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeComboBox3ActionPerformed(evt);
            }
        });

        confirm3.setText("Confirm");
        confirm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm3ActionPerformed(evt);
            }
        });

        dateComboBox3.setMaximumRowCount(4);
        dateComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a Date", "15 December 2024", "16 December 2024", "17 December 2024", "18 December 2024", "19 December 2024", "20 December 2024", "21 December 2024", "23 December 2024", "27 December 2024", "30 December 2024" }));
        dateComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(confirm3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(doctorComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dayComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateComboBox3, 0, 157, Short.MAX_VALUE)
                    .addComponent(timeComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(confirm3)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Orthopedic ", jPanel6);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor", "Day", "Time", "Date", "Clinic", "Availability"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Book Here");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("My Appointment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(279, 279, 279))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(257, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Dental ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Dental ");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoctorsActionPerformed
        // TODO add your handling code here:
        Doctors DoctorsFrame = new Doctors();
        DoctorsFrame.setVisible(true);
        DoctorsFrame.pack();// This adjusts the frame size based on its components
        DoctorsFrame.setLocationRelativeTo(null); // Centers the frame on the screen
    }//GEN-LAST:event_DoctorsActionPerformed

    private void ServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServicesActionPerformed
        // TODO add your handling code here:
         Services ServicesFrame = new Services();
         ServicesFrame.setVisible(true);
         ServicesFrame.pack();// This adjusts the frame size based on its components
         ServicesFrame.setLocationRelativeTo(null); // Centers the frame on the screen
    }//GEN-LAST:event_ServicesActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        // Show a friendly confirmation message to the patient
    javax.swing.JOptionPane.showMessageDialog(null, 
        "Visit us again. Take care of yourself!", 
        "Logout", 
        javax.swing.JOptionPane.INFORMATION_MESSAGE);

    // Open the Login frame
    oop_project.Login LoginFrame = new oop_project.Login();
    LoginFrame.setVisible(true);
    LoginFrame.pack(); // Adjusts the frame size based on its components
    LoginFrame.setLocationRelativeTo(null); // Centers the frame on the screen

    // Close the current frame
    this.dispose();
    }//GEN-LAST:event_LogoutActionPerformed

    private void AboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutUsActionPerformed
        // TODO add your handling code here:
        About AboutFrame = new About();
        AboutFrame.setVisible(true);
        AboutFrame.pack();// This adjusts the frame size based on its components
        AboutFrame.setLocationRelativeTo(null); // Centers the frame on the screen
    }//GEN-LAST:event_AboutUsActionPerformed

    private void Services1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Services1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Services1ActionPerformed

    private void doctorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorComboBoxActionPerformed

    private void dayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayComboBoxActionPerformed

    private void timeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeComboBoxActionPerformed
// Declare the Random object as a class-level variable
private final Random random = new Random(); // Initialize it once
// Initialize it once
    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
    // Retrieve selected values from the ComboBoxes
    String doctor = (String) doctorComboBox.getSelectedItem();
    String day = (String) dayComboBox.getSelectedItem();
    String time = (String) timeComboBox.getSelectedItem();
    String date = (String) dateComboBox.getSelectedItem();

    // Validation: Ensure all fields are selected
    if ("Choose a Doctor".equals(doctor) || "Choose a Day".equals(day) || "Choose a Time".equals(time) || "Choose a Date".equals(date)) {
        JOptionPane.showMessageDialog(this, "Please select all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Check if the user already has an appointment for the same day, time, and date with another doctor
        if (userHasAppointmentAtSameTimeWithDifferentDoctor(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "You already have an appointment at this time with a different doctor. Please choose a different time or doctor.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the appointment already exists in the database (same doctor, day, time, date)
        if (appointmentExists(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "This appointment is already taken. Please choose a different time.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve Clinic Name from Database (match full_name column in the doctors table)
        String clinic = getClinicByDoctor(doctor); // Pass the full doctor name
        if (clinic == null) {
            JOptionPane.showMessageDialog(this, "Clinic not found for the selected doctor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Random Availability State
        String[] availabilityStates = {"Confirmed", "Suspended", "Canceled"};
        String availability = availabilityStates[random.nextInt(availabilityStates.length)]; // Use the class-level Random object

        // Display Confirmation Message
        String message = String.format("Appointment confirmed!\nDoctor: %s\nDay: %s\nTime: %s\nDate: %s\nClinic: %s\nStatus: %s", 
                                        doctor, day, time, date, clinic, availability);
        JOptionPane.showMessageDialog(this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        // Add the new appointment row to the JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{doctor, day, time, date, clinic, availability});

        // Optionally Reset ComboBoxes
        doctorComboBox.setSelectedIndex(0);
        dayComboBox.setSelectedIndex(0);
        timeComboBox.setSelectedIndex(0);
        dateComboBox.setSelectedIndex(0);

        // Insert the appointment into the database
        insertAppointmentIntoDatabase(doctor, day, time, date, clinic, availability); // Pass the userId
    }
    }//GEN-LAST:event_confirmActionPerformed

    private void dateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxActionPerformed

    private void doctorComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorComboBox1ActionPerformed

    private void dayComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayComboBox1ActionPerformed

    private void timeComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeComboBox1ActionPerformed

    private void confirm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm1ActionPerformed
   // Retrieve selected values from the ComboBoxes
    String doctor = (String) doctorComboBox1.getSelectedItem();
    String day = (String) dayComboBox1.getSelectedItem();
    String time = (String) timeComboBox1.getSelectedItem();
    String date = (String) dateComboBox1.getSelectedItem();

    // Validation: Ensure all fields are selected
    if ("Choose a Doctor".equals(doctor) || "Choose a Day".equals(day) || "Choose a Time".equals(time) || "Choose a Date".equals(date)) {
        JOptionPane.showMessageDialog(this, "Please select all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Check if the user already has an appointment for the same day, time, and date with another doctor
        if (userHasAppointmentAtSameTimeWithDifferentDoctor(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "You already have an appointment at this time with a different doctor. Please choose a different time or doctor.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the appointment already exists in the database (same doctor, day, time, date)
        if (appointmentExists(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "This appointment is already taken. Please choose a different time.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve Clinic Name from Database (match full_name column in the doctors table)
        String clinic = getClinicByDoctor(doctor); // Pass the full doctor name
        if (clinic == null) {
            JOptionPane.showMessageDialog(this, "Clinic not found for the selected doctor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Random Availability State
        String[] availabilityStates = {"Confirmed", "Suspended", "Canceled"};
        String availability = availabilityStates[random.nextInt(availabilityStates.length)]; // Use the class-level Random object

        // Display Confirmation Message
        String message = String.format("Appointment confirmed!\nDoctor: %s\nDay: %s\nTime: %s\nDate: %s\nClinic: %s\nStatus: %s", 
                                        doctor, day, time, date, clinic, availability);
        JOptionPane.showMessageDialog(this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        // Add the new appointment row to the JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{doctor, day, time, date, clinic, availability});

        // Optionally Reset ComboBoxes
        doctorComboBox1.setSelectedIndex(0);
        dayComboBox1.setSelectedIndex(0);
        timeComboBox1.setSelectedIndex(0);
        dateComboBox1.setSelectedIndex(0);

        // Insert the appointment into the database
        insertAppointmentIntoDatabase(doctor, day, time, date, clinic, availability); // Pass the userId
    }
    }//GEN-LAST:event_confirm1ActionPerformed

    private void dateComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBox1ActionPerformed

    private void doctorComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorComboBox2ActionPerformed

    private void dayComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayComboBox2ActionPerformed

    private void timeComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeComboBox2ActionPerformed

    private void confirm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm2ActionPerformed
    // Retrieve selected values from the ComboBoxes
    String doctor = (String) doctorComboBox2.getSelectedItem();
    String day = (String) dayComboBox2.getSelectedItem();
    String time = (String) timeComboBox2.getSelectedItem();
    String date = (String) dateComboBox2.getSelectedItem();

    // Validation: Ensure all fields are selected
    if ("Choose a Doctor".equals(doctor) || "Choose a Day".equals(day) || "Choose a Time".equals(time) || "Choose a Date".equals(date)) {
        JOptionPane.showMessageDialog(this, "Please select all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Check if the user already has an appointment for the same day, time, and date with another doctor
        if (userHasAppointmentAtSameTimeWithDifferentDoctor(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "You already have an appointment at this time with a different doctor. Please choose a different time or doctor.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the appointment already exists in the database (same doctor, day, time, date)
        if (appointmentExists(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "This appointment is already taken. Please choose a different time.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve Clinic Name from Database (match full_name column in the doctors table)
        String clinic = getClinicByDoctor(doctor); // Pass the full doctor name
        if (clinic == null) {
            JOptionPane.showMessageDialog(this, "Clinic not found for the selected doctor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Random Availability State
        String[] availabilityStates = {"Confirmed", "Suspended", "Canceled"};
        String availability = availabilityStates[random.nextInt(availabilityStates.length)]; // Use the class-level Random object

        // Display Confirmation Message
        String message = String.format("Appointment confirmed!\nDoctor: %s\nDay: %s\nTime: %s\nDate: %s\nClinic: %s\nStatus: %s", 
                                        doctor, day, time, date, clinic, availability);
        JOptionPane.showMessageDialog(this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        // Add the new appointment row to the JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{doctor, day, time, date, clinic, availability});

        // Optionally Reset ComboBoxes
        doctorComboBox2.setSelectedIndex(0);
        dayComboBox2.setSelectedIndex(0);
        timeComboBox2.setSelectedIndex(0);
        dateComboBox2.setSelectedIndex(0);

        // Insert the appointment into the database
        insertAppointmentIntoDatabase(doctor, day, time, date, clinic, availability); // Pass the userId
    }
    }//GEN-LAST:event_confirm2ActionPerformed

    private void dateComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBox2ActionPerformed

    private void doctorComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorComboBox3ActionPerformed

    private void dayComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayComboBox3ActionPerformed

    private void timeComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeComboBox3ActionPerformed

    private void confirm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm3ActionPerformed
    // Retrieve selected values from the ComboBoxes
    String doctor = (String) doctorComboBox3.getSelectedItem();
    String day = (String) dayComboBox3.getSelectedItem();
    String time = (String) timeComboBox3.getSelectedItem();
    String date = (String) dateComboBox3.getSelectedItem();

    // Validation: Ensure all fields are selected
    if ("Choose a Doctor".equals(doctor) || "Choose a Day".equals(day) || "Choose a Time".equals(time) || "Choose a Date".equals(date)) {
        JOptionPane.showMessageDialog(this, "Please select all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Check if the user already has an appointment for the same day, time, and date with another doctor
        if (userHasAppointmentAtSameTimeWithDifferentDoctor(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "You already have an appointment at this time with a different doctor. Please choose a different time or doctor.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the appointment already exists in the database (same doctor, day, time, date)
        if (appointmentExists(doctor, day, time, date)) {
            JOptionPane.showMessageDialog(this, "This appointment is already taken. Please choose a different time.", "Appointment Conflict", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve Clinic Name from Database (match full_name column in the doctors table)
        String clinic = getClinicByDoctor(doctor); // Pass the full doctor name
        if (clinic == null) {
            JOptionPane.showMessageDialog(this, "Clinic not found for the selected doctor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Random Availability State
        String[] availabilityStates = {"Confirmed", "Suspended", "Canceled"};
        String availability = availabilityStates[random.nextInt(availabilityStates.length)]; // Use the class-level Random object

        // Display Confirmation Message
        String message = String.format("Appointment confirmed!\nDoctor: %s\nDay: %s\nTime: %s\nDate: %s\nClinic: %s\nStatus: %s", 
                                        doctor, day, time, date, clinic, availability);
        JOptionPane.showMessageDialog(this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        // Add the new appointment row to the JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{doctor, day, time, date, clinic, availability});

        // Optionally Reset ComboBoxes
        doctorComboBox3.setSelectedIndex(0);
        dayComboBox3.setSelectedIndex(0);
        timeComboBox3.setSelectedIndex(0);
        dateComboBox3.setSelectedIndex(0);

        // Insert the appointment into the database
        insertAppointmentIntoDatabase(doctor, day, time, date, clinic, availability); // Pass the userId
    }
    }//GEN-LAST:event_confirm3ActionPerformed

    private void dateComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBox3ActionPerformed

    private void Logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout1ActionPerformed
        // TODO add your handling code here:
        HomePage HomeFrame = new HomePage();
            HomeFrame.setVisible(true);
            HomeFrame.pack();
            HomeFrame.setLocationRelativeTo(null);
            
    }//GEN-LAST:event_Logout1ActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Book().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AboutUs;
    private javax.swing.JButton Doctors;
    private javax.swing.JLabel Hope_Label;
    private javax.swing.JButton Logout;
    private javax.swing.JButton Logout1;
    private javax.swing.JButton Services;
    private javax.swing.JButton Services1;
    private javax.swing.JButton confirm;
    private javax.swing.JButton confirm1;
    private javax.swing.JButton confirm2;
    private javax.swing.JButton confirm3;
    private javax.swing.JComboBox<String> dateComboBox;
    private javax.swing.JComboBox<String> dateComboBox1;
    private javax.swing.JComboBox<String> dateComboBox2;
    private javax.swing.JComboBox<String> dateComboBox3;
    private javax.swing.JComboBox<String> dayComboBox;
    private javax.swing.JComboBox<String> dayComboBox1;
    private javax.swing.JComboBox<String> dayComboBox2;
    private javax.swing.JComboBox<String> dayComboBox3;
    private javax.swing.JComboBox<String> doctorComboBox;
    private javax.swing.JComboBox<String> doctorComboBox1;
    private javax.swing.JComboBox<String> doctorComboBox2;
    private javax.swing.JComboBox<String> doctorComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> timeComboBox;
    private javax.swing.JComboBox<String> timeComboBox1;
    private javax.swing.JComboBox<String> timeComboBox2;
    private javax.swing.JComboBox<String> timeComboBox3;
    // End of variables declaration//GEN-END:variables
}
