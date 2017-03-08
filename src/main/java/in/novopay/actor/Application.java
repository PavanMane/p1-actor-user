package in.novopay.actor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import in.novopay.actor.config.MongoDBCredentials;

@SpringBootApplication
@ComponentScan(basePackages={"in.novopay.actor.*"})
public class Application {
    
    public static void main(String[] args) {
    	
    	
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
//        
//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//        
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
        MongoDBCredentials mongoDBCredentials = ctx.getBean(MongoDBCredentials.class);
        
        System.out.println("************************* Connected to Database ---> [  " + mongoDBCredentials.getDatabaseName() + "  ] >>> "
				+ mongoDBCredentials.getHost() + ":" + mongoDBCredentials.getPort() + " *************************");
    }

}
