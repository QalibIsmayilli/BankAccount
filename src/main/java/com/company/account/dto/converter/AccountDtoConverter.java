package com.company.account.dto.converter;

import com.company.account.dto.AccountDto;
import com.company.account.dto.functionalDto.AccountCustomerDto;
import com.company.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto convertToAccountDto(Account account) {
        if (account == null) {
            return null;
        }
        AccountCustomerDto accountCustomerDto = customerDtoConverter.convertToAccountCustomerDto(account.getCustomer());
        return new AccountDto(account.getId(), account.getBalance(), account.getCreationDate(), accountCustomerDto
                , account.getTransactions().stream()
                .map(t -> transactionDtoConverter.convertToTransactionDto(t)).collect(Collectors.toSet()));
    }

}
