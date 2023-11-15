package com.kh.demo.mapper;

import com.kh.demo.domain.dto.ProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileMapper {
	int insertFile(ProfileDTO profile);
	

	List<ProfileDTO> getFiles(String trainerId, String profileCategory);

	ProfileDTO getProfiles(String Id, String profileCategory);


	
	int deleteBySystemname(String sysName);
	
	int deleteByBoardnum(Long boardNum);


	ProfileDTO getProfileInfo(String trainerId);

    ProfileDTO getCareerInfo(String trainerId);

}
