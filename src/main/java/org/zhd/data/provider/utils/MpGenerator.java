package org.zhd.data.provider.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/03
 */
@Profile("test")
public class MpGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        String prefix = "DdType";
        String tableName = "BASIC_DDTYPE";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 作者
        gc.setAuthor("cth");
        // 生成后打开目录
        gc.setOpen(false);
        // 设置时间类型使用哪个包下的
        gc.setDateType(DateType.ONLY_DATE);
        // 文件命名方式
        gc.setMapperName(prefix + "Mapper");
        gc.setEntityName(prefix + "Bean");
        gc.setControllerName(prefix + "Controller");
        gc.setServiceName(prefix + "Service");
        // 主键策略
        gc.setIdType(IdType.INPUT);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:oracle:thin:@172.16.120.234:1521/orcl");
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername("jszhd");
        dsc.setPassword("jszhd");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("org.zhd.data.provider")
                .setEntity("entity")
                .setMapper("mapper")
                .setController("controller.basic")
                .setService("service");
        mpg.setPackageInfo(pc);

        //配置自定义属性注入
        InjectionConfig injectionConfig = new InjectionConfig() {
            //.vm模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                map.put("lowerCaseMapperName", lowCaseFirstChar(this.getConfig().getGlobalConfig().getMapperName()));
                map.put("lowerCaseEntityName", lowCaseFirstChar(this.getConfig().getGlobalConfig().getEntityName()));
                map.put("methodPrefix", prefix);
                this.setMap(map);
            }
        };
        mpg.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 使用自定义模板,不想要生成就设置为null,如果不设置null会使用默认模板
        templateConfig.setEntity("templates/entity2.java");
        templateConfig.setController("templates/controller2.java");
        templateConfig.setMapper("templates/mapper2.java");
        templateConfig.setService("templates/service2.java");
        templateConfig.setServiceImpl(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 使用lombok
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableName.toUpperCase());
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // restController
        strategy.setRestControllerStyle(true);
        // 自定义继承的类全称，带包名
        strategy.setSuperControllerClass("org.zhd.data.provider.controller.BaseController");
        strategy.setSuperServiceClass(null);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    private static String lowCaseFirstChar(String str) {
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

}
