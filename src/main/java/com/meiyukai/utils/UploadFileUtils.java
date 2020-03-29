package com.meiyukai.utils;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class UploadFileUtils {
    public static final String UPLAOD_DIRECTORY = "src/main/resources/static/upload_imgs/";

    public static final String IMG_SERVER_PATH="http://118.31.19.39:8080/img_server/uploads/";

    public static final String DELETE_PATH="http://118.31.19.39:8080/img_server/delete?fileName=";

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

    /**
     * 跨服服务器实现文件上传
     */
    public static String uploadFileToServer(String finalName , MultipartFile uploadFile ) throws Exception{
        Client client = Client.create();
        WebResource webResource = client.resource(IMG_SERVER_PATH + finalName);
        webResource.put(uploadFile.getBytes());
        return IMG_SERVER_PATH+finalName;
    }


    /**
     * 获取文件的唯一名字
     * @param originalName
     * @return
     */
    public static String getFileName( String originalName){
        String suffix = originalName.substring(originalName.lastIndexOf("."));
        String unique = UUID.randomUUID().toString().replace("-","");
        return unique+suffix;
    }

    /**
     * 删除文件
     * @param iconPath 待上传的文件名
     * @return 处理的结果 success ：删除成功 ;  failed: 删除失败
     */
    public static String deleteFileByFileName(String iconPath){
        String fileName = iconPath.substring(iconPath.lastIndexOf("/")+1);
        RestTemplate restTemplate  = new RestTemplate();
        return  restTemplate.getForObject(DELETE_PATH+fileName , String.class);
    }






}
