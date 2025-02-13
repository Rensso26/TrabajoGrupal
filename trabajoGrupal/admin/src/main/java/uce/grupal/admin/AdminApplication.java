package uce.grupal.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "uce.gruapal.shared.repository")
@EnableJpaRepositories(basePackages = "uce.gruapal.shared.repository")
@EntityScan(basePackages = "uce.gruapal.shared.model")
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	 @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
