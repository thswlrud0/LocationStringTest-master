package net.daum.www.locationstringtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class imageView extends AppCompatActivity {

    private Button mNextbutton;
    ViewFlipper flipper;


    public static  Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, imageView.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        flipper = (ViewFlipper) findViewById(R.id.flipper);


        Animation showln = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        flipper.setInAnimation(showln);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);

        mNextbutton = (Button) findViewById(R.id.nextpage);
        mNextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = Location.newIntent(imageView.this);
                startActivity(intent);
            }
        });

    }


    public void mOnClick(View v) {

        switch ( v.getId() ) {
            case R.id.btn_previous:
                flipper.showPrevious();
                break;
            case R.id.B_next:
                flipper.showNext();
                break;

        }
    }


}
