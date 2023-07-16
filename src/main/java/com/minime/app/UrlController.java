package com.minime.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/urls")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public List<Url> getUrls() {
        return urlService.getUrls();
    }

    @PostMapping
    public void createUrl(@RequestBody Url url) {
        urlService.createUrl(url);
    }

    @DeleteMapping(path = "{urlId}")
    public void deleteUrl(@PathVariable("urlId") Long urlId) {
        urlService.deleteUrl(urlId);
    }
}
