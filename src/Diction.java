import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Diction {
    private Map<String,String> dictionary = new HashMap<String,String>();
    private String path;

    public Diction(String path) throws IOException {
        this.path = path;
        fill(path);
    }

    public String getPath() {
        return path;
    }
    private String type;

    public abstract String getEx();
    public abstract String getRagex();
    public boolean add(String key, String val){
        if(!key.matches(getRagex()) || dictionary.containsKey(key)){
            throw new IllegalArgumentException("Формат ключа " + key + " не подходит для данного словаря \n "+ getEx() );
        }
        dictionary.put(key, val);
        return true;
    }

    public String dell(String key){
        if(!dictionary.containsKey(key)){
          throw new IllegalArgumentException("Слово не найдено!");
        }
        return key + " - " + dictionary.remove(key);
    }

    public String search(String key){
        if(!dictionary.containsKey(key)){
            throw new IllegalArgumentException("Слово не найдено!");
        }
        return dictionary.get(key);
    }

    public void fill(String path) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    add(parts[0], parts[1]);
                } else {
                    System.err.println("Строка " + line + " не соответствует формату \"ключ - значение\"");
                }
            }
        }
    }

    public boolean save(String path){
        try (FileWriter fw = new FileWriter(path, false)) {
            fw.write(toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean save(){
        return save(getPath());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            builder.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }
}
