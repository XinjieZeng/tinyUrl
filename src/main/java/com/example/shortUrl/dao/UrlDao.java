package com.example.shortUrl.dao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlDao{
    @Select("select short_url from url_map where long_url = #{longUrl}")
    String getShortUrl(String longUrl);

    @Select("select long_url from url_map where short_url = #{shortUrl}")
    String getLongUrl(String shortUrl);

    @Select("select id from url_map where short_url = #{shortUrl}")
    Long isExist(String shortUrl);

    @Insert({"insert into url_map (short_url, long_url) values (#{shortUrl}, #{longUrl})"})
    void save(String shortUrl, String longUrl);
}
