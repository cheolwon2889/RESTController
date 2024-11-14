package com.itwillbs.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/file/*")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public void uploadForm() throws Exception{
		
		logger.info(" 파일 업로드 폼페이지 뷰");
		logger.info(" /file/form.jsp 페이지 연결");
		
	}
	
	
	// 파일 업로드
	// http://localhost:8088/file/upload
	@RequestMapping(value = "/upload" , method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest multiReq, 
			Model model) throws Exception {
		logger.info(" 파일 업로드 폼페이지 뷰");
		
		multiReq.setCharacterEncoding("UTF-8");
		
		// 파라메터 정보 + 첨부파일 정보 저장
		Map map = new HashMap();
		
		// 1. 파라메터 정보 처리
		Enumeration enu = multiReq.getParameterNames();
		logger.info("enu : {}", enu);
		while(enu.hasMoreElements()) {
			// 해당요소가 있을때 (파라메터 정보가 있을때)
			String name = (String)enu.nextElement();
			logger.info("name : {}", name);
			String value = multiReq.getParameter(name);
			logger.info("value : {}", value);
			// map에 정보를 모두 저장
			map.put(name, value);
		}
		
		logger.info("map : {}" , map);
	
		////////////////////////////////////
		// 2. 첨부파일(업로드 파일) 처리
		
		List fileList = fileUploadProcess(multiReq);
		map.put("fileList", fileList);
		
		model.addAttribute("map", map);
		
		// /file/uploadResult.jsp 결과 출력
		return "file/uploadResult";
	}
	
	// 파일 업로드 처리
	public List<String> fileUploadProcess(MultipartHttpServletRequest multiReq) throws Exception{
		
		// 파일정보 저장하는 리스트
		List<String> fileList = new ArrayList<String>();
		logger.info("fileList : {}", fileList);
		
		// 업로드된 파일정보를 가져오기
		// 파일 input태그들의 이름을 가져오기
		Iterator<String> fileNames = multiReq.getFileNames();
		logger.info("fileNames : {}", fileNames);
		while(fileNames.hasNext()) {
			
			// 파일 input태그들의 이름
			String fileName = fileNames.next();
			logger.info("fileName : {}", fileName);
			// 파일 임시 저장
			MultipartFile mFile = multiReq.getFile(fileName);
			logger.info("mFile : {}", mFile);
			
			// 실제 파일의 이름
			String oFile = mFile.getOriginalFilename();
			// 파일이름을 리스트에 저장
			fileList.add(oFile);
			
			File file = new File("C:\\upload\\"+oFile);
			if(mFile.getSize() != 0 ) {
				// => 파일(mFile)이 있을때
				if(!file.exists()) {
					// => 파일(file)이 해당 경로에 없을때
					if(file.getParentFile().mkdirs()) {
						// 해당파일의 부모 폴더(디렉토리 C:\\upload\\) 생성
						file.createNewFile();
						
					}//3
				}//2
				// 파일 업로드 수행 (임시파일 -> 실제 파일)
				mFile.transferTo(file);
			}//
			
			
		}
		
		return fileList;
	}
	
	// 파일 다운로드
	@RequestMapping(value = "/download" , method = RequestMethod.GET )
	public void fileDownload(@RequestParam("fileName") String fileName, 
			HttpServletResponse response) throws Exception {
		logger.info(" 파일 다운로드 처리 fileDownload() "); 
		logger.info(" 다운로드할 파일명 : {} ",fileName); 
		
		// 다운로드할 파일
		File dfile = new File("C:\\upload\\"+fileName);
		
		// 외부로 정보 전송
		OutputStream out = response.getOutputStream();
		
		
		// 뷰페이지 -> 다운로드 창으로
		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+URLEncoder.encode(fileName, "UTF-8"));
		
		// 다운로드 파일을 가져와서 화면(다운로드창)으로 전송
		FileInputStream fis = new FileInputStream(dfile);
		
		byte[] buffer = new byte[1024*8]; // 8kb
		int data = 0;
		while((data = fis.read(buffer)) != -1) { // 파일의 끝(-1)
			out.write(buffer,0,data);
		}
		out.flush(); // 버퍼에 공백 전달
		fis.close();
		out.close();
		
		
	}
	
	
	
	
	
	
	
}
