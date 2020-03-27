package com.ibm.ngemrservice.testapi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.ngemrservice.testapi.utils.MediaTypeUtils;

@Controller
public class FileDownloadController {

	    private static final String DEFAULT_FILE_NAME = "Sample Bill.pdf";

	    @Autowired
	    private ServletContext servletContext;
 
	    @RequestMapping("/bill")
	    public ResponseEntity<InputStreamResource> downloadFile()throws IOException {
	 
	        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, DEFAULT_FILE_NAME);
	        Resource resource = new ClassPathResource("Sample Bill.pdf");
	    	File file = resource.getFile();
	        
	        InputStreamResource inputResource = new InputStreamResource(new FileInputStream(file));
	 
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
	                .contentType(mediaType)
	                .contentLength(file.length()) 
	                .body(inputResource);
	    }
}
