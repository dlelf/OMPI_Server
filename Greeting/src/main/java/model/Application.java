package model;


import hello.POST2GCM;
import model.Person;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

//import model.EventPattern;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@ComponentScan
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application implements CommandLineRunner 	{
	static ConfigurableApplicationContext context;
	
@Bean
public DataSource dataSource()
{
	DataSource tomcatDataSource = new DataSource();
	PoolProperties dbProperties = new PoolProperties();

	
	HashMap<String,String> cfgMap = new HashMap<String,String>();
	
	String strConfig = loadConfig();
	if ( strConfig != null ) {
		
		String[] arConfig = strConfig.split("\\r?\\n");
		
		
		for ( String str : arConfig ) {
			cfgMap.put(str.split("=")[0].trim(), str.split("=")[1].trim());
		}
		
	} 
	
	dbProperties.setUrl("jdbc:postgresql://" + cfgMap.get("Server") + ":" + cfgMap.get("Port") + "/" + cfgMap.get("DB") );
	dbProperties.setDriverClassName("org.postgresql.Driver");
	dbProperties.setUsername(cfgMap.get("user"));
	dbProperties.setPassword(cfgMap.get("password"));
	dbProperties.setJmxEnabled(true);
	dbProperties.setTestWhileIdle(false);
	dbProperties.setTestOnBorrow(true);
	dbProperties.setValidationQuery("SELECT 1");
	dbProperties.setTestOnReturn(false);
	dbProperties.setValidationInterval(30000);
	dbProperties.setTimeBetweenEvictionRunsMillis(30000);
	dbProperties.setMaxActive(50);
	dbProperties.setInitialSize(10);
	dbProperties.setMaxWait(10000);
	dbProperties.setRemoveAbandonedTimeout(60);
	dbProperties.setMinEvictableIdleTimeMillis(30000);
	dbProperties.setMinIdle(10);
	dbProperties.setMaxIdle(25);
	dbProperties.setLogAbandoned(true);
	dbProperties.setRemoveAbandoned(true);

	dbProperties
			.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
								 "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
					
	tomcatDataSource.setPoolProperties(dbProperties);

	return tomcatDataSource;

}

@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory(	
		DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
	LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
	lef.setDataSource(dataSource);
	lef.setJpaVendorAdapter(jpaVendorAdapter);
	lef.setPackagesToScan("model");
	return lef;
}

@Bean
public JpaVendorAdapter jpaVendorAdapter() {
	HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
	hibernateJpaVendorAdapter.setShowSql(false);
	hibernateJpaVendorAdapter.setGenerateDdl(true);
	hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
	return hibernateJpaVendorAdapter;
}

@Bean
public PlatformTransactionManager transactionManager() {
	return new JpaTransactionManager();
}

public static void main(String[] args) {

	context = SpringApplication.run(Application.class);	
	//broadcastGroupNr();
}

public static void broadcastGroupNr(){
	System.out.println( "Sending POST to GCM" );
	POST2GCM post2gcm = new POST2GCM();
	String apiKey = "AIzaSyBWm2UWRRh3RAff6srwPoJGmLH6PZ3KYFo";
	PersonRepository pp = context.getBean(PersonRepository.class);	
	List<Person> allPersons = (List<Person>) pp.findAll();
	for (Person person:allPersons){
		if (person.getGoogleCloudId() != null)
		System.out.println( "Sending GroupNr to Person"+person.getId() );
		post2gcm.sendMessage(person.getGoogleCloudId(), apiKey,person.getGroupNr().toString());	
		
	}
	
	
}

public void run(String... args) throws Exception {
	
	/*EventPattern.TestCompile();*/
}

private String loadConfig() {
	
	try {
		//InputStream is = this.getClass().getResourceAsStream("DBConfig.cfg");
		//InputStream is = this.getClass().getResourceAsStream("classpath:DBConfig.cfg");
		InputStream is = this.getClass().getResourceAsStream("/DBConfig.cfg");
		//InputStream is = this.getClass().getResourceAsStream("DBConfig.cfg");
		if(is != null){
			System.out.println("Config loaded !!!");
		}
		Scanner scan = new Scanner(is).useDelimiter("\\A");
		String strConfig = scan.hasNext() ? scan.next() : "";		
		
		System.out.println("DB Config successfully loaded");
		return strConfig;
		
	} catch ( Exception ex) {
		System.err.println("Error reading Database Config: " + ex);
		ex.printStackTrace();
	}
	
	return null;
}
}

