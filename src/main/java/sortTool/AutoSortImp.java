package sortTool;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AutoSortImp implements AutoSort {

    @Override
    public void move(String newPath, String oldPath) {
        File f = new File(oldPath);
        if(f.exists()){
            f.renameTo(new File(newPath));
        }
    }

    @Override
    public void delFile(String oldFile) {
        File f = new File(oldFile);
        f.delete();
    }
}
