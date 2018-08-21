/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pem.com;

import java.io.Serializable;

/**
 *
 * @author Eugene
 */
public class Category implements Serializable{
    private Long categoryId = System.currentTimeMillis(); //generate unique id
    private String name;
    
    public Category(String name){
        this.name = name;
    }
    
    public Category(Long categoryID, String name){
        this.categoryId = categoryID;
        this.name = name;
    }
    
    
    
    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
