package com.minime.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<Url> getUrls() {
        return urlRepository.findAll();
        //return List.of(new Url("abc", "https://google.se"));
    }
}
