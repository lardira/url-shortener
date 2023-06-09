package com.github.lardira.url_shortener.repositories;

import com.github.lardira.url_shortener.models.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<URL, Integer> {
    public Optional<URL> findByEncodedUrlId(String encodedUrlId);
    public Optional<URL> findByOriginal(String original);
}
