package com.example.fc1_subbook;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2018-01-29.
 */

public class Subscription implements Serializable{
    private String name;
    private String date;
    private String monthlyCharge;
    private String comment;

    public Subscription(String name, String date, String monthlyCharge, String comment)  {
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

    public String getName() {
        return name;
    }

    public void setName(String name) throws ArithmeticException {
        if (name.length() <= 20){
            this.name = name;
        }
        else {
            throw new ArithmeticException();
        }
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonthlyCharge(){

        return monthlyCharge;
    }

    public void setMonthlyCharge(String monthlyCharge) {

        this.monthlyCharge = monthlyCharge;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment) throws ArithmeticException {
        if (comment.length() <= 30){
            this.comment = comment;
        }
        else{
            throw new ArithmeticException();
        }
    }

    public void update(String name, String date, String monthlyCharge, String comment) {
        this.name = name;
        this.date = date;
        this.monthlyCharge = monthlyCharge;
        this.comment = comment;

    }

}

