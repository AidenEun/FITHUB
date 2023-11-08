package com.kh.demo.mapper;


import com.kh.demo.domain.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    AdminDTO findById(String userid);

}
