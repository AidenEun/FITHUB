package com.kh.demo.mapper;

import com.kh.demo.domain.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
	int insertFile(FileDTO file);
	
	List<FileDTO> getFiles(Long board_num);
	
	int deleteBySystemname(String sys_name);
	
	int deleteByBoardnum(Long board_num);
}
