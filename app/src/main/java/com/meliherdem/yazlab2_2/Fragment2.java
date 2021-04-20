package com.meliherdem.yazlab2_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private Button sendBtn;
    private TextView text;
    private EditText textDate1;
    private EditText textDate2;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2_layout,container,false);
        sendBtn=view.findViewById(R.id.SendButton);
        text= view.findViewById(R.id.WriteText);
        textDate1= view.findViewById(R.id.TextDate1);
        textDate2=view.findViewById(R.id.TextDate2);
        //"2020-01-01"
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sorguTwo(textDate1.getText().toString(),textDate2.getText().toString(),text);
            }
        });
        return view;
    }
}
