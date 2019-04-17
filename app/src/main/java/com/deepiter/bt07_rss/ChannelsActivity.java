package com.deepiter.bt07_rss;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChannelsActivity extends AppCompatActivity {
    String website;
    ArrayAdapter<String> adapterChannels;
    ListView listViewChannel;
    Context context;
    ChannelItem selectedChannelItem;
    String[] urlCaption;
    String[] urlAddress;

    String[][] allTuoiTreChannels = {
            {"https://thanhnien.vn/rss/home.rss", "TIN NÓNG"},
            {"https://thanhnien.vn/rss/viet-nam.rss", "THỜI SỰ"},
            {"https://thanhnien.vn/rss/toi-viet.rss", "TÔI VIẾT"},
            {"https://thanhnien.vn/rss/the-gioi.rss", "THẾ GIỚI"},
            {"https://thanhnien.vn/rss/van-hoa-nghe-thuat.rss", "VĂN HÓA"},
            {"https://thethao.thanhnien.vn/rss/home.rss", "THỂ THAO"},
            {"https://thanhnien.vn/rss/doi-song.rss", "ĐỜI SỐNG"},
            {"https://thanhnien.vn/rss/kinh-doanh.rss", "TÀI CHÍNH - KINH DOANH"},
            {"https://thanhnien.vn/rss/the-gioi-tre.rss", "GIỚI TRẺ"},
            {"https://thanhnien.vn/rss/giao-duc.rss", "GIÁO DỤC"},
            {"https://thanhnien.vn/rss/cong-nghe-thong-tin.rss", "CÔNG NGHỆ"},
            {"https://game.thanhnien.vn/rss/home.rss", "GAME"},
            {"https://thanhnien.vn/rss/doi-song/suc-khoe.rss", "SỨC KHỎE"},
            {"https://thanhnien.vn/rss/doi-song/du-lich.rss", "DU LỊCH"},
            {"https://xe.thanhnien.vn/rss/home.rss", "XE"},
            {"https://thanhnien.vn/rss/ban-can-biet.rss", "BẠN CẦN BIẾT"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        // Initialize elements
        listViewChannel = findViewById(R.id.listChannels);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        website = args.getString("website").toString();

        //new AlertDialog.Builder(this).setTitle("Load Thành Công").setMessage(website).create().show();
        switch (website) {
            case "tuoitre":
                // define convenient URL and CAPTIONs arrays
                urlCaption = new String[allTuoiTreChannels.length];
                urlAddress = new String[allTuoiTreChannels.length];

                for (int i = 0; i < allTuoiTreChannels.length; i++) {
                    urlAddress[i] = allTuoiTreChannels[i][0];
                    urlCaption[i] = allTuoiTreChannels[i][1];
                }

                context = getApplicationContext();
                this.setTitle("Headline News\n" + niceDate());

                break;
            default:
                new AlertDialog.Builder(this).setTitle("Xuất hiện lỗi").setMessage("Website không được hỗ trợ tải tin mới.").create().show();
                return;
        }

        // Handle list view clicked
        listViewChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent callShowHeadlines = new Intent(ChannelsActivity.this, ShowHeadlinesActivity.class);
                Bundle data = new Bundle();
                data.putString("address", urlAddress[position]);
                data.putString("caption", urlCaption[position]);
                callShowHeadlines.putExtras(data);

                startActivity(callShowHeadlines);
            }
        });

        // fill up the Main-GUI’s ListView with main news categories
        adapterChannels = new ArrayAdapter<String>(this, R.layout.channel_item, urlCaption);
        listViewChannel.setAdapter(adapterChannels);
    }

    public static String niceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy ", Locale.US);
        return sdf.format(new Date() );
    }
}
