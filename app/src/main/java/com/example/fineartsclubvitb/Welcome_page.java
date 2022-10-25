package com.example.fineartsclubvitb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Welcome_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_section,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.upcoming:
                Intent intent = new Intent(getApplicationContext(),Upcoming_events.class);
                startActivity(intent);
                return true;

            case R.id.past:
                Intent intent1 = new Intent(getApplicationContext(),Past_events.class);
                startActivity(intent1);
                return true;

            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(Welcome_page.this);
                builder.setTitle("Alert !");
                builder.setMessage("Do you want to exit?");
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(1);
                    }
                });
                builder.setCancelable(false);
                builder.show();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }
}
