package com.example.todd.trackitcoach;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Todd on 7/16/2015.
 */
public class App_Manger implements Parcelable {
    String name;

    public static String[] EventsNames ={"All","60 meters", "100 meters", "200 meters", "400 meters", "800 meters", "1500 meters", "3000 meters", "5000 meters", "10000 meters", "60 meters Hurdles", "100 meters hurdles", "110 meters hurdles", "400  meters hurdles", "3000 meters steeplechase", "4X100 meters", "4X400 meters", "Long Jump", "Triple Jump", "High Jump", "Pole vault Jump", "Shot put throw", "Discus throw", "Hammer throw","Javelin throw"};
public MeetEvent Eve;
    @Override
    public int describeContents() {
        return 0;
    }



    public App_Manger()
    {
        name = "Manager";
Eve = null;
    }
    public void MakeEvent(String _Name)
    {
        Eve = new MeetEvent(_Name);
    }

    public static final Parcelable.Creator< App_Manger> CREATOR
            = new Parcelable.Creator< App_Manger>() {
        public  App_Manger createFromParcel(Parcel in) {
            return new  App_Manger(in);
        }

        public App_Manger[] newArray(int size) {
            return new  App_Manger[size];
        }
    };
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeValue(Eve);
    }

    private  App_Manger(Parcel in) {
        name = in.readString();
        Eve = (MeetEvent) in.readValue(MeetEvent.class.getClassLoader());

    }

}

