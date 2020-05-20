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
public class Server implements java.io.Serializable{
    
    UserList userList = new UserList();
    String filename = "info.ser";
    
    public String register(String username, String password, String email){
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
        else if(email.isEmpty()){
            error = "3";
            return error;
        }
        else{
            User user = new User(username, password, email, false);
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
    
    public String getCategoryTitle(String username, int i){
        User user = userList.getUser(username);
        
        return user.getCategory(i);
    }
    
    public String addCategory(String username, String categoryTitle){
        String error;
        User user = userList.getUser(username);
        BudgetCategory category = new BudgetCategory(categoryTitle);
        //Doesn't add categories with the same name to the User's arraylist
        if(user.categoryCheck(category) == true){
             error = "1";
            return error;
        }
        else{
            user.addCategory(category);
            return error = "0";
        }
    }
    
    public int totalCategories(String username){
        User user = userList.getUser(username);
            return user.getTotalCategories();
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
    
    public int removeCategory(String username, String categoryTitle){
        User user = userList.getUser(username);
        BudgetCategory category = user.getCategory(categoryTitle);
        
        if(user.categoryCheck(category) == false){
          int error = -1;
          return error;
        }
        else{
          int index = user.categoryIndex(category);
          System.out.println("The index is " + index);
          user.removeCategory(index);
          return index;
        }
    }
    
    public int checkCategory(String username, String categoryTitle){
        User user = userList.getUser(username);
        BudgetCategory category = user.getCategory(categoryTitle);
        
        if(user.categoryCheck(category) == false){
          int error = -1;
          return error;
        }
        else{
            int error = -2;
            return error;
        }
    }
    
    public int getCategoryIndex(String username, String categoryTitle){
        User user = userList.getUser(username);
        BudgetCategory category = user.getCategory(categoryTitle);
        
        if(user.categoryCheck(category) == false){
            int error = -1;
            return error;
        }
        else{
            return user.categoryIndex(category);
        }       
    }
   
    public void addItem(String username, String categoryTitle, String item, String price){
        User user = userList.getUser(username);
        BudgetItem product = new BudgetItem(item, price);
        BudgetCategory category = user.getCategory(categoryTitle);
        
        user.addItemToCategory(category, product);

    }
    
    public String removeItem(String username, String categoryTitle, String itemInfo){
        User user = userList.getUser(username);
        BudgetCategory category = user.getCategory(categoryTitle);
        
        int str = itemInfo.indexOf("$");
        String name = itemInfo.substring(1, str);
        String price = itemInfo.substring(str + 1, itemInfo.length() - 1);
        System.out.println(name + " " + price);
        
        int item = category.getItem(name.trim(), price.trim());
        user.removeItemFromCategory(category, item);
        return price;
    }
    
    public String getItemName(String username, String title, int i){
        User user = userList.getUser(username);
        BudgetCategory category = user.getCategory(title);
        return category.getItem(i).name;
    }
    
    public String getItemPrice(String username, String title, int i){
        User user = userList.getUser(username);
        BudgetCategory category = user.getCategory(title);
        return category.getItem(i).price;
    }

    public int getCategoryTotalItems(String username, String title){
        User user = userList.getUser(username);
        BudgetCategory category = user.getCategory(title);
        return user.getCategorySize(category);
    }
    
    public void setBudget(String username, String monthlyIncome, String spendingGoal){
        User user = userList.getUser(username);
        
        user.setBudget(Integer.parseInt(monthlyIncome), Integer.parseInt(spendingGoal));
    }
    
    public UserList getUsers(){
        return userList;
    }
    
    public int getSpendingGoal(String username){
        User user = userList.getUser(username);
        return user.spendingGoal;
    }
    
    public User checkOnline(String username){
        User user = userList.getUser(username);
        
        if(user.status == true){
            return user; 
        }
        else{
            return null;
        }
    }
}   