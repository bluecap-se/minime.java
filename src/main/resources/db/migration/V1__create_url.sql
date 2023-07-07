CREATE SEQUENCE url_sequence
    START WITH 1 INCREMENT BY 1;

CREATE TABLE url (id bigint NOT NULL,
                  hash varchar(6) UNIQUE NOT NULL,
                  created TIMESTAMP DEFAULT NOW() NOT NULL,
                  url varchar(2048) NOT NULL,
                  password varchar(255),
                  PRIMARY KEY (id));

CREATE INDEX url_index_hash
    ON url(hash);
