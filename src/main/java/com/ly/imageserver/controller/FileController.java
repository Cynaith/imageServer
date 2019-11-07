package com.ly.imageserver.controller;


import com.ly.imageserver.domain.Images;
import com.ly.imageserver.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    ImagesRepository imagesRepository;

    @ResponseBody
    @RequestMapping("uploadFile")
    public String uploadFile(@RequestParam(value = "username") String username,@RequestParam(value = "file") MultipartFile file){

        System.out.println(username);
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        String filePath = "/Users/ly/IdeaProjects/imageServer/src/main/resources/static/imgs/";
       String filePath = "/root/home/nginx/iMart/images/";
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "http://47.101.171.252:81/static/" + fileName;
        Images images = new Images();
        images.setImgname(fileName);
        images.setUrl(filename);
        images.setUsername(username);
        imagesRepository.save(images);
        return filename;
    }
}
