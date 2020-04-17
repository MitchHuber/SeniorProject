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
public class BudgetItem {
    ArrayList<BudgetItem> budgetItems;
    String name;
    String price;
    
    public BudgetItem(){
        budgetItems = new ArrayList();
        name = "";
        price = "";
    }
    
    public BudgetItem(String name, String price){
        this.name = name;
        this.price = price;
    }
    
    public void setItem(String name, String price){
        this.name = name;
        this.price = price;
    }
    
    public String getItemName(int i){
        BudgetItem item = budgetItems.get(i);
        return item.name;
    }
    
    public String getItemPrice(int i){
        BudgetItem item = budgetItems.get(i);
        return item.price;
    }
    
    public BudgetItem getItem(int i){
        return budgetItems.get(i);
    }
    
    
    public void addItem(BudgetItem item){
         budgetItems.add(item);
    }
    
    public int getSize(){
        return budgetItems.size();
    }
}
