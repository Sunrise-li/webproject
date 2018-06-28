package com.taotao.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastFDFClient {
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageClient storageClient = null;
	public String uploadFile(byte[] b,String extName) {
		try {
			ClientGlobal.init(getClass().getClassLoader().getResource("fdfs_client.conf").getPath());
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, null);
			String[] str = storageClient.upload_file(b, extName, null);
			return getPictureUrl(str);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getPictureUrl(String[] str) {
		String url = "";
		for(int i = 0; i< str.length;i++) {
			url+="/"+str[i];
		}
		return url;
	}
}
