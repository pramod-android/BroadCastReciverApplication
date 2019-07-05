package com.example.broadcastreciverapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);




//        Intent intent = new Intent("my.action.string");
//        intent.putExtra("extra", "My string"); // phoneNo is the value to sent
//        sendBroadcast(intent);
    }

    public void SendBroadcast(View view) {
        Intent intent = new Intent("my.action.string");
        intent.putExtra("extra", editText.getText().toString()); // phoneNo is the value to sent
        sendBroadcast(intent);

    }
}
