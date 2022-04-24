package com.wixteam.barbershop.Users.User.Infraestructure.Hibernate;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration

public class HibernateConfigFactory {
    @Autowired
    private Environment env;

    @Bean("session-factory")
    public LocalSessionFactoryBean sesionFactoryBean(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(this.datasource());
        sessionFactory.setHibernateProperties(this.hibernateProperties());
        //TODO: Resources

        sessionFactory.setMappingLocations();
        return sessionFactory;
    }
    @Bean("transactional-manager")
    public PlatformTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sesionFactoryBean().getObject());
        return transactionManager;
    }


    private Properties hibernateProperties(){
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(AvailableSettings.HBM2DDL_AUTO,"none");
        hibernateProperties.put(AvailableSettings.SHOW_SQL,false);
        hibernateProperties.put(AvailableSettings.DIALECT,"org.hibernate.dialect.MariaDBDialect");
        return hibernateProperties;
    }
    private DataSource datasource(){
        String url = env.getProperty("datasource.url");
        String UserName = env.getProperty("datasource.username");
        String password = env.getProperty("datasource.password");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mariadb://"+url);
        dataSource.setUsername(UserName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        return dataSource;
    }
}
