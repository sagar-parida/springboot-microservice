package com.springmicroservice.accounts.service;

import com.springmicroservice.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on given mobileNumber;
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update operation was successful or not.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return boolean indicating if the deletion of the account and customer was successful or not.
     */
    boolean deleteAccount(String mobileNumber);
}
