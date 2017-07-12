package com.mirkomantovani.matrixcalculatorwear;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //ImageView iv1;

/*
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
*/
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //iv1 = findViewById(R.id.imageView1);     wtf.. i have to create another xml and another class, this ones get me crazy


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send an email to the developer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"mirkomantovani23@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Mirko," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(this,"No settings for now",
                    Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFirstImageClick(){
        Toast.makeText(this,"Solve every matrix in just a few clicks :D",
                Toast.LENGTH_LONG).show();
    }
    public void onSecondImageClick(){
        Toast.makeText(this,"Find eigenvalues,determinant and rank",
                Toast.LENGTH_LONG).show();
    }
    public void onThirdImageClick(){
        Toast.makeText(this,"This is how the app looks like on a Moto 360",
                Toast.LENGTH_LONG).show();
    }
}
