package org.y9nba.dict;

import org.y9nba.dict.util.DictUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public  class Dictionary {
    private final Map<String, DictEntry> entries = new HashMap<>();
    private final String name;
    private final String requirementString;
    private String pathDict;

    // region Constructors and Additional

    public Dictionary(String name) {
        this(name, "");
    }

    public Dictionary(String name, String requirement) {
        this.name = name;
        this.requirementString = requirement;
        init();
        loadEntries();
    }

    private void loadEntries() {
        try (BufferedReader br = new BufferedReader(new FileReader(pathDict))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    entries.put(parts[0], new DictEntry(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("!- Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void init() {
        try {
            if (this.name.isEmpty() || !this.name.matches("^[a-zA-Z0-9]*[.]txt$"))
                throw new IOException();

            String homePath = DictUtil.getDictionariesPath();
            File dictFile = new File(homePath + "\\" + this.name);

            if (!dictFile.exists())
                dictFile.createNewFile();

            pathDict = dictFile.getAbsolutePath();
        } catch (IOException e) {
            System.out.printf("!- Недопустимое название словаря |%s|!\n", this.name);
        }
    }

    // endregion

    // region Action of entries

    public void addEntry(String key, String value) {
        if (isValidKey(key)) {
            entries.put(key, new DictEntry(key, value));
            saveEntries();
            return;
        }

        System.out.printf("!~ Слово |%s| не подходит под требования\n", key);
        System.out.printf("*- У данного словаря требование к слову: %s\n", this.requirementString);
    }

    public void removeEntry(String key) {
        entries.remove(key);
        saveEntries();
    }

    public DictEntry findEntry(String key) {
        return entries.get(key);
    }

    private void saveEntries() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathDict))) {
            for (DictEntry entry : entries.values()) {
                bw.write(entry.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("!- Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public void showEntries() {
        for (DictEntry entry : entries.values()) {
            System.out.println(entry);
        }
    }

    private boolean isValidKey(String key) {
        return key.matches(this.requirementString);
    }

    // endregion

    public String getName() {
        return this.name;
    }
}
