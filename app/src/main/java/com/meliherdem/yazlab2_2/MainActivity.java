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
    //public static MainActivity Instance;
    private static String ip = "34.78.28.63";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "yazlab";
    private static String username = "test";
    private static String password = "test";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private static Connection connection = null;
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

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sorguOne(TextView text){
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                //2020-12-10 and 2020-12-12
                String query = "select cast(tpep_pickup_datetime as date) as 'Tarih' , sum(CAST(passenger_count AS int)) as 'yolcu_sayisi'\n" +
                        "FROM  [dbo].[yellow_tripdata_2020-12]   group by cast(tpep_pickup_datetime as date) ORDER BY 'yolcu_sayisi'  desc OFFSET (0) ROWS \n" +
                        "  FETCH NEXT 5 ROWS ONLY;";

                ResultSet resultSet = statement.executeQuery(query);
                String allText= "";
                //ArrayList<Taxi> list = new ArrayList<Taxi>();
                //DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                //String strDate = dateFormat.format(date);
                allText+= "Tarih"+" YolcuSayısı"+"\n";
                while (resultSet.next()){
                    allText+=resultSet.getString(1)+ " "+resultSet.getString(2)+"\n";
                }

                text.setText(allText);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection is null");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sorguTwo(String date1,String date2,TextView text){
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                //2020-12-10 and 2020-12-12
                String query = "SELECT tpep_pickup_datetime, tpep_dropoff_datetime,cast(trip_distance as float) as 'trip_distance',PULocationID,DOLocationID,total_amount FROM [dbo].[yellow_tripdata_2020-12] \n" +
                        "where cast(tpep_pickup_datetime as date)between '"+date1+"' and '"+date2+"' and cast(trip_distance as float) > 0 order by 'trip_distance' asc OFFSET (0) ROWS \n" +
                        "  FETCH NEXT 5 ROWS ONLY;";

                ResultSet resultSet = statement.executeQuery(query);
                String allText= "";
                //ArrayList<Taxi> list = new ArrayList<Taxi>();
                //DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                //String strDate = dateFormat.format(date);
                allText+= "Pickup_date"+" Dropoff_date"+" Distance"+ " PULocation"+" DOLocation"+" Amount"+"\n";
                while (resultSet.next()){
                    allText+=resultSet.getString(1).substring(0,19)+ " "+resultSet.getString(2).substring(0,19)+ " "+resultSet.getString(3)+ " "+resultSet.getString(4)+ " "+resultSet.getString(5)+" "+resultSet.getString(6)+"\n";
                }

                text.setText(allText);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection is null");
        }
    }
}
/*
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
}*/