package com.kh.demo.service;


import com.kh.demo.domain.dto.AdminDTO;
import com.kh.demo.domain.dto.TrainerDTO;
import com.kh.demo.mapper.AdminMapper;
import com.kh.demo.mapper.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AdminServiceImpl")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper amapper;

    @Override
    public AdminDTO getDetail(String userid) {
        return amapper.findById(userid);
    }




}
