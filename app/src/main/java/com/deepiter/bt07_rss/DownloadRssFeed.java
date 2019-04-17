package com.deepiter.bt07_rss;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DownloadRssFeed extends AsyncTask<String, Void, ArrayList<ChannelItem>> {
    private ShowHeadlinesActivity callerContext;
    private String urlAddress = "";
    private String urlCaption = "";
    private ProgressDialog dialog = null;

    public DownloadRssFeed( Context callerContext){
        this.callerContext = (ShowHeadlinesActivity) callerContext;
        dialog = new ProgressDialog(callerContext);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please wait\nReading RSS feed ..." ); this.dialog.setCancelable(false);
    }

    @Override
    protected ArrayList<ChannelItem> doInBackground(String... params) {
        ArrayList<ChannelItem> newsList = new ArrayList<ChannelItem>();
        urlAddress = params[0];
        urlCaption = params[1];
        this.dialog.setMessage("Please wait\nReading RSS feed " + urlCaption + "...");

        try {
            // try to get connected to RSS source
            URL url = new URL(urlAddress);
            URLConnection connection;
            connection = url.openConnection();

            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();

                // define a document builder to work on incoming stream
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                // make DOM-tree for incoming XML stream
                Document dom = db.parse(in);

                // make available all access nodes in the parse tree
                Element treeElements = dom.getDocumentElement();

                // look for individual 'stories' (<items> in this case)
                // add each found item to a NodeList collection (newsList)
                newsList.clear();
                NodeList itemNodes = treeElements.getElementsByTagName("item");
                if ((itemNodes != null) && (itemNodes.getLength() > 0)) {
                    for (int i = 0; i < itemNodes.getLength(); i++) {
                        newsList.add( dissectItemNode(itemNodes, i) );
                    }// for
                }// if

            }// if

            // time to close. we don't need the connection anymore
            httpConnection.disconnect();
        } catch (Exception e) {
            Log.e("Error>> ", e.getMessage() );
        }
        return newsList; //to be consumed by onPostExecute
    }// doInBackground

    @Override
    protected void onPostExecute(ArrayList<ChannelItem> result) {
        super.onPostExecute(result);
        callerContext.newsList = result;

        int layoutID = R.layout.channel_item;
        ArrayAdapter<ChannelItem> adapterNews = new ArrayAdapter<ChannelItem>(callerContext, layoutID, result);
        callerContext.listView.setAdapter(adapterNews);
        dialog.dismiss();
    }

    private ChannelItem dissectItemNode(NodeList nodeList, int i) {
        try {
            Element entry = (Element) nodeList.item(i);
            Element title = (Element) entry.getElementsByTagName("title").item(0);
            Element description = (Element) entry.getElementsByTagName("description").item(0);
            Element pubDate = (Element) entry.getElementsByTagName("pubDate").item(0);
            Element link = (Element) entry.getElementsByTagName("link").item(0);

            String titleValue = title.getFirstChild().getNodeValue();
            String descriptionValue =description.getFirstChild().getNodeValue();
            String dateValue = pubDate.getFirstChild().getNodeValue();
            String linkValue = link.getFirstChild().getNodeValue();

            return new ChannelItem(titleValue, descriptionValue, linkValue, dateValue);
        }catch (DOMException exception) {
            return new ChannelItem("", "Error", exception.getMessage(), null);
        }
    }

}
