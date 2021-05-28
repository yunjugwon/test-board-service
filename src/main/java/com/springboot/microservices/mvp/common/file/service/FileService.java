package com.springboot.microservices.mvp.common.file.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.microservices.mvp.common.file.dto.FileInfoVO;
import com.springboot.microservices.mvp.common.file.dto.FileResult;
import com.springboot.microservices.mvp.common.file.dto.FileResult.Insert;
import com.springboot.microservices.mvp.common.file.dto.FileResult.Update;
import com.springboot.microservices.mvp.common.file.dto.FileUploadErrVO;
import com.springboot.microservices.mvp.common.file.dto.RequestFileInfoVO;

public interface FileService {
	
	/**
	 * Description : 파일 변경
	 * 
	 * @param key 테이블에 key되는 값
	 * @param insertList MultipartFile 목록
	 * @param deleteList 삭제 되는 파일 목록
	 * @return
	 */
	Update updateFile(RequestFileInfoVO vo);
	
	/**
	 * Description : 파일 입력(리스트)
	 * 
	 * @param key 테이블에 key되는 값
	 * @param list MultipartFile 목록
	 * @return
	 */
	List<FileResult.Insert> insertFile(List<MultipartFile> list);
	
	/**
	 * Description : 화면에서 받은 파일을 정보 입력하기 
	 * 
	 * @param key 테이블에 key되는 값
	 * @param vo MultipartFile
	 * @return
	 */
	Insert insertFile(MultipartFile vo);
	
	
	Insert insertTempFile(long attachSeq, MultipartFile vo);
	
	int moveTempFile(long attachSeq);
	
	
	/**
	 * Description : 파일 목록 조회
	 * 
	 * @param vo
	 * @return
	 */ 
	List<FileInfoVO> selectFileInfoListByAttachSeq(long attachSeq);
	
	/**
	 * Description : 파일 상세 조회
	 * 
	 * @param vo
	 * @return
	 */
	FileInfoVO selectFileInfo(FileInfoVO vo);
	
	/**
	 * Description : 화면에서 받은 파일을 서버에 저장하기 
	 * 
	 * @param file
	 * @param path
	 * @return
	 */
	Path createFile(MultipartFile file,String path);
	
	
	/**
	 * Description :  파입 업로드중 에러 발생시 정보 입력
	 * 
	 * @param vo
	 * @return
	 */
	int insertFileUploadErr(FileUploadErrVO vo);
	



}