import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final WordsDict latinDictionary;
    private final NumDict numDictionary;

    private final Scanner scanner;

    private final List<String> mainMenuMess = Arrays.asList(
            "1. Словарь с цифрами", "2. Словарь с латинскими буквами","3. Показать содержимое обоих словарей",
            "4. Выход"
    );

    private final List<String> operMenuMess = Arrays.asList(
            "1. Найти по ключу", "2. Дабавить слово", "3. Удалить слово",
            "4. Вывод всего словаря", "5. Назад"
    );

     public Menu() throws IOException {
         latinDictionary = new WordsDict("latinFile.txt");
         numDictionary = new NumDict("numFile.txt");
         scanner = new Scanner(System.in);
     }

     private void operMenu(Dictionary dictionary){
         System.out.println();
         boolean flag = true;
         while(flag){
             try{
                 operMenuMess.forEach(System.out::println);
                 switch (scanner.nextLine()) {
                     case ("1"):
                         System.out.print("Введите слово: ");
                         System.out.println(dictionary.search(scanner.nextLine()));
                         System.out.println();
                         break;
                     case ("2"):
                         System.out.print("Введите слово: ");
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
                         System.out.print("Введите слово: ");
                         String dellStr = dictionary.dell(scanner.nextLine());
                         if(!dellStr.isEmpty()){
                             System.out.println(dellStr + " - удалено");
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

     public void start(){
        boolean flag = true;
        while (flag){
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
                    flag = false;
                    break;
                default:
                    System.out.println("Такого действия нет!");
            }
        }
     }
    public static String getFilePath(){
        System.out.println("Введите имя файла в .ser формате без (.ser)");
        String mapPath = inputLine() ;
        File f = new File(mapPath+ ".ser");
        if(!f.exists() || f.isDirectory()){
            return ""; }
        return  mapPath;
    }
}
