package root.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import root.model.User;

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
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(hikariDataSource());

        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
