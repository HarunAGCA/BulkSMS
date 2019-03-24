package com.example.harun.bulksms.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.harun.bulksms.R;


public class FragmentDeliveredPersons extends Fragment {

    ListView lvDelivered;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_delivered_persons,container,false);

        lvDelivered = view.findViewById(R.id.lvDelivered);

        return view;
    }
}
