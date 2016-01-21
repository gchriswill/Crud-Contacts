package com.example.christophergonzalez.crudcontacts;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by christophergonzalez on 1/15/16.
 */
public class ContactsListScreenFragment extends ListFragment {

    public static final String TAG = "ContactsListScreenFragment";
    private ContactsListInterface mContacts;

    FloatingActionButton back;

    public static ContactsListScreenFragment newInstanceOf(){

        ContactsListScreenFragment fragment = new ContactsListScreenFragment();

        return fragment;
    }

    public interface ContactsListInterface {

        void ContactsListInterfaceMethod(String a, String b, String c, int d);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ContactsListInterface){

            mContacts = (ContactsListInterface)context;

        }else{

            throw new IllegalArgumentException(
                    "\n Error on ContactsListScreenFragment on Attach condition!!! \n");

        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FileStore store = new FileStore();
        Context act = getActivity().getApplicationContext();

        ArrayList<Contact> list = store.fileReader(act);
        ArrayList<String> names = new ArrayList<>();

        for (Contact c:list) {

            names.add(c.mName);

        }

        setListAdapter(new ArrayAdapter<>(getActivity(), R.layout
                .list_contact_item, R.id.contact_item_text_view, names));

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        TextView tv = (TextView) v.findViewById(R.id.contact_item_text_view);

        FileStore store = new FileStore();
        Context act = getActivity().getApplicationContext();

        Contact contact = store.fileContactExtractor(tv.getText().toString(), act);

        mContacts.ContactsListInterfaceMethod(contact.mName, contact.mPhoneNumber, contact
                .mEmail, position);


    }

}
