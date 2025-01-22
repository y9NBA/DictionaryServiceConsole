package org.y9nba.dict.exception.ui;

import java.io.IOException;

public class EmptyDictionariesException extends UIException {
    public EmptyDictionariesException() {
    }

    public EmptyDictionariesException(String message) {
        super(message);
    }
}
