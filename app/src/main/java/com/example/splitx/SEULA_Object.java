package com.example.splitx;

import com.google.firebase.firestore.Exclude;

public class SEULA_Object {

    String amount ,email, userPhoto, name;

    boolean selected;

    public SEULA_Object(){

    }
    public SEULA_Object(String name, String userPhoto, String amount, String email, boolean selected){
        this.name = name;
        this.userPhoto = userPhoto;
        this.amount = amount;
        this.email = email;
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }


}
