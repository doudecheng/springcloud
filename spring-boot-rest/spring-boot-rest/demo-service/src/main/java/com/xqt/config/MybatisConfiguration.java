package com.xqt.config;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@AutoConfigureAfter({DataBaseConfiguration.class})
public class MybatisConfiguration implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);


    @SuppressWarnings("unused")
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        //this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }

    @Bean(name="readSqlSession")
    public SqlSessionTemplate readSqlSessionFactoryBean(@Qualifier("readDataSource")DataSource dataSource) throws Exception {
    	long startTime = System.currentTimeMillis();
    	SqlSessionTemplate sqlSession = null;
    	try {
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource);
	        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[]  xqtResources = resolver.getResources("classpath:maps/read/*.xml");
			Resource[]  xqtResources2 = resolver.getResources("classpath:maps/common/*.xml");
			Resource[] resources = ArrayUtils.addAll(xqtResources,xqtResources2);
	        sessionFactory.setMapperLocations(resources);
	        SqlSessionFactory factory = sessionFactory.getObject();

			factory.getConfiguration().setCallSettersOnNulls(true);

	        sqlSession = new SqlSessionTemplate(factory);
    	} catch (Exception ex){
    		logger.error("......configruing sqlsession is error......", ex);
    	}
        logger.info("......configruing sqlsession end time is:{}ms......", (System.currentTimeMillis()-startTime));
        return sqlSession;
    }

	@Bean(name="writeSqlSession")
	public SqlSessionTemplate writeSqlSessionFactoryBean(@Qualifier("writeDataSource")DataSource dataSource) throws Exception {
		long startTime = System.currentTimeMillis();
		SqlSessionTemplate sqlSession = null;
		try {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[]  xqtResources = resolver.getResources("classpath:maps/write/*.xml");
			Resource[]  xqtResources2 = resolver.getResources("classpath:maps/common/*.xml");
			Resource[] resources = ArrayUtils.addAll(xqtResources,xqtResources2);
			sessionFactory.setMapperLocations(resources);
			SqlSessionFactory factory = sessionFactory.getObject();

			factory.getConfiguration().setCallSettersOnNulls(true);

			sqlSession = new SqlSessionTemplate(factory);
		} catch (Exception ex){
			logger.error("......configruing sqlsession is error......", ex);
		}
		logger.info("......configruing sqlsession end time is:{}ms......", (System.currentTimeMillis()-startTime));
		return sqlSession;
	}

	@Bean(name="xqtTransactionManager")
	public DataSourceTransactionManager xqtTransactionManager(@Qualifier("writeDataSource")DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}




}