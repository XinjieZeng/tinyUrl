package com.example.shortUrl.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="url_map")
public class UrlMapping {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "long_url")
    private String longUrl;

    public UrlMapping() {
        super();
    }

    public UrlMapping(String shortUrl, String longUrl) {
        super();
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
