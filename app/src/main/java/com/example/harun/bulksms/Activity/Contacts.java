package com.example.harun.bulksms.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.harun.bulksms.Adapter.ListViewCustomAdapter;
import com.example.harun.bulksms.Model.Person;
import com.example.harun.bulksms.R;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {

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

        listView = (ListView) findViewById(R.id.list_view);

        saveButton = (FloatingActionButton) findViewById(R.id.save_button);

        final List<Person> mContacts = new ArrayList<>();

        mainActivity = new MainActivity();


        final Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mContacts.add(new Person(name, number));

        }
        cursor.close();


        final ListViewCustomAdapter adapter = new ListViewCustomAdapter(getApplicationContext(), mContacts);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



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

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        for(int i=0 ; i < mContacts.size() ; i++){

            mContacts.get(i).setSelected(false);

        }

        listView.setAdapter(adapter);

    }


}
