import java.io.IOException;

public class NumDictionary extends AbsDictionary{
    public NumDictionary(String path) throws IOException {
        super(path);
    }

    @Override
    public String getRagex() {
        return "^\\d{5}$";
    }
}
