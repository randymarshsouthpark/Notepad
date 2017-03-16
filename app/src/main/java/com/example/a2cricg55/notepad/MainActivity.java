package com.example.a2cricg55.notepad;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumode, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String path_to_storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        EditText noteEditText = (EditText) findViewById(R.id.theedittext);

        if (item.getItemId() == R.id.load) {
            BufferedReader buff = null;

            try {

                buff = new BufferedReader(new FileReader(path_to_storage + "/notedpad.txt"));
                String line = "";
                while ((line = buff.readLine()) != null) {
                    System.out.println(line);
                    noteEditText.setText(noteEditText.getText() + line);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return true;
        } else if (item.getItemId() == R.id.save) {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileWriter(path_to_storage + "/notedpad.txt", false));
                pw.println(noteEditText.getText());
                pw.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }
}

