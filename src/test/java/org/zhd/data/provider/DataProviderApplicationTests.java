package org.zhd.data.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zhd.data.provider.utils.DateTimeUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataProviderApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
    public void testDateTimeFormatter() {
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 20; i++) {
            service.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        System.out.println(DateTimeUtils.stringToDate("2019-05-27"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
