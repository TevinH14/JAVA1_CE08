// Tevin Hamilton
// JAV1 2001
//MainActivity.java


package com.example.hamiltontevin_ce08;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mainListView;
    private EditText mainText;
    private ArrayList<String> textList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton;

        mainListView = findViewById(R.id.lv_mainListView);
        mainText = findViewById(R.id.et_inputText);
        mainButton = findViewById(R.id.btn_Submit);

        mainButton.setOnClickListener(addTextToListView);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputText",mainText.getText().toString());
        outState.putStringArrayList("enterText",textList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mainText.setText(savedInstanceState.getString("inputText"));
        textList = savedInstanceState.getStringArrayList("enterText");
        SetUpListView();
    }

    private final View.OnClickListener addTextToListView = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(mainText.getText().length() == 0){
                Toast.makeText(MainActivity.this, R.string.empty,Toast.LENGTH_SHORT).show();

            }else if( mainText.getText().toString().trim().length() <= 0){
                Toast.makeText(MainActivity.this, R.string.spaces,Toast.LENGTH_SHORT).show();
            }else {
                textList.add(mainText.getText().toString());
                mainText.setText("");
                SetUpListView();
            }

        }
    };

    private void SetUpListView(){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview_items, R.id.name_listView_item,textList);
        mainListView.setAdapter(adapter);
    }
}
