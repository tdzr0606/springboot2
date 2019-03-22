package com.nature.jet.controller.system;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * springboot2
 * UploadController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-22 20:32
 */
@Controller
@Slf4j
public class UploadController extends BaseController
{
    @Value("${web.upload-path}")
    String rootPath;

    /**
     * Upload img common result.
     *
     * @param imgFile the img file
     * @return the common result
     * @author:竺志伟
     * @date :2019-03-22 20:36:58
     */
    @RequestMapping(value = "/upload/layui/img")
    @ResponseBody
    public CommonResult uploadImg(@RequestParam(value = "file", required = true) MultipartFile imgFile)
    {
        log.info("layui富文本编辑器上传图片开始");
        Map<String, String> dataMap = new HashMap<>();
        int code = 0;
        String msg = "图片上传成功";
        try
        {
            String datePath = Tools.getNowDate("yyyyMMdd");
            File parentFile = new File(rootPath, "layui/img/" + datePath + "/");
            if(!parentFile.exists())
            {
                parentFile.mkdirs();
            }
            String fileName = Tools.getNowDateTime("yyyyMMddHHmmssS") + "_" +
                    UUID.randomUUID().toString().replace("-", "").substring(0, 10) + "." +
                    FilenameUtils.getExtension(imgFile.getOriginalFilename());

            FileUtils.copyInputStreamToFile(imgFile.getInputStream(), new File(parentFile.getPath(), fileName));
            dataMap.put("src", "/files/layui/img/" + datePath + "/" + fileName);
            dataMap.put("title", imgFile.getOriginalFilename());
        }
        catch(IOException e)
        {
            e.printStackTrace();
            log.error("富文本编辑器图片上传错误", e);
            code = 1;
            msg = "图片上传错误";
            dataMap.put("src", "");
            dataMap.put("title", imgFile.getOriginalFilename());
        }
        finally
        {
            log.info("layui富文本编辑器上传图片结束:{}", dataMap.get("title"));
            return resultWrapper(code, msg, dataMap);
        }

    }
}
