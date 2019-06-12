package org.zhd.data.provider;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yj
 * @date 2019/06/03
 */
@SpringBootApplication
@EnableDistributedTransaction
public class DataProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataProviderApplication.class, args);
	}

}
