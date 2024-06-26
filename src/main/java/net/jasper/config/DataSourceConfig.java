// package net.jasper.config;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.stereotype.Component;

// @Configuration
// @Component
// public class DataSourceConfig {

//     @Value("${spring.datasource.url}")
//     private String url;

//     @Value("${spring.datasource.username}")
//     private String username;

//     @Value("${spring.datasource.password}")
//     private String password;

//     @Value("${spring.datasource.driver-class-name}")
//     private String driverClassName;

//     @Bean(name = "primaryDataSource")
//     @Primary
//     @Qualifier("primaryDataSource")
//     @ConfigurationProperties(prefix = "spring.datasource")
//     public DataSource dataSource() {
//         return DataSourceBuilder.create()
//                 .driverClassName(driverClassName)
//                 .url(url)
//                 .password(password)
//                 .username(username)
//                 .build();
//     }
// }
