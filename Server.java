/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.easybudget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mitch
 */
public class Server {
    
    UserList userList = new UserList();
    
    public static void main(String[] args){
        Server server = new Server();
        server.register("J", "1");
        server.addCategory("J", "Utilities");
        server.addCategory("J", "Entertainment");
        server.addCategory("J", "Food");
        
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
                    String check = server.register(username, password);
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
                        int total = server.categories(username);
                        System.out.println(total);
                        out.println(Integer.toString(total));
                        boolean let = server.checkEmpty(username);
                        if(let == true){
                            String category = "";
                            out.println(category);
                            System.out.println("Categories are empty");
                        }
                        else{
                            for(int i = 0; i < server.categories(username); i++){
                                String category = server.getCategories(username, i);
                                System.out.println(category);
                                out.println(category);
                            }
                            System.out.println("The user: " + username + " is logged in");
                        }   
                    }
                }
                if(request.equals("ADDCAT")){
                    out.println("OK");
                    String user = in.readLine();
                    String title = in.readLine();
                    if(title.equals("NO")){
                        out.close();
                        client.close();
                    }
                    else{
                        String check = server.addCategory(user, title);
                        System.out.println(server.addCategory(user, title));
                        out.println(check);
                        System.out.println("The number of categories are " + 
                            server.categories(user) );
                        out.close();
                        client.close();
                    }
                }
                if(request.equals("ADDITEM")){
                    out.println("OK");
                    String check = in.readLine();
                    if(check.equals("NO")){
                        out.close();
                        client.close();
                    }
                    else if(check.equals("YES")){
                        String item = in.readLine();
                        String price = in.readLine();
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

    public String register(String username, String password){
        String error = "0";
        //Forces user to create a password
        if(password.isEmpty()){
            error = "1";
            return error;
        }
        //Checks username isn't already taken
        else if(userList.checkUser(username) == true){
            error = "2";
            return error;
        }
        else{
            User user = new User(username, password, false);
            userList.addNewUser(username, user);                                                                               
            return error;
        }
    }
    
    public String login(String username, String password){
        String error = "0";
        User user = userList.getUser(username);
        //User isnt registered yet
        if(userList.checkUser(username) == false){
            error = "1";
            return error;
        }
        //Checks password is correct
        else if(!user.getPassword().equals(password)){
            error = "2";
            return error;
        }
        //checks to see if user is already logged on
        else if(user.getStatus() == true){
            error = "3";
            return error;
        }
        else{
            user.setStatus(true);
            return error;
        }
    }
    public String logoff(String username){
        User user = userList.getUser(username);
        String error = "0";
        if(user.getStatus() == true)
            user.setStatus(false);
        return error;
    }
    public String getCategories(String username, int index){
        User user = userList.getUser(username);
        for(int i = 0; i < user.getCategoriesSize(); i++){
            return user.getCategory(index);
        }
        return null;
    }
    
    public String addCategory(String username, String category){
        String error = "0";
        User user = userList.getUser(username);
        //Doesn't add categories with the same name to the User's arraylist
        if(user.categoryCheck(category) == false){
            error = "1";
            return error;
        }
        else{
            user.addCategory(category);
            return error;
        }
    }
    public int categories(String username){
        User user = userList.getUser(username);
            return user.getCategoriesSize();
        
    }
    
    public boolean checkEmpty(String username){
        User user = userList.getUser(username);
        return user.emptyCheck();
    }
    
    public boolean checkUser(String username){
       if(userList.checkUser(username) == false){
           return false;
        }
        else{
            return true;
        }
       
    }
}
