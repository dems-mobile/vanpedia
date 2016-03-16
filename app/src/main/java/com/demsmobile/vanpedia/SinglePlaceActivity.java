package com.demsmobile.vanpedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.demsmobile.vanpedia.places.GooglePlaces;
import com.demsmobile.vanpedia.places.Place;
import com.demsmobile.vanpedia.places.PlaceDetails;
import com.demsmobile.vanpedia.service.LocationService;
import com.demsmobile.vanpedia.util.AlertManager;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SinglePlaceActivity extends FragmentActivity implements OnMapReadyCallback {

    AlertManager alert = new AlertManager();
    GooglePlaces googlePlaces;
    PlaceDetails placeDetails;
    ProgressDialog pDialog;
    public static String KEY_REFERENCE = "reference"; // id of the place
    ImageButton dialBtn;
    TextView numTxt;
    private GoogleMap googleMap;
    Location location;
    Double lat;
    Double lgn;
    private static final float MIN_DISTANCE = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_place);

        Intent i = getIntent();
        String reference = i.getStringExtra(KEY_REFERENCE);

        new LoadSinglePlaceDetails().execute(reference);

        dialBtn = (ImageButton) findViewById(R.id.callButton);
        numTxt = (TextView) findViewById(R.id.phone);
        dialBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (numTxt != null) {
                        startActivity(new Intent(Intent.ACTION_CALL,
                                Uri.parse("tel:" + numTxt.getText())));
                    } else if (numTxt != null && numTxt.getText().length() == 0) {
                        Toast.makeText(getApplicationContext(),
                                "You missed to type the number!", Toast.LENGTH_SHORT).show();
                    } else if (numTxt != null &&
                            numTxt.getText().length() < 10) {
                        Toast.makeText(getApplicationContext(), "Error, feature not available", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("DialerAppActivity", "error: " +
                            e.getMessage(), e);//Runtime error will be logged
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap){

            LatLng marker = new LatLng(lat, lgn);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker, MIN_DISTANCE);
            googleMap.addMarker(new MarkerOptions().position(marker).title("Destination"));
            googleMap.moveCamera(cameraUpdate);

        if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
    }

    /**
     * Background Async Task to Load Google places
     * */
    class LoadSinglePlaceDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SinglePlaceActivity.this);
            pDialog.setMessage("Loading profile ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        /**
         * getting Profile JSON
         * */
        protected String doInBackground(String... args) {
            String reference = args[0];

            // creating Places class object
            googlePlaces = new GooglePlaces();

            // Check if used is connected to Internet
            try {
                placeDetails = googlePlaces.getPlaceDetails(reference);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {

            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed Places into LISTVIEW
                     * */
                    if (placeDetails != null) {
                        String status = placeDetails.status;

                        if (status.equals("OK")) {
                            if (placeDetails.result != null) {
                                String name = placeDetails.result.name;
                                String address = placeDetails.result.formatted_address;
                                String phone = placeDetails.result.formatted_phone_number;
                                lat = placeDetails.result.geometry.location.lat;
                                lgn = placeDetails.result.geometry.location.lng;


                                TextView lbl_name = (TextView) findViewById(R.id.name);
                                TextView lbl_address = (TextView) findViewById(R.id.address);
                                TextView lbl_phone = (TextView) findViewById(R.id.phone);

                                name = name == null ? "Not present" : name; // if name is null display as "Not present"
                                address = address == null ? "Not present" : address;
                                phone = phone == null ? "Not present" : phone;

                                lbl_name.setText(name);
                                lbl_address.setText(address);
                                lbl_phone.setText(Html.fromHtml("<b>Phone:</b> " + phone));

                            }


                        } else if (status.equals("ZERO_RESULTS")) {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Near Places",
                                    "Sorry no place found.",
                                    false);
                        } else if (status.equals("UNKNOWN_ERROR")) {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry unknown error occured.",
                                    false);
                        } else if (status.equals("OVER_QUERY_LIMIT")) {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry query limit to google places is reached",
                                    false);
                        } else if (status.equals("REQUEST_DENIED")) {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry error occured. Request is denied",
                                    false);
                        } else if (status.equals("INVALID_REQUEST")) {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry error occured. Invalid Request",
                                    false);
                        } else {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry error occured.",
                                    false);
                        }
                    } else {
                        alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                "Sorry error occured.",
                                false);
                    }

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(SinglePlaceActivity.this);

                }
            });

        }

    }

}
