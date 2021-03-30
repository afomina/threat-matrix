package io.threatrix.threatmatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("io.threatrix.threatmatrix")
public class BootApp implements CommandLineRunner {

    List<Application> apps = new ArrayList<>();
    @Autowired private ApplicationContext ctx;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(BootApp.class,new String[]{"ThreatMatrix"});
    }

    /*
    *     
    *    
    *    Matching Project: https://spring.io/projects/spring-boot
    *    Matching Release: https://github.com/spring-projects/spring-boot/releases/tag/v2.5.0-M3
    *    Matching Source File: ConfigurationPropertiesBinder.java
    *    
    *    Auto-attribution by Threatrix, Inc.
    */
    @Override
    public void run(String... args) throws Exception {
        for(int x=0; x<args.length; x++) {
            System.out.println("AppContents: "+ctx);
            Application application = (Application) ctx.getBean(args[x],ctx);
            application.start();
            apps.add(application);
        }
    }

    @PreDestroy
    public void shutdown() {
        apps.forEach(
            app -> {
                app.shutdown();
            }
        );
    }
}

