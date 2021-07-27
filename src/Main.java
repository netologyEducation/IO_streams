import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    static List<String> gameDirs = Arrays.asList("/src/main", "/src/test",
            "/res/drawables", "/res/vectors", "/res/icons",
            "/savegames", "/temp");
    static List<String> javaFiles = Arrays.asList("Main.java", "Utils.java");
    static List<String> tempFiles = Arrays.asList("temp.txt");
    static final String GAME_DIR = "Games";


    public static void main(String[] args) throws IOException {
        String rootPath = Main.class.getResource("/").getPath() + GAME_DIR;
//        File fileTemp = new File(rootPath + "/temp/temp.txt");
        System.out.println(createStruct(rootPath, gameDirs));
        System.out.println(createFile(rootPath + "/src/main", javaFiles));
        System.out.println(createFile(rootPath + "/temp", tempFiles));
    }

    public static void writeLog(String fileName, String rowLog) throws IOException {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(rowLog);
            fw.flush();
        }

    public static String createFile(String rootPath, List<String> listFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (listFile.isEmpty()) return sb.append("Список файлов пуст").toString();
        else {
            for (String fileName : listFile) {
                sb.append(new Date() + " | В директории " + rootPath + " будет создан файл " + fileName);
                if(new File(rootPath + "//" + fileName).createNewFile()){
                    sb.append(" успешно!\n");
                }else {
                    sb.append(" не успешно!\n");
                }
            }
            writeLog(rootPath + "/temp/temp.txt", sb.toString());
            return sb.toString();
        }
    }

    public static String createStruct(String rootPath, List<String> listDirs) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (listDirs.isEmpty()) return sb.append("Список директорий пуст").toString();
        else{
            for (String dir : listDirs) {
                sb.append(new Date() + " | Будет создана директория " + rootPath + dir);
                if (new File(rootPath + "//" + dir).mkdirs()){
                    sb.append(" успешно!\n");
                }else {
                    sb.append(" не успешно!\n");
                }
            }
            writeLog(rootPath + "/temp/temp.txt", sb.toString());
            return sb.toString();
        }
    }
}
