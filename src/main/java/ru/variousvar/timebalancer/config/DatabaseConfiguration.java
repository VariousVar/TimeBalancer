package ru.variousvar.timebalancer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration  {

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource());

        // Classpath scanning of @Component, @Service, etc annotated class
        entityManagerFactory.setPackagesToScan("ru.variousvar.timebalancer.entity");

        // Vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQL5InnoDBDialect");
        additionalProperties.put(
                "hibernate.show_sql",
                true);
        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }
}
