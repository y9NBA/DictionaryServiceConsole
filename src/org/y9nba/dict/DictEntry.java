package org.y9nba.dict;

public class DictEntry {
    private String key;
    private String value;

    public DictEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " - " + value;
    }

    //region GETTER
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    //endregion
}
