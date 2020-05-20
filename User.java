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
class User implements java.io.Serializable{
    String username, password, email;
    boolean status;
    int monthlyIncome, spendingGoal;
    ArrayList<BudgetCategory> categories;
    
    public User(){
        username = "";
        password = "";
        email = "";
        status = false;
        monthlyIncome = 0;
        spendingGoal = 0;
        categories = new ArrayList<>();
    }
    
    public User(String username, String password, String email, boolean status){
        this.username = username;
        this.password = password;
        this.status = status;
        this.email = email;
        spendingGoal = 0;
        categories = new ArrayList<>();
    }

    
    public String getPassword(){
        return password;
    }
    
    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    
    public void setBudget(int income, int goal){
        monthlyIncome = income;
        spendingGoal = goal;
    }
    
    public String getCategoryTitle(){
        for(BudgetCategory category1 : categories){
            return category1.categoryTitle;
        }
        return null;
    }
    
    public int getCategorySize(BudgetCategory title){
        return title.getItemCount(title);
    }
    
    public void addCategory(BudgetCategory categoryTitle){
        categories.add(categoryTitle);
    }
    
    public void removeCategory(int i){
        categories.remove(i);
    }
    
    public int getTotalCategories(){
       return categories.size();
    }
    
    public String getCategory(int i){
        return categories.get(i).categoryTitle;
    }
    
    public BudgetCategory getCategory(String title){    
        for (BudgetCategory category1 : categories) {
            if(category1.categoryTitle.equals(title))
                return category1;
        }
        return null;
    }
    
    public int getCategoryItemSize(String title){
        for(BudgetCategory category1 : categories){
            if(category1.categoryTitle.equals(title)){
                return category1.budgetItems.size();
            }
        }
        return -1;
    }
    
    public void addItemToCategory(BudgetCategory category, BudgetItem item){
        category.addItemToCategory(item);
    }
    
    public void removeItemFromCategory(BudgetCategory category, int item){
        category.removeItem(item);
    }
        
    public boolean emptyCheck(){
        return categories.isEmpty();
    }
    
    public boolean categoryCheck(BudgetCategory title){
        return categories.contains(title);
    }
    
    public int categoryIndex(BudgetCategory category){
        return this.categories.indexOf(category);
    }
   
}
