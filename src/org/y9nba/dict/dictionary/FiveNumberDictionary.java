package org.y9nba.dict.dictionary;

import org.y9nba.dict.constant.Dictionaries;

public class FiveNumberDictionary extends BaseDictionary {
    public FiveNumberDictionary() {
        this(Dictionaries.FiveNumberDict);
    }

    private FiveNumberDictionary(Dictionaries dictionary) {
        super(dictionary);
    }

    public FiveNumberDictionary(String name) {
        super(name);
    }

    public FiveNumberDictionary(String name, String requirement) {
        super(name, requirement);
    }
}
