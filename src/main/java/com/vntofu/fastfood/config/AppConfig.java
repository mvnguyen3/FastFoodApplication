package com.vntofu.fastfood.config;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.vntofu.fastfood.domain.Cart;
import com.vntofu.fastfood.domain.Customer;
import com.vntofu.fastfood.domain.Food;
import com.vntofu.fastfood.domain.Role;
import com.vntofu.fastfood.domain.User;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer{
	
	/*
	 * private static final Logger LOGGER =
	 * LoggerFactory.getLogger(AppConfig.class);
	 * 
	 * @Bean(name="taskExecutor") public Executor taskExecutor() {
	 * LOGGER.debug("Creating Async Task Executor"); final ThreadPoolTaskExecutor
	 * executor = new ThreadPoolTaskExecutor(); executor.setCorePoolSize(2);
	 * executor.setMaxPoolSize(2); executor.setQueueCapacity(100);
	 * executor.setThreadNamePrefix("CarThread"); executor.initialize();
	 * 
	 * return executor; }
	 */
	
	
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/vntofudb?useSSL=false&createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("Cps38102991");
		return dataSource;		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.vntofu.fastfood.domain");
		
		// Must add class on domain package
		sessionFactory.setAnnotatedClasses(Food.class, Cart.class, Customer.class, User.class, Role.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
		
	}
	// The primary method
	@Bean
	@Primary
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("com.vntofu.fastfood.domain");		
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaProperties(hibernateProperties());		
		
		return entityManagerFactory;
	}
	
	
	@Bean
	 Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect" );
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // Could be change to create for testing
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		return hibernateProperties;
	}
	
	@Bean
	PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		
		return viewResolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new MappingJackson2XmlHttpMessageConverter());
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		.addResourceLocations("classpath:/static/");
	}

	
}







































