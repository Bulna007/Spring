package org.example.init;

import org.example.entity.Users;
import org.example.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {
    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepository userDetailsRepository,
                                             PasswordEncoder passwordEncoder){
        return args -> {
            if(userDetailsRepository.findByUsername("admin").isEmpty()){
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole("ROLE_ADMIN");
                userDetailsRepository.save(admin);
                System.out.println("Default admin user created");
            }
        };
    }
}
