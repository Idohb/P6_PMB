package com.p6.paymybuddy.mapper;

import com.p6.paymybuddy.model.entity.BankEntity;
import com.p6.paymybuddy.service.data.Bank;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankConverter {

    public Bank mapperBank(BankEntity bankEntity) {
        PersonConverter personConverter = new PersonConverter();

        Bank bank = new Bank();
        bank.setId(bankEntity.getId());
        bank.setAmount(bankEntity.getAmount());
        bank.setIban(bankEntity.getIban());
        bank.setUser(personConverter.mapperPerson(bankEntity.getUser()));
        return bank;
    }

    public List<Bank> mapperBank(List<BankEntity> bankEntities) {
        return bankEntities.stream().map(this::mapperBank).collect(Collectors.toList());
    }
}
