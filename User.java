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
    int monthlyIncome;
    BudgetCategory categories;
    
    public User(){
        username = "";
        password = "";
        status = false;
        monthlyIncome = 0;
        categories = new BudgetCategory();
    }
    
    public User(String username, String password, boolean status){
        this.username = username;
        this.password = password;
        this.status = status;
        categories = new BudgetCategory();
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
  /*  
    public String getCategory(int index){
        return categories.get(index);

    }
    
    public void addCategory(String category){
        categories.add(category);
    }
    
    public int getCategoriesSize(){
       return categories.size();
    }
    
    public boolean categoryIsEmpty(){
        if(categories.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }*/
}
