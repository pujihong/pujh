package com.hewei.pujh.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {
    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @param localPath     文件保存路径
     * @param date          日期
     * @param fileName      文件名称
     * @throws IOException
     */
    public static void upload(MultipartFile multipartFile, String localPath, String date, String fileName) throws IOException {
        File dir = new File(localPath + File.separator, date);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File targetFile = new File(localPath + File.separator, date + File.separator + fileName);
        if (!targetFile.exists()) {
            multipartFile.transferTo(targetFile);
        }
    }

    /**
     * 将文件转换成byte数组
     *
     * @param file 文件
     * @return byte[]
     */
    public static byte[] File2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * 根据byte数组，生成文件
     * filePath  文件路径
     * fileName  文件名称（需要带后缀，如*.jpg、*.java、*.xml）
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists() && !dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
