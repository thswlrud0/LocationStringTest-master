package net.daum.www.locationstringtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class page1 extends AppCompatActivity {
    public static  Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, page1.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

    }
}
