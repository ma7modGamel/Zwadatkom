package com.safwa.zawadatkm_user.map;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.CreateNewAddressModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ActivityMapsBinding;
import com.safwa.zawadatkm_user.home.MainActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    private GoogleMap mMap;
    ActivityMapsBinding binding;
    GlobalPrefrencies globalPrefrencies;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 5445;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;
    private Location currentLocation;
    private boolean firstTimeFlag = true;
    MapViewModel mapViewModel;
    String fromaddress;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_maps);
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        binding.setMapViewModel(mapViewModel);
        globalPrefrencies = new GlobalPrefrencies(getApplicationContext());
        Utils.setLocale(getApplicationContext(), globalPrefrencies.getLanguage());


        if(getIntent().getExtras()!=null) {
            fromaddress = getIntent().getExtras().getString("fromaddress");
        }else {
            fromaddress="no";
        }

        setupCartAdress();
        //binding.setOnClickListener(clickListener);
        MapsInitializer.initialize(getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.idSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = binding.idSearchView.getQuery().toString();

                // below line is to create a list of address
                // where we will store the list of all address.
                List<Address> addressList = null;

                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        Log.e("AAA", e.getMessage());
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    mMap.clear();
                    if (addressList != null && addressList.size() > 0) {

                        Address address = addressList.get(0);

                        // on below line we are creating a variable for our location
                        // where we will add our locations latitude and longitude.
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        // on below line we are adding marker to that position.
                        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin)).position(latLng).title("موقعك الحالى"));

                        // below line is to animate camera to that position.
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        getAddress(MapsActivity.this, address.getLatitude(), address.getLongitude());
                    } else {
                        Toast.makeText(MapsActivity.this, "لا يمكن الحصول علي منطقة بالاسم المطلوب", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        binding.buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zzz", "ASD");
                googleMap.clear();
                // Animating to the touched position
                if(currentLocation!=null || currentLocation!=null) {
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
                    animateCamera(currentLocation);
                    // Placing a marker on the touched position
                    googleMap.addMarker(markerOptions);
                    getAddress(MapsActivity.this, currentLocation.getLatitude(), currentLocation.getLongitude());
                }else {
                     final LocationCallback mLocationCallback = new LocationCallback() {

                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            if (locationResult.getLastLocation() == null)
                                return;
                            currentLocation = locationResult.getLastLocation();
                            if (firstTimeFlag && googleMap != null) {
                                animateCamera(currentLocation);
                                firstTimeFlag = false;
                                getAddress(MapsActivity.this, currentLocation.getLatitude(), currentLocation.getLongitude());

                            }
                            showMarker(currentLocation);


                        }
                    };
                }
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setupCartAdress() {
        item1select();
        binding.adressLay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1select();
                item2Notselect();
                item3Notselect();
            }
        });
        binding.addressLay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item2select();
                item1Notselect();
                item3Notselect();
            }
        });


        binding.adressLay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item3select();
                item1Notselect();
                item2Notselect();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void item1select() {

        binding.txt1.setTextColor(getColor(R.color.white));
        binding.img1.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.MULTIPLY);
        binding.adressLay1.setBackgroundResource(R.drawable.textcardgreen);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void item2select() {

        binding.txt2.setTextColor(getColor(R.color.white));
        binding.img2.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.MULTIPLY);
        binding.addressLay2.setBackgroundResource(R.drawable.textcardgreen);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void item3select() {

        binding.txt3.setTextColor(getColor(R.color.white));
        binding.img3.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.MULTIPLY);
        binding.adressLay3.setBackgroundResource(R.drawable.textcardgreen);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void item1Notselect() {

        binding.txt1.setTextColor(getColor(R.color.black));
        binding.img1.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.MULTIPLY);
        binding.adressLay1.setBackgroundResource(R.drawable.edittextcardgraygreen);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void item2Notselect() {

        binding.txt2.setTextColor(getColor(R.color.black));
        binding.img2.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.MULTIPLY);
        binding.addressLay2.setBackgroundResource(R.drawable.edittextcardgraygreen);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void item3Notselect() {

        binding.txt3.setTextColor(getColor(R.color.black));
        binding.img3.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.MULTIPLY);
        binding.adressLay3.setBackgroundResource(R.drawable.edittextcardgraygreen);

    }


    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status)
            return true;
        else {
            if (googleApiAvailability.isUserResolvableError(status))
                Toast.makeText(this, "Please Install google play services to use this application", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.googleMap = googleMap;
        // Add a marker in Sydney and move the camera
        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();
                // Setting the position for the marker
                markerOptions.position(latLng);
                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title("موقع اخر");
                // Clears the previously touched position
                googleMap.clear();
                // Animating to the touched position
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                // Placing a marker on the touched position
                googleMap.addMarker(markerOptions);
                getAddress(MapsActivity.this, latLng.latitude, latLng.longitude);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isGooglePlayServicesAvailable()) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            startCurrentLocationUpdates();
        }
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        if (mGoogleApiClient != null &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            Log.e("erroorResume1",ex.getMessage()+"");
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            Log.e("erroorResume2",ex.getMessage()+"");
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            boolean finalGps_enabled = gps_enabled;
            boolean finalNetwork_enabled = network_enabled;
            new AlertDialog.Builder(this)
                    .setMessage("تحديد الموقع الجغرافي غير مفعل هل تريد تفعيله ؟")
                    .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            MapsActivity.this.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            if (finalGps_enabled && finalNetwork_enabled) {
                                animateCamera(currentLocation);
                            }
                        }
                    })
                    .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double speed = location.getSpeed(); //spedd in meter/minute
        speed = (speed * 3600) / 1000;      // speed in km/// minute
        // Toast.makeText(GraphViews.this, "Current speed:" + location.getSpeed(),Toast.LENGTH_SHORT).show();


        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
        getAddress(MapsActivity.this, location.getLatitude(), location.getLongitude());

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setSmallestDisplacement(0.1F); //added
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); //changed
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(MapsActivity.this, "" + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    private final LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult.getLastLocation() == null)
                return;
            currentLocation = locationResult.getLastLocation();
            if (firstTimeFlag && googleMap != null) {
                animateCamera(currentLocation);
                firstTimeFlag = false;
                getAddress(MapsActivity.this, currentLocation.getLatitude(), currentLocation.getLongitude());

            }
            showMarker(currentLocation);


        }
    };

    private void startCurrentLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                return;
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }            else if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startCurrentLocationUpdates();
        }
    }

    private void animateCamera(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));

    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(16).build();
    }

    private void showMarker(@NonNull Location currentLocation) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        if (currentLocationMarker == null) {
            Log.e("LL", googleMap + " 1112 ");
            currentLocationMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin)).position(latLng).title("موقعك الحالى"));
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    MarkerOptions markerOptions = new MarkerOptions();

                    // Setting the position for the marker
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin)).position(latLng).title("موقعك الحالى");
                    // Clears the previously touched position
                    googleMap.clear();

                    // Animating to the touched position
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                    // Placing a marker on the touched position
                    googleMap.addMarker(markerOptions);
                    getAddress(MapsActivity.this, latLng.latitude, latLng.longitude);

                }
            });

        } else {
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fusedLocationProviderClient != null)
            fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {

//Set Address
        try {
            String language = globalPrefrencies.getLanguage();
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
            Geocoder geocoder = new Geocoder(context, locale);
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {


                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL


                binding.tct.setText(country);
                binding.city.setText(city + " , " + state +" , "+knownName);

                binding.FinishMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mapViewModel.onClickAddNewAddress(LATITUDE + "", LONGITUDE + "", country, city, address, 1 + "", context);
                        mapViewModel.userAddAddressModelMutableLiveData.observe(MapsActivity.this, new Observer<CreateNewAddressModel>() {
                            @Override
                            public void onChanged(CreateNewAddressModel createNewAddressModel) {

                                    globalPrefrencies.setIsFirstOpeen(false);
                                    globalPrefrencies.storeLoginStatus(true);
                                    globalPrefrencies.storeAddress(new Gson().toJson(addresses.get(0).getAddressLine(0)).toString());
                                    Toast.makeText(MapsActivity.this, "" + createNewAddressModel.getMessage(), Toast.LENGTH_SHORT).show();

                                    if (fromaddress.equals("yes")) {
                                        finish();
                                    } else {
                                        Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                        finish();
                                    }

                            }
                        });

                    }
                });
                Log.e(TAG, "getAddress:  address" + new Gson().toJson(addresses.get(0).getFeatureName()));


            }
        } catch (IOException e) {
            Log.e("SSSAS", e.getMessage() + " ");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fusedLocationProviderClient = null;
        googleMap = null;
    }
}


