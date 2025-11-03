package com.codex.accounts.service.impl;

import com.codex.accounts.dto.CustomerDto;
import com.codex.accounts.repository.AccountsRepository;
import com.codex.accounts.repository.CustomerRepository;
import com.codex.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountsServiceImpl implements IAccountsService {
  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;
  /**
   * @param customerDto
   */
  @Override
  public void createAccount(CustomerDto customerDto) {

  }
}
