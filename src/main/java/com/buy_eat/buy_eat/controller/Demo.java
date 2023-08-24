package com.buy_eat.buy_eat.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.buy_eat.buy_eat.entity.FileData;
import com.buy_eat.buy_eat.model.response.FileResponse;
import com.buy_eat.buy_eat.service.IFileService;

@RestController
@RequestMapping("/api")
public class Demo {

    @Autowired
    IFileService fileService;
    @RequestMapping(path = "", method = RequestMethod.POST)
    public String getLogin() {
        System.out.println("有鬼阿..........=.=+，1");

        return "login";
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getLogin2() {
        System.out.println("有鬼阿..........=.=+， 2");

        return "login";
    }

    @Transactional
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<FileResponse> uploadFile(MultipartFile file) {

        System.out.println(file.getName());

        FileData save = fileService.save(file);

        FileResponse fileResponse = new FileResponse(save.getId(),  save.getFileName());
        return ResponseEntity.ok().body(fileResponse);
    }


}
