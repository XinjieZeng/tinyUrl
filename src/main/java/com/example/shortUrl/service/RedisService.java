package com.example.shortUrl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ShortUrlService shortUrlService;

    public String getShortUrl(String longUrl) {
        if(exists(longUrl)) {
            return get(longUrl);
        }

        String shortUrl = shortUrlService.getShortUrl(longUrl);
        set(shortUrl, longUrl);
        set(longUrl, shortUrl);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        if (exists(shortUrl)) {
            return get(shortUrl);
        }

        return null;

    }

    /**
     * write into the cache
     * @param key
     * @param value
     * @return
     */
    private boolean set(String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * determine if the cache has existing key
     * @param key
     * @return
     */
    private boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * read the cache to get the value
     * @param key
     * @return
     */
    private String get(final String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

}
