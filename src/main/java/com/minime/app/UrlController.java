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

    @GetMapping(path = "{id}")
    public Url getUrl(@PathVariable Long id) {
        return urlService.getUrl(id);
    }

    @PostMapping
    public Url createUrl(@RequestBody Url url) {
        return urlService.createUrl(url);
    }

    @PutMapping(path = "{id}")
    public Url updateUrl(@PathVariable Long id,
                          @RequestBody Url newUrl) {
        return urlService.updateUrl(id, newUrl);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUrl(@PathVariable Long id) {
        urlService.deleteUrl(id);
    }
}
