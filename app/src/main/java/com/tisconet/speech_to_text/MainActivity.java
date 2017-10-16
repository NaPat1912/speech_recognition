package com.tisconet.speech_to_text;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.RecognizerResultsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    TextView resultTEXT = (TextView)findViewById(R.id.tvResult);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void onButterCilck(View v){

        if(v.getId() == R.id.imageButton){

            promptSpeechInput();
        }
    }


    private void promptSpeechInput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerResultsIntent.EXTRA_VOICE_SEARCH_RESULT_STRINGS);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT , "Say something!");

        try {
            startActivityForResult(i, 100);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(MainActivity.this, "Sorry! Your device doesn't support speech language!", Toast.LENGTH_LONG).show();

        }
    }

    public void onActivityResult(int request_code, int result_code, Intent i){
    super.onActivityResult(request_code , result_code , i);
        switch(request_code){

            case 100: if(request_code == RESULT_OK && i != null){
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                resultTEXT.setText(result.get(0));
            }
                break;
        }

    }

}
