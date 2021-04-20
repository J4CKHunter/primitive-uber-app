package com.meliherdem.yazlab2_2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment3 extends Fragment {
    String location1= "40.52729818,-74.22954655";
    String location2= "40.87406155,-73.83042363";
    Button btTrack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment3_layout,container,false);
        btTrack=view.findViewById(R.id.btTrack);
        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSource = location1.trim();
                String sDestination= location2.trim();

                if(sSource.equals("")&&sDestination.equals(""))
                {

                }
                else {
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
        return view;
    }

    private void DisplayTrack(String sSource, String sDestination) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+sSource+"/"+sDestination);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
