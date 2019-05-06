package service;

import org.junit.Test;

import java.io.File;

public class Main {
    @Test
    public void test(){
        String oldPath = "E:\\chrome download\\115276txt.zip";
        String newPath = "E:\\chrome download\\jar\\115276txt.zip";
        File f = new File(oldPath);
        if(f.exists()){
            f.renameTo(new File(newPath));
        }
    }
}
