package authservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"authservice.repository"})
public class App {
    public static void main(String[] args){
        ApplicationContext iocContainer = SpringApplication.run(App.class, args);
    }
}
