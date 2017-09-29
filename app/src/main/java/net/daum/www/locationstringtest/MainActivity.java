package net.daum.www.locationstringtest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.FloatProperty;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<UserModel> result;
    private UserAdapter mAdapter;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private static final int REQUEST_ERROR = 0;
    private static final String TAG = "locationStringTest";

    private GoogleApiClient mClient;
    private mLocationModel mLocationModel = new mLocationModel();

    private TextView mEmptyText;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        mEmptyText = (TextView)findViewById(R.id.text_no_data);
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("users");
        result = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.user_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(llm);

        mAdapter = new UserAdapter(result);
        mRecyclerView.setAdapter(mAdapter);

        updateList();
        checkIfEmpty();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                removeUser(item.getGroupId());
                break;

            case 1:
             //   removeLocation(item.getGroupId());

                //changeUser(item.getGroupId());

                break;
        }
        return super.onContextItemSelected(item);
    }

    private void updateList() {

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(UserModel.class));
                mAdapter.notifyDataSetChanged();

                checkIfEmpty();
            }
//
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
/*
                UserModel model = dataSnapshot.getValue(UserModel.class);
                int index = getItemIndex(model);

                result.set(index, model);

                mAdapter.notifyItemChanged(index);
*/
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                UserModel model = dataSnapshot.getValue(UserModel.class);
                int index = getItemIndex(model);

                result.remove(index);
                mAdapter.notifyItemRemoved(index);
                checkIfEmpty();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(UserModel user) {
        int index = -1;

            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).key.equals(user.key)) {
                    index = i;
                    break;
                }
            }

        return index;
    }


    private void removeUser(int position){
        mReference.child(result.get(position).key).removeValue();

    }

    /* 따로 만든곳 */
    private void removeLocation(int position){
        UserModel user = result.get(position);

        if(user.location >= mLocationModel.getAltitude()) {
            mReference.child(result.get(position).key).removeValue();
        }
    }

    private void changeUser(int position){
        UserModel user = result.get(position);
        user.age=100;

        Map<String, Object> userValues = user.toMap();
        Map<String, Object> newUser = new HashMap<>();

        newUser.put(user.key, userValues);

        mReference.updateChildren(newUser);
    }

    private void checkIfEmpty(){
        if(result.size() == 0){
            mRecyclerView.setVisibility(View.INVISIBLE);
            mEmptyText.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyText.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_crime_list, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Toast.makeText(this, "new one", Toast.LENGTH_SHORT).show();
                Intent intent = addData.newIntent(MainActivity.this);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume(){
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

    @Override
    public void onStart(){
        super.onStart();

        invalidateOptionsMenu();
        mClient.connect();
    }


    @Override
    public void onStop() {
        super.onStop();

        mClient.disconnect();
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
                .requestLocationUpdates(mClient, request, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.i(TAG, "Got a fix: " + location);
                        // mUserModel.setLocation(" " + location.getAltitude());
                        mLocationModel.setAltitude(location.getAltitude());
                        mLocationModel.setLongtitude(location.getLongitude());
                    }
                });
    }
}

