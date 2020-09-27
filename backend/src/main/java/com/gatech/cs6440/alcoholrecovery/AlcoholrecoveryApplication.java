package com.gatech.cs6440.alcoholrecovery;


import com.gatech.cs6440.alcoholrecovery.fhir.FhirService;
import com.gatech.cs6440.alcoholrecovery.model.Tracking;
import com.gatech.cs6440.alcoholrecovery.model.TrackingRepository;
import com.gatech.cs6440.alcoholrecovery.model.User;
import com.gatech.cs6440.alcoholrecovery.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.Random;


@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages="com.gatech.cs6440.alcoholrecovery")
public class AlcoholrecoveryApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AlcoholrecoveryApplication.class, args);
	}

	@Bean
	public CommandLineRunner loader(UserRepository repository, TrackingRepository trackingRepository,FhirService _fhirContact) {
	;
		return (args) -> {
			String[] arr = {"demo1", "demo2", "demo3"};
			User user = new User();
			for (String username : arr) {
				int id = -1;
				if (username.equals("demo1")) {
					id = 140604;
				} else if (username.equals("demo2")) {
					id = 140562;
				} else if (username.equals("demo3")) {
					id = 140608;
				}

                long count = repository.count();

				if(repository.findById(id).isPresent()){
					continue;
				}

				//set user info from db or FHIR db

				String firstName;
				String lastName;
				try {
					String full = _fhirContact.getName(id + "");
					String[] splt = full.split("\\s+");
					firstName = splt[0];
					lastName = splt[1];
				} catch (Exception e) {
					firstName = "test";
					lastName = "test";

				}

				String password = username;
				user.setUser(id, "bobby@gmail.com", firstName, lastName, username, password, "05/05/91", (int) _fhirContact.getLionicValue("29463-7", id + ""), (int) _fhirContact.getLionicValue("8302-2", id + ""), true, _fhirContact.getCondition(id + ""));
		        user.setLoggedIn(false);
		        repository.save(user);

			}

			for(int i=365;i>1;i--){
				LocalDateTime date = LocalDateTime.now().minusDays(i);
				double oz1;
				double oz2;
				if(i>4) {
					oz1 = new Double(getRandomNumberInRange()) * 4;
					oz2 = new Double(getRandomNumberInRange()) * 4;
				}else{
					oz1 = new Double(0);
					oz2 = new Double(getRandomNumberInRange()) * 4;
				}
				Tracking track = new Tracking(date,oz1,oz2,140604);
				trackingRepository.save(track);
			}


			for(int i=365;i>1;i--){
				LocalDateTime date = LocalDateTime.now().minusDays(i);
				double oz1;
				double oz2;
				if(i>36) {
					oz1 = new Double(getRandomNumberInRange()) * 4;
					oz2 = new Double(getRandomNumberInRange()) * 8;
				}else{
					oz1 = new Double(0);
					oz2 = new Double(getRandomNumberInRange()) * 8;
				}
				Tracking track = new Tracking(date,oz1,oz2,140562);
				trackingRepository.save(track);
			}



			for(int i=365;i>1;i--){
				LocalDateTime date = LocalDateTime.now().minusDays(i);
				double oz1;
				double oz2;
				if(i>333) {
					oz1 = new Double(getRandomNumberInRange()) * 8;
					oz2 = new Double(getRandomNumberInRange()) * 8;
				}else{
					oz1 = new Double(0);
					oz2 = new Double(getRandomNumberInRange()) * 8;
				}
				Tracking track = new Tracking(date,oz1,oz2,140608);
				trackingRepository.save(track);
			}

		};
	}

    private static int getRandomNumberInRange() {
	     int min = 0; int max=4;
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
