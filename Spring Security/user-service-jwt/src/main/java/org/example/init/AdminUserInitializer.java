package org.example.init;

import org.example.entity.Role;
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
                admin.setRole(Role.ADMIN);
                userDetailsRepository.save(admin);
                System.out.println("Default admin user created");
            }
            if(userDetailsRepository.findByUsername("user").isEmpty()){
                Users user = new Users();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user"));
                user.setRole(Role.USER);
                userDetailsRepository.save(user);
                System.out.println("Default user created");
            }
        };
    }
}
