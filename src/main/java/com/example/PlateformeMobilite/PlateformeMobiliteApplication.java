package com.example.PlateformeMobilite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class PlateformeMobiliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlateformeMobiliteApplication.class, args);
//		UserDetails userDetails =
//				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String ud = userDetails.getUsername();
//		System.out.println(ud);
// userDetails.getPassword()
// userDetails.getAuthorities()
	}

}
