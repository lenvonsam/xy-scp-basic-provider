package org.zhd.data.provider;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author samy
 * @date 2019/06/03
 */
@SpringBootApplication
@EnableDistributedTransaction
public class DataProviderApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(DataProviderApplication.class, args);
	}

}
