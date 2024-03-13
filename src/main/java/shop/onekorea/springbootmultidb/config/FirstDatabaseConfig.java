package shop.onekorea.springbootmultidb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

import static shop.onekorea.springbootmultidb.SpringbootmultidbApplication.getInfo;

@Configuration
@EnableJpaRepositories(
        basePackages = "shop.onekorea.springbootmultidb.firstdb.repository",
        entityManagerFactoryRef = "firstEntityManager",
        transactionManagerRef = "firstTransactionManager"
)
public class FirstDatabaseConfig {

    /*
    [application.yml] 파일에서 세팅한 값 가져오기 실험...
     */
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String jpaHibernateDdlAuto;

    @Value("${spring.datasource.hikari.primary.driver-class-name}")
    private String driverClassName1;
    @Value("${spring.datasource.hikari.primary.jdbc-url}")
    private String url1;
    @Value("${spring.datasource.hikari.primary.username}")
    private String username1;
    @Value("${spring.datasource.hikari.primary.password}")
    private String password1;

    @Primary
    @Bean
    public PlatformTransactionManager firstTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(firstEntityManager().getObject());

        return transactionManager;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean firstEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(firstDataSource());
        em.setPackagesToScan(new String[]{"shop.onekorea.springbootmultidb.firstdb.entity"});
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
//        properties.put("hibernate.hbm2ddl.auto", "update");

        em.setJpaPropertyMap(properties);

        return em;

    }

    // DB 등록
    @Primary
    @Bean
    public DataSource firstDataSource() {

        System.err.println(getInfo() + " driverClassName1: " + driverClassName1);
//        System.err.println(getInfo() + " driverClassName2: " + driverClassName2);
        System.err.println(getInfo() + " url1: " + url1);
//        System.err.println(getInfo() + " url2: " + url2);

        return DataSourceBuilder.create()
                .driverClassName(driverClassName1)
                .url(url1)
                .username(username1)
                .password(password1)
                .build();
//        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/powerapp?serverTimezone=UTC&characterEncoding=UTF-8")
//                .username("sa")
//                .password("*963210z")
//                .build();
    }

}
