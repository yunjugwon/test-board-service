package com.springboot.microservices.mvp.common.file.dto;

import lombok.Data;

@Data
public class FileDownloadRequestVO {
	private long attachSeq;
	private int fileSeq = 1;
}
