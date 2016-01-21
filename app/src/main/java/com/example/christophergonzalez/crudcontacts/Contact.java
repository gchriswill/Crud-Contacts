package com.example.christophergonzalez.crudcontacts;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by christophergonzalez on 1/15/16.
 */
public class Contact implements Serializable {

    public static final String TAG = "Contact";

    public String mName;
    public String mPhoneNumber;
    public String mEmail;
    public String mObjId;

    public Contact(){

    }

    public Contact(String name, String phoneNumber, String email ) {

        this.mName = name;
        this.mPhoneNumber = phoneNumber;
        this.mEmail = email;

    }

}
