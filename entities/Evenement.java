/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Date;



/**
 *
 * @author mehdi
 */
public class Evenement  extends Publication{
    private java.sql.Date date_even;
    private String image;
    private String sujet;

    public Evenement() {
    }

    public Evenement( java.sql.Date date_pub,java.sql.Date date_even, String image, String sujet) {
        super( date_pub);
        this.date_even = date_even;
        this.image = image;
        this.sujet = sujet;
    }
    

    public Evenement(Date date_even, String image, String sujet) {
        this.date_even = date_even;
        this.image = image;
        this.sujet = sujet;
    }

    public Evenement(Date date_even, String image, String sujet, int id_pub, Date date_pub) {
        super(id_pub, date_pub);
        this.date_even = date_even;
        this.image = image;
        this.sujet = sujet;
    }

    public Evenement(Date date_even, String image, String sujet, Date date_pub) {
        super(date_pub);
        this.date_even = date_even;
        this.image = image;
        this.sujet = sujet;
    }

   

    public java.sql.Date getDate_even() {
        return date_even;
    }

    public void setDate_even(java.sql.Date date_even) {
        this.date_even = date_even;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    @Override
    public java.sql.Date getDate_pub() {
        return super.getDate_pub(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId_pub() {
        return super.getId_pub(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDate_pub(java.sql.Date date_pub) {
        super.setDate_pub(date_pub); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId_pub(int id_pub) {
        super.setId_pub(id_pub); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString()+ "Evenement{" + "date_even=" + date_even + ", image=" + image + ", sujet=" + sujet + '}';
    }
    
    
    
    
    
   
  
    
    
}
