package sortTool;


import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Component
public class AutoSortImpl implements AutoSort {
    private static File f;
    String path = "";

    List<String>listName = new LinkedList();
    HashMap<String,String> suffixName = new HashMap<>();
    HashSet<String> suffixSet = new HashSet();
    HashSet<String> pathSet = new HashSet<>();

    @Value("classpath:conf/suffix.json")
    public Resource suffixJson;

    @Value("classpath:conf/paths.json")
    public Resource pathsJson;

    private void jsonArrayToArr(JSONArray arr,String key){
        for(int i=0;i<arr.length();i++){
            String value = (String) arr.get(i);
            suffixName.put(value,key);
        }
    }

    private void scanField() {
        f = new File(path);
        File[] fs = f.listFiles();
        for(File ff : fs){
            if(!ff.isDirectory()){
                String fileName = ff.getName();
                inputFileNameToMap(fileName);
            }
        }
    }

    @Override
    public void sort() {
        try {
            loadPathJson();
            loadJsonParam();
            for(String path : pathSet){
                this.path = path;
                createFolder();
                scanField();
                for(int i=0;i<listName.size();i++){
                    String name = listName.get(i);
                    try{
                        String suffix = getSuffix(name);
                        String prefix = getPrefix(name);
                        if(!isEmpty(suffix) && suffixName.containsKey(suffix)){
                            String folder = suffixName.get(suffix);
                            String newPath = path + "\\" + folder + "\\" + prefix + "." + suffix;
                            String oldPath = path + "\\" + prefix + "." + suffix;
                            move(newPath,oldPath);
                            System.out.println(oldPath + "------>" + newPath);
                        }
                    } catch (Exception e){
                    }
                }
                listName.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isExist(String name){
        f = new File(name);
        return f.exists();
    }


    private void inputFileNameToMap(String input){
        listName.add(input);
    }

    private void createFolder(){
        for(String str : suffixSet){
            String temp = path + "\\" +str;
            if (!isExist(temp)) {
                f.mkdirs();
            }
        }
    }

    private String getPrefix(String fileName){
        int index = fileName.lastIndexOf(".");
        return fileName.substring(0,index);
    }

    private String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index+1);
        return suffix;
    }

    private void loadJsonParam() throws IOException {
        String json =  IOUtils.toString(suffixJson.getInputStream(), Charset.forName("UTF-8"));
        JSONObject jsonObject = new JSONObject(json);
        traverseJson(jsonObject);
    }

    private void loadPathJson() throws IOException {
        String pahtJson = IOUtils.toString(pathsJson.getInputStream(), Charset.forName("UTF-8"));
        JSONObject jsonObject = new JSONObject(pahtJson);
        if (jsonObject != null) {
            JSONArray arr = jsonObject.getJSONArray("path");
            for(Object path : arr){
                pathSet.add((String)path);
            }
        }
    }

    private Object traverseJson(Object json) {
        // check null
        if (json == null) {
            return null;
        }
        try {
            if (json instanceof JSONObject) {
                JSONObject retObj = new JSONObject();
                JSONObject jsonObj = (JSONObject) json;
                Iterator it = jsonObj.keys();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    Object value = jsonObj.get(key);
                    suffixSet.add(key);
                    jsonArrayToArr((JSONArray) value,key);
                    //System.out.println();
                }
                return retObj;
            }
        } catch (Exception e) {
            // deal Exception or throw it
        }
        return null;
    }

    private void delFile(String oldFile){
        f = new File(oldFile);
        f.delete();
    }

    @Override
    public void move(String newPath,String oldPath){
        f = new File(oldPath);
        if(f.exists()){
            f.renameTo(new File(newPath));
        }
    }

    private Boolean isEmpty(String str){
        if(str != null && str.length() != 0){
            return false;
        }
        return true;
    }
}
