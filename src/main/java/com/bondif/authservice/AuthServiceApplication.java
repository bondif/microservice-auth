package com.bondif.authservice;

import com.bondif.authservice.dao.AppRoleRepository;
import com.bondif.authservice.dao.AppUserRepository;
import com.bondif.authservice.entities.AppRole;
import com.bondif.authservice.entities.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AppUserRepository appUserRepository,
                            AppRoleRepository appRoleRepository,
                            BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            appUserRepository.deleteAll();
            appRoleRepository.deleteAll();

            AppRole r1 = new AppRole(null, "ADMIN", new ArrayList<>());
            AppRole r2 = new AppRole(null, "USER", new ArrayList<>());
            AppRole r3 = new AppRole(null, "ANALYSE_MANAGER", new ArrayList<>());

            AppUser u1 = new AppUser(null, "admin", passwordEncoder.encode("password"), new ArrayList<>());
            AppUser u2 = new AppUser(null, "user", passwordEncoder.encode("password"), new ArrayList<>());
            AppUser u3 = new AppUser(null, "am", passwordEncoder.encode("password"), new ArrayList<>());
            u1.getRoles().add(r1);
            u2.getRoles().add(r2);
            u3.getRoles().add(r3);

            appRoleRepository.save(r1);
            appRoleRepository.save(r2);
            appRoleRepository.save(r3);

            appUserRepository.save(u1);
            appUserRepository.save(u2);
            appUserRepository.save(u3);
        };
    }
}
