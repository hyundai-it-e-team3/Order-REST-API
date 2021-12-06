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
	basePackages="com.mycompany.orderAPI.dao.orderDB", 
	sqlSessionFactoryRef="orderDBSqlSessionFactory"
)
public class OrderDBConfig {
	@Value("${spring.orderDB.datasource.xa-data-source-class-name}") 
	private String xaDataSourceClassName;
	
	@Value("${spring.orderDB.datasource.xa-properties.url}") 
	private String url;
    
	@Value("${spring.orderDB.datasource.xa-properties.user}") 
	private String user;
    
	@Value("${spring.orderDB.datasource.xa-properties.password}") 
	private String password;
    
    public static final String ORDERDB_DATASOURCE = "orderDBDataSource";
	
	@Bean(name=ORDERDB_DATASOURCE)
	public DataSource dataSource() {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setUniqueResourceName(ORDERDB_DATASOURCE);
		ds.setXaDataSourceClassName(xaDataSourceClassName);

		Properties p = new Properties();
		p.setProperty("URL", url);
		p.setProperty("user", user);
		p.setProperty("password", password);
		ds.setXaProperties(p);

		return ds;
	}
	
	@Bean(name="orderDBSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier(ORDERDB_DATASOURCE) DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		PathMatchingResourcePatternResolver resover = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resover.getResources("classpath:mybatis/orderDB/*.xml"));
		sessionFactory.setConfigLocation(resover.getResource("classpath:mybatis/mapper-config.xml"));
		return sessionFactory.getObject();
	}
}
