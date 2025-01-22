package org.y9nba.dict;

import org.y9nba.dict.dictionary.BaseDictionary;
import org.y9nba.dict.dictionary.FiveNumberDictionary;
import org.y9nba.dict.dictionary.FourLatSymbolDictionary;
import org.y9nba.dict.ui.ConsoleUI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<BaseDictionary> dictionaries = new ArrayList<>();
        dictionaries.add(new FiveNumberDictionary());
        dictionaries.add(new FourLatSymbolDictionary());
        dictionaries.add(new BaseDictionary("myDict.txt", ""));

        ConsoleUI app = new ConsoleUI(dictionaries);
        app.start();
    }
}