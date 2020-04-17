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
    String categoryTitle;
    int totalItems;
    BudgetItem budgetItems;
    
    public BudgetCategory(){
        categories = new ArrayList();
        categoryTitle = "";
        totalItems = 0;
        budgetItems = new BudgetItem();
    }
   
    public String getCategoryAt(int index){
        return categories.get(index);   
    }  
    
    public void add(String category){
        categories.add(category);
    }
    
    public void remove(int i){
        categories.remove(i);
    }
    
    public int getSize(){
       return categories.size();
    }
    
    public int getIndex(String category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category))
                return i;
        }
        return -1;
    }
    
    public boolean categoryCheck(String category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category))
                return true;                
        }
        return false;
    }
    
    public boolean categoryIsEmpty(){
        if(categories.isEmpty())
            return true;
        else{
            return false;
        }
    }
    
    public void setCategoryTitle(String category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category)){
                categoryTitle = category;
            }
        }
    }
    
    public void addItemToCategory(String category, BudgetItem item){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category)){
                budgetItems.addItem(item);
                totalItems++;
            }
        }
    }
    
    public BudgetItem getItemFromCategory(String category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category)){
                return budgetItems.getItem(i);
            }
        }
            return null;
    }
    
    public void setItemCount(String category, int items){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category))
                totalItems = items;
        }
    }
    
    public int getItemCount(String category){
        for(int i = 0; i < categories.size(); i++){
            if(categories.get(i).equals(category)){
                return totalItems;
            }
        }
        return -1;
    }
}
