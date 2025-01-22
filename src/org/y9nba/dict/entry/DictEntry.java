package org.y9nba.dict.entry;

public record DictEntry(String key, String value) {

    @Override
    public String toString() {
        return key + " - " + value;
    }
}
