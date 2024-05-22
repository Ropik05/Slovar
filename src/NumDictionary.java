import java.io.IOException;

public class NumDictionary extends Diction{
    public NumDictionary(String path) throws IOException {
        super(path);
    }
    @Override
    public String getEx(){return "Ключь должен состоять из 5 чисел";}

    @Override
    public String getRagex() {
        return "^\\d{5}$";
    }
}
