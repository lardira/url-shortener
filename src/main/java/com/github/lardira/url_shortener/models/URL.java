package com.github.lardira.url_shortener.models;

import javax.persistence.*;

@Entity
@Table(name = "urls")
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "original")
    private String original;
    @Column(name = "encoded ")
    private String encoded;
    @Column(name = "encoded_url_id")
    private String encodedUrlId;

    public URL() {
    }

    public URL(String original, String encoded, String encodedUrlId) {
        this.original = original;
        this.encoded = encoded;
        this.encodedUrlId = encodedUrlId;
    }

    public URL(String original) {
        this.original = original;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getEncodedUrlId() {
        return encodedUrlId;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }

    public void setEncodedUrlId(String encodedUrlId) {
        this.encodedUrlId = encodedUrlId;
    }

    public String getOriginal() {
        return original;
    }

    @Override
    public String toString() {
        return "URL{" +
                "id=" + id +
                ", original='" + original + '\'' +
                ", encoded='" + encoded + '\'' +
                ", encodedUrlId='" + encodedUrlId + '\'' +
                '}';
    }
}
