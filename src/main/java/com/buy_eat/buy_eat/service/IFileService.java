package com.buy_eat.buy_eat.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.buy_eat.buy_eat.entity.FileData;

public interface IFileService {
    
    FileData getOne(Integer Id);

    FileData getFileById(Integer imgId);

    FileData getAll();

    FileData save(FileData fileData);

    FileData save(MultipartFile multipartFile);

    List<FileData> saveAll(List<FileData> fileData);

    List<FileData>  saveAll(FileData fileData);

}
