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
public class BudgetItem implements java.io.Serializable{
    String name;
    String price;
    
    public BudgetItem(){
        name = "";
        price = "";
    }
    
    public BudgetItem(String name, String price){
        this.name = name;
        this.price = price;
    }

}
