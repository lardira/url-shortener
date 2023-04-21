package com.github.lardira.url_shortener.repositories;

import com.github.lardira.url_shortener.models.URL;

public interface UrlRepository {
    URL save(URL url);
    URL getBy(String encodedUrlId);
    URL find(String url);
}
