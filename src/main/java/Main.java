import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        String str = new File("C:\\Users\\Admin\\IdeaProjects\\CRUD_Tomcat\\src\\main\\resources\\config.properties").getAbsolutePath();
        System.out.println(str);
    }
}