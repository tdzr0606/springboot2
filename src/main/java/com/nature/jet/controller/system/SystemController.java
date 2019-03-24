package com.nature.jet.controller.system;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.utils.Fields;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.IOException;

/**
 * springboot2
 * SystemController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 23:40
 */
@Controller
@Slf4j
public class SystemController extends BaseController
{
    @Value("${web.upload-path}")
    String rootPath;

    /**
     * To index model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-19 23:45:11
     */
    @RequestMapping(value = "/")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.addObject("nav","index");
        modelAndView.setViewName("au/index");
        return modelAndView;
    }


    /**
     * 自定义错误处理
     * Error model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-19 23:42:20
     */
    @RequestMapping(value = "/common/error")
    public ModelAndView error()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/error500");
        return modelAndView;
    }


    /**
     * 读取文件内容
     * Read file common result.
     *
     * @param fileUrl the file url
     * @return the common result
     * @author:竺志伟
     * @date :2019-03-22 21:22:22
     */
    @RequestMapping(value = "/system/readfile")
    @ResponseBody
    public CommonResult readFile(@RequestParam(value = "fileUrl",required = true,defaultValue = "") String fileUrl)
    {
        int code = CommonResult.SUCCESS;
        String msg = "";
        String fileContent = "";
        log.info("读取文件内容:{}", fileUrl);
        try
        {
            File file = new File(rootPath, fileUrl);
            if(file.exists())
            {
                fileContent = FileUtils.readFileToString(file, Fields.CHARACTER_ENCODING);
                msg = "文件内容读取成功";
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            code = CommonResult.FAILS;
            msg = "文件内容读取失败";
            log.error("读取文件内容错误", e);
        }
        finally
        {
            log.info("文件内容读取完成:{}", msg);
            return resultWrapper(code, msg, fileContent);
        }
    }
}
