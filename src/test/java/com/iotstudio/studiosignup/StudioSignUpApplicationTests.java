package com.iotstudio.studiosignup;

import com.iotstudio.studiosignup.util.fileutil.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudioSignUpApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		FileUtil.deleteFile(new File("C:\\Users\\hyz\\Desktop/新建文件夹"));
	}
}
