package com.example.shortUrl.web;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String a = "tinyurl";
        String b = "sdfsdfsdfsf";
        String c = "tinyUrl";

        System.out.println("is not tinyurl: " + Pattern.matches("(?!tinyurl).*$", a));
        System.out.println("is not tinyurl: " + Pattern.matches( "(?!tinyurl).*$", b));
        System.out.println("is not tinyurl: " + Pattern.matches("(?!tinyurl).*$", c));

    }
}
