package ru.digitalleague.core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = {"ru.digitalleague.core.mapper"})
public class MyBatisConfig {

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setVfs(SpringBootVFS.class);
        sessionFactory.setTypeAliasesPackage("ru.digitalleague.core.model");

        SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
        if (sqlSessionFactory != null) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);
            // lazy loading
            configuration.getLazyLoadTriggerMethods().clear();
            configuration.setLazyLoadingEnabled(true);
        }

        return sessionFactory.getObject();
    }
}
