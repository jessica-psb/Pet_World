package com.example.petworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    final Fragment beranda = new BerandaActivity();
    final Fragment favorit = new FavoritActivity();
    final Fragment profil = new ProfilActivity();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = beranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.menu);
        nav.setOnNavigationItemSelectedListener(this);

        ImageButton Cart = (ImageButton) findViewById(R.id.btnCart);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        ImageButton Notif = (ImageButton) findViewById(R.id.btnNotif);
        Notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aksiAlert();
            }
        });

        fm.beginTransaction().add(R.id.main_container, profil, "3").hide(profil).commit();
        fm.beginTransaction().add(R.id.main_container, favorit, "2").hide(favorit).commit();
        fm.beginTransaction().add(R.id.main_container, beranda, "1").commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home_menu) {
            fm.beginTransaction().hide(active).show(beranda).commit();
            active = beranda;
            return true;
        } else if (id == R.id.favorite_menu) {
            fm.beginTransaction().hide(active).show(favorit).commit();
            active = favorit;
            return true;
        } else if (id == R.id.account_menu) {
            fm.beginTransaction().hide(active).show(profil).commit();
            active = profil;
            return true;
        }
        return true;
    }

    public void aksiAlert(){
        final AlertDialog.Builder warning= new AlertDialog.Builder(this);
        warning.setMessage("Maaf fitur ini sedang dikembangkan...")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).create().show();
    }
}
