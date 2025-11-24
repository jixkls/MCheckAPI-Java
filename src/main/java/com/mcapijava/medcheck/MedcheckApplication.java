package com.mcapijava.medcheck;

import com.mcapijava.medcheck.models.Role;
import com.mcapijava.medcheck.models.UserAccount;
import com.mcapijava.medcheck.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MedcheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedcheckApplication.class, args);
	}

    @Bean
    CommandLineRunner initUsers(UserAccountRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.count() == 0) {
                UserAccount admin = UserAccount.builder()
                        .name("Admin")
                        .email("admin@medcheck.com")
                        .password(encoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .build();

                UserAccount user = UserAccount.builder()
                        .name("Usuário")
                        .email("user@medcheck.com")
                        .password(encoder.encode("user123"))
                        .role(Role.USER)
                        .build();

                userRepo.save(admin);
                userRepo.save(user);
            }
        };
    }
}
