package com.example.christophergonzalez.crudcontacts;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by christophergonzalez on 1/15/16.
 */
public class FileStore {

    public static final String TAG = "Storer";
    public String File = "cache";

    public void fileWriterAll(ArrayList<Contact> contacts, Context inContext){

        try {

            FileOutputStream fos;

            fos = inContext.openFileOutput(File, Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(contacts);
            oos.close();

        }catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void fileWriter(String name, String phoneNumber, String email, Context inContext) {

        Contact c = new Contact(name, phoneNumber, email);
        ArrayList<Contact> internalDataStored =  fileReader(inContext);
        internalDataStored.add(c);

        try {

            FileOutputStream fos;

            fos = inContext.openFileOutput(File, Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(internalDataStored);
            oos.close();

        }catch (IOException e) {

            e.printStackTrace();

        }

    }

    public ArrayList<Contact> fileReader( Context inContext) {

        FileInputStream fis;
        ArrayList<Contact> internalDataStored = new ArrayList<>();

        try {

            fis = inContext.openFileInput(File);
            ObjectInputStream ois = new ObjectInputStream(fis);
            internalDataStored = (ArrayList<Contact>) ois.readObject();
            ois.close();

        } catch (ClassNotFoundException | IOException e) {

            e.printStackTrace();

        }

        return internalDataStored;

    }

    public void fileUpdater(String name, String phoneNumber, String email, int
            position,
            Context inContext) {

        ArrayList<Contact> internalDataStored = fileReader(inContext);

        internalDataStored.get(position).mName = name;
        internalDataStored.get(position).mPhoneNumber = phoneNumber;
        internalDataStored.get(position).mEmail = email;

        fileWriterAll(internalDataStored, inContext);

    }

    public ArrayList<Contact> fileDeleter(int position, Context inContext) {

        ArrayList<Contact> internalDataStored = fileReader(inContext);

        internalDataStored.remove(position);

        fileWriterAll(internalDataStored, inContext);

        return internalDataStored;

    }

    public boolean filePurger(Context inContext){

        ArrayList<Contact> internalDataStored = fileReader(inContext);

        internalDataStored = new ArrayList<>();

        try {

            FileOutputStream fos;

            fos = inContext.openFileOutput(File, Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(internalDataStored);
            oos.close();

        }catch (IOException e) {

            e.printStackTrace();

        }

        return true;

    }

    public Contact fileContactExtractor (String byName, Context inContext){

        ArrayList<Contact> internalDataStored = fileReader(inContext);

        Contact contact = new Contact();

        for ( Contact c: internalDataStored) {

            if (c.mName.equals(byName) ){

                contact = c;


            }

        }

        return contact;

    }

}
