package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		String filePath = "";
		System.out.println("UploadFile");
		File convertFile = new File(filePath+file.getOriginalFilename());
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return "File is upload successfully";
		
	}
	@RequestMapping("/download/{fileId}")
	public ResponseEntity<Object> downloadFile(@PathVariable String fileId) throws FileNotFoundException{
		System.out.println(fileId);
		String fileName = "C:/Users/49367/Desktop/v2-8402ddc1d352f36a7959e0ddb69c99a0_r.jpg";
		File file = new File(fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	      headers.add("Pragma", "no-cache");
	      headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
	}
	@RequestMapping(value = "/test")
	public String test(HttpRequest httpRequest) {
		System.out.println("TTTTTTTTTTTTTTTTTTTTTT");
		System.out.println(httpRequest.getURI());
		return "TTTTTTTTTTT";
	}
}
