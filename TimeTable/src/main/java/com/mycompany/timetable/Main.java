/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.timetable;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author mi
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    //public JLabel time;
    public String Weak;
//    public boolean tcheck=true;
    public Main() {
        initComponents();
        //time.setText("ram");
        JTableHeader th=table1.getTableHeader();
        th.setFont(new Font("Tahome",Font.BOLD,20));
        JTableHeader th1=table2.getTableHeader();
        th1.setFont(new Font("Tahome",Font.BOLD,20));
         LocalDate currentDate = LocalDate.now();
        
        // Get the day of the week from the current date
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        Weak=dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
        Weak=Weak.toUpperCase();
        D1.setText(Weak);
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setFont(new Font("Arial", Font.PLAIN, 36));
        //Desktop.add(time);
        //add(time,BorderLayout.CENTER);
        
        String t1=getTime();
        String t2=getTime2();
        
        
        
        //t3.setText(t1);
        //time.getText().substring(0,time.getText().length()-3)
        if(t1.compareTo("")==0||t2.compareTo("")==0){
            JOptionPane.showMessageDialog(null, "No data found");
        }else{
            update(t1,t2);
            String t3=getTime_t1(t1);
            String t4=getTime_t2(t2);
            if(t3.compareTo("")==0||t4.compareTo("")==0){
            JOptionPane.showMessageDialog(null,"No Next Lecture Found");
            }else
            update2(t3,t4);
        }
        
        updateTime();
        //update(t1,t2);
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
        //Desktop.add(time);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        //Desktop.add(time);
        
    }
    public String getDay(){
        return Weak;
    }
    private String getTime(){
        
        String t1="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time1 = sdf.format(new Date());
            Connection con;
            PreparedStatement pre;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/time_table","root","satish#9567");
            pre=con.prepareStatement("select Time from shedule where Day=? and Time<? ORDER BY Time DESC LIMIT 1 ");
            pre.setString(1,Weak);
            pre.setString(2, time1);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
            t1= rs.getString("Time");
            //JOptionPane.showMessageDialog(null, " data found");
            }
            else{
                //JOptionPane.showMessageDialog(null, "No t1 data found");
            }
            //System.out.println(t1+" chuthiya");
            //t3.setText(t2);
            //update(t1,t2);
            return t1;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t1;
    }
    private String getTime_t1(String t){
        
        String t1="";
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String time1 = sdf.format(new Date());
            Connection con;
            PreparedStatement pre;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/time_table","root","satish#9567");
            pre=con.prepareStatement("select Time from shedule where Day=? and Time>? ORDER BY Time ASC LIMIT 1 ");
            pre.setString(1,Weak);
            pre.setString(2, t);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
            t1= rs.getString("Time");
            //JOptionPane.showMessageDialog(null, " data found");
            }
            else{
                //JOptionPane.showMessageDialog(null, "No t1 data found");
            }
            //System.out.println(t1+" chuthiya");
            //t3.setText(t2);
            //update(t1,t2);
            return t1;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t1;
    }
    private String getTime_t2(String t){
        
        String t1="";
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String time1 = sdf.format(new Date());
            Connection con;
            PreparedStatement pre;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/time_table","root","satish#9567");
            pre=con.prepareStatement("select Time2 from shedule where Day=? and Time2>? ORDER BY Time2 ASC LIMIT 1 ");
            pre.setString(1,Weak);
            pre.setString(2, t);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
            t1= rs.getString("Time2");
            //JOptionPane.showMessageDialog(null, " data found");
            }
            else{
                //JOptionPane.showMessageDialog(null, "No t1 data found");
            }
            //System.out.println(t1+" chuthiya");
            //t3.setText(t2);
            //update(t1,t2);
            return t1;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t1;
    }
    private String getTime2(){
        
        String t1="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time1 = sdf.format(new Date());
            Connection con;
            PreparedStatement pre;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/time_table","root","satish#9567");
            pre=con.prepareStatement("select Time2 from shedule where Day=? and Time2>? ORDER BY Time ASC\n" +
"LIMIT 1 ");
            pre.setString(1,Weak);
            pre.setString(2, time1);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
            t1= rs.getString("Time2");
            //JOptionPane.showMessageDialog(null, "t2 data found");
            }
            else{
                //JOptionPane.showMessageDialog(null, "No t2 data found");
            }
            //System.out.println(t1+" chuthiya");
            //t3.setText(t2);
            //update(t1,t2);
            return t1;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t1;
    }
    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time1 = sdf.format(new Date());
        time.setText(time1);
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        DefaultTableModel model1 = (DefaultTableModel) table2.getModel();
        int min=Integer.parseInt(time1.substring(3,5));
        if(time1.equals("10:30:00"))
        {
            model.setRowCount(0);
            model1.setRowCount(0);
            update(time1.substring(0, time1.length()-3),"11:15");
            update2("11:15","12:00");
            
        }
        else if(time1.equals("11:15:00")){
            //update(time1);
            model.setRowCount(0);
            update(time1.substring(0, time1.length()-3),"12:00");
            model1.setRowCount(0);
           update2("12:00","12:45");
        }
        else if(time1.equals("12:00:00")){
           // update(time1);
           model.setRowCount(0);
           update(time1.substring(0, time1.length()-3),"12:45");
           model1.setRowCount(0);
           update2("12:45","13:30");
           
        }
        else if(time1.equals("12:45:00")){
            //update(time1);
            model.setRowCount(0);
            update(time1.substring(0, time1.length()-3),"13:30");
            model1.setRowCount(0);
           update2("13:30","14:15");
        }
        else if(time1.equals("13:30:00")){
           // update(time1);
           model.setRowCount(0);
           update(time1.substring(0, time1.length()-3),"14:15");
           model1.setRowCount(0);
           update2("14:15","15:00");
        }else if(time1.equals("14:15:00")){
            model.setRowCount(0);
            update(time1.substring(0, time1.length()-3),"15:00");
           model1.setRowCount(0);
           update2("15:00","15:45");
           //JOptionPane.showMessageDialog(null, "data found");
        }
        //Desktop.add(time);
    }
        private void update(String t,String t1) {                                         
        try {
            // TODO add your handling code here:
            boolean check=true;
            Connection con;
            PreparedStatement pre;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/time_table","root","satish#9567");
            pre=con.prepareStatement("select * from shedule where Day=? and Time>=? and Time2<=?");
            pre.setString(1, Weak);
            pre.setString(2, t);
            pre.setString(3, t1);
            ResultSet rs=pre.executeQuery();
            ResultSetMetaData RSMD=rs.getMetaData();
            int cc=RSMD.getColumnCount();
            DefaultTableModel DFT=(DefaultTableModel) table1.getModel();
            
            while(rs.next()){
                Vector v=new Vector();
                for(int i=0;i<cc;i++){
                    //v.add(rs.getString("teacher_id"));
                    v.add(rs.getString("class_name"));
                    v.add(rs.getString("subject"));
                    v.add(rs.getString("teacher_name"));
                    //v.add(rs.getString("subject"));
                    v.add(rs.getString("class_no"));
                    
                    v.add(rs.getString("Time"));
                    //v.add(rs.getString("Day"));
                }
                DFT.addRow(v);
                check=false;
            }
            if(check){
                JOptionPane.showMessageDialog(null, "No data found");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        private void update2(String t,String t1) {                                         
        try {
            // TODO add your handling code here:
            boolean check=true;
            Connection con;
            PreparedStatement pre;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/time_table","root","satish#9567");
            pre=con.prepareStatement("select * from shedule where Day=? and Time>=? and Time2<=?");
            pre.setString(1, Weak);
            pre.setString(2, t);
            pre.setString(3, t1);
            ResultSet rs=pre.executeQuery();
            ResultSetMetaData RSMD=rs.getMetaData();
            int cc=RSMD.getColumnCount();
            DefaultTableModel DFT=(DefaultTableModel) table2.getModel();
            
            while(rs.next()){
                Vector v=new Vector();
                for(int i=0;i<cc;i++){
                    //v.add(rs.getString("teacher_id"));
                    v.add(rs.getString("class_name"));
                    v.add(rs.getString("subject"));
                    v.add(rs.getString("teacher_name"));
                    
                    v.add(rs.getString("class_no"));
                    
                    v.add(rs.getString("Time"));
                    //v.add(rs.getString("Day"));
                }
                DFT.addRow(v);
                check=false;
            }
            if(check){
                JOptionPane.showMessageDialog(null, "No data found");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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

        popupMenu1 = new java.awt.PopupMenu();
        Desktop = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        D1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Desktop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        table1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class name", "Subject", "Teacher name", "Class no", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        table1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                table1ComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setResizable(false);
            table1.getColumnModel().getColumn(1).setResizable(false);
            table1.getColumnModel().getColumn(2).setResizable(false);
            table1.getColumnModel().getColumn(3).setResizable(false);
            table1.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Current Lecture");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        table2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class Name", "Subject", "Teacher name", "Class no", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(0).setResizable(false);
            table2.getColumnModel().getColumn(1).setResizable(false);
            table2.getColumnModel().getColumn(2).setResizable(false);
            table2.getColumnModel().getColumn(3).setResizable(false);
            table2.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Next Lecture");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("REFRESH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Desktop.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DesktopLayout.createSequentialGroup()
                        .addGroup(DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        time.setForeground(new java.awt.Color(255, 255, 255));

        D1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        D1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText(" Teacher");

        jMenuItem1.setText("Add new Teacher");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Delete Teacher");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("View Schedule");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Modify Table");

        jMenuItem2.setText("Add Shedule");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem5.setText("Delete Shedule");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Add_teacher teacher=new Add_teacher();
        Desktop.add(teacher);
        teacher.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void table1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_table1ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_table1ComponentShown

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Allot_class allot=new Allot_class();
        Desktop.add(allot);
        allot.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        Delete delete=new Delete();
        Desktop.add(delete);
        delete.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Delete_Teacher delete=new Delete_Teacher();
        Desktop.add(delete);
        delete.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Main man=new Main();
        man.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        View_Schedule view=new View_Schedule();
        Desktop.add(view);
        view.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //SwingUtilities.invokeLater(() -> new Main());
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel D1;
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
