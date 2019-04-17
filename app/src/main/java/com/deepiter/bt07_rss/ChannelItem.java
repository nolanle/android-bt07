package com.deepiter.bt07_rss;

public class ChannelItem {
    private String title;
    private String description;
    private String link;
    private String pubDate;

    public ChannelItem(String title, String description, String link, String pubDate) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() { return  title; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public String getPubDate() { return pubDate; }

    @Override
    public String toString() { return title; }
}
