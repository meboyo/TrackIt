package com.example.todd.trackitcoach;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;


public class RunEventActivity extends ActionBarActivity implements StdInfoFragment.OnFragmentInteractionListener {
 App_Manger ManApp;
    int AtheIndex;
    CheckBox Form;
    CheckBox Breathing;
    CheckBox Hunger;
    CheckBox Tired;
    TextView AtheName;
    EditText Datareport;
    Athelete[] Athes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_event);
        ManApp = getIntent().getParcelableExtra("AppMan");
        TextView Eventname = (TextView) findViewById(R.id.textViewEventName);

        Eventname.setText(ManApp.Eve.Name);
        AtheIndex =0;
        Athes = ManApp.Eve.Athe;
        SetUpInfoLayout();

        SetUpButtons();

       // NextPageInformationSetUp(0);

        AtheName.setText(Athes[AtheIndex].Name);
        Form.setChecked(Athes[AtheIndex].Form);
        Breathing.setChecked(Athes[AtheIndex].Form);
        Hunger.setChecked(Athes[AtheIndex].Hungry);
        Tired.setChecked(Athes[AtheIndex].Tired);
    }

    void NextPageInformationSetUp(int count)
    {

        Athes[AtheIndex].Form= Form.isChecked();
       Athes[AtheIndex].Breathing =  Breathing.isChecked();
        Athes[AtheIndex].Hungry = Hunger.isChecked();
       Athes[AtheIndex].Tired =  Tired.isChecked();
       Athes[AtheIndex].Report =  Datareport.getText().toString();
        AtheIndex+= count;
        if(AtheIndex>= Athes.length)
            AtheIndex=0;
else if(AtheIndex < 0)
            AtheIndex = Athes.length-1;
        //Setting Up information for next person
        AtheName.setText(Athes[AtheIndex].Name);
        Form.setChecked(Athes[AtheIndex].Form);
        Breathing.setChecked(Athes[AtheIndex].Breathing);
        Hunger.setChecked(Athes[AtheIndex].Hungry);
        Tired.setChecked(Athes[AtheIndex].Tired);
        Datareport.setText(Athes[AtheIndex].Report);
    }
    void SetUpInfoLayout()
    {
        Form = (CheckBox) findViewById(R.id.checkBoxForm);
        Breathing = (CheckBox) findViewById(R.id.checkBoxBreath);
        Hunger = (CheckBox) findViewById(R.id.checkBoxHunger);
        Tired = (CheckBox) findViewById(R.id.checkBoxTired);
        AtheName = (TextView) findViewById(R.id.textViewAthlete);
        Datareport = (EditText)findViewById(R.id.editTextDataLog);
        Datareport.setText("");
    }
    void SetUpButtons()
    {
        boolean Timed = ManApp.Eve.IsTimed();

    final    EditText Dis = (EditText) findViewById(R.id.editTextHeight);
      final  Chronometer Timer = (Chronometer)findViewById(R.id.chronometerTimer);
        //Buttons for both Events
      //  Button AddNote = (Button) findViewById(R.id.buttonAddNote);
        Button EndEvent = (Button) findViewById(R.id.buttonEndEvent);
        Button PrevBut = (Button) findViewById(R.id.buttonPrev);
        Button NextBut = (Button) findViewById(R.id.buttonNext);

        // For non Timed Events
        Button EnterHeight = (Button) findViewById(R.id.buttonHeight);

        //ForTimedEvents
        final Button StartTime = (Button) findViewById(R.id.buttonStartTIme);
        Button AddLap = (Button) findViewById(R.id.buttonLap);
        final Button EndTime = (Button) findViewById(R.id.buttonEndTime);
        Button ResetTime = (Button) findViewById(R.id.buttonResetTimer);



        //Sections Enabled or Disabled and setting
        if(Timed)
        {
        //Disabled UNused
            Dis.setEnabled(false);
            EnterHeight.setEnabled(false);
            EndTime.setEnabled(false);
            StartTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Timer.start();
                    StartTime.setEnabled(false);
                    EndTime.setEnabled(true);
                }
            });
            EndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Timer.stop();
                    StartTime.setEnabled(true);
                    EndTime.setEnabled(false);
                }
            });
            AddLap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 String Information= (String) ("Lap: "+ Timer.getText()+"\n");
                    Datareport.setText(Datareport.getText() + Information);
                }
            });
            ResetTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Timer.stop();
                    Timer.setText(0);
                    StartTime.setEnabled(true);
                    EndTime.setEnabled(false);
                }
            });

        }else
        {
            //Disabled Unused
            Timer.setEnabled(false);
            StartTime.setEnabled(false);
            AddLap.setEnabled(false);
            EndTime.setEnabled(false);
            ResetTime.setEnabled(false);

EnterHeight.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String Information = (String) Datareport.getText().toString();
        Information +="Distance: "+ Dis.getText() +"\n";
        Dis.setText("");
        Datareport.setText(Information);
    }
});

        }
        // Generic Setting
        NextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextPageInformationSetUp(1);
            }
        });
        PrevBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextPageInformationSetUp(-1);
            }
        });




    }


public void SetEventWidgets()
{

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_run_event, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
