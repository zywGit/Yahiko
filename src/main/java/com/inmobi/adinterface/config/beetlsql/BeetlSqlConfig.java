package com.inmobi.adinterface.config.beetlsql;

import com.zaxxer.hikari.HikariDataSource;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @创建人 Mr.Yan
 * @创建时间 2018/8/23
 * @描述: beet + springboot +mysql 整合
 */
@Configuration
@Profile("db")
public class BeetlSqlConfig {

    //配置文件
    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration  getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        //打成jar 包不能访问 修改下面
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);
        //读取配置文件信息
        return beetlGroupUtilConfiguration;

    }

    //配置数据库
    @Bean(name = "datasource")
    public DataSource getDataSource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.mysql.url"));
        ds.setUsername(env.getProperty("spring.datasource.mysql.username"));
        ds.setPassword(env.getProperty("spring.datasource.mysql.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }

   /* //配置数据库
    @Bean(name = "datasourceOther")
    public DataSource getDataSourceOther(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource-other.mysql.url"));
        ds.setUsername(env.getProperty("spring.datasource-other.mysql.username"));
        ds.setPassword(env.getProperty("spring.datasource-other.mysql.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource-other.driver-class-name"));
        return ds;
    }*/

    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(
            @Qualifier("datasource") DataSource datasource,
            //@Qualifier("datasourceOther") DataSource datasourceOther,
            Environment env) {
        String sqlLoader =env.getProperty("beetlsql.sqlLoader");

        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(datasource);
        //配置多数据库连接
        //source.setSlaves(new DataSource[]{datasourceOther});

        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        factory.setNc(new UnderlinedNameConversion());//开启自定义映射
        factory.setSqlLoader(new ClasspathLoader(sqlLoader));//sql文件路径
        return factory;
    }

    //开启事务
  /* @Bean(name = "txManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(
            @Qualifier("datasource") DataSource datasource) {
        DataSourceTransactionManager dsm = new DataSourceTransactionManager();
        dsm.setDataSource(datasource);
        return dsm;
    }*/


}
