package com.p6.paymybuddy.service;

import com.p6.paymybuddy.mapper.BankConverter;
import com.p6.paymybuddy.model.repository.BankRepository;
import com.p6.paymybuddy.service.data.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankConverter bankConverter;

    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getBanks() {
        return bankConverter.mapperBank(bankRepository.findAll());
    }
}
