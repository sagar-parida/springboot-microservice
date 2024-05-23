package com.springmicroservice.accounts.service.impl;

import com.springmicroservice.accounts.Entity.Accounts;
import com.springmicroservice.accounts.Entity.Customer;
import com.springmicroservice.accounts.Repository.AccountsRepository;
import com.springmicroservice.accounts.Repository.CustomerRepository;
import com.springmicroservice.accounts.constants.AccountsConstants;
import com.springmicroservice.accounts.dto.CustomerDto;
import com.springmicroservice.accounts.exception.CustomerAlreadyExistsException;
import com.springmicroservice.accounts.mapper.CustomerMapper;
import com.springmicroservice.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with this mobile number already exists " + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     *
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randAccountNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}
