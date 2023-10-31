package com.kh.demo.mapper;


import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
	int insertUser(UserDTO user);
	
	UserDTO findById(String userid);

	List<UserDTO> getSignUpListInUser(Criteria cri);

	int updateUser(UserDTO user);
}
