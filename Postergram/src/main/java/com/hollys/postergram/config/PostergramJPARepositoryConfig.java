package com.hollys.postergram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hollys.postergram.repository.Repository;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackageClasses = { Repository.class })
@EnableTransactionManagement
public class PostergramJPARepositoryConfig {
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("root");
		dataSourceBuilder.url("jdbc:mysql://localhost/postergramdb?autoReconnect=true&useSSL=false&charset=utf-8");
		dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
		return dataSourceBuilder.build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.hollys.postergram");
		factory.setDataSource(dataSource);

		Properties jpaProperties = new Properties();

		//Configures the used database dialect. This allows Hibernate to create SQL
		//that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		//Specifies the action that is invoked to the database when the Hibernate
		jpaProperties.put("hibernate.hbm2ddl.auto", "none");

		//If the value of this property is true, Hibernate writes all SQL
		//statements to the console.
		jpaProperties.put("hibernate.show_sql", true);

		//If the value of this property is true, Hibernate will format the SQL
		//that is written to the console.
		jpaProperties.put("hibernate.format_sql", true);

		factory.setJpaProperties(jpaProperties);

		return factory;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
}
