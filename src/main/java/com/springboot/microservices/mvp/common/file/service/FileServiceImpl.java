package com.springboot.microservices.mvp.common.file.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.microservices.mvp.common.file.dao.FileDao;
import com.springboot.microservices.mvp.common.file.dto.FileInfoVO;
import com.springboot.microservices.mvp.common.file.dto.FileResult;
import com.springboot.microservices.mvp.common.file.dto.FileResult.Update;
import com.springboot.microservices.mvp.common.file.dto.FileUploadErrVO;
import com.springboot.microservices.mvp.common.file.dto.RequestFileInfoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileDao fileDao;

	@Value("${prop.file.root}")
	private String ROOT_PATH;
	
	@Value("${prop.file.temp}")
	private String TEMP_PATH;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

	@Override
	public List<FileResult.Insert> insertFile(List<MultipartFile> list) {
		long key = fileDao.selectFileKey();
		return createFileAndInsert(key, list);
	}

	@Override
	public FileResult.Insert insertFile(MultipartFile vo) {
		long key = fileDao.selectFileKey();
		return createFileAndInsert(key, vo);
	}
	

	@Override
	public FileResult.Insert insertTempFile(long attachSeq, MultipartFile vo) {
		long newKey = (attachSeq == 0)?fileDao.selectFileKey() : attachSeq;
		return createFileAndInsert(newKey, vo, TEMP_PATH);
	}
	
	
	@Override
	public int moveTempFile(long attachSeq) {
		List<FileInfoVO> fileList = selectFileInfoListByAttachSeq(attachSeq);
		
		return fileList.stream().mapToInt( vo -> {
			
			String targetPathString = vo.getFilePath().replaceAll(TEMP_PATH, ROOT_PATH);
			Path tempPath = Paths.get(vo.getFilePath() + "/" + vo.getFileLogicalNm());
			Path targetPath = Paths.get(targetPathString);
			
			
			createDirectory(targetPathString);
			
			try {
				Files.move(tempPath, targetPath.resolve(tempPath.getFileName()));
			} catch (IOException e) {
				log.error("FILE MOVE ERROR", e);
				e.printStackTrace();
			}
			
			vo.setFilePath(targetPathString);
			return fileDao.updateMoveFileInfo(vo);
		}).sum();
	}

	/**
	 * Description : 리스트에 맞게 파일생성
	 * 
	 * @param key
	 * @param list
	 * @return
	 */
	private List<FileResult.Insert> createFileAndInsert(long key, List<MultipartFile> list) {
		return list.stream().map(o -> createFileAndInsert(key, o)).collect(Collectors.toList());
	}

	private String getCurrentDate() {
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	
	/**
	 * file 저장
	 * @param key
	 * @param vo
	 * @param rootPath
	 * @return
	 */
	private FileResult.Insert createFileAndInsert(long key, MultipartFile vo, String rootPath) {
		
		FileInfoVO info = FileInfoVO.create(vo);
		int fileSeq = fileDao.selectFileSeq(key);
		
		info.setFileSeq(fileSeq);
		info.setAttachSeq(key);
		info.setFileLogicalNm(key + "_" + Calendar.getInstance().getTimeInMillis() + "_" + fileSeq);
		info.setFilePath(rootPath + "/" + getCurrentDate() + "/" );
		
		createDirectory(info.getFilePath());

		String filePathStirng = info.getFilePath() + "/" + info.getFileLogicalNm();
		createFile(vo, filePathStirng);

		return new FileResult.Insert(key, fileSeq, fileDao.insertFileInfo(info));
	}
	
	
	


	/**
	 * Description : 파일 생성 파일 이름 : key_time
	 * 
	 * @param key
	 * @param vo
	 * @return
	 */
	private FileResult.Insert createFileAndInsert(long key, MultipartFile vo) {
		return createFileAndInsert(key, vo, ROOT_PATH);
	}
	

	

	private void createDirectory(String direcotryName) {
		Path directoryPath = Paths.get(direcotryName);
		if (!Files.exists(directoryPath)) {
			try {
				Files.createDirectories(directoryPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Path createFile(MultipartFile file, String path) {
		Path filePath = Paths.get(path);
		try {
			file.transferTo(filePath.toFile());
		} catch (IOException e) {
			log.info("파일(디렉토리)  생성중 오류 :" + e.toString());
		}
		
		log.info("파일 생성 여부 : {}, {}", filePath.toFile().isFile(), filePath.toFile().getAbsolutePath());

		return filePath;
	}

	@Override
	public Update updateFile(RequestFileInfoVO vo) {
		
		List<FileResult.Delete> deleteList = vo.getDeleteList().stream()
		.map( o -> {
			return new FileResult.Delete(o.getAttachSeq(), o.getFileSeq(), fileDao.deleteFileInfoByAttachSeqAndFileSeq(o));
		}).collect(Collectors.toList());
		

		List<FileResult.Insert> insertList = Collections.emptyList();
		if (vo.getFileList().size() > 0) {
			if (vo.getAttachSeq() == 0) {
				vo.setAttachSeq(fileDao.selectFileKey());
			}
			insertList = createFileAndInsert(vo.getAttachSeq(), vo.getFileList());
		}

		// 2017.01.05 첨부파일 전체 수 추가
		List<FileInfoVO> seletList = selectFileInfoListByAttachSeq(vo.getAttachSeq());
		return new FileResult.Update(vo.getAttachSeq(), seletList, insertList, deleteList);
	}

	@Override
	public List<FileInfoVO> selectFileInfoListByAttachSeq(long attachSeq) {
		return fileDao.selectFileInfoListByAttachSeq(attachSeq);
	}

	@Override
	public FileInfoVO selectFileInfo(FileInfoVO vo) {
		return fileDao.selectFileInfo(vo);
	}


	@Override
	public int insertFileUploadErr(FileUploadErrVO vo) {
		return fileDao.insertFileUploadErr(vo);
	}



}
