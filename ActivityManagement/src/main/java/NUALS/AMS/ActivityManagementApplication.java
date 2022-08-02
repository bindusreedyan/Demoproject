package NUALS.AMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("NUALS.AMS")
public class ActivityManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityManagementApplication.class, args);
	}

}
