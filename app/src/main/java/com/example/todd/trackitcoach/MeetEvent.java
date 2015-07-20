package com.example.todd.trackitcoach;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;

/**
 * Created by Todd on 7/16/2015.
 */
public class MeetEvent implements Parcelable {
String Name;
Athelete[] Athe;

    //boolean Time;

    String[] AtheNames;//={"Bill","James","Mikel","James","Bill","James","Mikel"};
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        //  dest.writeValue(Athe);
        dest.writeTypedArray(Athe,0);

    }
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<MeetEvent> CREATOR = new Parcelable.Creator<MeetEvent>() {
        public MeetEvent createFromParcel(Parcel in) {
            return new MeetEvent(in);
        }

        public MeetEvent[] newArray(int size) {
            return new MeetEvent[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    public MeetEvent(Parcel in) {
       Name = in.readString();

        // ap = (App_Manger[])in.readArray(App_Manger.class.getClassLoader());
        Athe = new Athelete[10];
         in.readTypedArray(Athe, Athelete.CREATOR);

}
    public boolean IsTimed()
    {
        return Name.contains(" meters");
    }
    public MeetEvent() {
        // just and exaple for temp
        Athe = new Athelete[10];
        SetListofAtheletes();
    }
    public Athelete GetAtheleteByName(String Name)
    {
        for (int i =0; i < Athe.length;i++ )
        {
            if(Name.compareTo( Athe[i].Name)==0)
                return Athe[i];
        }
        return null;
    }
    public MeetEvent(String _Name)
    {
        Name = _Name;
       SetListofAtheletes();


    }
    public void SetListofAtheletes()
    {
        Athe = new Athelete[10];
        AtheNames = new String[10];
        //ToDo put list of Atheletes here
        for(int i = 0; i < Athe.length; i++)
        {
            Athe[i] = new Athelete(String.valueOf(i)+"Example");
            AtheNames[i]= Athe[i].Name;
        }

    }
    public void AddAthelete(Athelete _NewAthe)
    {
        Athelete[] NewAr = new Athelete[Athe.length +1];

        for(int i = 0; i < Athe.length; i++)
        {
            NewAr[i]= Athe[i];
        }
        NewAr [Athe.length +1]= _NewAthe;
        Athe = NewAr;
    }

}







