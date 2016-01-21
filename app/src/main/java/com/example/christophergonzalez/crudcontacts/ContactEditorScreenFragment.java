package com.example.christophergonzalez.crudcontacts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by christophergonzalez on 1/15/16.
 */
public class ContactEditorScreenFragment extends Fragment {

    public static final String TAG = "ContactEditorScreenFragment";
    public static final String NAME_KEY = "namekey";
    public static final String PHONE_KEY = "phonekey";
    public static final String EMAIL_KEY = "emailkey";
    public static final String ID_KEY = "idkey";

    private ContactsEditorInterface mContacts;

    EditText mNameField;
    EditText mPhoneNumberField;
    EditText mEmailField;

    public static ContactEditorScreenFragment newInstanceOf(String name, String phoneNumber,
                                                            String email, int position,
                                                            boolean passed){

        ContactEditorScreenFragment fragment = new ContactEditorScreenFragment();

        Bundle bundle = new Bundle();

        if (name != null || phoneNumber != null || email != null){

            bundle.putString(NAME_KEY, name);
            bundle.putString(PHONE_KEY, phoneNumber);
            bundle.putString(EMAIL_KEY, email);
            bundle.putInt(ID_KEY, position);

            bundle.putBoolean("isPassed", passed);


        }else {

            bundle.putBoolean("isPassed", false);

        }

        fragment.setArguments(bundle);

        return fragment;

    }

    public interface ContactsEditorInterface {

        void ContactsEditorInterfaceMethod(String a, String b, String c, int d, boolean e);

    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.contact_editor_screen_fragment, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ContactsEditorInterface){

            mContacts = (ContactsEditorInterface)context;

        }else{

            throw new IllegalArgumentException(
                    "\n Error on ContactEditorScreenFragment on Attach condition!!! \n");

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity act = getActivity();

        mNameField = (EditText) act.findViewById(R.id.name_field);
        mPhoneNumberField = (EditText) act.findViewById(R.id.phone_number_field);
        mEmailField = (EditText) act.findViewById(R.id.email_field);

        Bundle bundle = getArguments();

        int mReceivedPosition = 0;
        boolean isPassed = false;

        if (bundle.getBoolean("isPassed") ){

            String name = bundle.getString(NAME_KEY);
            String phone = bundle.getString(PHONE_KEY);
            String email = bundle.getString(EMAIL_KEY);
            mReceivedPosition = bundle.getInt(ID_KEY);
            isPassed = true;

            mNameField.setText( name );
            mPhoneNumberField.setText( phone );
            mEmailField.setText( email );

        }

        final boolean finalIsPassed = isPassed;
        final int finalMReceivedPosition = mReceivedPosition;

        getActivity().findViewById(R.id.save).setOnClickListener
                (new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = mNameField.getText().toString();
                String phone = mPhoneNumberField.getText().toString();
                String email = mEmailField.getText().toString();

                v.setOnClickListener(null);

                mContacts.ContactsEditorInterfaceMethod(name, phone, email, finalMReceivedPosition, finalIsPassed);

            }

        });

    }

}
