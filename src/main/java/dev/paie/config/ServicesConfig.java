package dev.paie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

@Configuration
@ComponentScan({ "dev.paie.service", "dev.paie.util", "dev.paie.entite" })
@Import({ DataSourceMySQLConfig.class, JpaConfig.class })
@EnableJpaRepositories("dev.paie.repository")
@ImportResource({ 
	"classpath:cotisations-imposables.xml",
	"classpath:cotisations-non-imposables.xml",
	"classpath:entreprises.xml",
	"classpath:grades.xml",
	"classpath:profils-remuneration.xml"
})
public class ServicesConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
