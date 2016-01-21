package com.example.christophergonzalez.crudcontacts;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
        ContactsListScreenFragment.ContactsListInterface,
        ContactEditorScreenFragment.ContactsEditorInterface,
        ContactDisplayScreenFragment.ContactsDisplayInterface {

    public static final String TAG = "MainActivity";

    FloatingActionButton add;
    FloatingActionButton save;
    FloatingActionButton delete;
    FloatingActionButton back;
    FloatingActionButton edit;

    FileStore store;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        add = (FloatingActionButton) findViewById(R.id.add);
        save = (FloatingActionButton) findViewById(R.id.save);
        delete = (FloatingActionButton) findViewById(R.id.delete);
        back = (FloatingActionButton) findViewById(R.id.back);
        edit = (FloatingActionButton) findViewById(R.id.edit);

        save.hide();
        delete.hide();
        back.hide();
        edit.hide();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save.hide();
                delete.hide();
                back.hide();
                edit.hide();
                add.show();

                ContactsListScreenFragment frag = (ContactsListScreenFragment) getSupportFragmentManager()
                        .findFragmentByTag(ContactsListScreenFragment.TAG);

                if (frag == null) {

                    frag = ContactsListScreenFragment.newInstanceOf();

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,
                        frag, ContactsListScreenFragment.TAG).commit();

            }

        });

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                add.hide();
                edit.hide();
                delete.hide();
                save.show();
                back.show();

                ContactEditorScreenFragment frag = (ContactEditorScreenFragment) getSupportFragmentManager()
                        .findFragmentByTag(ContactEditorScreenFragment.TAG);

                if (frag == null) {

                    frag = ContactEditorScreenFragment.newInstanceOf(null, null, null, 0, false);

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,
                        frag, ContactEditorScreenFragment.TAG).commit();

            }

        });

        if (savedInstanceState == null) {

            ContactsListScreenFragment frag = (ContactsListScreenFragment) getSupportFragmentManager()
                    .findFragmentByTag(ContactsListScreenFragment.TAG);

            if (frag == null) {

                frag = ContactsListScreenFragment.newInstanceOf();

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,
                    frag, ContactsListScreenFragment.TAG).commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //TODO: Delete All Data Implementation Goes Here!!!


        return super.onOptionsItemSelected(item);

    }

    public void onSave(View view) {

    }

    public void onDelete(View view) {

    }

    @Override
    public void ContactsListInterfaceMethod(String a, String b, String c, int d) {

        add.hide();
        save.hide();
        delete.show();
        back.show();
        edit.show();

        ContactDisplayScreenFragment frag = (ContactDisplayScreenFragment) getSupportFragmentManager()
                .findFragmentByTag(ContactDisplayScreenFragment.TAG);

        if (frag == null) {

            frag = ContactDisplayScreenFragment.newInstanceOf(a,b, c, d);

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,
                frag, ContactDisplayScreenFragment.TAG).commit();

    }


    @Override
    public void ContactsEditorInterfaceMethod(String a, String b, String c,
                                              int position, boolean editMode) {

        add.show();
        edit.hide();
        delete.hide();
        save.hide();
        back.hide();

        Context con = getApplicationContext();

        store = new FileStore();

        if (editMode){

            store.fileUpdater(a,b,c,position,con);

        }else {

            store.fileWriter(a, b, c, con);

        }

        ContactsListScreenFragment frag = ContactsListScreenFragment.newInstanceOf();

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,
                frag, ContactsListScreenFragment.TAG).commit();

    }

    @Override
    public void ContactsDisplayInterfaceMethod(String a, String b, String c, int d) {

        add.hide();
        edit.hide();
        delete.hide();
        save.show();
        back.show();

        ContactEditorScreenFragment frag = ContactEditorScreenFragment.newInstanceOf(a, b, c, d,
                true);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,
                frag, ContactEditorScreenFragment.TAG).commit();

    }

    @Override
    public void ContactsDisplayInterfaceDeleteMethod(int d) {

        add.show();
        edit.hide();
        delete.hide();
        save.hide();
        back.hide();

        Context con = getApplicationContext();

        store = new FileStore();

        store.fileDeleter(d, con);

        ContactsListScreenFragment frag = ContactsListScreenFragment.newInstanceOf();

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,
                frag, ContactsListScreenFragment.TAG).commit();

    }

}
