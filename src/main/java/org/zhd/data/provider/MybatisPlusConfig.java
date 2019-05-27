package org.zhd.data.provider;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan({"org.zhd.data.provider.mapper"})
@Configuration
public class MybatisPlusConfig {
    // 指定插入空值时的类型映射
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return (configuration) -> {
            configuration.setJdbcTypeForNull(JdbcType.NULL);
        };
    }

    // mp 分页
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // mp oracle主键生成
    @Bean
    public OracleKeyGenerator oracleKeyGenerator(){
        return new OracleKeyGenerator();
    }
}
