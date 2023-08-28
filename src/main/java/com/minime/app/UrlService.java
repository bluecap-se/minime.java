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

    public Url getUrl(String hash) {
        return urlRepository.findUrlByHash(hash)
            .orElseThrow(() -> new UrlNotFoundException(-1L));
    }

    public Url createUrl(Url url) {
        return urlRepository.save(url);
    }

    @Transactional
    public Url updateUrl(Long id, Url newUrl) {
        Url urlObject = urlRepository.findUrlById(id)
            .orElseThrow(() -> new UrlNotFoundException(id));

        String url = newUrl.getUrl();
        if (url != null && !url.isEmpty()
            && !Objects.equals(urlObject.getUrl(), url)) {
            urlObject.setUrl(url);
        }

        urlObject.setPassword(newUrl.getPassword());

        return urlObject;
    }

    public void deleteUrl(Long id) {
        if (!urlRepository.existsById(id)) {
            throw new UrlNotFoundException(id);
        }
        urlRepository.deleteById(id);
    }

}
