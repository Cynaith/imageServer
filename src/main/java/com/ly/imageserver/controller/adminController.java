package com.ly.imageserver.controller;



import com.ly.imageserver.repository.ImagesRepository;
import com.ly.imageserver.utils.PageWrapperForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class adminController {

    @Autowired
    ImagesRepository imagesRepository;

    @ResponseBody
    @RequestMapping("/getImages")
    public PageWrapperForLayui getAllUser(
            @RequestParam(name="page",defaultValue = "1") int page,
            @RequestParam(name="limit",defaultValue = "10") int size

    ) {
        Pageable pageable =PageRequest.of(page-1,size);

        return PageWrapperForLayui.markSuccess((int)imagesRepository.count(),imagesRepository.findAll(pageable));
    }



}
