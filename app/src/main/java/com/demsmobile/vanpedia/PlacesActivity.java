package com.demsmobile.vanpedia;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.demsmobile.vanpedia.places.JSONfunctions;
import com.demsmobile.vanpedia.places.SearchSingle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class PlacesActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mGoogleApiClient;
    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;


/*-------------------------------------------------------------*/
    protected  String s=null;
    String reference[]=new String[20];
    String keyWord = "restaurant";
/*-------------------------------------------------------------*/


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        //--Snippet
        mGoogleApiClient = new GoogleApiClient
                .Builder( this )
                .enableAutoManage( this, 0, this )
                .addApi( Places.GEO_DATA_API )
                .addApi( Places.PLACE_DETECTION_API )
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        setContentView(R.layout.activity_search_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( mGoogleApiClient != null )
            mGoogleApiClient.connect();

        //startActivity(new Intent(PlacesActivity.this, SearchPlacesList.class));

/*------------------------------------------------------------------------------------------------------------------------------------------------------------*/
//        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
//
//        //takes a key word
//        // if we want within 500meters --> change location to location=-33.8670522,151.1957362&radius=500
//        String url="https://maps.googleapis.com/maps/api/place/search/json?location=41.8584,-87.6307&radius=5000&types="+keyWord+"&sensor=false&key=AIzaSyAY5H67_Nuvb40ISHt21LGHGN60SJcXN4c";
//        JSONObject json = JSONfunctions.getJSONfromURL(url);
//
//        try{
//
//            JSONArray searchResult = json.getJSONArray("results");
//
//            for(int i=1;i< searchResult.length();i++){
//                HashMap<String, String> map = new HashMap<String, String>();
//                JSONObject e = searchResult.getJSONObject(i);
//                map.put("id",  String.valueOf(i));
//                map.put("name", "Name:" + e.getString("name"));
//                map.put("magnitude", "Rating: " +  e.getString("rating"));
//                map.put("vicinity", "Vicinity: "+ e.getString("vicinity"));
//                reference[i]=e.getString("reference");
//                mylist.add(map);
//
//            }
//        }catch(JSONException e)        {
//            Log.e("log_tag", "Error parsing data " + e.toString());
//        }
//
//        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.httpex,
//                new String[] { "name", "magnitude","vicinity" },
//                new int[] { R.id.item_title, R.id.item_subtitle,R.id.item_subtitle2 });
//
//        setListAdapter(adapter);
//
//        final ListView lv = getListView();
//        lv.setTextFilterEnabled(true);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                @SuppressWarnings("unchecked")
//                HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);
//                Toast.makeText(PlacesActivity.this, "ID '" + o.get("id") + "' was clicked.", Toast.LENGTH_SHORT).show();
//                s=reference[position];
//                startActivity(new Intent(PlacesActivity.this, PlaceDetails.class));
//                SearchSingle.getInstance().setter(s);
//            }
//        });
    }
    /*--------------------------------------------------------------------------------------------------------------------------------------------------*/


    @Override
    protected void onStop() {
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (connectionResult.hasResolution()) {
            try {
                mResolvingError = true;
                connectionResult.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        } else {
            // Show dialog using GoogleApiAvailability.getErrorDialog()
            showErrorDialog(connectionResult.getErrorCode());
            mResolvingError = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }


    private void showErrorDialog(int errorCode) {
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();

        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "error dialog");
    }


    public void onDialogDismissed() {
        mResolvingError = false;
    }

    @Override
    public void onConnected(Bundle bundle) {
        ////startActivity(new Intent(PlacesActivity.this, SearchPlacesList.class));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() { }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GoogleApiAvailability.getInstance().getErrorDialog(
                    this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            ((PlacesActivity) getActivity()).onDialogDismissed();
        }

        public void show(FragmentManager supportFragmentManager, String errordialog) {
        }
    }
}
