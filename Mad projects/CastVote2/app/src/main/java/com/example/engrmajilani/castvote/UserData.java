package com.example.engrmajilani.castvote;

/**
 * Created by Engr M A Jilani on 12/25/2017.
 */

public class UserData {
    public String uid, name, cnic, pass, mobileno;

    public  UserData(String uid,String cnic,String name){
        this.uid = uid;
        this.cnic = cnic;
        this.name = name;
    }

    public UserData(String uid, String name, String cnic, String pass, String mobileno) {
        this.uid = uid;
        this.name = name;
        this.cnic = cnic;
        this.pass = pass;
        this.mobileno = mobileno;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public String getPass() {
        return pass;
    }

    public String getMobileno() {
        return mobileno;
    }
}
