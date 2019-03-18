package com.example.harun.bulksms.Fragment;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.harun.bulksms.Activity.Contacts;
import com.example.harun.bulksms.Activity.MainActivity;
import com.example.harun.bulksms.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HARUN on 24.04.2018.
 */



public class FragmentMessage extends Fragment {

    public EditText messageBox;

    FloatingActionButton sendMessage;
    FloatingActionButton addPerson;

    ArrayAdapter<String> arrayAdapter;

    FragmentDeliveredPersons fragmentDeliveredPersons;

    Spinner spinner;

    List<String> selectedPeopleNames;
    List<String> selectedPeopleNumbers;

    String message = "";
    String [] a;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_message, container, false);

        messageBox = view.findViewById(R.id.message);

        sendMessage = view.findViewById(R.id.send_message);
        addPerson = view.findViewById(R.id.add_person);

        spinner = view.findViewById(R.id.spinnerChoosedPeople);

        selectedPeopleNames = new ArrayList<>();
        selectedPeopleNumbers = new ArrayList<>();

        a = new String[1];

        a[0] = ""+ selectedPeopleNumbers.size()+ " " + "People selected. Touch long to see.";

        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, a);
        spinner.setAdapter(arrayAdapter);

        fragmentDeliveredPersons = new FragmentDeliveredPersons ();

        spinner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                buildDialog("Selected People",selectedPeopleNames.toArray(new CharSequence[selectedPeopleNames.size()]));
                return false;
            }
        });


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    message = messageBox.getText().toString();

                    if (selectedPeopleNumbers.size() != 0) {

                        messageBox.setText("");

                        for (int i = 0; i < selectedPeopleNumbers.size(); i++) {
                            sendSMS(selectedPeopleNumbers.get(i), message);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Phone number can not empty!", Toast.LENGTH_LONG).show();
                    }

                    Log.d("debug", "before"+selectedPeopleNames.size());
                    Log.d("debug", "before"+selectedPeopleNumbers.size());
                    selectedPeopleNames.clear();
                    selectedPeopleNumbers.clear();
                    a[0] = ""+selectedPeopleNumbers.size()+ " " + "People selected. Touch long to see.";
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("debug", "after"+selectedPeopleNames.size());
                    Log.d("debug", "after"+selectedPeopleNumbers.size());




                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString() + "An unexcepted error has occoured!", Toast.LENGTH_LONG).show();
                }
            }
        });


        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(getActivity(), Contacts.class), 1);


            }
        });

        return view;
    }

    public void sendSMS(final String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(), 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(getActivity(), 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        getActivity().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getActivity(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getActivity(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getActivity(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getActivity(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getActivity(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        getActivity().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getActivity(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getActivity(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);


    }

    //-------------------------------- DIALOG BUILDING -----------------------------------------
    private void buildDialog(String title, CharSequence[] choosedPeople) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setNegativeButton("OK", null);
        builder.setItems(choosedPeople, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            Log.d("debug", "requestCode = " + requestCode);
            if (resultCode == Activity.RESULT_OK){
                Log.d("debug", "result_ok");
                selectedPeopleNames = (List<String>) data.getSerializableExtra("name");
                selectedPeopleNumbers = (List<String>) data.getSerializableExtra("number");
                a[0] = ""+ selectedPeopleNumbers.size()+ " " + "People selected. Touch long to see.";
                arrayAdapter.notifyDataSetChanged();
            }else {
                Log.d("debug", "else");
                selectedPeopleNames.clear();
                selectedPeopleNumbers.clear();
                a[0] = ""+ selectedPeopleNumbers.size()+ " " + "People selected. Touch long to see.";
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }
}

