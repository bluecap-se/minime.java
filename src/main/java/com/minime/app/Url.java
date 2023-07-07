package com.minime.app;

import jakarta.persistence.*;

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

}
