package com.p6.paymybuddy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.paymybuddy.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PayMyBuddyApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Autowired
    private PersonRepository personRepository;
    /*
    *  L'annotation Transactional permet de s'assurer les données sont enregistrés en cache pour que les méthodes save
    *  Ainsi, il exécutera tout en même temps.
    *
    * Un bon SQL est un SQL que tu peux exécuter plusieurs fois.
    */


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void run(String... args) throws Exception {
//        PersonEntity pe1 = personRepository.findById(1L).orElse(null);
//        PersonEntity pe2 = personRepository.findById(2L).orElse(null);
//        PersonEntity pe3 = personRepository.findById(3L).orElse(null);
//        List<PersonEntity> peFriends = new ArrayList<>();
//
//        peFriends.add(pe2);
//        peFriends.add(pe3);
//        pe1.setFriends(peFriends);
////        pe1.setFriends(List.of(pe2));
//        personRepository.save(pe1);
    }

//    @Transactional
//    public void doTransaction(Long idCreditor, Long idDebitor, double amount) {
//        User credit = userRepository.findById(idCreditor);
//        User debit = userRepository.findById(idDebitor);
//        double commission = amount * 0.05;
//
//        credit.setAmount(credit.getAmount() + amount);
//        debit.setAmount(debit.getAmount() - amount - commission);
//        userRepository.save(credit);
//        userRepository.save(debit);
//        transactionService.addTransaction(credit, debit, amount, commission);
//    }

}
