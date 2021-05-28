package com.springboot.microservices.mvp.common.file.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springboot.microservices.mvp.common.file.dto.FileInfoVO;
import com.springboot.microservices.mvp.common.file.dto.FileResult;
import com.springboot.microservices.mvp.common.file.dto.FileUploadErrVO;
import com.springboot.microservices.mvp.common.file.dto.RequestFileInfoVO;
import com.springboot.microservices.mvp.common.file.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/app/common/file")
@Slf4j
public class FileController {

	@Autowired
	FileService fileService;

	private static final String VIEW_PATH = "common/file";
	

	
	
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public ResponseEntity<FileResult.Insert> setUpload(@RequestParam("file") MultipartFile file , @RequestParam long attachSeq) {
    	FileResult.Insert fileResultVO = new FileResult.Insert();
    	
    	String fileName = file.getName();
    	log.debug("file name : "+fileName);
    	return new ResponseEntity<>(fileResultVO, HttpStatus.OK);
    }
	

    @RequestMapping(value="/drag/upload", method=RequestMethod.POST)
    public ResponseEntity<FileResult.Insert> setDragUpload(MultipartHttpServletRequest request , @RequestParam long attachSeq) {
    	
    	FileResult.Insert fileResultVO = new FileResult.Insert();
        try {
            Iterator<String> itr = request.getFileNames();
            
            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                fileResultVO = fileService.insertTempFile(attachSeq, file);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(fileResultVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(fileResultVO, HttpStatus.OK);
    }
    
	

	/**
	 * Description : 파일 업로드
	 * 
	 * @param fileList
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/ajax/insert.json")
//	public ResponseEntity<List<FileResult.Insert>> setFileInsert(RequestFileInfoVO fileList) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.TEXT_HTML);
//
//		return new ResponseEntity<List<FileResult.Insert>>(fileService.insertFile(fileList.getFileList()), headers, HttpStatus.OK);
//	}
	

	/**
	 * Description : 파일 업데이트
	 * 
	 * @param vo
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/ajax/update.json")
//	public ResponseEntity<FileResult.Update> setFileUpdate(RequestFileInfoVO vo) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.TEXT_HTML);
//
//		return new ResponseEntity<FileResult.Update>(fileService.updateFile(vo), headers, HttpStatus.OK);
//
//	}

	/**
	 * Description : 파일 리스트 조회
	 * 
	 * @param fileNo
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/list/{fileNo}")
//	public List<FileInfoVO> getFileList(@PathVariable long fileNo) {
//		return fileService.selectFileInfoListByAttachSeq(fileNo);
//	}

	/**
	 * Description : 파일 다운로드
	 * 
	 * @param vo
	 * @param response
	 */
//	@RequestMapping(value = "/download")
//	public void getFileDownload(FileInfoVO vo, HttpServletResponse response) {
//		FileInfoVO info = fileService.selectFileInfo(vo);
//
//		String docName = "";
//		try {
//			docName = URLEncoder.encode(info.getFilePhysicalNm(), "UTF-8").replaceAll("\\+", "%20");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		Path downloadFilePath = Paths.get(info.getFilePath() + "/" + info.getFileLogicalNm());
//		log.info("다운로드 파일 이름 : " + downloadFilePath.getFileName());
//
//		response.setContentType("application/octet-stream");
//		response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
//		response.setHeader("Content-Transfer-Encoding", "binary");
//
//		try {
//			byte fileByte[] = FileUtils.readFileToByteArray(downloadFilePath.toFile());
//			response.getOutputStream().write(fileByte);
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//			response.setContentLength(fileByte.length);
//		} catch (IOException e) {
//			log.error("파일 생성중", e);
//		}
//	}
//
//	@RequestMapping("/error/popup/list")
//	public String getFileUploadErrList(FileUploadErrVO vo) {
//		return VIEW_PATH + "/popup/common_file_error_plist";
//	}

}
