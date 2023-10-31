package com.kh.demo.mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	int insertUser(UserDTO user);
	
	UserDTO findById(String userid);

	int updateUser(UserDTO user);
}
