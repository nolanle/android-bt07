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
                Bundle args = new Bundle();
                args.putString("website", "tuoitre");
                channelsActivity.putExtras(args);
                startActivity(channelsActivity);
            }
        });

        baoBaoMoi = findViewById(R.id.cardViewBaoMoi);
        baoBaoMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent channelsActivity = new Intent(MainActivity.this, ChannelsActivity.class);
                Bundle args = new Bundle();
                args.putString("website", "baomoi");
                channelsActivity.putExtras(args);
                startActivity(channelsActivity);
            }
        });
    }

    //public void clickedTuoiTre(View view) {
    //    new AlertDialog.Builder(this).setTitle("CLicked Alert").setMessage("Báo Tuổi Trẻ").create().show();
    //}
}
