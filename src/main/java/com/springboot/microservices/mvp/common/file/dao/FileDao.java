package com.springboot.microservices.mvp.common.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.microservices.mvp.common.file.dto.FileInfoVO;
import com.springboot.microservices.mvp.common.file.dto.FileUploadErrVO;


@Mapper
public interface FileDao {
	/**
	 * Description : 파일 정보 입력
	 * 
	 * @param vo
	 * @return
	 */
	int insertFileInfo(FileInfoVO vo);
	
	/**
	 * Description :fileNo 하고 fileSeq 2개의 키가지고 삭제 처리 
	 * 
	 * @param vo
	 * @return
	 */
	int deleteFileInfoByAttachSeqAndFileSeq(FileInfoVO vo);
	
	/**
	 * Description : attachSeq 로 조회
	 * 
	 * @param attachSeq
	 * @return
	 */
	List<FileInfoVO> selectFileInfoListByAttachSeq(long attachSeq);
	
	/**
	 * Description : fileNo 로 삭제
	 * 
	 * @param attachSeq
	 * @return
	 */
	int deleteFileInfoByAttachSeq(long attachSeq);
	
	/**
	 * Description : 상세조회
	 * 
	 * @param vo
	 * @return
	 */
	FileInfoVO selectFileInfo(FileInfoVO vo);
	
	/**
	 * Description : fileNo  생성
	 * 
	 * @return
	 */
	int selectFileKey();
	
	/**
	 * Description : fileSeq 생성
	 * 
	 * @param fileSeq
	 * @return
	 */
	int selectFileSeq(long fileSeq);
	
	/**
	 * Description : 파일업로드 시 error 발생시 입력처리
	 * 
	 * @param vo
	 * @return
	 */
	int insertFileUploadErr(FileUploadErrVO vo);
	
	
	List<FileUploadErrVO> selectFileUploadErrPage(FileUploadErrVO vo);
	
	int selectFileUploadErrPageCount(FileUploadErrVO vo);

	int updateMoveFileInfo(FileInfoVO vo);
	
}
