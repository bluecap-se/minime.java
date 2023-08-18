# MiniMe

[![Backend](https://github.com/bluecap-se/minime.java/actions/workflows/backend.yml/badge.svg)](https://github.com/bluecap-se/minime.java/actions/workflows/backend.yml)
[![Frontend](https://github.com/bluecap-se/minime.java/actions/workflows/frontend.yml/badge.svg)](https://github.com/bluecap-se/minime.java/actions/workflows/frontend.yml)
![Status](https://img.shields.io/badge/status-stable-brightgreen.svg)
![Java Version](https://img.shields.io/badge/java-20-blue.svg)
[![Docker pulls](https://img.shields.io/docker/pulls/bluecap/minime.java)](https://hub.docker.com/r/bluecap/minime.java)
![Platform](https://img.shields.io/badge/platform-win%20%7C%20lin%20%7C%20osx-lightgrey.svg)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/bluecap-se/minime.java/blob/master/LICENSE)

The last link shortener you'll ever need.

This is a fork of the Minime project in Python: https://github.com/bluecap-se/minime

## Get started

### Run with Docker

Minime can run with [Docker](https://www.docker.com) compose, to set up follow these steps:

```
$ git clone git@github.com:bluecap-se/minime.java.git && cd "$_"
$ make up
$ open http://127.0.0.1:8080
```

## Run tests

Run the tests in docker container:

```
$ make test
```

### Test coverage

*Coming soon*

```
$ make test-coverage
$ open htmlcov/index.html
```

## License

Published under [MIT License](https://github.com/bluecap-se/minime.java/blob/master/LICENSE).
