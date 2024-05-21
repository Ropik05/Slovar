import java.io.IOException;

public class LatinDictionary extends Diction{

    public LatinDictionary(String path) throws IOException {
        super(path);
    }

    @Override
    public String getRagex() {
        return "^[a-zA-Z]{4}$";
    }
}
