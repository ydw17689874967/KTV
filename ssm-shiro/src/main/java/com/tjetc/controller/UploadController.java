package com.tjetc.controller;

import com.tjetc.common.AdminConstant;
import com.tjetc.common.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private AdminConstant adminConstant;


    @RequestMapping("/image")
    @ResponseBody
    public JsonResult uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        //获取上传文件的名称(文件名称.后缀名)
        String originalFilename = file.getOriginalFilename();
        //获取文件的后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        //判断上传文件的后缀名是否符合要求，不符合要求禁止上传
        if ("jpg".equals(suffix) || "png".equals(suffix)) {
            //拼接文件夹的路径：日期
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            String strDate = dtf.format(LocalDate.now());
            //文件的名称：uuid（去掉了中杠）+文件后缀名
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
            //拼接上传文件的路径
            StringBuilder sb = new StringBuilder();
            sb.append(strDate).append("/").append(fileName);
            //返回结果值的url路径
            String returnUrl = sb.toString();
            sb.insert(0,adminConstant.getVideoBasePath()+"/");
            //文件存储的物理路径
            String realPath = sb.toString();
            //实例化File对象
            File file1 = new File(realPath);
            if (!file1.exists()){
                file1.mkdirs();
            }
            //MultipartFile对象 tranferTo(File)
            file.transferTo(file1);
            //返回结果
            return new JsonResult(1,"上传成功",returnUrl);
        } else {
            return new JsonResult(1, "上传文件格式不正确，只能上传MP4", null);
        }
    }
    @RequestMapping("/video")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        //获取上传文件的名称(文件名称.后缀名)
        String originalFilename = file.getOriginalFilename();
        //获取文件的后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        //判断上传文件的后缀名是否符合要求，不符合要求禁止上传
        if ("mp4".equals(suffix) ) {
            //拼接文件夹的路径：日期
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            String strDate = dtf.format(LocalDate.now());
            //文件的名称：uuid（去掉了中杠）+文件后缀名
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
            //拼接上传文件的路径
            StringBuilder sb = new StringBuilder();
            sb.append(strDate).append("/").append(fileName);
            //返回结果值的url路径
            String returnUrl = sb.toString();
            sb.insert(0,adminConstant.getVideoBasePath()+"/");
            //文件存储的物理路径
            String realPath = sb.toString();
            //实例化File对象
            File file1 = new File(realPath);
            if (!file1.exists()){
                file1.mkdirs();
            }
            //MultipartFile对象 tranferTo(File)
            file.transferTo(file1);
            //返回结果
            return new JsonResult(1,"上传成功",returnUrl);
        } else {
            return new JsonResult(1, "上传文件格式不正确，只能上传MP4", null);
        }
    }
}
