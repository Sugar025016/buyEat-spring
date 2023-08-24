package com.buy_eat.buy_eat.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.buy_eat.buy_eat.entity.FileData;
import com.buy_eat.buy_eat.repository.IFileDateRepository;
import com.buy_eat.buy_eat.service.IFileService;


@Transactional
@Service
public class FileService implements IFileService {

    @Autowired
    IFileDateRepository iFileDateRepository;

    @Value("/home/jessie/work/my/img/")
    String ubuntuImgUrl;

    @Override
    public FileData getOne(Integer id) {
//        FileData one = iImageRepository.getOne(id);
        return null;
    }

    @Override
    public FileData getAll() {
        return null;
    }

    @Override
    public FileData save(FileData fileData) {
        return null;
    }


    @Override
    public FileData save(MultipartFile multipartFile) {
//        String[] suffix = multipartFile.getOriginalFilename().split(".");
        String originalFilename=multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        Date date = new Date();
        Long round = Math.round(Math.random() * 10000);
        Long time = date.getTime();
        String fileName= String.valueOf(time)+round;
//        String filePath = imagePutUrl+"/"+fileName+suffix;
        String filePath = ubuntuImgUrl+fileName+suffix;
        File file = new File(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileData fileData = new FileData(multipartFile,suffix,fileName);
        FileData save = iFileDateRepository.save(fileData);
        
        return save;
    }

    @Override
    public List<FileData> saveAll(List<FileData> fileData) {
        return null;
    }

    @Override
    public List<FileData> saveAll(FileData fileData) {
        return null;
    }
}
