/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.easybudget;

import java.util.ArrayList;

/**
 *
 * @author mitch
 */
public class BudgetCategory {
    ArrayList<String> categories;
    
    public BudgetCategory(){
        categories = new ArrayList();
    }
   
    public String getCategoryAt(int index){
        return categories.get(index);   
    }  
    
    public void add(String category){
        categories.add(category);
    }
    
    public int getSize(){
       return categories.size();
    }
    
    public boolean categoryCheck(String category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category))
                return false;                
        }
        return true;
    }
    
    public boolean categoryIsEmpty(){
        if(categories.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}
