package com.example.security;

import com.example.security.entities.Role;
import com.example.security.entities.User;
import com.example.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityApplication implements CommandLineRunner {
	@Autowired
	private final UserRepository userRepository;


    public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (adminAccount == null) {
			User user = new User();
			System.out.println("----------------no admin. We are adding one---------"+user.getUsername());
			user.setFirstName("keneth");
			user.setLastName("kipyegon");
			user.setEmail("kipyegonkeneth@gmail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);
			userRepository.save(user);
		} else {
			String exEmail= adminAccount.getUsername();
			System.out.println("Admin already exists with email :"+ exEmail);
		}
		User user1 = new User();
		System.out.println("----------------no admin. We are adding one---------"+user1.getUsername());
		user1.setFirstName("korir");
		user1.setLastName("korir");
		user1.setEmail("kipyegon@gmail.com");
		user1.setPassword(new BCryptPasswordEncoder().encode("user"));
		user1.setRole(Role.USER);
		userRepository.save(user1);

	}
}
