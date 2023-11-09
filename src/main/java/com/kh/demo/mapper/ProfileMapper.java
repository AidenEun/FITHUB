package com.kh.demo.mapper;

import com.kh.demo.domain.dto.FileDTO;
import com.kh.demo.domain.dto.ProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileMapper {
	int insertFile(ProfileDTO profile);
	
	List<FileDTO> getFiles(String trainerId);
	
	int deleteBySystemname(String sysName);
	
	int deleteByBoardnum(Long boardNum);


	ProfileDTO getProfileInfo(String trainerId);

    ProfileDTO getCareerInfo(String trainerId);
}
