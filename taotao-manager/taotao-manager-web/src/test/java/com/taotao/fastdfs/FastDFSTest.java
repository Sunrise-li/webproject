package com.taotao.fastdfs;

import java.io.IOException;
import java.util.Arrays;

import org.aspectj.util.GenericSignature.ArrayTypeSignature;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class FastDFSTest {
	
	@Test
	public void testUpload() throws IOException, MyException {
		//吧fastdfs提供的jar包添加到工程
		//初始化全局配置，加载配置文件
	/*	ClassPathResource resource = new ClassPathResource("fdfs_client.conf");*/
		ClientGlobal.init(getClass().getClassLoader().getResource("fdfs_client.conf").getPath());
		
	/*	ClientGlobal.init("F:\\taotao\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\fdfs_client.conf");
	*/
		//创建TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		System.out.println(trackerClient);
		//创建TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		System.out.println(trackerClient);
		//创建StorageClient对象
		StorageClient storageClient = new StorageClient(trackerServer, null);
		System.out.println(storageClient);
		//上传文件
		String[] strs = storageClient.upload_file( "F:\\3.png","png",null);
		System.out.println(Arrays.toString(strs));
	}
}
