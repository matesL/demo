package com.example.webreact.mapper;

import com.example.webreact.entity.uploadimage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadMapper {
    void save(uploadimage upload);
}
