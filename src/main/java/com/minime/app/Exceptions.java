package com.minime.app;

class UrlNotFoundException extends RuntimeException {

    UrlNotFoundException(Long id) {
        super("Could not find Url with ID " + id);
    }
}
