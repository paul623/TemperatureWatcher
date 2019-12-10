package Manager;

import java.io.*;

public class FileManager {
    /**
     * 保存文件到指定目录
     * @param data 数据
     * @param path 文件路径
     * */
    public static void saveDataIntoFile(String data,String path){
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
            FileWriter fileWriter=new FileWriter(file,false);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 读取指定目录下的文件内容，若不存在返回空
     * @param filePath 文件目录
     * */
    public static String  readFile(String filePath){
        try(FileReader reader=new FileReader(filePath);
            BufferedReader bufferedReader=new BufferedReader(reader)) {
            String line;
            String data="";
            while((line=bufferedReader.readLine())!=null){
                data=data+line;
            }
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
