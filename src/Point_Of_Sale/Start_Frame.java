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

/**
 *
 * @author rahulkartick
 */
//
//public class myRunnable implements Runnable{
//    private int var;
//    
//    public myRunnable(int var){
//        this.var = var;
//    }
//    
//
//    
//}


public class Start_Frame extends javax.swing.JFrame {

    /**
     * Creates new form Start_Frame
     */
    //String command = "python /c start python home/pi/Desktop/spitest1.py";
    
    //BufferedReader reader;

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
    
    
//    public void update_state2 (String new_state){
//        try{
//            BufferedWriter writer_state = new BufferedWriter(new FileWriter("stm_whisper.txt"));
//            writer_state.write(new_state);
//            writer_state.close();
//        }catch(IOException ex){
//            System.out.println("ead file exception");
//            ex.printStackTrace();
//        }
//    }
    
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
            writer_user.write( email+ "," + pwd+ "\n" + fullname + "," + diet+ "," + budget);
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
            }else if(codet.equals("new")){
                jPanel9.setVisible(true);
                jPanel11.setVisible(false);
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
            jLabel7.setVisible(true);
            try{
                 BufferedWriter writer1 = new BufferedWriter(new FileWriter("stm_whisper.txt"));
                 writer1.close();   
             }catch(IOException e){
                 System.out.println("ead file exception");
                 e.printStackTrace();
             }
        }

    }
    
    public String get_state(){
        return state;
    }
    
    public void new_game(){
        jPanel6.setVisible(false);
        jPanel1.setVisible(true);
    }
    
    public void update_time(long ti){
        jLabel46.setText("Time Remaining: "+ ti +" s");
    }
    
    public void swipe_card(String cr_no,String cr_name,String cr_date){
        jTextField20.setText(cr_name);
        jTextField9.setText(cr_no);
        jTextField21.setText(cr_date);
        jButton9.setVisible(true);
    }          
    
    public String read_whisper(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("stm_whisper.txt"));
            //System.out.println(reader.readLine());
            String man_val = reader.readLine();
            reader.close();
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
             System.out.println(data);
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
        
    public void cursor(){
        try{
            Process p;
            p = Runtime.getRuntime().exec("python curser.py ");
        }catch (IOException e){
            System.out.println("python exception");
            e.printStackTrace();
        }
    }
    
    
    public Start_Frame(){
        //initializations 
//        try{
//                    BufferedWriter writer0 = new BufferedWriter(new FileWriter("stm_whisper.txt"));
//                writer0.close(); 
//        }catch (IOException m){
//            System.out.println("ead file exception");
//            m.printStackTrace();
//        }

        //jPanel1.setVisible(true);
//        jPanel2.setVisible(false);
//        jPanel3.setVisible(false);
//        jPanel4.setVisible(false);
//        jPanel5.setVisible(false);
//        jPanel6.setVisible(false);
//        jPanel7.setVisible(false);
//        jPanel9.setVisible(false);
//        jPanel10.setVisible(false);
        
        //this.setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents(); 
        jLabel7.setVisible(false);
        
        //swipe card frame
        jTextField9.setEditable(false);
        jTextField20.setEditable(false);
        jTextField21.setEditable(false);
        jButton9.setVisible(false);
        
        //update state 
        update_state("IDLE");
           
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        
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
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 360), new java.awt.Dimension(0, 360), new java.awt.Dimension(32767, 360));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 340), new java.awt.Dimension(0, 340), new java.awt.Dimension(32767, 340));
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
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
        jPanel8 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel2.setText("Username");

        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Login  ");

        jLabel5.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel5.setText("Password");

        jButton4.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton4.setText("Sign Up");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("Username or Password is incorrect! ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(219, 219, 219))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card3");

        jPanel3.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel3.setMaximumSize(new java.awt.Dimension(800, 480));
        jPanel3.setMinimumSize(new java.awt.Dimension(800, 480));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 480));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel8.setText("Sign Up");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel9.setText("Personal Information");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel10.setText("Dietary Restrictions");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        jLabel11.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel11.setText("Budget");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, -1, -1));

        jButton5.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton5.setText("Submit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 178, 61));

        jLabel12.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel12.setText("Enter Budget");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 169, -1));

        jLabel13.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel13.setText("Full Name");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 149, -1));

        jLabel14.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel14.setText("Email");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 149, -1));
        jPanel3.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 149, -1));

        jLabel15.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel15.setText("Password ");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel17.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel17.setText("Street Address");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 180, -1));

        jLabel18.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel18.setText("State");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel19.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel19.setText("Zip Code");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, -1));

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 31, -1));

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 75, -1));

        jLabel20.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jLabel20.setText("Enter Dietary Restrictions");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("Enter dietary restrictions seperated by commas");
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 235, 260));
        jPanel3.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));
        jPanel3.add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));
        jPanel3.add(filler3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));
        jPanel3.add(filler4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 0));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, 312));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, -1, 314));

        getContentPane().add(jPanel3, "card4");

        jPanel4.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel4.setMaximumSize(new java.awt.Dimension(800, 480));
        jPanel4.setPreferredSize(new java.awt.Dimension(800, 480));

        jLabel16.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel16.setText("Checkout");

        jButton6.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        jButton6.setText("Enter Card");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        jButton7.setText("Swipe Card");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel16)
                .addGap(97, 97, 97)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(160, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, "card5");

        jPanel5.setBounds(new java.awt.Rectangle(0, 0, 800, 480));
        jPanel5.setMaximumSize(new java.awt.Dimension(800, 480));
        jPanel5.setPreferredSize(new java.awt.Dimension(800, 480));

        jLabel21.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel21.setText("Enter Card Information");

        jLabel22.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel22.setText("Card Number");

        jLabel23.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel23.setText("Expiry Date");

        jLabel24.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel24.setText("CVV");

        jLabel25.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel25.setText("Zipcode");

        jButton8.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButton8.setText("Continue");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("mm/dd/yyyy"))));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(193, 193, 193)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(89, 89, 89))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel21)
                .addGap(33, 33, 33)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, "card6");

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

        jButton13.setText("Edit Inforrmation");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setText("Budget:");
        jPanel8.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 354, -1, -1));

        jLabel35.setText("Total: ");
        jPanel8.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 354, -1, -1));
        jPanel8.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 349, 79, -1));
        jPanel8.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 349, 72, -1));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Quantity", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 490, 300));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
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

//        if (read_whisper() == "0x03"){

        String user = jTextField1.getText();
        String pwd = jPasswordField1.getText();
        auth_user(user,pwd);
//        if(user == "AS"){
//            update_state2("TRUE");
//        }
//        else{
//            update_state2("FALSE");
//        }
//
//      
//        send_data("h 0x03");
//        send_data("l " + user + "," + pwd);
//        send_data("s " + user + "," + pwd);
//        
//            //System.out.println(user + "," + pwd);
// 
//        }
        
        //lookup user id 
//        if(pwd.equals("GoldRush1") &&  user.equals("PurduePete") ){
//            //JOptionPane.showMessageDialog(null, "Hello "+ user );
//            System.exit(0);
//            jPanel2.setVisible(false);
//            jPanel9.setVisible(true);
            state = "AUTH";
            update_state("AUTH");
//        }
//        else{
//            //JOptionPane.showMessageDialog(null, "Incorrect Passward");
//            jLabel7.setVisible(true);
//            
//        }
        //  send SPI - 0x03 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //send_data("h 0x02");
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

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //enter values in the sd card 
//        String budget = jTextField2.getText();
//        String full_name = jTextField3.getText();
//        String email = jTextField4.getText();
//        String street_ad = jTextField6.getText();
//        String state = jTextField7.getText();
//        String zip_code = jTextField8.getText();
//        String dietary_restrictions = jTextArea1.getText();
//        String pwd2 = jPasswordField2.getText();
        update_state("MAIN");
        state = "MAIN";
        jPanel4.setVisible(false);
        jPanel9.setVisible(true);

           //send SPI - 0x09 - handshake 
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        state = "ENTER_CARD";
        jPanel4.setVisible(false);
        jPanel5.setVisible(true);
        update_state("ENTER_CARD");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        state = "SWIPE_CARD";                
        jPanel4.setVisible(false);
        jPanel7.setVisible(true);
        update_state("SWIPE_CARD");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        //perform card verification 
        state = "END";
        startDate = new Date();
        jPanel7.setVisible(false);
        jPanel6.setVisible(true);
        update_state("END");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //TODO-  perform card verification 
        state = "END";
        startDate = new Date();
        jPanel5.setVisible(false);
        jPanel6.setVisible(true);
        update_state("END");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        state = "SWIPE_CARD";
        jPanel9.setVisible(false);
        jPanel7.setVisible(true);
        update_state("SWIPE_CARD");
        //0x13
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
       //spi send - 0x12
        state = "EDIT";
        jPanel9.setVisible(false);
        jPanel10.setVisible(true);
        update_state("EDIT");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
        //send_data("h 0xBE");
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
                //enter values in the sd card 
        String budget = jTextField22.getText();
        String full_name = jTextField23.getText();
        String email = jTextField24.getText();
        String diet = jTextArea3.getText();
        String pwd12 = jPasswordField4.getText();
        new_user(full_name,email,pwd12,diet,budget);
        
        state = "NEW_USER";
        update_state("NEW_USER");
        

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

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
            
//            URL url = getClass().getResource("credit_card.txt");
//            try{
//                        File file = new File(url.toURI());
//            }catch(URISyntaxException u){
//            System.out.println("URO");
//        }

             File file = new File("credit_card.txt"); 
             File file2 = new File("stm_whisper.txt"); 
             
             String value_auth;
             String value_new;
             int k2 = 1;
             int k1 = 1;
 


        
             
            public void run(){



            while(true){
                curr_state = fr.get_state();
                switch(curr_state){
                   case "IDLE": //send_data("t idle"); 
                       k2 = 1;
                       if(k1 == 1){ 
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
                            k1 = k1-1;
                       }
                       
                        System.out.println("IDLE");
                        break;
                   case "LOGIN": //send_data("t login"); 
                        System.out.println("LOGIN");
                                
                        break;
                   case "AUTH": //send_data("t login"); 
                        value_auth = fr.read_whisper();
                        if(value_auth != null){
                            fr.auth_it(value_auth,"auth");
                        }                             
                        break;
                   case "SIGN_UP"://send_data("t sign_up");  System.out.println("SIGN_UP");
                        break;
                   case "NEW_USER": //send_data("t login"); 
                        value_new = fr.read_whisper();
                        if(value_new != null){
                            fr.auth_it(value_new,"new");
                        }                             
                        break;
                   case "MAIN"://send_data("t main"); 
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
                            k2 = k2-1;
                       }

                        System.out.println("MAIN");
                        break;
                   case "EDIT"://send_data("t edit");
                        break;
                   case "CHECK_OUT"://send_data("t checkout");
                        System.out.println("CHECKOUT");
                        break;
                   case "ENTER_CARD"://send_data("t enter"); 
                        break;
                   case "SWIPE_CARD"://send_data("t swipw"); 
                       System.out.println("SWIPE");
//                       try{
//                             System.out.println("hello");
//                         } catch (Exception e){
//                             e.printStackTrace();
//                         }
                       
//                       while(true){ 
                            try{
                                System.out.println("hello");
                                BufferedReader reader = new BufferedReader(new FileReader(file));

                                String line = reader.readLine();
                                while(line  != null){  
                                    String line2 = reader.readLine();
                                    //System.out.println(line.substring(0,2));
                                    if(line2 == null){
                                    //will fail if name is FA 
                                    System.out.println("EHAT2");
                                    JOptionPane.showMessageDialog(null, "Please swipe either a credit or debit card");                                    
                                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                                    writer.close();                                   
                                    }else{
                                      String cr_no = line2;
                                      String cr_name = line;
                                      String cr_date = reader.readLine();
                                      fr.swipe_card(cr_no,cr_name,cr_date);
                                      BufferedWriter writer2 = new BufferedWriter(new FileWriter(file));
                                      writer2.close();  
                                    }
                                 line = reader.readLine();
                                }
                                reader.close();
                                
                                
//                                if (line == null){
//                                    System.out.println("EHAT");
//                                }else if(line == "FALSE"){
//                                    System.out.println("EHAT2");
//                                    JOptionPane.showMessageDialog(null, "Please swipe either a credit or debit card");
//                                    writer.write("");
//                                    
//                                } else{
//                                      String cr_no = reader.readLine();
//                                      String cr_name = line;
//                                      String cr_date = reader.readLine();
//                                      fr.swipe_card(cr_no,cr_name,cr_date);
//                                
//                                    break;
//                                }
                            }catch(IOException ex){
                                System.out.println("ead file exception");
                                ex.printStackTrace();
                            }
//                       }   

                       break;
                   case "END"://send_data("t end");
                        //System.out.println("END");
                       k1 =1;
                        fr.endDate = new Date();
                        long Lapse = (fr.endDate.getTime() -fr.startDate.getTime())/1000;
                        if(Lapse > 10){
                            fr.new_game();
                        }else{
                            fr.update_time(10 - Lapse);
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
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFormattedTextField jFormattedTextField1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
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
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
