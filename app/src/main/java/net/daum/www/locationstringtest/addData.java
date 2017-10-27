package net.daum.www.locationstringtest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by thswl on 2017-09-12.
 */

public class addData extends AppCompatActivity {
    //
    private static final int REQUEST_ERROR = 0;

    private EditText mDataContent;
    private EditText mUserName;
    /*
    private Button mAlbumCamera;
    */
    private Button mUploadButton;
   // private ImageView showImage;
    private UserModel mUserModel = new UserModel();
    private CheckBox mCheckBox;     ////////////////////
    //private Button mSetLocation;
    private Switch mSwitch;
    public int mNumber;
    private Uri filePath;

    private static final String TAG2 = "locationStringTest";

    private GoogleApiClient mClient;

    private static final String TAG = "addData";
     private String a = new String();
     private Uri mURI;

    DatabaseReference mDataset = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = mDataset.child("users");
  //  DatabaseReference mDataRefCheck = mDataset.child("Checkbox");

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, addData.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_camera_and_title);

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

        mDataContent = (EditText) findViewById(R.id.data_content);
        mDataContent.setText(" ");
        mUserName = (EditText) findViewById(R.id.inputName);
        mUserName.setText(" ");
        mSwitch = (Switch) findViewById(R.id.switch1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Toast.makeText(addData.this, "체크상태 = " + b, Toast.LENGTH_SHORT).show();
                if(b == true){
                    findImage();
                }else{
                    mUserModel.setLongtitude(0);
                    mUserModel.setLongtitude(0);
                }
            }
        });

        mUploadButton = (Button) findViewById(R.id.dataUploadButton);
        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //업로드//

              //  uploadFile();

                String realText = mDataContent.getText().toString().trim();
                String firstName = mUserName.getText().toString().trim();

                DatabaseReference bindEdit;
                bindEdit = myRef.push();
                bindEdit.child("realText").setValue(realText);
                //왜 하나는 되고 여러개는 멈출까...
                bindEdit.child("firstName").setValue(firstName);
                bindEdit.child("location").setValue(mUserModel.getLocation());
                bindEdit.child("longtitude").setValue(mUserModel.getLongtitude());
                bindEdit.child("age").setValue(0);
                bindEdit.child("key").setValue(bindEdit.getKey());
                bindEdit.child("hate").setValue(0);

                mDataContent.setText("");
                mUserName.setText("");

                mDataContent.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(
                            CharSequence s, int start, int count, int after) {
                        //이 메서드의 실행 코드는 여기서는 필요없음
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //이 메서드의 실행 코드는 여기서 필요 99없음
                    }
                });

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

        }

    @Override
    protected void onStart() {
        super.onStart();

        invalidateOptionsMenu();
        mClient.connect();

    }

    @Override
    protected void onPause() {
        super.onPause();

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
                            //서비스를 사용할 수 없으면 실행을 중단한다.
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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi
                .requestLocationUpdates(mClient, request, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.i(TAG2, "Got a fix: " + location);
                        mUserModel.setLocation(location.getAltitude());
                        mUserModel.setLongtitude(location.getLongitude());
                    }
                });
    }


}
