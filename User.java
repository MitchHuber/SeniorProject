/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.easybudget;


/**
 *
 * @author mitch
 */
class User {
    String username, password;
    boolean status;
    int monthlyIncome, spendingGoal, itemIndex;
    BudgetCategory categories;
    BudgetItem budgetItems;
    
    public User(){
        username = "";
        password = "";
        status = false;
        monthlyIncome = 0;
        spendingGoal = 0;
        itemIndex = 0;
        categories = new BudgetCategory();
        budgetItems = new BudgetItem();
    }
    
    public User(String username, String password, boolean status){
        this.username = username;
        this.password = password;
        this.status = status;
        spendingGoal = 0;
        itemIndex = 0;
        categories = new BudgetCategory();  
        budgetItems = new BudgetItem();
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
    
    public BudgetCategory getCategories(String username){
        return categories;
    }
    
    public void addCategory(String category){
        categories.add(category);
    }
    
    public void removeCategory(int i){
        categories.remove(i);
    }
    
    public int getCategoriesSize(){
       return categories.getSize();
    }
    
    public String getCategory(int index){
        return categories.getCategoryAt(index);
    }
        
    public boolean emptyCheck(){
        return categories.categoryIsEmpty();
    }
    public boolean categoryCheck(String category){
        return categories.categoryCheck(category);
    }
    
    public int categoryIndex(String category){
        return categories.getIndex(category);
    }
    
    public void addItem(BudgetItem item){
        budgetItems.addItem(item);
        itemIndex++;
    }
    
    public int getItemIndex(){
        return itemIndex;
    }
    
    public String getItemName(int i){
        return budgetItems.getItemName(i);
    }
    
    public String getItemPrice(int i){
        return budgetItems.getItemPrice(i);
    }
    
    public int getItemSize(){
       return budgetItems.getSize();
    }
    
    public void setCategoryItemSize(String category, int size){
        categories.setItemCount(category, size);
    }
    
    public int getCategoryItemSize(String category){
        return categories.getItemCount(category);
    }
    
    public void setBudget(int income, int goal){
        monthlyIncome = income;
        spendingGoal = goal;
    }
}
