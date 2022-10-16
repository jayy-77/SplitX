package com.example.splitx;

public class SEULA_Object_For_Fire {
    String email, paymentStatus;
    public SEULA_Object_For_Fire(){

    }
    public SEULA_Object_For_Fire(String email, String paymentStatus){
        this.email = email;
        this.paymentStatus = paymentStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


}
