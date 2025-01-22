package org.y9nba.dict.dictionary;

import org.y9nba.dict.constant.Dictionaries;

public class FourLatSymbolDictionary extends BaseDictionary {
    public FourLatSymbolDictionary() {
        this(Dictionaries.FourLatSymbolDict);
    }

    private FourLatSymbolDictionary(Dictionaries dictionary) {
        super(dictionary);
    }

    public FourLatSymbolDictionary(String name) {
        super(name);
    }

    public FourLatSymbolDictionary(String name, String requirement) {
        super(name, requirement);
    }
}
