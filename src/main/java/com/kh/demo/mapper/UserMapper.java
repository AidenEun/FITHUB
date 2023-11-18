package com.kh.demo.mapper;


import com.kh.demo.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
	int insertUser(UserDTO user);
	
	UserDTO findById(String userid);

	List<UserDTO> getSignUpListInUser(Criteria cri);

	int updateUser(UserDTO user);

	AdminDTO findAdminById(String userId);


	UserDTO findByNickname(String usernickname);

  TrainerDTO findTrainerById(String userId);

  int profileUpdateUser(UserDTO user);

	void updateUserAttendance(String userid);

	int getUserAttendance(String userid);

	void updateUserPoint(String userid);

	Long getUserPoint(String userid);

	void resetUserAttendance(String userid);

}
