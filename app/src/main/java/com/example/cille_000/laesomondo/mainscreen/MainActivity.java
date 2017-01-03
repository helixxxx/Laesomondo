package com.example.cille_000.laesomondo.mainscreen;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoActivity;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton burgerButton, userButton;
    private ArrayList<ImageButton> coverList, adventureList;
    private UserProfileFragment userprofile;
    private MenuFragment menu;
    private TextView title;
    private Boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        userprofile = new UserProfileFragment();
        menu = new MenuFragment();
        coverList = new ArrayList<>();
        adventureList = new ArrayList<>();

        userButton = (ImageButton) findViewById(R.id.userbutton);
        title = (TextView) findViewById(R.id.toolbar_title);
        burgerButton = (ImageButton) findViewById(R.id.burgerbutton);
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover1));
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover2));
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover3));
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover4));
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover5));

        userButton.setOnClickListener(this);
        burgerButton.setOnClickListener(this);

        for (ImageButton i: coverList) {
            i.setOnClickListener(this);
        }

        adventureList.add((ImageButton) findViewById(R.id.imageButtonAdventure01));

        bool = true;
        if (bool){
            for (ImageButton i: adventureList) {
                i.setOnClickListener(this);
            }
        }
        if (!bool) {
            for (int i = 0; i < adventureList.size(); i++) {
                adventureList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < coverList.size(); i++) {
            if(v == coverList.get(i)) {
                Intent intent = new Intent(this,TextInfoActivity.class);
                intent.putExtra("textID",++i);
                startActivity(intent);
            }
        }

        for(int i = 0; i < adventureList.size(); i++) {
            if(v == adventureList.get(i)) {
                Intent intent = new Intent(this,TextInfoActivity.class);
                intent.putExtra("textID",++i+5);
                startActivity(intent);
            }
        }

        if(v == burgerButton) {
            if(userprofile != null && userprofile.isVisible()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.remove(userprofile).commit();

                burgerButton.setBackground(getResources().getDrawable(R.drawable.burgerbutton01));
                userButton.setVisibility(View.VISIBLE);
                title.setText(getResources().getString(R.string.MainScreenTitle));
            } else if(menu != null && menu.isVisible()){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.remove(menu).commit();

                burgerButton.setBackground(getResources().getDrawable(R.drawable.burgerbutton01));
                userButton.setVisibility(View.VISIBLE);
                title.setText(getResources().getString(R.string.MainScreenTitle));
            } else {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_framelayout, menu);
                transaction.commit();

                burgerButton.setBackground(getResources().getDrawable(R.drawable.backarrow));
                userButton.setVisibility(View.INVISIBLE);
                title.setText(getResources().getString(R.string.MainScreenBurgermenu));
            }
        }
        else if (v == userButton) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_framelayout, userprofile);
            transaction.commit();

            burgerButton.setBackground(getResources().getDrawable(R.drawable.backarrow));
            userButton.setVisibility(View.INVISIBLE);
            title.setText(getResources().getString(R.string.MainScreenUserProfile));
        }
    }
}
