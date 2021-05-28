package com.springboot.microservices.mvp.common.file.dto;

import java.io.File;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileInfoVO {
	
	private long attachSeq;
	private int fileSeq;
	private String fileTypeCd;
	private String filePath;
	private String fileLogicalNm;
	
	private String filePhysicalNm;
	
	private String fileExt;
	
	private long fileSize;
	

	
	public static FileInfoVO create(MultipartFile mpf) {
		FileInfoVO vo = new FileInfoVO();
		
		String[] fileNameSplit = mpf.getOriginalFilename().split("\\.");
		vo.setFileExt(fileNameSplit[fileNameSplit.length-1]);
		vo.setFilePhysicalNm(mpf.getOriginalFilename());
		vo.setFileSize(mpf.getSize());
		return vo;
	}
	
	public static FileInfoVO create(Path path) {
		FileInfoVO vo = new FileInfoVO();
		
		File file = path.toFile();
		String[] fileNameSplit = file.getName().split("\\.");
		vo.setFileExt(fileNameSplit[fileNameSplit.length-1]);
		vo.setFilePhysicalNm(file.getName());
		vo.setFileSize(file.length());
		
		return vo;
	}

	public static FileInfoVO empty() {
		return  new FileInfoVO();
	}

	public static FileInfoVO conver(FileDownloadRequestVO request) {
		FileInfoVO vo = new FileInfoVO();
		vo.setAttachSeq(request.getAttachSeq());
		vo.setFileSeq(request.getFileSeq()); //파일 번호( 복수 처리시 파라미터값 받아와야합니다. 단일 처리 때문에 1기본 지정)
		

		return vo;
	}
}
