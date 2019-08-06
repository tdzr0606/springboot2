package com.nature.jet.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * springboot2
 * NewsFileTools
 *
 * @Author: 竺志伟
 * @Date: 2019-03-22 21:10
 */
public class NewsFileTools
{

    /**
     * 保存文件
     * Save to file string.
     *
     * @param rootPath    the root path
     * @param fileContent the file content
     * @return the string
     * @author:竺志伟
     * @date :2019-03-22 21:14:38
     */
    public static String saveToFile(String rootPath, String fileContent)
    {
        String filePath = "";
        try
        {
            String datePath = Tools.getNowDate("yyyyMMdd");
            File parentFile = new File(rootPath, "news/file/" + datePath + "/");
            if(!parentFile.exists())
            {
                parentFile.mkdirs();
            }
            String fileName = Tools.getNowDateTime("yyyyMMddHHmmssS") + "_" +
                    UUID.randomUUID().toString().replace("-", "").substring(0, 10) + ".htm";
            FileUtils.write(new File(parentFile, fileName), fileContent, Fields.CHARACTER_ENCODING);
            filePath = "/news/file/" + datePath + "/" + fileName;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return filePath;
        }
    }


    /**
     * 更新文件
     * Update file.
     *
     * @param filePath    the file path
     * @param fileContent the file content
     * @author:竺志伟
     * @date :2019-03-22 21:52:08
     */
    public static void updateFile(String filePath, String fileContent)
    {
        try
        {
            File file = new File(filePath);
            if(file.exists())
            {
                file.delete();
                file.createNewFile();
            }
            FileUtils.write(file, fileContent, Fields.CHARACTER_ENCODING);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
