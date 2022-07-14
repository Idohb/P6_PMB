package com.p6.paymybuddy.service;

import com.p6.paymybuddy.controller.dto.bank.BankRequest;
import com.p6.paymybuddy.controller.dto.transactionInternal.TransactionInternalRequest;
import com.p6.paymybuddy.mapper.BankConverter;
import com.p6.paymybuddy.model.entity.BankEntity;
import com.p6.paymybuddy.model.entity.PersonEntity;
import com.p6.paymybuddy.model.repository.BankRepository;
import com.p6.paymybuddy.model.repository.LoginRepository;
import com.p6.paymybuddy.model.repository.PersonRepository;
import com.p6.paymybuddy.service.data.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankService {

    @Autowired
    private BankConverter bankConverter;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TransactionExternalService transactionExternalService;

    public List<Bank> getBanks() {
        return bankConverter.mapperBank(bankRepository.findAll());
    }

    public Bank getBank(final Long id) {
        BankEntity bankEntity = accessBank(id);
        return bankConverter.mapperBank(bankEntity);
    }

    public BankEntity accessBank(final Long id) {
        PersonEntity pe = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id Person " + id + " not found"));
        return bankRepository.findByPerson(pe).orElseThrow(() -> new NoSuchElementException("Id bank " + id + " not found"));
    }

    public BankEntity createBankWithPerson(final Long id) {
        PersonEntity pe = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id Person " + id + " not found"));
        BankEntity bankEntity = new BankEntity(0L,
                (double) 0,
                "",
                pe
                );
        bankEntity = bankRepository.save(bankEntity);
        return bankEntity;

    }


    public void bankTransaction(PersonEntity crediteur, PersonEntity debiteur, Double amount, String description) {
        BankEntity beCrediteur = crediteur.getBank();
        BankEntity beDebiteur  = debiteur.getBank();

        beCrediteur.setAmount(beCrediteur.getAmount() - amount);
        beDebiteur.setAmount (beDebiteur.getAmount()  + amount);
        this.transactionExternalService.processTransactionExternal(-amount, description, crediteur);
        this.transactionExternalService.processTransactionExternal(amount, description, debiteur);
        bankRepository.save(beCrediteur);
        bankRepository.save(beDebiteur);
    }

    public BankEntity searchBankWithPersonId(final Long id) {
        return bankRepository.findByPerson_Id(id).orElseThrow(() -> new NoSuchElementException(""));
    }

}
