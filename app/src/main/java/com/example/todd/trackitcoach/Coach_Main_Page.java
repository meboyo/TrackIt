package com.example.todd.trackitcoach;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class Coach_Main_Page extends ActionBarActivity implements Frag_Event_Conatiner.OnFragmentInteractionListener {

    Button Rev_Butt;
    Button Track_Butt;
    App_Manger ManApp;
    Frag_Event_Conatiner EList;
    //frag_Track_Athlete_Conatiner AList=null;
    ListView AList;
    ArrayAdapter<String> adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ToDo Make List for fragment
        ManApp = new App_Manger();


       EList = new Frag_Event_Conatiner();

        Bundle bund = new Bundle();
        bund.putStringArray("EvesNames", ManApp.EventsNames);
        EList.setArguments(bund);
        getFragmentManager().beginTransaction().add(R.id.Event_Container,EList).commit();

        setContentView(R.layout.activity_coach__main__page);
        SetUpButtons();
    }

    public void OpenTrackPage()
    {
        Intent giveAppMan = new Intent(Coach_Main_Page.this, Review_Page.class);
        giveAppMan.putExtra("AppMan", ManApp);
        startActivity(giveAppMan);
    }

    // No Need for extra information here
    public void OpenIndepthReview(String Name)
    {
        Intent giveAthelteName = new Intent(Coach_Main_Page.this, Review_Page.class);
        giveAthelteName.putExtra("TypeOfReport","InDepth");
        giveAthelteName.putExtra("AppMan",ManApp);
        giveAthelteName.putExtra("Athelet",Name);
        startActivity(giveAthelteName);

    }
    public void OpenReviewPage()
    {
        if(ManApp.Eve==null)
        SetEvent("All");
        Intent giveEventName = new Intent(Coach_Main_Page.this, Review_Page.class);

        giveEventName.putExtra("Name","All");
        giveEventName.putExtra("TypeOfReport","OverVeiwDepth");
        giveEventName.putExtra("AppMan",ManApp);
        startActivity( giveEventName);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coach__main__page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
public void SetUpButtons()
{
    Rev_Butt = (Button) findViewById(R.id.Review_Button);
    Rev_Butt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            OpenReviewPage();
        }
    });


    Button EventButt = (Button) findViewById(R.id.buttonTracking);
    EventButt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent GotoEvent = new Intent(Coach_Main_Page.this, RunEventActivity.class);

            GotoEvent.putExtra("AppMan",ManApp);
            startActivity(GotoEvent);
        }
    });

}

    public void onFragmentInteraction(Uri uri){

    }
    public void SetUpListForEventContainer(){

    }
    public void SendAMessage(String Message)
    {
        //Todo Add MakeEvent
        SetEvent(Message);
        SetUpAtheleteList();

    }
    public void SetEvent(String EvenName)
    {
        ManApp.Eve = new MeetEvent(EvenName);

    }
    public void SetUpAtheleteList()
    {

        //ToDo Add LoadListFeature
        if(AList == null) {
            AList = (ListView) findViewById(R.id.listViewAethe);
            if (!(AList.hasOnClickListeners())) {
                AList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        OpenIndepthReview(String.valueOf(parent.getItemAtPosition(position)));
                    }
                });
            }
        }
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ManApp.Eve.AtheNames);
        AList.setAdapter(adapt);


    }
}
