import java.io.IOException;

public class WordDiction extends Diction{

    public WordDiction(String path) throws IOException {
        super(path);
    }
    @Override
    public String getEx(){return "Ключь должен состоять из 4 латинских букв";}

    @Override
    public String getRagex() {
        return "^[a-zA-Z]{4}$";
    }
}
