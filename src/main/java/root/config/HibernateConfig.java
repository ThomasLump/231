package root.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@EnableTransactionManagement
@Configuration

public class HibernateConfig {

    @Bean
    public Properties properties() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("app.yml"));
        return yamlPropertiesFactoryBean.getObject();
    }

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties().getProperty("database.driver"));
        config.setJdbcUrl(properties().getProperty("database.url"));
        config.setUsername(properties().getProperty("database.username"));
        config.setPassword(properties().getProperty("database.password"));
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(hikariDataSource());
        factoryBean.setPackagesToScan("root.model");
        factoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", properties().getProperty("hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", properties().getProperty("hibernate.dialect"));

        factoryBean.setJpaProperties(properties);
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }
//    @Bean
//    EntityManager entityManager() {
//        return entityManagerFactoryBean().getObject().createEntityManager();
//    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactoryBean().getObject());
    }
}
