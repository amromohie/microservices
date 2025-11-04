package com.codex.accounts.service;

import com.codex.accounts.dto.CustomerDto;

public interface AccountsService {

  void createAccount(CustomerDto customerDto);

  CustomerDto fetchAccountDetails(String mobileNumber);

  boolean updateAccount(CustomerDto customerDto);

}
