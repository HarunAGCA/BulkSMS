package com.example.harun.bulksms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harun.bulksms.Model.Person;
import com.example.harun.bulksms.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by HARUN on 27.04.2018.
 */

public class ListViewCustomAdapter extends BaseAdapter {

    Context context;
    List<Person> personList;
    LayoutInflater mInflater;

    public ListViewCustomAdapter(Context context, List personList) {
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.personList = personList;
    }


    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View satirView;

        satirView = mInflater.inflate(R.layout.activity_row, null);

        TextView name = satirView.findViewById(R.id.name);
        TextView number = satirView.findViewById(R.id.number);
        ImageView icon = satirView.findViewById(R.id.icon);
        final CheckBox checkBox = satirView.findViewById(R.id.checkbox);

        name.setText(personList.get(position).getName());
        number.setText(personList.get(position).getNumber());

        icon.setImageResource(R.drawable.profile_icon);


        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                return person.getName().compareTo(t1.getName());
            }
        });


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (checkBox.isChecked()) {

                    personList.get(position).setSelected(true);

                } else {

                    personList.get(position).setSelected(false);

                }
            }

        });


        return satirView;
    }


}
