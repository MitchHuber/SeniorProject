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
public class BudgetCategory implements java.io.Serializable {
    String categoryTitle;
    ArrayList<BudgetItem> budgetItems;
    
    public BudgetCategory(String title){
        categoryTitle = title;
        budgetItems = new ArrayList<>();
    }
    
    public void addItemToCategory( BudgetItem item){
        budgetItems.add(item);
    }

    public BudgetItem getItem(int i){
        return budgetItems.get(i);
    }
    
    public int getItem(String name, String price){
        for (BudgetItem budgetItem : budgetItems) {
            if(budgetItem.name.equals(name) && budgetItem.price.equals(price))  
                return budgetItems.indexOf(budgetItem);
        }
        return -1;
    }
    
    public void removeItem(int i){
                budgetItems.remove(i);
    }
    
    public int getItemCount(BudgetCategory category){
        return category.budgetItems.size();
    }
}
