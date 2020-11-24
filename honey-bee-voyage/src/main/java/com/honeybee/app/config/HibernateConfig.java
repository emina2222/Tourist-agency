package com.honeybee.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Bean
	public LocalSessionFactoryBean hibernateSessionFactory(DataSource ds) {
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(ds);
		sessionFactory.setPackagesToScan("com.honeybee.app"); //skenira sve pakete
		sessionFactory.setHibernateProperties(additionalProperties()); //uzima podesavanja iz property-ja (dialect i show_sql)
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sf) {
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sf);
		return htm;
	}
	
	@Bean
	@Primary
	public DataSource dataSource() { //podesavanje datasource (postavljanje jdbc konekcije i jdbc drivera)
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=turisticka_agencija6_test;integratedSecurity=true;");
		return dataSource;
	}
	
	public Properties additionalProperties() { //podesavanje dialecta i vidljivog SQL koda
		Properties props=new Properties();
		props.setProperty("hibernate.dialect","org.hibernate.dialect.SQLServerDialect");
		props.setProperty("hibernate.show_sql", "true");
		return props;
		//"org.hibernate.dialect.SQLServerDialect"
	}
	
}








