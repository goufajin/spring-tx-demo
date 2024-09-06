package org.demo.jyhspringdemo.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Arrays;

/**
 *
 *
 *
 * @author goufj
 * @date 2023-09-27
 */
@Configuration
public class MybatisPlusConfig {



    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        /* 数据源 */
        sqlSessionFactory.setDataSource(dataSource);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        /* 驼峰转下划线 */
        configuration.setMapUnderscoreToCamelCase(true);

        /* map 下划线转驼峰 */
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());

        //设置driver
//        configuration.setDefaultScriptingLanguage(RawLanguageDriver.class);

        sqlSessionFactory.setConfiguration(configuration);

        //
        GlobalConfig config = new GlobalConfig();
        config.setDbConfig(new GlobalConfig.DbConfig().setKeyGenerators(Arrays.asList(new OracleKeyGenerator())));
        sqlSessionFactory.setGlobalConfig(config);
//        config.setSqlInjector(new InsertBatchSqlInjector());
//        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/*.xml"));
      //  sqlSessionFactory.setTypeAliasesPackage("com.asiainfo.scan.mapper");

        return sqlSessionFactory.getObject();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }



}