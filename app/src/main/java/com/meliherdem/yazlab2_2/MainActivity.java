package com.meliherdem.yazlab2_2;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.meliherdem.yazlab2_2.ui.main.SectionsPagerAdapter;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String ip = "34.78.28.63";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "yazlab";
    private static String username = "test";
    private static String password = "test";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            System.out.println("success");
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("err");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail");
        }

        sqlButton();
        String fsfdsf = "";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sqlButton(){
        if (connection!=null){
            Statement statement = null;
            try {

                String date1 = "2020-01-01";
                String date2 = "2020-01-01";

                statement = connection.createStatement();

                String query = "SELECT TOP(1) * FROM  [dbo].[yellow_tripdata_2020-12] where [tpep_pickup_datetime] between '2020-12-10' and '2020-12-12' ";

                ResultSet resultSet = statement.executeQuery(query);

                ArrayList<Taxi> list = new ArrayList<Taxi>();
                while (resultSet.next()){
                    String xxx =resultSet.getString("tpep_pickup_datetime");
                    String yy ="";

                }

                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                //String strDate = dateFormat.format(date);

                String xx ="";

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection is null");
        }
    }


}

class Test{

    Test(int id, String name){
        Id = id;
        Name = name;
    }

    int Id;
    String Name;
}

class Taxi{
    Taxi(int vendorId, DateTime date){
        VendorID = vendorId;
        Tpep_pickup_datetime = date;
    }

    int VendorID;
    DateTime Tpep_pickup_datetime;
}