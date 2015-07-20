package com.example.todd.trackitcoach;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Todd on 7/16/2015.
 */
public class Athelete implements Parcelable {


    public  boolean Breathing, Form, Hungry, Tired;
    public  String Report,Name,email;
    public float DataforEventMeasuerMent;
    @Override
    public int describeContents() {
        return 0;
    }


    public Athelete()
    {
        Breathing = Form= Hungry=Tired = false;
        Name = "ExampleAthelte";
        Report="";
    }
    public Athelete(String _Name)
    {
        Breathing = Form= Hungry=Tired = false;
        Name = _Name;
        Report = "";
        email="NotAvailable";
    }

    public Athelete(String _email,boolean _Breath, boolean _Form, boolean _Hungry, boolean _Tired, String _Report, String _Name)
    {
        email = _email;
        Breathing = _Breath;
        Form= _Form;
        Hungry= _Hungry;
        Tired = _Tired;
        Report = _Report;
        Name = _Name;

    }
    public static final Parcelable.Creator<  Athelete> CREATOR
            = new Parcelable.Creator< Athelete>() {
        public   Athelete createFromParcel(Parcel in) {
            return new  Athelete(in);
        }

        public  Athelete[] newArray(int size) {
            return new  Athelete[size];
        }
    };
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        boolean[] BAr = {Breathing, Form, Hungry, Tired};

        dest.writeBooleanArray(BAr);
        dest.writeString(Report);
    }
    private  Athelete(Parcel in) {
        Name = in.readString();

        boolean[] BAr = new boolean[4];
        in.readBooleanArray(BAr);
        Breathing= BAr[0];
        Form= BAr[1];
        Hungry=BAr[2];
        Tired=BAr[3];
        Report = in.readString();
    }
}


