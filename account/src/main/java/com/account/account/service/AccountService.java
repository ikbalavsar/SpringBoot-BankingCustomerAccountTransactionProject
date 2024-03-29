package com.account.account.service;

import com.account.account.dto.AccountDto;
import com.account.account.dto.AccountDtoConverter;
import com.account.account.dto.CreateAccountRequest;
import com.account.account.model.Account;
import com.account.account.model.Customer;
import com.account.account.model.Transaction;
import com.account.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountDtoConverter converter;
    public AccountService(AccountRepository accountRepository, CustomerService customerService, TransactionService transactionService, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());

        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            Transaction transaction = transactionService.initiateMoney(account, createAccountRequest.getInitialCredit());
            account.getTransaction().add(transaction);
        }

        return converter.convert(accountRepository.save(account));
    }
}
