

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

public class FastDFSTest {
/*	public void testUpload() {

		ClientGlobal.init(getClass().getClassLoader().getResourceAsStream(""));
		TrackerClient trackerClient = new TrackerClient("/taotao-manager-web/src/main/resources/client.conf");
		TrackerServer trackerServer = trackerClient.getConnection();
		
	}*/
	public static void main(String[] args) {
		new FastDFSTest().test1();
	}
	public void test1() {
		ClassPathResource resource = new ClassPathResource("client.conf");
		System.out.println(resource.getClassLoader().getResource("client.conf").getPath());
	}
}
