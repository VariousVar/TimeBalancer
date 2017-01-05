package ru.variousvar.timebalancer.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jdo.LocalPersistenceManagerFactoryBean;
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

    @Value("${classpath:db/migrations}")
    private Resource migrationsPath;

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
//        additionalProperties.put(
//                "hibernate.hbm2ddl.auto",
//                "update");
        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }

//    @Bean
//    public Flyway flyway() throws Exception {
//        Flyway flyway = new Flyway();
//        flyway.setBaselineOnMigrate(true);
//        flyway.setDataSource(dataSource());
//        flyway.setLocations("classpath:" + migrationsPath.getFile().getAbsolutePath());
//
//        return flyway;
//    }
}
