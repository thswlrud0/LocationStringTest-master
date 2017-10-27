package net.daum.www.locationstringtest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import static android.os.SystemClock.sleep;

/**
 * Created by USER on 2017-09-26.
 */

public class Location extends AppCompatActivity {

    private static final int REQUEST_ERROR = 0;
    private static final String TAG = "locationStringTest";

    private GoogleApiClient mClient;
    private Button mfindbutton;
    private TextView maltitude;
    private TextView mlongitude;
    private findLo mfind = new findLo();

    ViewFlipper mLocationFlipper;
    ImageView mimage1;
    ImageView mimage2;
    ImageView mimage3;
    ImageView mimage4;
    ImageView mimage5;
    ImageView mimage6;
    ImageView mImageView;
    Button mGetimage;




    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, Location.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        invalidateOptionsMenu();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .build();

        mfindbutton = (Button)findViewById(R.id.FIND);
        maltitude = (TextView)findViewById(R.id.altitude);
        mlongitude = (TextView)findViewById(R.id.longitude);


        mimage1= (ImageView)findViewById(R.id.welcome);
        mimage2 = (ImageView)findViewById(R.id.lotte_layout);
        mimage3 = (ImageView)findViewById(R.id.suwon_layout);
        mimage4 = (ImageView)findViewById(R.id.namsan_layout);
        mimage5 = (ImageView)findViewById(R.id.changduk_layout);
        mimage6 = (ImageView)findViewById(R.id.myungdong_layout);
        mimage1.setImageResource(R.drawable.welcome);
        mimage2.setImageResource(R.drawable.lotte);
        mimage3.setImageResource(R.drawable.suwon);
        mimage4.setImageResource(R.drawable.frontpage);
        mimage5.setImageResource(R.drawable.changduk);
        mimage6.setImageResource(R.drawable.myungdong);

        mimage1.setVisibility(View.VISIBLE);
        mimage2.setVisibility(View.INVISIBLE);
        mimage3.setVisibility(View.INVISIBLE);
        mimage4.setVisibility(View.INVISIBLE);
        mimage5.setVisibility(View.INVISIBLE);
        mimage6.setVisibility(View.INVISIBLE);

        mfindbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findImage();
                sleep(1500);
                    Intent intent = MainActivity.newIntent(Location.this);
                    startActivity(intent);

            }

        });







    }




    @Override
    public void onStart() {
        super.onStart();

        invalidateOptionsMenu();
        mClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();

        mClient.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode, this, REQUEST_ERROR,
                    new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {

                            finish();
                        }
                    });
            errorDialog.show();
        }
    }



    private void findImage() {
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setNumUpdates(1);
        request.setInterval(0);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        LocationServices.FusedLocationApi
                .requestLocationUpdates(mClient, request, new LocationListener(){
                    @Override
                    public void onLocationChanged(android.location.Location location) {
                        Log.i(TAG, "Got a fix: " + location);
                        mfind.setAltitude(" "+location.getAltitude());
                        mfind.setLongitude(" "+location.getLongitude());
                        maltitude.setText("Altitude is  "+mfind.getAltitude());
                        mlongitude.setText("Longitude is  "+mfind.getLongitude());

                                double Lati = Double.valueOf(mfind.getAltitude()).doubleValue();
                                double Longi = Double.valueOf(mfind.getLongitude()).doubleValue();

                                if(Lati >= 37.5131525 && Lati <= 37.5133227 && Longi <= 127.1059174 && Longi >=127.1002097) //롯데타워
                                {
                                    mimage1.setVisibility(View.INVISIBLE);
                                    mimage2.setVisibility(View.VISIBLE);
                                    mimage3.setVisibility(View.INVISIBLE);
                                    mimage4.setVisibility(View.INVISIBLE);
                                    mimage5.setVisibility(View.INVISIBLE);
                                    mimage6.setVisibility(View.INVISIBLE);
                                }
                                else if(Lati >= 37.2871646 && Lati <= 37.2882785  && Longi <= 127.0183896 && Longi >=127.0178961)   //수원화성
                                {
                                    mimage1.setVisibility(View.INVISIBLE);
                                    mimage2.setVisibility(View.INVISIBLE);
                                    mimage3.setVisibility(View.VISIBLE);
                                    mimage4.setVisibility(View.INVISIBLE);
                                    mimage5.setVisibility(View.INVISIBLE);
                                    mimage6.setVisibility(View.INVISIBLE);
                                }
                                else if(Lati >= 37.5544012 && Lati <= 37.5507267  && Longi <= 126.9964667 && Longi >=126.9855662)      //남산
                                {
                                    mimage1.setVisibility(View.INVISIBLE);
                                    mimage2.setVisibility(View.INVISIBLE);
                                    mimage3.setVisibility(View.INVISIBLE);
                                    mimage4.setVisibility(View.VISIBLE);
                                    mimage5.setVisibility(View.INVISIBLE);
                                    mimage6.setVisibility(View.INVISIBLE);
                                }
                                else if(Lati >= 37.5774243 && Lati <= 37.5860288 && Longi <= 126.9923734 && Longi >=126.9894551 )       //창덕궁
                                {
                                    mimage1.setVisibility(View.INVISIBLE);
                                    mimage2.setVisibility(View.INVISIBLE);
                                    mimage3.setVisibility(View.INVISIBLE);
                                    mimage4.setVisibility(View.INVISIBLE);
                                    mimage5.setVisibility(View.VISIBLE);
                                    mimage6.setVisibility(View.INVISIBLE);
                                }
                                else if//(Lati >= 37.5609581 && Lati <= 37.5658482 && Longi <= 126.9863879 && Longi >=126.9863772)        //명동
                                        (Lati >= 280.0 && Lati <= 300 && Longi <= -80 && Longi >=-90)
                                {
                                    mimage1.setVisibility(View.INVISIBLE);
                                    mimage2.setVisibility(View.INVISIBLE);
                                    mimage3.setVisibility(View.INVISIBLE);
                                    mimage4.setVisibility(View.INVISIBLE);
                                    mimage5.setVisibility(View.INVISIBLE);
                                    mimage6.setVisibility(View.VISIBLE);
                                }
                                else                                                                                                    //타이틀이미지
                                {
                                    mimage1.setVisibility(View.VISIBLE);
                                    mimage2.setVisibility(View.INVISIBLE);
                                    mimage3.setVisibility(View.INVISIBLE);
                                    mimage4.setVisibility(View.INVISIBLE);
                                    mimage5.setVisibility(View.INVISIBLE);
                                    mimage6.setVisibility(View.INVISIBLE);
                                }
                            }
                        });


                    }

}
