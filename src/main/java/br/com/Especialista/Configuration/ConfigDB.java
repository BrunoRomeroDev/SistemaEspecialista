package br.com.Especialista.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@Profile("dev")
public class ConfigDB {

	@Value("${spring.datasource.driver-class-name}")
	private String drive;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String user;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Primary
	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		
		liquibase.setDataSource(dataSourcePostgres());
		liquibase.setChangeLog("classpath:/db/changelog/changelog-master.xml");

		return liquibase;
	}
	
    public DataSource dataSourcePostgres()  {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

            dataSource.setDriverClassName(drive);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);

        return dataSource;
    }

}
