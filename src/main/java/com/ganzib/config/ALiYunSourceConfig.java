package com.ganzib.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by GanZiB on 2017/6/15.
 */
@Configuration
@MapperScan(basePackages = "com.ganzib.mapper.aliyun",sqlSessionTemplateRef = "ALiYunSessionTemplate")
public class ALiYunSourceConfig {

    @Bean(name = "aliyunDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.aliyun")
    public DataSource aliyunDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "aliyunSqlSessionFactory")
    public SqlSessionFactory aliyunSqlSessionFactory(@Qualifier("aliyunDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "aliyunTransactionManager")
    public DataSourceTransactionManager aliyunTransactionManager(@Qualifier("aliyunDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ALiYunSessionTemplate")
    public SqlSessionTemplate aliyunSqlSessionTemplate(@Qualifier("aliyunSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

