package com.example.harun.bulksms.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.harun.bulksms.Adapter.ListViewCustomAdapter;
import com.example.harun.bulksms.Model.Person;
import com.example.harun.bulksms.R;


public class Contacts extends AppCompatActivity implements View.OnClickListener{

    MainActivity mainActivity;

    ListView listView;
    FloatingActionButton saveButton;

    String name;
    String number;

    public static List<Integer> choicedPersonPositions = new ArrayList<>();
    public static List<String> choicedPersonNumbers = new ArrayList<>();
    public static List<String> choicedPersonNames = new ArrayList<>();
    List<Person> mContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getSupportActionBar().setTitle("Contacts");


        listView = (ListView) findViewById(R.id.list_view);
        saveButton = (FloatingActionButton) findViewById(R.id.save_button);

        mContacts = new ArrayList<>();
        mainActivity = new MainActivity();


        final Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mContacts.add(new Person(name, number));

        }
        cursor.close();


        final ListViewCustomAdapter adapter = new ListViewCustomAdapter(getApplicationContext(), mContacts);

        choicedPersonNumbers.clear();
        choicedPersonNames.clear();
        choicedPersonPositions.clear();


        saveButton.setOnClickListener(this);

        for(int i=0 ; i < mContacts.size() ; i++){

            mContacts.get(i).setSelected(false);

        }

        listView.setAdapter(adapter);

    }


    //-------------------------------- SAVE BUTTON EVENTS ------------------------------------------
    private void saveButtonEvent(){

        for (int i = 0; i < mContacts.size(); i++) {

            if (mContacts.get(i).isSelected()) {

                choicedPersonPositions.add(i);

            }

        }

        for (int i = 0; i < choicedPersonPositions.size(); i++) {

            choicedPersonNumbers.add(mContacts.get(choicedPersonPositions.get(i)).getNumber());
            choicedPersonNames.add(mContacts.get(choicedPersonPositions.get(i)).getName());


        }

        if(choicedPersonNumbers.size() != 0){
            Toast.makeText(getApplicationContext(), "Contacts have added to list", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"No one has been chosen!",Toast.LENGTH_SHORT).show();
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("name", (Serializable) choicedPersonNames);
        returnIntent.putExtra("number", (Serializable) choicedPersonNumbers);
        setResult(Activity.RESULT_OK,returnIntent);



        finish();


    }


    //------------------------------------- CLICK EVENTS -------------------------------------------
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_button:
                saveButtonEvent();
                break;

            default:

                break;

        }
    }


}
