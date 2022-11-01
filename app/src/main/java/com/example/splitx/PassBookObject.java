package com.example.splitx;

public class PassBookObject {
    String date, time, name, upi, amount, status;

    public PassBookObject(){

    }
    public PassBookObject(String date, String time, String name, String upi, String amount,String status){
        this.date = date;
        this.time = time;
        this.name = name;
        this.upi = upi;
        this.amount = amount;
        this.status = status;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
