package com.example.shortUrl.web;
import com.example.shortUrl.service.RedisService;
import com.example.shortUrl.service.ShortUrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;



@Controller
public class FrontendController {

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private RedisService redisService;

    private final String LOCALHOST = "http://localhost:8080/";

    @GetMapping("/tinyurl")
    private String index() {
        return "index";
    }


    @GetMapping("/toshort/**")
    @ResponseBody
    private ResponseEntity getShortUrl(HttpServletRequest request) {
        if (request.getRequestURI().substring(9).isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("the long url is empty");
        }

        String shortUrl = LOCALHOST + redisService.getShortUrl(request.getRequestURI().substring(9));
        //String shortUrl = LOCALHOST + shortUrlService.getShortUrl(request.getRequestURI().substring(9));
        return ResponseEntity.ok(shortUrl);
    }


    @GetMapping(value="/{path:^(?!tinyurl|submitForm.js).*}")
    @ResponseBody
    private ResponseEntity getLongUrl(@PathVariable String path) {
        if(StringUtils.isBlank(path)){
            return ResponseEntity.notFound().build();
        }

        //String longUrl = shortUrlService.getLongUrl(path);

        String longUrl = redisService.getLongUrl(path);

        if (longUrl == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", longUrl);
        return new ResponseEntity(headers, HttpStatus.PERMANENT_REDIRECT);
    }

}
