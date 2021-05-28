package com.springboot.microservices.mvp.common.file.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RequestFileInfoVO {
	private List<MultipartFile> fileList = new ArrayList<>();
	private List<FileInfoVO> deleteList = new ArrayList<>();
	private long attachSeq;
}
