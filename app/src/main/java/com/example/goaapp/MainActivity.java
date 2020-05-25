package com.example.goaapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goaapp.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TouristPlaces.OnFragmentInteractionListener
        ,Hotels.OnFragmentInteractionListener,Restaurants.OnFragmentInteractionListener
        ,Markets.OnFragmentInteractionListener,Transportation.OnFragmentInteractionListener,HomeTab.OnFragmentInteractionListener,View.OnClickListener {

    private Boolean isFabOpen = false;
    public FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private Button feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
       // Toast.makeText(getApplicationContext(), "Welcome !!",Toast.LENGTH_SHORT).show();
        tabs.addTab(tabs.newTab().setText("Home"));
        tabs.addTab(tabs.newTab().setText("Tourist Places"));
        tabs.addTab(tabs.newTab().setText("Hotels"));
        tabs.addTab(tabs.newTab().setText("Restaurants"));
        tabs.addTab(tabs.newTab().setText("Markets"));
        tabs.addTab(tabs.newTab().setText("Transportation"));

        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        //tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabs.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:
                Log.d("Raj", "Fab 1");
                break;
            case R.id.fab2:
                Log.d("Raj", "Fab 2");
                break;
        }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),PicWithCamera.class));
                }
            });
            fab2.setClickable(true);
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),PicFromGallery.class));
                }
            });
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }

    public void CekSession()
    {
        Boolean Check = Boolean.valueOf(SharedPrefs.readSharedSettings(MainActivity.this,"CaptainCode", "true"));
        Intent introIntent = new Intent(MainActivity.this,login.class);
        introIntent.putExtra("CaptainCode",Check);
        if(Check)
        {
            startActivity(introIntent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch((item.getItemId()))
        {
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                SharedPrefs.saveSharedSetting(MainActivity.this, "CaptainCode", "true");
                startActivity(new Intent(this, login.class));
                finish();
                CekSession();

                break;

            case R.id.menuProfile:
                startActivity(new Intent(this,Profile.class));
                break;

            case R.id.feedback:
                Intent intent = new Intent(MainActivity.this, RecyclerList.class);
                startActivity(intent);
                break;
        }
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}