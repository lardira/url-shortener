package com.github.lardira.url_shortener.dao;

import com.github.lardira.url_shortener.models.URL;

public interface UrlRepository {
    public URL save(URL url);
    public URL getBy(String encodedUrlId);

    URL find(String url);
}
