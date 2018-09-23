package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.MyApplication3.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToastMessage("Bienvenido bo");
    }



    public void sendMessage(View view){

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        saveFile(message);


        startActivity(intent);


    }


    public void saveFile(String textToSave){

        if (isExternalStorageWritable()) {
            String filename = "myfile";
            String fileContents = "Hello world!";
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(textToSave.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setToastMessage("Los datos fueron grabados");
        }else{
            setToastMessage("No es writable, man");
        }
    }

    public void readFile(View view){
        StringBuffer datax = new StringBuffer("");
        try {
        FileInputStream inputStream = openFileInput("myfile");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readString = bufferedReader.readLine ( ) ;
            while ( readString != null ) {
                datax.append(readString);
                readString = bufferedReader.readLine ( ) ;
            }
            inputStreamReader.close ( ) ;
            setToastMessage(datax.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }






    private void setToastMessage(CharSequence message){

        //OnLoad app greeting message
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message,duration);
        toast.show();




    }




}
