import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        try {

            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String daoType = properties.getProperty("DAOType");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}