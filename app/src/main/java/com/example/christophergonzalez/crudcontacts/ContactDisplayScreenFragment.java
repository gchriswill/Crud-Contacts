package com.example.christophergonzalez.crudcontacts;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * Created by christophergonzalez on 1/15/16.
 */
public class ContactDisplayScreenFragment extends ListFragment {

    public static final String TAG = "ContactDisplayScreenFragment";
    private ContactsDisplayInterface  mContacts;

    public static final String NAME_KEY = "namekey";
    public static final String PHONE_KEY = "phonekey";
    public static final String EMAIL_KEY = "emailkey";
    public static final String ID_KEY = "idkey";

    String mReceivedName;
    String mReceivedPhoneNumber;
    String mReceivedEmail;
    int mReceivedPosition;

    FloatingActionButton edit;
    FloatingActionButton delete;

    public static ContactDisplayScreenFragment newInstanceOf(String name, String phoneNumber,
                                                             String email, int position) {

        ContactDisplayScreenFragment fragment = new ContactDisplayScreenFragment();

        Bundle bundle = new Bundle();
        bundle.putString(NAME_KEY, name);
        bundle.putString(PHONE_KEY, phoneNumber);
        bundle.putString(EMAIL_KEY, email);
        bundle.putInt(ID_KEY, position);

        fragment.setArguments(bundle);

        return fragment;

    }

    public interface ContactsDisplayInterface {

        void ContactsDisplayInterfaceMethod(String a, String b, String c, int d);
        void ContactsDisplayInterfaceDeleteMethod(int d);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ContactsDisplayInterface){

            mContacts = (ContactsDisplayInterface)context;

        }else{

            throw new IllegalArgumentException(
                    "\n Error on ContactEditorScreenFragment on Attach condition!!! \n");

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        mReceivedName = bundle.getString(NAME_KEY);
        mReceivedPhoneNumber = bundle.getString(PHONE_KEY);
        mReceivedEmail = bundle.getString(EMAIL_KEY);
        mReceivedPosition = bundle.getInt(ID_KEY);

        String[] titles = {mReceivedName, mReceivedPhoneNumber, mReceivedEmail};

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                titles) );

        edit = (FloatingActionButton) getActivity().findViewById(R.id.edit);
        delete = (FloatingActionButton) getActivity().findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mContacts.ContactsDisplayInterfaceDeleteMethod(mReceivedPosition);

            }

        });

        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mContacts.ContactsDisplayInterfaceMethod( mReceivedName, mReceivedPhoneNumber,
                        mReceivedEmail, mReceivedPosition );

            }

        });

    }

}
