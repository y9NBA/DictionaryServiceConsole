package org.y9nba.dict.exception.dictionary;

import java.io.IOException;

public class DictException extends IOException {
    public DictException() {
    }

    public DictException(String message) {
        super(message);
    }
}
