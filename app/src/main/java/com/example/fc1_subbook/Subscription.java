/*
 * Copyright © 2018 Fangting Chen. CMPUT301. University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the
 * Code of Student Behaviour at University of Alberta. You can find a copy of the
 * license in this project. Otherwise please contact fc1@ualberta.ca
 *
 */

package com.example.fc1_subbook;

import java.io.Serializable;

/**
 * Represent a subscription class
 *
 * @author fc1
 *
 */

public class Subscription implements Serializable{
    private String name;
    private String date;
    private Float monthlyCharge;
    private String comment;

    /**
     * Constructor of a subscription
     *
     * @param name name of the subscription
     * @param date date started
     * @param monthlyCharge monthly charge of the subscription
     * @param comment comment about the subscription
     */
    public Subscription(String name, String date, Float monthlyCharge, String comment)  {
        if (name.length() > 20){
            throw new ArithmeticException("Name is too long.");
        }
        if (comment.length() > 30){
            throw new ArithmeticException("Comment is too long.");
        }
        this.name = name;
        this.date = date;
        this.monthlyCharge = monthlyCharge;
        this.comment = comment;

    }

    /**
     * Getter of the name of the subscription
     * @return name of subscription
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the name of the subscription
     * @param name name of the subscription that is being set to
     * @throws ArithmeticException
     */
    public void setName(String name) throws ArithmeticException {
        if (name.length() <= 20){
            this.name = name;
        }
        else {
            throw new ArithmeticException();
        }
    }

    /**
     * Getter of the date of subscription
     * @return date
     */
    public String getDate() {

        return date;
    }

    /**
     * Setter of date
     * @param date date being set to
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter of the monthly charge
     * @return monthly charge
     */
    public Float getMonthlyCharge(){

        return monthlyCharge;
    }

    /**
     * Setter of the monthly charge
     * @param monthlyCharge new montly charge that is being set to
     */
    public void setMonthlyCharge(Float monthlyCharge) {

        this.monthlyCharge = monthlyCharge;
    }

    /**
     * Getter of the comment
     * @return comment of subscription
     */
    public String getComment(){
        return comment;
    }

    /**
     * Setter of comment
     * @param comment new comment 
     * @throws ArithmeticException
     */
    public void setComment(String comment) throws ArithmeticException {
        if (comment.length() <= 30){
            this.comment = comment;
        }
        else{
            throw new ArithmeticException();
        }
    }


}

