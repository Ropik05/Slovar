import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private LatinDictionary latinDictionary;
    private NumDictionary numDictionary;

    private Scanner scanner;

    private final List<String> mainMenuMess = Arrays.asList(
            "1. Словарь с цифрами", "2. Словарь с латинскими буквами","3. Показать содержимое обоих словарей",
            "4. Выход"
    );

    private final List<String> operMenuMess = Arrays.asList(
            "1. Найти по ключу", "2. Добавить слово", "3. Удалить слово",
            "4. Вывод всего словаря", "5. Другой файл","6. Назад"
    );

    public Menu() throws IOException {
        try {
            File latinFile = new File("latinFile.txt");
            File numFile = new File("numFile.txt");

            if(!latinFile.exists() && !numFile.exists()){
                File latin = new File("BaseLatinFile.txt");
                File num = new File("BaseNumFile.txt");
                latin.createNewFile();
                num.createNewFile();
                latinDictionary = new LatinDictionary(latin.getPath());
                numDictionary = new NumDictionary(num.getPath());
            }
            else if (!latinFile.exists()) {
                File def = new File("defaultLatinFile.txt");
                def.createNewFile();
                latinDictionary = new LatinDictionary(def.getPath());
                numDictionary = new NumDictionary("numFile.txt");
            } else if (!numFile.exists()) {
                File def = new File("defaultNumFile.txt");
                def.createNewFile();
                numDictionary = new NumDictionary(new File("defaultNumFile.txt").getPath());
                latinDictionary = new LatinDictionary("latinFile.txt");
            } else {
                latinDictionary = new LatinDictionary("latinFile.txt");
                numDictionary = new NumDictionary("numFile.txt");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Default file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An IO error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        scanner = new Scanner(System.in);
    }

    private void operMenu(AbsDictionary dictionary){
        System.out.println();
        boolean flag = true;
        while(flag){
            try{
                operMenuMess.forEach(System.out::println);
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
                        dictionary.setPath(scanner.nextLine());
                        break;
                    case ("6"):
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

    public void start(){
        while (true){
            mainMenuMess.forEach(System.out::println);
            switch (scanner.nextLine()){
                case ("1"):
                    operMenu(numDictionary);
                    break;
                case ("2"):
                    operMenu(latinDictionary);
                    break;
                case("3"):
                    System.out.println("Словарь с цифрами");
                    System.out.println(numDictionary);
                    System.out.println("Словарь с латинскими буквами");
                    System.out.println(latinDictionary);
                    break;
                case ("4"):
                    System.exit(0);
                default:
                    System.out.println("Такого действия нет!");
            }
        }
    }
}
