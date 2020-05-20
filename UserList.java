/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.easybudget;

import java.util.HashMap;
/**
 *
 * @author mitch
 */
public class UserList implements java.io.Serializable{
    HashMap <String, User> hashmap;
    
    public UserList(){
        hashmap = new HashMap<>();
        
    }
    
    public void addNewUser(String username, User user){
         hashmap.put(username, user);
    }
    
    public User getUser(String username){
        return hashmap.get(username);
    }
    
    public int getSize(){
        return hashmap.size();
    }
    
    public boolean checkUser(String user){
        if(!hashmap.containsKey(user))
            return false;
        else{
            return true;
        }
    }
            
}
