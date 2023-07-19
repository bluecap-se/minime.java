package com.minime.app;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Url getUrl(Long id) {
        return urlRepository.findUrlById(id)
            .orElseThrow(() -> new UrlNotFoundException(id));
    }

    public Url createUrl(Url url) {
        // Check if hash exists first
        if (urlRepository.findUrlByHash(url.getHash()).isPresent()) {
            throw new IllegalStateException("Hash is taken.");
        }
        return urlRepository.save(url);
    }

    @Transactional
    public Url updateUrl(Long id, Url newUrl) {
        Url urlObject = urlRepository.findUrlById(id)
            .orElseThrow(() -> new UrlNotFoundException(id));

        String hash = newUrl.getHash();
        if (hash != null && hash.length() > 0
            && !Objects.equals(urlObject.getHash(), hash)) {
            if (urlRepository.findUrlByHash(hash).isPresent()) {
                throw new IllegalStateException("Hash already exists.");
            }
            urlObject.setHash(hash);
        }

        String url = newUrl.getUrl();
        if (url != null && url.length() > 0
            && !Objects.equals(urlObject.getUrl(), url)) {
            urlObject.setUrl(url);
        }

        return urlObject;
    }

    public void deleteUrl(Long id) {
        if (!urlRepository.existsById(id)) {
            throw new UrlNotFoundException(id);
        }
        urlRepository.deleteById(id);
    }

}
