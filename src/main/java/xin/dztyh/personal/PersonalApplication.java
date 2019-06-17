package xin.dztyh.personal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//拦截器扫描
@MapperScan("xin.dztyh.personal.dao")//项目中对应的mapper类的路径
public class PersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalApplication.class, args);
    }

}
