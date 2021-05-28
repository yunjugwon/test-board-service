package com.springboot.microservices.mvp.common.file.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper=false)
@Data
public class FileUploadErrVO {
	private String fileErrNo;
	private String errTitle;
	private long attachSeq;
	private String actvTaskCd;
	private String actvItemCd;
	private String actvEventCd;
}
