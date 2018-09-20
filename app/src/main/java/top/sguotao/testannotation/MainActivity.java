package top.sguotao.testannotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import annotation.sguotao.top.testannotationprocessor.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
