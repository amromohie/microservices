package com.codex.accounts.service;

import com.codex.accounts.dto.CustomerDto;

public interface IAccountsService {

  /**
   * @param customerDto
   */
  void createAccount(CustomerDto customerDto);
}
