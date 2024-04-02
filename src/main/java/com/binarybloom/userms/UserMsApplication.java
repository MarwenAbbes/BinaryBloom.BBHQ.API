package com.binarybloom.userms;

import com.binarybloom.userms.entities.*;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class UserMsApplication {


    public UserMsApplication() {

    }

    public static void main(String[] args) {
        SpringApplication.run(UserMsApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                System.out.println("adding auth to registry");
//                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow all HTTP methods
//                        .allowedHeaders("*") // Allow all headers
//                        .allowCredentials(true); // Allow sending cookies
//                System.out.println("added auth to registry");
//            }
//        };
//    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, StoreRepository storeRepository, RoleRepository roleRepository, GenderRepository genderRepository, CompanyInfoRepository companyInfoRepository) {
        return args -> {

            CompanyInfo _companyInfo = CompanyInfo.builder()
                    .id(1)
                    .name("")
                    .email("")
                    .faxNumber("")
                    .principalAddress("")
                    .secondaryAddress("")
                    .taxIdentificationNumber("")
                    .principalPhoneNumber("")
                    .build();
            companyInfoRepository.save(_companyInfo);

            Gender genderM = Gender.builder()
                    .id(1)
                    .name("Male").build();
            genderRepository.save(genderM);

            Gender genderF = Gender.builder()
                    .id(2)
                    .name("Female").build();
            genderRepository.save(genderF);

            Role role = Role.builder()
                    .name("SUPERADMIN")
                    .status(DataStatus.A.toString())
                    .build();
            roleRepository.save(role);

            Store store = Store.builder()
                    .name("Store Name")
                    .address("Store Address")
                    .status(DataStatus.A.toString())
                    .build();
            storeRepository.save(store);


            User user = User.builder()
                    .name("Marwen")
                    .surname("Abbes")
                    .username("mabbes")
                    .password(("HELLO"))
                    .createdAt(LocalDateTime.now())
                    .birthDay(LocalDate.now())
                    .gender(genderM)
                    .store(store)
                    .role(role)
                    .enabled(true)
                    .status(DataStatus.A.toString())
                    .build();
            userRepository.save(user);


            user = User.builder()
                    .name("Manel")
                    .surname("Abbes")
                    .username("manel")
                    .password(("HELLO"))
                    .createdAt(LocalDateTime.now())
                    .birthDay(LocalDate.now())
                    .gender(genderF)
                    .store(store)
                    .role(role)
                    .enabled(true)
                    .status(DataStatus.A.toString())
                    .build();
            userRepository.save(user);
        };
    }
}
