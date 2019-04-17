package com.deepiter.bt07_rss;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView baoTuoiTre;
    CardView baoBaoMoi;
    CardView baoThanhNien;
    CardView baoDanTri;
    CardView baoVNExpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baoTuoiTre = findViewById(R.id.cardViewTuoiTre);
        baoTuoiTre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent channelsActivity = new Intent(MainActivity.this, ChannelsActivity.class);
                Bundle data = new Bundle();
                data.putString("website", "tuoitre");
                channelsActivity.putExtras(data);
                startActivity(channelsActivity);
            }
        });

        baoBaoMoi = findViewById(R.id.cardViewBaoMoi);
        baoBaoMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent channelsActivity = new Intent(MainActivity.this, ChannelsActivity.class);
                Bundle data = new Bundle();
                data.putString("website", "bao24h");
                channelsActivity.putExtras(data);
                startActivity(channelsActivity);
            }
        });

        baoThanhNien = findViewById(R.id.cardViewThanhNien);
        baoThanhNien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent channelsActivity = new Intent(MainActivity.this, ChannelsActivity.class);
                Bundle data = new Bundle();
                data.putString("website", "thanhnien");
                channelsActivity.putExtras(data);
                startActivity(channelsActivity);
            }
        });

        baoDanTri = findViewById(R.id.cardViewDanTri);
        baoDanTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent channelsActivity = new Intent(MainActivity.this, ChannelsActivity.class);
                Bundle data = new Bundle();
                data.putString("website", "dantri");
                channelsActivity.putExtras(data);
                startActivity(channelsActivity);
            }
        });

        baoVNExpress = findViewById(R.id.cardViewVNExpress);
        baoVNExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent channelsActivity = new Intent(MainActivity.this, ChannelsActivity.class);
                Bundle data = new Bundle();
                data.putString("website", "vnexpress");
                channelsActivity.putExtras(data);
                startActivity(channelsActivity);
            }
        });
    }
}
