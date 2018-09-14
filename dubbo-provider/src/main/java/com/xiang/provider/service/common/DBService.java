package com.xiang.provider.service.common;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="com.xiang.provider.dao.mapper")
public class DBService {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getDataSource() throws Exception{
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		datasource.setUrl(env.getProperty("spring.datasource.url"));
		datasource.setUsername(env.getProperty("spring.datasource.username"));
		datasource.setPassword(env.getProperty("spring.datasource.password"));
		datasource.setTestOnBorrow(true);
		datasource.setValidationQuery("select 1");
		return datasource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource getDataSource) throws Exception{
		SqlSessionFactoryBean fBean = new SqlSessionFactoryBean();
		fBean.setDataSource(getDataSource);
		return fBean.getObject();
	}
}
