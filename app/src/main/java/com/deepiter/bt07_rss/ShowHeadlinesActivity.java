package com.deepiter.bt07_rss;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowHeadlinesActivity extends AppCompatActivity {
    ArrayList<ChannelItem> newsList = new ArrayList<ChannelItem>();
    ListView listView;
    String urlCaption = "";
    String urlAddress = "";
    ChannelItem selectedNewsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_headlines);

        // find out which intent is calling us
        Intent callingIntent = getIntent();

        // grab data bundle holding selected url & caption sent to us
        Bundle bundle = callingIntent.getExtras();
        urlAddress = bundle.getString("address");
        urlCaption = bundle.getString("caption");

        // update app's top 'TitleBar' (eg. 'NPR - Business Wed April 09, 2014')
        this.setTitle("NPR - " + urlCaption + " \t" + ChannelsActivity.niceDate());

        // clicking on a row shows dialogBox with more info about selected item
        listView = findViewById(R.id.listNews);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNewsItem = newsList.get(position);
                showNiceDialogBox(selectedNewsItem, getApplicationContext());
            }
        });

        // get stories for the selected news option
        DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlinesActivity.this);
        downloader.execute(urlAddress, urlCaption);
    }

    private void showNiceDialogBox(ChannelItem selectedStoryItem, Context context) {
        // make a nice looking dialog box (story summary, btnClose, btnMore)
        // CAUTION: (check)on occasions title and description are the same!
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())){
            description = "";
        }
        try {
            //CAUTION: sometimes TITLE and DESCRIPTION include HTML markers
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder.setIcon(R.drawable.rss)
            .setTitle(Html.fromHtml(urlCaption) )
            .setMessage( title + "\n\n" + Html.fromHtml(description) + "\n" ) .setPositiveButton("Close", null)
            .setNegativeButton("More", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichOne) {
                    Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                    startActivity(browser); }
            })//setNegativeButton
            .show();
        } catch (Exception e) {
            Log.e("Error DialogBox", e.getMessage() ); }
    }
}
