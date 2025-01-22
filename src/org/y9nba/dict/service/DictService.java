package org.y9nba.dict.service;

import org.y9nba.dict.exception.dictionary.DontSaveDictException;
import org.y9nba.dict.entry.DictEntry;


public interface DictService {

    void removeEntry(String key) throws DontSaveDictException;

    DictEntry findEntry(String key);

    void saveEntries() throws DontSaveDictException;

    void showEntries();

    boolean isValidKey(String key);

}
