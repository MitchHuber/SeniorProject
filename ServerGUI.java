/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.easybudget;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
/**
 *
 * @author mitch
 */
public class ServerGUI extends javax.swing.JFrame implements java.io.Serializable {
    static String filename = "info.ser";
    static Server server = new Server();
    /**
     * Creates new form ServerGUI
     */
    public ServerGUI() {
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        shutdownBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("EBudget Server");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel1)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        shutdownBtn.setText("Shutdown");
        shutdownBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutdownBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(shutdownBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shutdownBtn)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shutdownBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownBtnActionPerformed
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
                      
            out.writeObject(server.getUsers());
            
            Set set = server.getUsers().hashmap.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                String key = mentry.getKey().toString();
                User user = server.getUsers().getUser(key);
                out.writeObject(user.monthlyIncome);
                out.writeObject(user.spendingGoal);
                out.writeObject(user.categories);
                
                for(int i = 0; i < server.totalCategories(key); i++){
                    BudgetCategory category = user.categories.get(i);
                    out.writeObject(category);
                    out.writeObject(category.budgetItems);
                    for(int j = 0; j < category.budgetItems.size(); j++){
                       out.writeObject(category.getItem(j));
                    }
                }
            }
            System.out.println("Serialization successful");
            out.close();
            file.close();
            System.exit(0);
        }
        catch(IOException ex){
            System.out.println("Caught");
            System.exit(-1);
        }
    }//GEN-LAST:event_shutdownBtnActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
       // UserList userList = server.getUsers();
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
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
                
               
            }
        });
         
        //Deserialize User's and user info
        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            
            UserList userList = (UserList)in.readObject();
            Set set = userList.hashmap.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                String key = mentry.getKey().toString();
                User user = userList.getUser(key);
                server.register(user.username, user.password, user.email);
                System.out.println(user.email);
                user.monthlyIncome = (int)in.readObject();
                user.spendingGoal = (int)in.readObject();
                server.setBudget(user.username, Integer.toString(user.monthlyIncome), Integer.toString(user.spendingGoal));
                user.categories  = (ArrayList)in.readObject();
                for (BudgetCategory categorie : user.categories) {
                    BudgetCategory category = (BudgetCategory)in.readObject();
                    server.addCategory(user.username, category.categoryTitle);
                    category.budgetItems = (ArrayList)in.readObject();
                    for (BudgetItem budgetItem : category.budgetItems) {
                        BudgetItem item = (BudgetItem)in.readObject();
                        server.addItem(user.username, categorie.categoryTitle, item.name, item.price);
                    }
                }
            }
            in.close();
            file.close();
            System.out.println("Deserialization was successful");
        }
        catch(IOException ex) { 
            System.out.println("IOException is caught"); 
        }
        
        //Start listening 
        try {
     
            ServerSocket listen = new ServerSocket( 2001 );
         
            System.out.println( "Listening on port:  " +listen.getLocalPort() );
            System.out.println("Listening on address: " + InetAddress.getLocalHost());
     
            while ( true ) {
                
                Socket client = listen.accept();
                PrintWriter out = new PrintWriter( client.getOutputStream(), true );
                BufferedReader in = new BufferedReader(
                        new InputStreamReader( client.getInputStream() ) );
                
                String request = in.readLine();
                System.out.println(request);
                
                
                if(request.equals("REG")){
                    out.println("OK");
                    String username = in.readLine();
                    String password = in.readLine();
                    String email = in.readLine();
                    String check = server.register(username, password,email);
                    server.register(username, password, email);
                    out.println(check);
                    out.close();
                    client.close();
                    System.out.print("The username " + username + "\n" + 
                            "The password " + password + "\n");
                }
                if(request.equals("LOGIN")){
                    out.println("OK");
                    String username = in.readLine();
                    String password = in.readLine();
                    String check = server.login(username, password);
                    out.println(check);

                    if(server.checkUser(username) == true){
                        int totalCats = server.totalCategories(username);
                        System.out.println("Total categories are " + totalCats);
                        out.println(Integer.toString(totalCats));
                        String goal = Integer.toString(server.getSpendingGoal(username));
                        out.println(goal);
                        boolean let = server.checkEmpty(username);
                        if(let){
                            String category = "";
                            out.println(category);
                            System.out.println("Categories are empty");
                        }
                        else{
                            for(int i = 0; i < totalCats; i++){
                                String category = server.getCategoryTitle(username, i);
                                System.out.println(category);
                                out.println(category);
                                int totalItems = server.getCategoryTotalItems(username, category);
                                System.out.println(totalItems);
                                out.println(Integer.toString(totalItems));
                                for(int j = 0; j < totalItems; j++){
                                    String item = server.getItemName(username, category, j);
                                    String price = server.getItemPrice(username, category, j);
                                    out.println(item);
                                    out.println(price);
                                    System.out.println(item + price);
                                }
                            }
                            System.out.println("The user: " + username + " is logged in");
                        }   
                    }
                }
                if(request.equals("ADDCAT")){
                    out.println("OK");
                    String username = in.readLine();
                    String title = in.readLine();
                    if(title.equals("NO")){
                        System.out.println("Category field was blank");
                        out.close();
                        client.close();
                    }
                    else if(server.checkCategory(username, title) == -2){
                        System.out.println("Category was already there" + server.checkCategory(username, title) );
                        out.println(Integer.toString(server.checkCategory(username, title)));
                        out.close();
                        client.close();
                    }
                    else{
                        System.out.println(server.totalCategories(username) + "THIS");
                        out.println(Integer.toString(server.totalCategories(username)));
                        server.addCategory(username, title);
                        System.out.println("The number of categories are " + 
                        server.totalCategories(username));
                        out.close();
                        client.close();
                    }
                }
                if(request.equals("REMOVECAT")){
                    out.println("OK");
                    String check = in.readLine();
                    if(check.equals("BLANK")){
                        System.out.println("User didn't include title.");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("EMPTY")){
                        System.out.println("There are no tabs created");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("OK")){
                        String username = in.readLine();
                        int goal = server.getSpendingGoal(username);
                        out.println(Integer.toString(goal));
                        String category = in.readLine();

                        if(server.checkCategory(username, category) == -1){
                            check = "NO";
                            out.println(check);
                            System.out.println("Category wasn't there");
                            out.close();
                            client.close();
                        }
                        else{
                            out.println("OK");
                            check = in.readLine();
                            if(check.equals("ACCIDENT")){
                                System.out.println("Decided not to delete category");
                                out.close();
                                client.close();
                            }
                            else if(check.equals("OK")){
                                if(server.checkCategory(username, category) == -1){
                                    check = "NO";
                                    out.println(check);
                                    System.out.println("Category wasn't there");
                                    out.close();
                                    client.close();
                                }
                                else{                       
                                    int categoryIndex = server.getCategoryIndex(username, category);
                                    int items = server.getCategoryTotalItems(username, category);
                                    out.println(Integer.toString(items));
                                    for(int i = 0; i < items; i++){
                                        String price = server.getItemPrice(username, category, i);
                                        out.println(price);
                                    }
                                    String index = Integer.toString(categoryIndex);
                                    out.println(index);
                                    server.removeCategory(username, category);
                                    System.out.println("The " + category + " category was removed");
                                    System.out.println("The # of cats are " + server.totalCategories(username));
                                    out.close();
                                    client.close();   
                                }
                            }
                        }
                    }
                }
                if(request.equals("ADDITEM")){
                    out.println("OK");
                    String check = in.readLine();
                    if(check.equals("BLANK")){
                        System.out.println("There were no categories");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("NO")){
                        System.out.println("Both item/price wasn't filled out");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("NUMS")){
                        System.out.println("User included numbers in the item field");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("CHARS")){
                        System.out.println("User included characters in the price field");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("YES")){
                        String username = in.readLine();
                        String goal = Integer.toString(server.getSpendingGoal(username));
                        out.println(goal);                      
                        String category = in.readLine();
                        System.out.println(category + " Category");
                        String item = in.readLine();
                        String price = in.readLine();
                        server.addItem(username, category, item.trim(), price.trim());
                        System.out.println(server.getCategoryTotalItems(username, category));
                        out.close();
                        client.close();
                    }
                }
                if(request.equals("REMOVEITEM")){
                    out.println("OK");
                    String username = in.readLine();
                    String check = in.readLine();
                    if(check.equals("BLANK")){
                        System.out.println("User didnt create a category");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("NO")){
                        System.out.println("User didnt select an item");
                        out.close();
                        client.close();
                    }
                    else{
                        String goal = Integer.toString(server.getSpendingGoal(username));
                        out.println(goal);
                        String category = in.readLine();
                        String itemInfo = in.readLine();
                        String price = server.removeItem(username, category, itemInfo);
                        out.println(price);
                        out.close();
                        client.close();
                    }
                }
                if(request.equals("SETBUDGET")){
                    out.println("OK");
                    String check = in.readLine();
                    if(check.equals("BLANK")){
                        System.out.println("User didnt fill out savings goal or monthly income");
                        out.close();
                        client.close();
                    }
                    else if(check.equals("CHARS")){
                        System.out.println("User included letters in either spending "
                                + "goal or monthly income");
                        out.close();
                        client.close();
                    }
                    else{
                        String username = in.readLine();
                        String monthlyIncome = in.readLine();
                        String spendingGoal = in.readLine();
                        server.setBudget(username, monthlyIncome, spendingGoal);
                        out.close();
                        client.close();
                    }
                }
                if(request.equals("LOGOFF")){
                    out.println("OK");
                    String username = in.readLine();
                    server.logoff(username);
                    out.close();
                    client.close();
                }
            }
        }
        catch( IOException e) {
            System.err.println( e.getMessage() );
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton shutdownBtn;
    // End of variables declaration//GEN-END:variables
}
