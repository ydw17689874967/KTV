package com.tjetc.controller;

import com.tjetc.common.AdminConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/download")
public class DownloadController {
    @Autowired
    private AdminConstant adminConstant;

    /**
     * @param folder    文件夹 20210716
     * @param imageName 视频文件名称 uuid.mp4
     * @param response  响应对象
     */
    @RequestMapping("/image/{folder}/{imageName:.+}")
    public void loadVideo(@PathVariable("folder") String folder,
                          @PathVariable("imageName") String imageName,
                          HttpServletResponse response) {

        //文件输出流
        ServletOutputStream outputStream = null;
        //文件输入流
        FileInputStream fis = null;
        try {
            //获取输出流对象
            outputStream = response.getOutputStream();
            //拼接视频文件的路径
            StringBuilder sb = new StringBuilder();
            sb.append(adminConstant.getVideoBasePath()).append("/").append(folder).append("/").append(imageName);
            //文件真实的物理路径
            String realPath = sb.toString();
            //实例化file对象
            File file = new File(realPath);
            if (!file.exists()) {
                response.setStatus(404);
                return;
            }
            //获取文件输入流对象（读取视频文件）
            fis = new FileInputStream(file);
            //定义buffer缓存
            byte[] buffer = new byte[1024 * 10];
            int len;
            //文件输入流循环读取视频文件到缓存中，
            while ((len = fis.read(buffer)) != -1) {
                //循环的写入到输出流中
                outputStream.write(buffer, 0, len);
            }
            //刷新
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @RequestMapping("/video/{folder}/{videoName:.+}")
    public void load(@PathVariable("folder") String folder,
                          @PathVariable("videoName") String videoName,
                          HttpServletResponse response) {
        response.setContentType("video/mp4");
        //文件输出流
        ServletOutputStream outputStream = null;
        //文件输入流
        FileInputStream fis = null;
        try {
            //获取输出流对象
            outputStream = response.getOutputStream();
            //拼接视频文件的路径
            StringBuilder sb = new StringBuilder();
            sb.append(adminConstant.getVideoBasePath()).append("/").append(folder).append("/").append(videoName);
            //文件真实的物理路径
            String realPath = sb.toString();
            //实例化file对象
            File file = new File(realPath);
            if (!file.exists()) {
                response.setStatus(404);
                return;
            }
            //获取文件输入流对象（读取视频文件）
            fis = new FileInputStream(file);
            //定义buffer缓存
            byte[] buffer = new byte[1024 * 10];
            int len;
            //文件输入流循环读取视频文件到缓存中，
            while ((len = fis.read(buffer)) != -1) {
                //循环的写入到输出流中
                outputStream.write(buffer, 0, len);
            }
            //刷新
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
