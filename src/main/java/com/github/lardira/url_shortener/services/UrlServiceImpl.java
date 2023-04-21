package com.github.lardira.url_shortener.services;

import com.github.lardira.url_shortener.repositories.UrlRepository;
import com.github.lardira.url_shortener.models.URL;
import com.github.lardira.url_shortener.utils.BaseEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.MalformedURLException;

@Component("urlService")
public class UrlServiceImpl implements UrlService {
    @Autowired
    UrlRepository urlRepository;
    @Autowired
    BaseEncoder baseEncoder;

    @Override
    public URL save(URL url) {
        return urlRepository.save(url);
    }

    @Override
    public URL findByEncodedUrlId(String encodedUrlId) {
        return urlRepository.findByEncodedUrlId(encodedUrlId).orElseThrow();
    }

    @Override
    public URL find(String originalUrl) {
        return urlRepository.findByOriginal(originalUrl).orElseThrow();
    }

    //should be in an external class
    @Override
    public String encode(URL url) {
        return baseEncoder.encode(url.getId());
    }

    @Override
    public boolean isValidUrl(String inputURL) throws MalformedURLException {
        new java.net.URL(inputURL);
        return true;
    }
}
