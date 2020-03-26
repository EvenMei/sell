package com.meiyukai.utils;


import java.io.File;
import java.util.UUID;

public class UploadFileUtils {
    public static final String UPLAOD_DIRECTORY = "src/main/resources/static/upload_imgs/";

    public static final String IMG_SERVER_PATH="http://118.31.19.39:8080/img_server/uploads/";

    /**
     * 上传到项目的根目录下
     * @param fileName
     * @return
     */
    public static File getTransferedFile(String fileName){
        String newName = UUID.randomUUID().toString().replace("-", "");
        String suffixName = fileName.substring(fileName.lastIndexOf(".")); //后缀名
        File file = new File(UPLAOD_DIRECTORY);
        if(!file.exists()){
            file.mkdirs(); //递归创建文件夹
        }
        File finalFIle = new File(file.getAbsolutePath() , newName+suffixName);
        return finalFIle;
    }


    public static String getFileName( String originalName){
        String suffix = originalName.substring(originalName.lastIndexOf("."));
        String unique = UUID.randomUUID().toString().replace("-","");
        return unique+suffix;
    }

}
