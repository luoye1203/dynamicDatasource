package com.xzst.modi;

import com.xzst.modi.app.fAop.DynamicDataSourceAnnotationAdvisor;
import com.xzst.modi.app.fAop.DynamicDataSourceAnnotationInterceptor;
import com.xzst.modi.app.register.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@Import(DynamicDataSourceRegister.class)
@EnableSwagger2
@EnableTransactionManagement
@MapperScan("com.xzst.modi.app.cDao")
@ServletComponentScan //使@WebFilter 起作用
public class ModiApplication  {

    @Bean
    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
    }


    public static void main(String[] args) {
        SpringApplication.run(ModiApplication.class, args);
    }
}
