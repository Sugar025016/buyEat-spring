package com.buy_eat.buy_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.FileData;


@Repository
public interface IFileDateRepository extends JpaRepository<FileData,Integer> {

    @Override
    FileData getOne(Integer fileDataId);
}
