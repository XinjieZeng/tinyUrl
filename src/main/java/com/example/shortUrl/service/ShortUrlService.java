package com.example.shortUrl.service;

import com.example.shortUrl.dao.UrlDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {

    @Autowired
    public UrlDao urlDao;

    public String getShortUrl(String longUrl) {
        String shortUrl = urlDao.getShortUrl(longUrl);

        if (shortUrl== null) {
            while (true) {
                // create a random 7-letter short url
                shortUrl = RandomStringUtils.randomAlphabetic(7);
                if (!urlDao.isExist(shortUrl)) {
                    urlDao.save(shortUrl, longUrl);
                    break;
                }
            }
        }

        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        return urlDao.getLongUrl(shortUrl);
    }
}
