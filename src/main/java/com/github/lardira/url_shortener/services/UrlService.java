package com.github.lardira.url_shortener.services;

import com.github.lardira.url_shortener.models.URL;

import java.net.MalformedURLException;

public interface UrlService {
    URL save(URL url);
    URL getBy(String encodedUrlId);
    URL find(String url);
    String encode(URL url);
    boolean isValidUrl(String inputURL) throws MalformedURLException;
}
