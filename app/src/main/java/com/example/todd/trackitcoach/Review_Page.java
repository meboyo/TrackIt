package com.example.todd.trackitcoach;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//Todo for OverVeiw Of Current Selected Event and Atheletes in the Event
public class Review_Page extends ActionBarActivity {
    EditText editText;//Wes's Code
    TextView textView;//Wes's Code
App_Manger ManApp;
    String ReviewType,Report,Emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review__page);
        TextView tex = (TextView) findViewById(R.id.textViewToWho);

        EditText EDTEx = (EditText) findViewById(R.id.edittextinput);
        ManApp = getIntent().getParcelableExtra("AppMan");
        ReviewType = getIntent().getStringExtra("TypeOfReport");
        Report="";
        if(ReviewType.compareTo("InDepth")==0)
        {
            Athelete[] Ath= {ManApp.Eve.GetAtheleteByName(getIntent().getStringExtra("Athelet"))};
           SetReports(Ath);
            SetEmails(Ath);
            tex.setText("About "+Ath[0].Name);
        }else
        {
            SetReports(ManApp.Eve.Athe);
            SetEmails(ManApp.Eve.Athe);
            tex.setText("About EveryBody in "+ManApp.Eve.Name);
        }


EDTEx.setText(Report);
    }

    public void SetReports(Athelete[] Athe)
    {

        for(int i=0; i < Athe.length;i++)
        {
            Report += Athe[i].Report;

        }
    }
    public void SetEmails(Athelete[] Athe)
    {
        for(int i=0; i < Athe.length;i++)
        {

            Emails += Athe[i].email;
        }
    }
public void writeMessage(View view)
{
    EditText EDTEx = (EditText) findViewById(R.id.edittextinput);
    Email(EDTEx.getText().toString());
    Toast.makeText(getApplicationContext(), "TempStubWrite "+ EDTEx.getText(), Toast.LENGTH_LONG).show();

    EDTEx.setText("");
}

    /*
    /////Start of Wes's code//////////////////////////////
    public void writeMessage(View view)
    {
        String Message = editText.getText().toString();
        String file_name = "hello_file";
        try {
            FileOutputStream fileOutputStream;
            fileOutputStream = openFileOutput(file_name, MODE_PRIVATE | MODE_APPEND);
           // fileOutputStream.
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Message Saved", Toast.LENGTH_LONG).show();
            editText.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readMessage(View view)
    {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("hello_file");
            InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader =  new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(Message +"\n");
            }
            //indian guy for texttoview etc....
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /////////End of Wes's Code////////////////////////
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review__page, menu);
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
    //Todo here is where when sending an email judeges how big the string array is and how informaiton is pass from coach main intent
    private void Email(final String _Report)
    {
        // To send an email name the array emailList

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report:");
                emailIntent.putExtra(Intent.EXTRA_TEXT, _Report);

                emailIntent.setType("message/rfc822");
                startActivityForResult(Intent.createChooser(emailIntent, "Choose mail..."), 0);



    }

}
