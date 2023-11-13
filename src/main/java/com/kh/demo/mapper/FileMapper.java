package com.kh.demo.mapper;

import com.kh.demo.domain.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
	int insertFile(FileDTO file);
	
	List<FileDTO> getFiles(Long boardNum, String boardCategory);

	int deleteBySystemname(String sysName);
	
	int deleteByBoardnum(Long boardNum);
}
