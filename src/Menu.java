import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private WordDiction wordDictionary;
    private NumDictionary numDictionary;

    private Scanner scanner;

    private final List<String> mainMenuMess = Arrays.asList(
            "1. Словарь чисел", "2. Словарь с латинскими словами","3. Показать содержимое обоих словарей",
            "4. Выход"
    );

    private final List<String> openMenuMess = Arrays.asList(
            "1. Найти по ключу", "2. Добавить слово", "3. Удалить слово",
            "4. Вывод всего словаря","5. Назад"
    );

    private void operMenu(Diction dictionary){
        System.out.println();
        boolean flag = true;
        scanner = new Scanner(System.in);
        while(flag){
            try{
                openMenuMess.forEach(System.out::println);
                switch (scanner.nextLine()) {
                    case ("1"):
                        System.out.print("Введите ключ: ");
                        System.out.println(dictionary.search(scanner.nextLine()));
                        System.out.println();
                        break;
                    case ("2"):
                        System.out.print("Введите ключ: ");
                        String key = scanner.nextLine();
                        System.out.print("Введите перевод: ");
                        String val = scanner.nextLine();
                        if(dictionary.add(key,val)){
                            System.out.println(key + " - " + val + ": успешно добавлено");
                            dictionary.save();
                        }
                        System.out.println();
                        break;
                    case ("3"):
                        System.out.print("Введите ключ: ");
                        String dellStr = dictionary.dell(scanner.nextLine());
                        if(!dellStr.isEmpty()){
                            System.out.println(dellStr + " - удален");
                            dictionary.save();
                        }
                        System.out.println();
                        break;
                    case ("4"):
                        System.out.println(dictionary);
                        break;
                    case ("5"):
                        flag = false;
                        break;
                    default:
                        System.out.println("Такого действия нет!");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
    public boolean checkFile(String path){
        File baseFile = new File(path);
        if(baseFile.exists()){
            return true;
        }
        else{
            System.out.println("Желаете создать файл с таким именем?(Да/Нет)");
            String x = scanner.nextLine();
            if (x.equals("Да")|| x.equals("да")){
                File def = new File(path);
                try {
                    def.createNewFile();
                    return true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(x.equals("Нет")|| x.equals("нет")) return false;
        }
        return false;
    }

    public void start(){
        while (true){
            scanner = new Scanner(System.in);
            mainMenuMess.forEach(System.out::println);
            switch (scanner.nextLine()){
                case ("1"):
                    System.out.println("Введите имя файла со словарем(с указанием типа файла)");
                    String path = scanner.nextLine();
                    if(!path.matches(".*txt$")) {
                        System.out.println("Недопустимый тип файла\n Возможные типы файлов: .txt");
                        break;
                    }
                    else {
                        try {
                            if(checkFile(path)) {
                                numDictionary = new NumDictionary(path);
                                if(numDictionary.worked) {
                                    operMenu(numDictionary);
                                }
                                else
                                    break;
                            }
                            else {
                                System.out.println("Данный файл не существует(с указанием типа файла)");
                                break;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case ("2"):
                    System.out.println("Введите имя файла со словарем");
                    path = scanner.nextLine();
                    if(!path.matches(".*txt$")) {
                        System.out.println("Недопустимый тип файла\n Возможные типы файлов: .txt");
                        break;
                    }
                    else {
                        try {
                            if (checkFile(path)) {
                                wordDictionary = new WordDiction(path);
                                if(wordDictionary.worked) {
                                    operMenu(wordDictionary);
                                }
                                else
                                    break;
                            } else {
                                System.out.println("Данный файл не существует(с указанием типа файла)");
                                break;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                        break;
                case("3"):
                    System.out.println("Словарь с цифрами");
                    System.out.println(numDictionary);
                    System.out.println("Словарь с латинскими буквами");
                    System.out.println(wordDictionary);
                    break;
                case ("4"):
                    System.exit(0);
                default:
                    System.out.println("Такого действия нет!");
            }
        }
    }
}
