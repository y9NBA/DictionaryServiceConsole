import org.y9nba.dict.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dictionary dict1 = new Dictionary("dict1.txt", "[a-zA-Z]{4}");
        Dictionary dict2 = new Dictionary("dict2.txt", "\\d{5}");

        Dictionary selectedDictionary = null;

        for (int choice = -1, action; ; ) {
            if (choice == -1) {
                System.out.println("#~ Выберите один из словарей:\n" +
                        "#~ \t1) Словарь 1\n" +
                        "#~ \t2) Словарь 2\n" +
                        "#~ \t0) Выход");

                choice = sc.nextInt();

                if (choice == 0)
                    return;

                selectedDictionary = choice == 1 ? dict1 : dict2;
            }

            System.out.printf("#~ Выберите действие со словарём |%s|:\n" +
                    "#~ \t1) Просмотреть\n" +
                    "#~ \t2) Добавить\n" +
                    "#~ \t3) Удалить\n" +
                    "#~ \t4) Найти\n" +
                    "#~ \t5) Назад\n", selectedDictionary.getName());

            action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1:
                    System.out.println("*~ Просмотр записей");
                    selectedDictionary.showEntries();
                    break;
                case 2:
                    System.out.println("*~ Добавление новой записи");
                    System.out.println("+~ Введите слово-ключ:");
                    String keyAdd = sc.nextLine();
                    System.out.println("+~ Введите слово-значение:");
                    String valueAdd = sc.nextLine();
                    selectedDictionary.addEntry(keyAdd, valueAdd);
                    break;
                case 3:
                    System.out.println("*~ Удаление записи");
                    System.out.println("-~ Введите слово-ключ для удаления: ");
                    String keyRemove = sc.nextLine();

                    if (selectedDictionary.findEntry(keyRemove) == null) {
                        System.out.println("!~ Такой записи нет");
                    } else {
                        selectedDictionary.removeEntry(keyRemove);
                        System.out.println("*~ Запись удалена");
                    }

                    break;
                case 4:
                    System.out.println("*~ Поиск записи");
                    System.out.println("?- Введите слово-ключ для поиска:");
                    String keyFind = sc.nextLine();
                    DictEntry foundDictEntry = selectedDictionary.findEntry(keyFind);
                    System.out.println("*~ " +
                            (
                                    foundDictEntry == null ?
                                            "Ничего не найдено" : foundDictEntry.toString()
                            )
                    );
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
}