package com.github.lardira.url_shortener.repositories;

import com.github.lardira.url_shortener.models.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UrlRepositoryImpl implements UrlRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public URL getBy(String encodedUrlId) {
        try {
            return (URL) entityManager
                    .createQuery("from URL where encoded_url_id=:encodedUrlId")
                    .setParameter("encodedUrlId", encodedUrlId)
                    .getSingleResult();
        } catch (
                NoResultException e) {
            return null;
        }
    }

    @Override
    public URL find(String url) {
        try {
            return (URL) entityManager
                    .createQuery("from URL where original=:url")
                    .setParameter("url", url)
                    .getSingleResult();
        } catch (
                NoResultException e) {
            return null;
        }
    }

    @Override
    public URL save(URL url) {
        return entityManager.merge(url);
    }
}
