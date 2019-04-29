package com.example.foodroulette;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectActivity extends AppCompatActivity implements LocationListener {
    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    private ArrayList<FoodGenre> mGenre = new ArrayList<>();
    private ArrayList<String> selected = new ArrayList<>();
    private Button mNext;
    private TextView logout;
    private RecyclerView mView2;
    private SelectTypeAdapter typeAdapter;
    private ImageView imageView;
    private CheckBox mcheckbox;
    private DBHelper db;
    ArrayList<String> getSelected;
    private LocationManager locationManager;
    private double[] latlng = new double[2];


    private String REQUEST = "https://maps.googleapis.com/maps/api/place/nearbysearch/jsonz?";

    private int[] images = {R.drawable.fastfood, R.drawable.italian, R.drawable.bbq, R.drawable.chinese};

    private int [] images= {R.drawable.fastfood,R.drawable.italian,R.drawable.bbq,R.drawable.chinese,R.drawable.seafood,R.drawable.vegetarian};

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        db = new DBHelper(getApplicationContext());
        db.getAllUsers();
        imageView = findViewById(R.id.imageView);
        mView2 = findViewById(R.id.recyclerView2);
        mcheckbox = findViewById(R.id.checkBox1);
        logout = findViewById(R.id.logout);
        typeAdapter = new SelectTypeAdapter(mGenre);
        String user = getIntent().getStringExtra("user");


       if(db.getLoggedIn(user)) {
            typeAdapter.setSelected(db.getSelGenre().get(0).split(","));
        }

        Log.v("first value",""+db.getSelected().get(1));
        mNext= findViewById(R.id.next2);
        mView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mView2.setItemAnimator(new DefaultItemAnimator());
        mView2.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mView2.setAdapter(typeAdapter);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncFetch fetch = new AsyncFetch();
                fetch.execute();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
                Intent k = new Intent(SelectActivity.this,LoginActivity.class);
                db.makeQuery("update logins set status = 0 where status = 1");
                startActivity(k);
                finish();
            }
        });



        mGenre.add(new FoodGenre("FastFood",images[0]));
        selected.add(mGenre.get(0).getGenreName());
        mGenre.add(new FoodGenre("Italian", images[1]));
        selected.add(mGenre.get(1).getGenreName());
        mGenre.add(new FoodGenre("BBQ", images[2]));
        selected.add(mGenre.get(2).getGenreName());
        mGenre.add(new FoodGenre("Chinese", images[3]));
        selected.add(mGenre.get(3).getGenreName());
        mGenre.add(new FoodGenre("Seafood",images[4]));
        selected.add(mGenre.get(4).getGenreName());
        mGenre.add(new FoodGenre("Vegetarian",images[5]));
        selected.add(mGenre.get(5).getGenreName());

        typeAdapter.notifyDataSetChanged();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location locaion = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(locaion);
    }

    @Override
    public void onLocationChanged(Location location) {
        latlng[0] = location.getLatitude();
        latlng[1] = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class AsyncFetch extends AsyncTask<ArrayList<Restaurant>, Void, ArrayList<Restaurant>> {

        @Override
        protected ArrayList<Restaurant> doInBackground(ArrayList<Restaurant>... arrayLists) {
            Log.v("Status","Made it to Async");
            RequestTask reqTask = new RequestTask("chinese|itallian", latlng,1000, mRestaurants);
            reqTask.run();
            return mRestaurants;
        }

        @Override
        protected void onPostExecute(ArrayList<Restaurant> res){
            Intent intent = new Intent(SelectActivity.this, HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("restaurants", mRestaurants);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
