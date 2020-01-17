package com.hewei.hewei.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
}
