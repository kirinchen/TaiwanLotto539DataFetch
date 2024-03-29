package net.surfm.ft539;

import java.util.Timer;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = { "net.surfm" })
@SpringBootApplication
@PropertySource({ "classpath:prod.properties", "classpath:${env}.properties" })
public class Application {

	public static Timer timer = new Timer();

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);

	}

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		DozerBeanMapper ans = new DozerBeanMapper();
		return ans;
	}
}
