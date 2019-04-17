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
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChannelsActivity extends AppCompatActivity {
    ImageView imgChannelLogo;

    String website;
    ArrayAdapter<String> adapterChannels;
    ListView listViewChannel;
    Context context;
    ChannelItem selectedChannelItem;
    String[] urlCaption;
    String[] urlAddress;

    String[][] allTuoiTreChannels = {
            {"https://tuoitre.vn/rss/tin-moi-nhat.rss", "TRANG CHỦ"},
            {"https://tuoitre.vn/rss/thoi-su.rss", "THỜI SỰ"},
            {"https://tuoitre.vn/rss/the-gioi.rss", "THẾ GIỚI"},
            {"https://tuoitre.vn/rss/phap-luat.rss", "PHÁP LUẬT"},
            {"https://tuoitre.vn/rss/kinh-doanh.rss", "KINH DOANH"},
            {"https://tuoitre.vn/rss/nhip-song-so.rss", "CÔNG NGHỆ"},
            {"https://tuoitre.vn/rss/xe.rss", "XE"},
            {"https://tuoitre.vn/rss/nhip-song-tre.rss", "NHỊP SỐNG TRẺ"},
            {"https://tuoitre.vn/rss/van-hoa.rss", "VĂN HÓA"},
            {"https://tuoitre.vn/rss/giai-tri.rss", "GIẢI TRÍ"},
            {"https://tuoitre.vn/rss/the-thao.rss", "THỂ THAO"},
            {"https://tuoitre.vn/rss/giao-duc.rss", "GIÁO DỤC"},
            {"https://tuoitre.vn/rss/khoa-hoc.rss", "KHOA HỌC"},
            {"https://tuoitre.vn/rss/suc-khoe.rss", "SỨC KHỎE"},
            {"https://tuoitre.vn/rss/gia-that.rss", "GIẢ THẬT"},
            {"https://tuoitre.vn/rss/thu-gian.rss", "THƯ GIÃN"},
            {"https://tuoitre.vn/rss/ban-doc-lam-bao.rss", "BẠN ĐỌC LÀM BÁO"},
            {"https://tuoitre.vn/rss/du-lich.rss", "DU LỊCH"}
    };
    String[][] allBaoMoiChannels = {};
    String[][] allThanhNienChannels = {
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
    String[][] allDanTriChannels = {
            {"https://dantri.com.vn/trangchu.rss", "TRANG CHỦ"},
            {"https://dantri.com.vn/suc-khoe.rss", "SỨC KHỎE"},
            {"https://dantri.com.vn/xa-hoi.rss", "XÃ HỘI"},
            {"https://dantri.com.vn/giai-tri.rss", "GIẢI TRÍ"},
            {"https://dantri.com.vn/giao-duc-khuyen-hoc.rss", "GIÁO DỤC & KHUYẾN HỌC"},
            {"https://dantri.com.vn/the-thao.rss", "THỂ THAO"},
            {"https://dantri.com.vn/the-gioi.rss", "THẾ GIỚI"},
            {"https://dantri.com.vn/kinh-doanh.rss", "KINH DOANH"},
            {"https://dantri.com.vn/o-to-xe-may.rss", "Ô TÔ & XE MÁY"},
            {"https://dantri.com.vn/suc-manh-so.rss", "SỨC MẠNH SỐ"},
            {"https://dantri.com.vn/tinh-yeu-gioi-tinh.rss", "TÌNH YÊU & GIỚI TÍNH"},
            {"https://dantri.com.vn/chuyen-la.rss", "CHUYỆN LẠ"},
            {"https://dantri.com.vn/viec-lam.rss", "VIỆC LÀM"},
            {"https://dantri.com.vn/nhip-song-tre.rss", "NHỊP SỐNG TRẺ"},
            {"https://dantri.com.vn/tam-long-nhan-ai.rss", "TẤM LÒNG NHÂN ÁI"},
            {"https://dantri.com.vn/phap-luat.rss", "PHÁP LUẬT"},
            {"https://dantri.com.vn/ban-doc.rss", "BẠN ĐỌC"},
            {"https://dantri.com.vn/dien-dan.rss", "DIỄN ĐÀN"},
            {"https://dantri.com.vn/tuyen-sinh.rss", "TUYỂN SINH"},
            {"https://dantri.com.vn/blog.rss", "BLOG"},
            {"https://dantri.com.vn/van-hoa.rss", "VĂN HÓA"},
            {"https://dantri.com.vn/du-hoc.rss", "DU HỌC"},
            {"https://dantri.com.vn/du-lich.rss", "DU LỊCH"},
            {"https://dantri.com.vn/doi-song.rss", "ĐỜI SỐNG"},
            {"https://dantri.com.vn/khoa-hoc-cong-nghe.rss", "KHOA HỌC & CÔNG NGHỆ"},
            {"https://dantri.com.vn/sea-games-28.rss", "SEA GAMES 28"}
    };
    String[][] allVNExpressChannels = {
            {"https://vnexpress.net/rss/tin-moi-nhat.rss", "TRANG CHỦ"},
            {"https://vnexpress.net/rss/suc-khoe.rss", "SỨC KHỎE"},
            {"https://vnexpress.net/rss/thoi-su.rss", "THỜI SỰ"},
            {"https://vnexpress.net/rss/gia-dinh.rss", "ĐỜI SỐNG"},
            {"https://vnexpress.net/rss/the-gioi.rss", "THẾ GIỚI"},
            {"https://vnexpress.net/rss/du-lich.rss", "DU LỊCH"},
            {"https://vnexpress.net/rss/kinh-doanh.rss", "KINH DOANH"},
            {"https://vnexpress.net/rss/khoa-hoc.rss", "KHOA HỌC"},
            {"https://vnexpress.net/rss/startup.rss", "STARTUP"},
            {"https://vnexpress.net/rss/so-hoa.rss", "SỐ HÓA"},
            {"https://vnexpress.net/rss/giai-tri.rss", "GIẢI TRÍ"},
            {"https://vnexpress.net/rss/oto-xe-may.rss", "Ô TÔ & XE MÁY"},
            {"https://vnexpress.net/rss/the-thao.rss", "THỂ THAO"},
            {"https://vnexpress.net/rss/y-kien.rss", "Ý KIẾN"},
            {"https://vnexpress.net/rss/phap-luat.rss", "PHÁP LUẬT"},
            {"https://vnexpress.net/rss/tam-su.rss", "TÂM SỰ"},
            {"https://vnexpress.net/rss/giao-duc.rss", "GIÁO DỤC"},
            {"https://vnexpress.net/rss/cuoi.rss", "CƯỜI"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        // Initialize elements
        listViewChannel = findViewById(R.id.listChannels);
        imgChannelLogo = findViewById(R.id.imgChannelLogo);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        website = args.getString("website").toString();

        //new AlertDialog.Builder(this).setTitle("Load Thành Công").setMessage(website).create().show();
        switch (website) {
            case "tuoitre":
                imgChannelLogo.setImageResource(R.drawable.tuoitre);

                // define convenient URL and CAPTIONs arrays
                urlCaption = new String[allTuoiTreChannels.length];
                urlAddress = new String[allTuoiTreChannels.length];

                for (int i = 0; i < allTuoiTreChannels.length; i++) {
                    urlAddress[i] = allTuoiTreChannels[i][0];
                    urlCaption[i] = allTuoiTreChannels[i][1];
                }
                break;

            case "baomoi":
                imgChannelLogo.setImageResource(R.drawable.baomoi);
                new AlertDialog.Builder(this).setTitle("Xuất hiện lỗi").setMessage("Trang Báo Mới Hiện Chưa Hỗ Trợ RSS.").create().show();

                // define convenient URL and CAPTIONs arrays
                //urlCaption = new String[allBaoMoiChannels.length];
                //urlAddress = new String[allBaoMoiChannels.length];
                //for (int i = 0; i < allBaoMoiChannels.length; i++) {
                //    urlAddress[i] = allBaoMoiChannels[i][0];
                //    urlCaption[i] = allBaoMoiChannels[i][1];
                //}
                return;

            case "thanhnien":
                imgChannelLogo.setImageResource(R.drawable.thanhnien);

                // define convenient URL and CAPTIONs arrays
                urlCaption = new String[allThanhNienChannels.length];
                urlAddress = new String[allThanhNienChannels.length];

                for (int i = 0; i < allThanhNienChannels.length; i++) {
                    urlAddress[i] = allThanhNienChannels[i][0];
                    urlCaption[i] = allThanhNienChannels[i][1];
                }
                break;

            case "dantri":
                imgChannelLogo.setImageResource(R.drawable.dantri);

                // define convenient URL and CAPTIONs arrays
                urlCaption = new String[allDanTriChannels.length];
                urlAddress = new String[allDanTriChannels.length];

                for (int i = 0; i < allDanTriChannels.length; i++) {
                    urlAddress[i] = allDanTriChannels[i][0];
                    urlCaption[i] = allDanTriChannels[i][1];
                }
                break;

            case "vnexpress":
                imgChannelLogo.setImageResource(R.drawable.vnexpress);

                // define convenient URL and CAPTIONs arrays
                urlCaption = new String[allVNExpressChannels.length];
                urlAddress = new String[allVNExpressChannels.length];

                for (int i = 0; i < allVNExpressChannels.length; i++) {
                    urlAddress[i] = allVNExpressChannels[i][0];
                    urlCaption[i] = allVNExpressChannels[i][1];
                }
                break;
            default:
                new AlertDialog.Builder(this).setTitle("Xuất hiện lỗi").setMessage("Website không được hỗ trợ tải tin mới.").create().show();
                return;
        }

        context = getApplicationContext();
        this.setTitle("Headline News\n" + niceDate());

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
