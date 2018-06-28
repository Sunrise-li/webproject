package com.taotao.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FastFDFClient;

@Service
public class FileServiceImpl implements FileService{
	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;
	
	@Override
	public PictureResult uploadFile(MultipartFile file) {
		PictureResult result = new PictureResult();
		if(file.isEmpty()) {
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		String originlFilename = file.getOriginalFilename();
		FastFDFClient fc = new FastFDFClient();
		String extname = originlFilename.substring(originlFilename.lastIndexOf(".")+1);
		try {
			String url = fc.uploadFile(file.getBytes(), extname);
			result.setError(0);
			result.setMessage("上传成功");
			result.setUrl(IMAGE_SERVER_BASE_URL + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*System.out.println("jackson---->"+json);*/
		System.out.println("fastjson---->"+result.toString());
		return result;
	}
}
