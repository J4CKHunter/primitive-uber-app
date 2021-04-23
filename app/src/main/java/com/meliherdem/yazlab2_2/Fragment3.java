package com.meliherdem.yazlab2_2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText textDate1;
    Button btTrack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment3_layout,container,false);
        btTrack=view.findViewById(R.id.btTrack);
        textDate1= view.findViewById(R.id.TextDate1);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubActivity.getDate(textDate1.getText().toString());
                Intent in = new Intent(getActivity(),SubActivity.class);
                //SubActivity.date= textDate1.getText().toString();

                startActivity(in);
            }
        });
        return view;
    }
}
