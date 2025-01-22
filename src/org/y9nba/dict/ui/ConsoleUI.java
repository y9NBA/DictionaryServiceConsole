package org.y9nba.dict.ui;

import org.y9nba.dict.dictionary.BaseDictionary;
import org.y9nba.dict.entry.DictEntry;
import org.y9nba.dict.exception.dictionary.DictException;
import org.y9nba.dict.exception.ui.EmptyDictionariesException;
import org.y9nba.dict.exception.ui.UIException;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    public List<BaseDictionary> dictionaries;

    private final Scanner sc = new Scanner(System.in);

    public ConsoleUI(List<BaseDictionary> dictionaries) {
        try {
            if (dictionaries.isEmpty()) {
                throw new EmptyDictionariesException("Словари не были переданы! Список пуст!");
            }

            this.dictionaries = dictionaries;
        } catch (UIException e) {
            System.out.println("!!!~ " + e.getMessage());
            throw new RuntimeException();
        }
    }

    public void start() {
        BaseDictionary selectedDictionary = null;

        for (int choice = -1, action; ; ) {
            if (choice == -1) {
                System.out.printf(
                        "#~ Выберите один из словарей (1 - %d):\n" +
                        "#~ \t0) Выход\n",
                        dictionaries.size()
                );

                choice = sc.nextInt();

                if (choice == 0) {
                    return;

                } else if (choice < 0 || choice > dictionaries.size()) {
                    choice = -1;
                    System.out.println("*~ Ничего не найдено\n");
                    continue;
                }

                selectedDictionary = dictionaries.get(choice - 1);
            }

            System.out.printf(
                    "#~ Выберите действие со словарём '%s':\n" +
                    "#~ \t1) Просмотреть\n" +
                    "#~ \t2) Добавить\n" +
                    "#~ \t3) Удалить\n" +
                    "#~ \t4) Найти\n" +
                    "#~ \t5) Назад\n",
                    selectedDictionary.getClass().getSimpleName());

            action = sc.nextInt();
            sc.nextLine();

            System.out.println();

            switch (action) {
                case 1:
                    show(selectedDictionary);
                    break;
                case 2:
                    add(selectedDictionary);
                    break;
                case 3:
                    remove(selectedDictionary);
                    break;
                case 4:
                    find(selectedDictionary);
                    break;
                case 5:
                    choice = -1;
                    break;
                default:
                    break;
            }

            System.out.println();
        }
    }

    private void add(BaseDictionary dictionary) {
        System.out.println("*~ Добавление новой записи");
        System.out.println("+~ Введите слово-ключ:");
        String keyAdd = sc.nextLine();
        System.out.println("+~ Введите слово-значение:");
        String valueAdd = sc.nextLine();

        try {
            dictionary.addEntry(keyAdd, valueAdd);
        } catch (DictException e) {
            System.out.println("!~ " + e.getMessage());
        }
    }

    private void remove(BaseDictionary dictionary) {
        System.out.println("*~ Удаление записи");
        System.out.println("-~ Введите слово-ключ для удаления: ");
        String keyRemove = sc.nextLine();

        if (dictionary.findEntry(keyRemove) == null) {
            System.out.println("!~ Такой записи нет");
        } else {

            try {
                dictionary.removeEntry(keyRemove);
                System.out.println("*~ Запись удалена");
            } catch (DictException e) {
                System.out.println("!~ " + e.getMessage());
            }
        }
    }

    private void find(BaseDictionary dictionary) {
        System.out.println("*~ Поиск записи");
        System.out.println("?- Введите слово-ключ для поиска:");
        String keyFind = sc.nextLine();
        DictEntry foundDictEntry = dictionary.findEntry(keyFind);
        System.out.println("*~ " +
                (
                        foundDictEntry == null ?
                                "Ничего не найдено" : foundDictEntry.toString()
                )
        );
    }

    private void show(BaseDictionary dictionary) {
        System.out.println("*~ Просмотр записей");
        dictionary.showEntries();
    }


}
