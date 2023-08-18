package com.minime.app;

import jakarta.persistence.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

@Entity
@Table(indexes = {@Index(name = "index_hash", columnList = "hash")})
public class Url {

    @Id
    @SequenceGenerator(
        name = "url_sequence",
        sequenceName = "url_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "url_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, length = 6, unique = true)
    private String hash;

    @Column(nullable = false, length = 2048)
    private String url;

    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private Date created;

    @Column(nullable = true)
    private String password;

    public Url(String url) {
        this.hash = this.generateHash(); // TODO: Check if hash exists
        this.url = url;
    }

    public Url(String url, String password) {
        this.hash = this.generateHash(); // TODO: Check if hash exists
        this.url = url;
        this.password = password;
    }

    public Url() {
        this.hash = this.generateHash(); // TODO: Check if hash exists
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String generateHash() {
        return RandomStringUtils.randomAlphanumeric(6);
    }

    @Override
    public String toString() {
        return "Url{" +
            "id=" + id +
            ", hash='" + hash + '\'' +
            ", url='" + url + '\'' +
            ", created=" + created +
            ", password='" + password + '\'' +
            '}';
    }
}
