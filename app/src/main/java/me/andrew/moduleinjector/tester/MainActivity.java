package me.andrew.moduleinjector.tester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.andrew.librarya.IAInterface;
import me.andrew.moduleinjector.ModuleInjector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModuleInjector.get(IAInterface.class).testA();
            }
        });
    }
}