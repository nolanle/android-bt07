package com.deepiter.bt07_rss;

import java.util.Date;

class ChannelItem {
    private String title;
    private String link;
    private String description;
    private Date publishedDate;

    ChannelItem(String title, String link, String description, Date publishedDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}
