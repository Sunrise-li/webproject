package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	
	@RequestMapping("/pic/upload")
	public @ResponseBody PictureResult uploadFile(MultipartFile uploadFile) {
		return fileService.uploadFile(uploadFile);
	}
}
