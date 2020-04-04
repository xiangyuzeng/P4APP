package com.xiangyu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et = findViewById(R.id.inputDoneText);
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP){
                    FutureTask<String> futureTask  = new FutureTask<>(new NetWorkCallable(et.getText().toString()));
                    Thread thread = new Thread(futureTask);
                    thread.start();
                    try {
                        String result = futureTask.get();
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(result);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
