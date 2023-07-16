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
    }

    public void createUrl(Url url) {
        // Check if hash exists first
        if (urlRepository.findUrlByHash(url.getHash()).isPresent()) {
            throw new IllegalStateException("Hash is taken.");
        }
        urlRepository.save(url);
    }

    public void deleteUrl(Long urlId) {
        if (!urlRepository.existsById(urlId)) {
            throw new IllegalStateException("Student with id " + urlId + " does not exist.");
        }
        urlRepository.deleteById(urlId);
    }
}
