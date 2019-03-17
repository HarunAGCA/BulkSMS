package com.example.harun.bulksms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.harun.bulksms.Fragment.FragmentDeliveredPersons;
import com.example.harun.bulksms.Fragment.FragmentMessage;

/**
 * Created by HARUN on 27.04.2018.
 */

public class FragmentPageAdapter extends FragmentStatePagerAdapter {

    int tabCount;


    public FragmentPageAdapter(FragmentManager fragmentManager,int tabCount){

        super(fragmentManager);
        this.tabCount = tabCount;


    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                FragmentMessage fragmentMessagge = new FragmentMessage();
                return fragmentMessagge;
            case 1:
                FragmentDeliveredPersons fragmentDeliveredPersons = new FragmentDeliveredPersons();
                return fragmentDeliveredPersons;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return tabCount;
    }


}


