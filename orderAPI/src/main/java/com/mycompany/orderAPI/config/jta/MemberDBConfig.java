package com.mycompany.orderAPI.config.jta;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@MapperScan(
	basePackages="com.mycompany.orderAPI.dao.memberDB", 
	sqlSessionFactoryRef="memberDBSqlSessionFactory"
)
public class MemberDBConfig {
    @Value("${spring.memberDB.datasource.xa-data-source-class-name}") 
    private String xaDataSourceClassName;
    
    @Value("${spring.memberDB.datasource.xa-properties.url}") 
    private String url;
    
    @Value("${spring.memberDB.datasource.xa-properties.user}") 
    private String user;
    
    @Value("${spring.memberDB.datasource.xa-properties.password}") 
    private String password; 
    
    public static final String MEMBERDB_DATASOURCE = "memberDBDataSource";
	
	@Bean(name=MEMBERDB_DATASOURCE)
	public DataSource dataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName(MEMBERDB_DATASOURCE);
        ds.setXaDataSourceClassName(xaDataSourceClassName);
        
        Properties p = new Properties();
        p.setProperty("URL", url);
        p.setProperty("user", user);
        p.setProperty("password", password);
        ds.setXaProperties (p);
        
        return ds;
	}
	
    @Bean(name="memberDBSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
    		@Qualifier(MEMBERDB_DATASOURCE) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        
        PathMatchingResourcePatternResolver resover = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resover.getResources("classpath:mybatis/memberDB/*.xml"));
		sessionFactory.setConfigLocation(resover.getResource("classpath:mybatis/mapper-config.xml"));
        return sessionFactory.getObject();
    }
}
