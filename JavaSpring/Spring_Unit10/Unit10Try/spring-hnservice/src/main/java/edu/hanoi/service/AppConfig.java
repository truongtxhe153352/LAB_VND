package edu.hanoi.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
@Configuration
@EnableWebMvc
@PropertySource("classpath:/application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {
    private Logger LOGGER = Logger.getLogger(AppConfig.class);
    @Autowired
    private org.springframework.core.env.Environment env;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        LOGGER.info("=============> context start application ==========> " + dataSource());
        try {
            createTable("HN_GROUP", "create table HN_GROUP(id bigint primary"
                    + " key generated always as identity (start with 1, increment by 1),"
                    + " name varchar(100))");
            // chay hai cai mot luc
            createTable("HN_USER", "create table HN_USER(username VARCHAR(100) primary key,"
                    + " password varchar(100),"
                    + " email varchar(100),"
                    + " age Integer,"
                    + " groupId bigint, "
                    + " CONSTRAINT GROUP_FK FOREIGN KEY (groupId) REFERENCES HN_GROUP(id))");

        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    private void createTable(String name, String script) throws SQLException {
        DatabaseMetaData dbmd = dataSource().getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, name, null);
        if (rs.next()) {
            LOGGER.info("========> Table " + rs.getString("TABLE_NAME") + " already exists !");
            return;
        }
        dataSource().getConnection().createStatement().executeUpdate(script);
    }

    @Bean
    public DataSource dataSource() {
        String url = env.getProperty("jdbc.url");
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(("edu.hanoi.service.model"));
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("edu.hanoi.service.model");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }

    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        return hibernateProperties;
    }
}
