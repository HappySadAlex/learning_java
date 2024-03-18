import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class findFiles {
    public static List<File> listFiles = new ArrayList<File>();

    public static List<File> parseFiles(File rootFile){
        if(rootFile.isDirectory()){
            File[] directoryFiles = rootFile.listFiles();
            if(directoryFiles != null) {
                for(File file : directoryFiles){
                    if(file.isDirectory()){
                        parseFiles(file);
                    }
                    else {
                        if(file.getName().toLowerCase().endsWith(".json") || file.getName().toLowerCase().endsWith(".csv")){
                            listFiles.add(file);
                        }
                    }
                }
            }
        }
        return listFiles;
    }
}
