package com.p6.paymybuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class PayMyBuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayMyBuddyApplication.class, args);
    }


    /*
    *  L'annotation Transactional permet de s'assurer les données sont enregistrés en cache pour que les méthodes save
    *  Ainsi, il exécutrera tout en même temps.
    *
    * Un bon SQL est un SQL que tu peux exécuter plusieurs fois.
    */


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
