/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Point_Of_Sale;

import java.awt.CardLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.JFrame;
import java.util.Date;
import java.net.URL;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rahulkartick
 */



public class Start_Frame extends javax.swing.JFrame {

    /**
     * Creates new form Start_Frame
     */
//    public static void init_run(){
//        try{
//            //Process p;
//            Runtime.getRuntime().exec("python state_machine2.py " );
//            //System.out.println(data);
//        }catch (IOException e){
//            System.out.println("python exception");
//            e.printStackTrace();
//        }
//    }
    
    
    public void kings_castle(){
        jTextField1.setText("");
        jTextField2.setText("");
        jPasswordField1.setText("");
        jTextField20.setText("");
        jTextField9.setText("");
        jTextField21.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField23.setText("");
        jTextField24.setText("");
        jPasswordField4.setText("");
        jTextField22.setText("");
        jTextArea3.setText("");

        DefaultTableModel table = (DefaultTableModel)jTable2.getModel();
        int len = jTable2.getRowCount();
        for(int i =0; i< len; i++)
        {
            table.removeRow(0);
         }
    }
    
    public int budget_check(int count){
        double budget = Double.parseDouble(jTextField12.getText());
        double total = Double.parseDouble(jTextField13.getText());
        if(total > budget){
            JOptionPane.showMessageDialog(null,"You are exceeding your budget by $" + (int)(total - budget));
        }
        return count-1;
    }
    
    public void update_state (String new_state){
        try{
            BufferedWriter writer_state = new BufferedWriter(new FileWriter("state.txt"));
            writer_state.write(new_state);
            writer_state.close();
        }catch(IOException ex){
            System.out.println("ead file exception");
            ex.printStackTrace();
        }
    }
    
    public void add_table_row(String product_name,String quantity, String cost){
        DefaultTableModel table = (DefaultTableModel)jTable2.getModel();
        table.addRow(new Object[]{product_name,quantity,cost});
//        table.fireTableDataChanged();
        double initial_value = 0.0;
        String k = jTextField13.getText();
        if("".equals(jTextField13.getText())){
            initial_value = 0.0;
        }else{
            initial_value = Double.parseDouble(jTextField13.getText());
        }
        String new_cost = "" + ( ((double)((int)((initial_value + Double.parseDouble(cost))*100)))/100);
        jTextField13.setText(new_cost);
    }
    
    
    
    public void delete_table_row(){
        DefaultTableModel table = (DefaultTableModel)jTable2.getModel();
        int i = jTable2.getSelectedRow();
        if(i >= 0){
            double old_cost = Double.parseDouble("" + table.getValueAt(i, 2));
            String new_cost = "" + (((double)((int)((Double.parseDouble(jTextField13.getText()) - old_cost)*100)))/100);
            jTextField13.setText(new_cost);
            table.removeRow(i);
        }else{
            System.out.println("Delete Error");
        }
    }

    
    public void auth_user(String user, String pwd){
        try{
            BufferedWriter writer_auth = new BufferedWriter(new FileWriter("auth.txt"));
            writer_auth.write(user + "," + pwd);
            writer_auth.close();
        }catch(IOException ex){
            System.out.println("ead file exception");
            ex.printStackTrace();
        }
    }
    
    
    public void new_user(String fullname, String email, String pwd, String diet, String budget){
        try{
            BufferedWriter writer_user = new BufferedWriter(new FileWriter("new_user.txt"));
            writer_user.write( email+ "," + pwd+ ":" + fullname + "," + diet+ "," + budget);
            writer_user.close();
        }catch(IOException ex){
            System.out.println("ead file exception");
            ex.printStackTrace();
        }
    }
    
    Date startDate;
    Date endDate;
    //loop through
    String state = "IDLE";
    
    
    public void auth_it(String val, String codet){
        if(val.equals("TRUE")){
            if(codet.equals("auth")){
               jPanel2.setVisible(false);
                jPanel9.setVisible(true);
                jTextField13.setText("0.0");
                jTextField12.setText(read_whisper("budget.txt"));
            }else if(codet.equals("new")){
                jPanel9.setVisible(true);
                jPanel11.setVisible(false);
                jTextField13.setText("0.0");
                jTextField12.setText(read_whisper("budget.txt"));
            }

            state = "MAIN";
            update_state("MAIN");
             try{
                 BufferedWriter writer3 = new BufferedWriter(new FileWriter("stm_whisper.txt"));
                 writer3.close();   
             }catch(IOException e){
                 System.out.println("ead file exception");
                 e.printStackTrace();
             }
        }
        else if(val.equals("FALSE")){
//            jLabel7.setText("Username or Password is incorrect! ");
//            jLabel7.setVisible(true);        
            JOptionPane.showMessageDialog(null, "Username or Password is incorrect!");
            try{
                 BufferedWriter writer1 = new BufferedWriter(new FileWriter("stm_whisper.txt"));
                 writer1.close();   
             }catch(IOException e){
                 System.out.println("ead file exception");
                 e.printStackTrace();
             }
            state = "LOGIN";
            update_state("LOGIN");
        }

    }
    
    public String get_state(){
        return state;
    }
    
    public void new_game(){
        jPanel6.setVisible(false);
        jPanel1.setVisible(true);
        state = "IDLE";
        update_state("IDLE");
        
    }
    
    public void update_time(long ti){
        jLabel46.setText("Time Remaining: "+ ti +" s");
    }
    
    public void swipe_card(String cr_no,String cr_name,String cr_date){
        jTextField20.setText(cr_name);
        jTextField9.setText(cr_no);
        jTextField21.setText(cr_date);
        jButton9.setVisible(true);
        update_state("END");
        //state = "END";
    }          
    
    public String read_whisper(String file_val){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file_val));
            //System.out.println(reader.readLine());
            String man_val = reader.readLine();
            reader.close();
            if(file_val.equals("budget.txt")){
                BufferedWriter writerB = new BufferedWriter(new FileWriter("budget.txt"));
                writerB.close();                 
            }
            return  man_val;
        }catch(IOException ex){
            System.out.println("reading exception");
            ex.printStackTrace();
            return "Error 404";
        }
    }
    
    public static void send_data(String data){
        try{
            Process p;
            p = Runtime.getRuntime().exec("python spitest1.py " + data );
            //System.out.println(data);
        }catch (IOException e){
            System.out.println("python exception");
            e.printStackTrace();
        }
    }
    
    //runs script to read card value
    public void read_card(){
        try{
            Process p;
            p = Runtime.getRuntime().exec("python otg.py");
        }catch (IOException e){
            System.out.println("python card exception");
            e.printStackTrace();
        }
    }
        
    
    
    public Start_Frame(){
        //initializations 
        
        this.setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        initComponents(); 
//        init_run();
        jLabel7.setVisible(false);
        
        //swipe card frame
        jTextField9.setEditable(false);
        jTextField20.setEditable(false);
        jTextField21.setEditable(false);
        jButton9.setVisible(false);
        
        //update state 
        update_state("IDLE");
        
        jTextField12.setEditable(false);
        jTextField13.setEditable(false);
        
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        double width = toolkit.getScreenSize().getWidth();
//        double height = toolkit.getScreenSize().getHeight();      
//        this.setSize((int)width, (int)height);   
//        this.setLocationByPlatform(true);      
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 360), new java.awt.Dimension(0, 360), new java.awt.Dimension(32767, 360));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jPasswordField4 = new javax.swing.JPasswordField();
        jLabel54 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 360), new java.awt.Dimension(0, 360), new java.awt.Dimension(32767, 360));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel55 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 480));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 480));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 480));

        jButton1.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome to SmartKart ");

        jButton3.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton3.setText("Sign Up");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel3.setText("OR");

        jLabel6.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel6.setText("Your Intelligent Shopping Assistant");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3)
                        .addGap(63, 63, 63)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(101, 101, 101)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        jPanel2.setPreferredSize(new java.awt.Dimension(800, 480));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel2.setText("Username");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 168, 118, -1));

        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 167, 161, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 380, 124, 40));

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Login  ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 97, 229, -1));

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel5.setText("Password");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 230, 136, -1));
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 229, 162, -1));

        jButton4.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton4.setText("Sign Up");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 380, 124, 40));

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("Username or Password is incorrect! ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 332, -1, -1));

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel8.setText("Budget ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 289, 109, -1));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel9.setText("$");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 289, 26, -1));

        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 288, 161, -1));

        getContentPane().add(jPanel2, "card3");

        jPanel7.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel7.setMaximumSize(new java.awt.Dimension(800, 480));

        jLabel26.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel26.setText("Swipe Card Now");

        jLabel27.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel27.setText("Card Number");

        jLabel28.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel28.setText("Expiry Date");

        jButton9.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton9.setText("Continue");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel45.setText("Name on Card ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(593, 593, 593)
                        .addComponent(jButton9))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel45))
                            .addComponent(jLabel26)
                            .addComponent(jLabel28)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel26)
                .addGap(40, 40, 40)
                .addComponent(jLabel45)
                .addGap(12, 12, 12)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7, "card6");

        jPanel6.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel6.setMaximumSize(new java.awt.Dimension(800, 480));

        jLabel29.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel29.setText("Transaction Completed!");

        jLabel30.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        jLabel30.setText("Thank you for shopping with SmartKart! ");

        jLabel46.setText("Time Remaining: 10 s");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(184, 184, 184))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(144, 144, 144))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(jLabel46)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel29)
                .addGap(36, 36, 36)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jLabel46)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, "card6");

        jPanel9.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel9.setMaximumSize(new java.awt.Dimension(800, 480));

        jButton10.setText("Checkout");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setText("Delete Item");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel34.setText("Budget: $");

        jLabel35.setText("Total:  $");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Quantity", "Cost ($)"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel35)
                        .addGap(5, 5, 5)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel9, "card6");

        jPanel10.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel10.setMaximumSize(new java.awt.Dimension(800, 480));
        jPanel10.setMinimumSize(new java.awt.Dimension(800, 480));
        jPanel10.setPreferredSize(new java.awt.Dimension(800, 480));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel31.setText("Edit Information");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jLabel32.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel32.setText("Personal Information");
        jPanel10.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel33.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel33.setText("Dietary Restrictions");
        jPanel10.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        jLabel36.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel36.setText("Budget");
        jPanel10.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, -1, -1));

        jButton12.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton12.setText("Submit");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 178, 61));

        jLabel37.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel37.setText("Enter Budget");
        jPanel10.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, -1));

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 169, -1));

        jLabel38.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel38.setText("Full Name");
        jPanel10.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));
        jPanel10.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 149, -1));

        jLabel39.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel39.setText("Email");
        jPanel10.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));
        jPanel10.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 149, -1));
        jPanel10.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 149, -1));

        jLabel40.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel40.setText("Password ");
        jPanel10.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel41.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel41.setText("Street Address");
        jPanel10.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 180, -1));

        jLabel42.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel42.setText("State");
        jPanel10.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel43.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel43.setText("Zip Code");
        jPanel10.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, -1));

        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 31, -1));

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 75, -1));

        jLabel44.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel44.setText("Enter Dietary Restrictions");
        jPanel10.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setToolTipText("Enter dietary restrictions seperated by commas");
        jScrollPane3.setViewportView(jTextArea2);

        jPanel10.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 235, 260));
        jPanel10.add(filler5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));
        jPanel10.add(filler6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));
        jPanel10.add(filler7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));
        jPanel10.add(filler8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel10.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, 312));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel10.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, -1, 314));

        getContentPane().add(jPanel10, "card4");

        jPanel11.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel11.setMaximumSize(new java.awt.Dimension(800, 480));
        jPanel11.setMinimumSize(new java.awt.Dimension(800, 480));
        jPanel11.setPreferredSize(new java.awt.Dimension(800, 480));

        jLabel47.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel47.setText("Sign Up");

        jLabel48.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel48.setText("Personal Information");

        jLabel49.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel49.setText("Dietary Restrictions");

        jLabel50.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel50.setText("Budget");

        jButton11.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton11.setText("Submit");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel51.setText("Enter Budget");

        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel52.setText("Full Name");

        jLabel53.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel53.setText("Email");

        jLabel54.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel54.setText("Password ");

        jLabel58.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel58.setText("Enter Dietary Restrictions");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setToolTipText("Enter dietary restrictions seperated by commas");
        jScrollPane4.setViewportView(jTextArea3);

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel55.setText("$");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(jLabel52)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jPasswordField4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel58))))
                .addGap(25, 25, 25)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filler10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(filler11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(filler9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(filler12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel48)
                .addGap(13, 13, 13)
                .addComponent(jLabel52)
                .addGap(3, 3, 3)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel53)
                .addGap(3, 3, 3)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel54)
                .addGap(3, 3, 3)
                .addComponent(jPasswordField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel47)
                .addGap(25, 25, 25)
                .addComponent(jLabel49)
                .addGap(13, 13, 13)
                .addComponent(jLabel58)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel50)
                .addGap(13, 13, 13)
                .addComponent(jLabel51)
                .addGap(3, 3, 3)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(174, 174, 174)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel11, "card4");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        send_data("h 0x04");
        jPanel1.setVisible(false);
        jPanel2.setVisible(true);
        state = "LOGIN";
        update_state("LOGIN");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String user = jTextField1.getText();
        String pwd = jPasswordField1.getText();
        String budget = jTextField2.getText();
        if(budget.trim().isEmpty() != true ){
            try{
                int i = Integer.parseInt(jTextField2.getText());
                    try{
                        BufferedWriter writer_B = new BufferedWriter(new FileWriter("budget.txt"));
                        writer_B.write(budget);
                        writer_B.close();
                    }catch(IOException e){
                        System.out.println("reading exception");
                        e.printStackTrace();           
                    }
                    auth_user(user,pwd);
                    state = "AUTH";
                    update_state("AUTH");
                               
            }catch(NumberFormatException e){
//                     jLabel7.setText("Please enter numeric budget ");
//                    jLabel7.setVisible(true);     
        JOptionPane.showMessageDialog(null, "Please enter numeric budget");
            }
                
        }else{
//             jLabel7.setText("Please enter desired budget ");
//             jLabel7.setVisible(true); 
        JOptionPane.showMessageDialog(null, "Please enter desired budget");
         } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        state = "SIGN_UP";
        jPanel1.setVisible(false);
        jPanel11.setVisible(true);
        send_data("h 0x02");
        update_state("SIGN_UP");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        state = "SIGN_UP";
        jPanel2.setVisible(false);
        jPanel11.setVisible(true);
        update_state("SIGN_UP");
        send_data("h 0x02");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        //perform card verification 
        state = "END";
        startDate = new Date();
        jPanel7.setVisible(false);
        jPanel6.setVisible(true);
        //update_state("END");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        state = "SWIPE_CARD";
        jPanel9.setVisible(false);
        jPanel7.setVisible(true);
        update_state("SWIPE_CARD");
        send_data("h 0x13");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        state = "MAIN";
        jPanel9.setVisible(true);
        jPanel10.setVisible(false);
        update_state("MAIN");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
       delete_table_row();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
                //enter values in the sd card 
        String budget = jTextField22.getText();
        String full_name = jTextField23.getText();
        String email = jTextField24.getText();
        String diet = jTextArea3.getText();
        String pwd12 = jPasswordField4.getText();
        if(budget.trim().isEmpty() || email.trim().isEmpty() || diet.trim().isEmpty() || full_name.trim().isEmpty() || pwd12.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please do not leave any fields empty");
        }else{
            try{
                int i = Integer.parseInt(jTextField22.getText());
                new_user(full_name,email,pwd12,diet,budget);

                try{
                    BufferedWriter writer_B = new BufferedWriter(new FileWriter("budget.txt"));
                    writer_B.write(budget);
                    writer_B.close();
                }catch(IOException e){
                    System.out.println("reading exception");
                    e.printStackTrace();           
                }

                state = "NEW_USER";
                update_state("NEW_USER");
                               
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter numeric budget");
            }
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MouseClicked

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
            java.util.logging.Logger.getLogger(Start_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Start_Frame fr = new Start_Frame();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
        
            public void run() {
               
                fr.setVisible(true);
                
            }      
        });
               
        Thread t;
        t = new Thread(new Runnable(){
            String curr_state;

            File file = new File("credit_card.txt"); 
            File file2 = new File("stm_whisper.txt"); 
             
            String value_auth;
            String value_new;
            int k2 = 1;
            int k1 = 1;
            int countB = 1;
            int x = 0;

            public void run(){

            while(true){
                curr_state = fr.get_state();
                switch(curr_state){
                   case "IDLE": 
                       k2 = 1;
                       if(k1 == 1){ 
                            try{
                                 BufferedWriter writerI = new BufferedWriter(new FileWriter("stm_whisper.txt"));
                                 writerI.close();
                                 BufferedWriter writerA = new BufferedWriter(new FileWriter("auth.txt"));
                                 writerA.close();
                                 BufferedWriter writerU = new BufferedWriter(new FileWriter("new_user.txt"));
                                 writerU.close();
                                 BufferedWriter writerS = new BufferedWriter(new FileWriter("new_scan.txt"));
                                 writerS.close(); 
                                 BufferedWriter writerB = new BufferedWriter(new FileWriter("budget.txt"));
                                 writerB.close();                                    
                             }catch(IOException e){
                                 System.out.println("ead file exception");
                                 e.printStackTrace();
                             }
                            k1 = k1-1;
                       }
                       
                        System.out.println("IDLE");
                        break;
                   case "LOGIN": 
                        //System.out.println("LOGIN");
                        //x = 0;
                                
                        break;
                   case "AUTH": 
                        value_auth = fr.read_whisper("stm_whisper.txt");
                        if(value_auth != null){
                            fr.auth_it(value_auth,"auth");
                        }                             
                        break;
                   case "SIGN_UP":
                        break;
                   case "NEW_USER": 
                        value_new = fr.read_whisper("stm_whisper.txt");
                        if(value_new != null){
                            fr.auth_it(value_new,"new");
                        }                             
                        break;
                   case "MAIN":
                       if(k2 == 1){ 
                            try{
                                 BufferedWriter writerI = new BufferedWriter(new FileWriter("stm_whisper.txt"));
                                 writerI.close();
                                 BufferedWriter writerA = new BufferedWriter(new FileWriter("auth.txt"));
                                 writerA.close();
                                 BufferedWriter writerU = new BufferedWriter(new FileWriter("new_user.txt"));
                                 writerU.close();
                             }catch(IOException e){
                                 System.out.println("ead file exception");
                                 e.printStackTrace();
                             }
//                            fr.add_table_row("Diet Conk", "1", "2.5");
                            k2 = k2-1;
                       }
                        try{
                             BufferedReader reader_scan = new BufferedReader(new FileReader("new_scan.txt"));
                             String product_name = reader_scan.readLine();
                             if(product_name == null){
                                reader_scan.close(); 
                             }else{
                                String product_val = reader_scan.readLine();
                                fr.add_table_row(product_name, "1", product_val);
                                reader_scan.close();
                                BufferedWriter writer_scan = new BufferedWriter(new FileWriter("new_scan.txt"));
                                writer_scan.close();
                                countB=1;
                             }
                            
                         }catch(IOException e){
                             System.out.println("ead file exception");
                             e.printStackTrace();
                         }
                        if(countB>0){
                            countB = fr.budget_check(1);  
                        }


                        //System.out.println("MAIN");
                        break;
                   case "SWIPE_CARD"://send_data("t swipw"); 
                       fr.send_data("h 0x13");
                       //System.out.println("SWIPE");
                       
//                       while(true){ 
                            try{
                                //System.out.println("hello");
                                BufferedReader reader = new BufferedReader(new FileReader(file));

                                String line = reader.readLine();
                                while(line  != null){  
                                    String line2 = reader.readLine();
                                    //System.out.println(line.substring(0,2));
                                    if(line2 == null){
                                    //System.out.println("EHAT2");
                                    JOptionPane.showMessageDialog(null, "Please swipe either a credit or debit card");                                    
                                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                                    writer.close();                                   
                                    }else{
                                      String cr_name = line2;
                                      String cr_no = line;
                                      String cr_date = reader.readLine();
                                      fr.swipe_card(cr_no,cr_name,cr_date);
                                      BufferedWriter writer2 = new BufferedWriter(new FileWriter(file));
                                      writer2.close();  
                                    }
                                 line = reader.readLine();
                                }
                                reader.close();
                                
                                
                            }catch(IOException ex){
                                System.out.println("ead file exception");
                                ex.printStackTrace();
                            }
//                       }   

                       break;
                   case "END":

                        fr.endDate = new Date();
                        long Lapse = (fr.endDate.getTime() -fr.startDate.getTime())/1000;
                        if(Lapse > 10){
                            fr.new_game();
                        }else{
                            fr.update_time(10 - Lapse);
                            if(Lapse == 5){
                              k1 =1;
                            k2 = 1;
                            countB=1;
                            fr.kings_castle();
                            }
                        }
                        break;                
                }
                
    }
            }
        });
        
        t.start();

        /* Create and display the form */

        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
