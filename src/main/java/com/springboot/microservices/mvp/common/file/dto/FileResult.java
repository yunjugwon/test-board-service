package com.springboot.microservices.mvp.common.file.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FileResult {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Delete{
		private long attachSeq;
		private int fileSeq;
		private int deleteCount;
	}
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Insert{
		private long attachSeq;
		private int fileSeq;
		private int insertCount;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Update{
		private long attachSeq;
		private List<FileInfoVO> select;
		private List<Insert> insert;
		private List<Delete> delete;
		
	}
	

	
	
}
