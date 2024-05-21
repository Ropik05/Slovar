import java.io.IOException;

public class NumDictionary extends Diction{
    public NumDictionary(String path) throws IOException {
        super(path);
    }

    @Override
    public String getRagex() {
        return "^\\d{5}$";
    }
}
