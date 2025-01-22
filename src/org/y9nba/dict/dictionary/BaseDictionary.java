package org.y9nba.dict.dictionary;

import org.y9nba.dict.constant.Dictionaries;
import org.y9nba.dict.exception.dictionary.*;
import org.y9nba.dict.entry.DictEntry;
import org.y9nba.dict.service.DictService;
import org.y9nba.dict.util.DictUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BaseDictionary implements DictService {
    private final Map<String, DictEntry> entries = new HashMap<>();
    private String name;
    private String requirement;
    private String pathDict;

    public BaseDictionary(Dictionaries dictionary) {
        this(dictionary.getDictName(), dictionary.getRequirement());
    }

    public BaseDictionary(String name) {
        this(name, "");
    }

    public BaseDictionary(String name, String requirement) {
        try {
            setName(name);
            setRequirement(requirement);
            init();
            loadEntries();
        } catch (DictException e) {
            throw new RuntimeException(e);
        }

    }

    private void init() throws InvalidNameDictException {
        try {
            String homePath = DictUtil.getDictionariesPath();
            File dictFile = new File(homePath + "\\" + this.name);

            if (!dictFile.exists())
                dictFile.createNewFile();

            pathDict = dictFile.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void loadEntries() throws DontReadDictException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.pathDict))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    this.entries.put(parts[0], new DictEntry(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            throw new DontReadDictException("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public String getName() {
        return this.name;
    }

    public String getRequirement() {
        return requirement;
    }

    private void setName(String name) throws InvalidNameDictException {
        if (name.isEmpty() || !name.matches("^[a-zA-Z0-9]*[.]txt$"))
            throw new InvalidNameDictException(
                    String.format(
                            "Недопустимое название словаря '%s'! Он должен быть по образцу - 'name.txt'.\n", this.name
                    )
            );

        this.name = name;
    }

    private void setRequirement(String requirement) throws InvalidRequirementDictException {
        if (requirement == null)
            throw new InvalidRequirementDictException(
                    "Условие словаря не может иметь значение null!"
            );

        this.requirement = requirement;
    }

    public void addEntry(String key, String value) throws DontSaveDictException, InvalidKeyDictException {
        try {
            if (isValidKey(key)) {
                entries.put(key, new DictEntry(key, value));
                saveEntries();
                return;
            }

            throw new IOException();

        } catch (DontSaveDictException e) {
            throw new DontSaveDictException(e.getMessage());

        } catch (IOException e) {
            throw new InvalidKeyDictException(
                    String.format(
                            "Слово '%s' не подходит под требования. У данного словаря требование к слову: %s\n",
                            key, this.requirement
                    )
            );
        }
    }

    @Override
    public void removeEntry(String key) throws DontSaveDictException {
        entries.remove(key);
        saveEntries();
    }

    @Override
    public DictEntry findEntry(String key) {
        return entries.get(key);
    }

    @Override
    public void saveEntries() throws DontSaveDictException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathDict))) {
            for (DictEntry entry : entries.values()) {
                bw.write(entry.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DontSaveDictException("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    @Override
    public void showEntries() {
        for (DictEntry entry : entries.values()) {
            System.out.println(entry);
        }
    }

    @Override
    public boolean isValidKey(String key) {
        return this.requirement.isEmpty() || key.matches(this.requirement);
    }
}
