package com.codex.accounts.service.impl;

import com.codex.accounts.constants.AccountsConstants;
import com.codex.accounts.dto.AccountsDto;
import com.codex.accounts.dto.CustomerDto;
import com.codex.accounts.entity.Accounts;
import com.codex.accounts.entity.Customer;
import com.codex.accounts.exception.CustomerAlreadyExitsException;
import com.codex.accounts.exception.ResourceNotFoundException;
import com.codex.accounts.mapper.AccountsMapper;
import com.codex.accounts.mapper.CustomerMapper;
import com.codex.accounts.repository.AccountsRepository;
import com.codex.accounts.repository.CustomerRepository;
import com.codex.accounts.service.AccountsService;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountsServiceImpl implements AccountsService {
  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;
  private final AccountsMapper accountsMapper;

  /**
   * @param customerDto
   */
  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = customerMapper.toEntity(customerDto);
    // check if customer already exists
    if (customerRepository.existsByMobileNumber(customer.getMobileNumber())) {
      throw new CustomerAlreadyExitsException("Customer already exits");
    }
    customerRepository.save(customer);
    Accounts accounts = createAccounts(customer.getCustomerId());
    accountsRepository.save(accounts);
  }

  @Override
  public CustomerDto fetchAccountDetails(String mobileNumber) {
    // find customer by mobile number

    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Customer not found with mobile number:%s".formatted(mobileNumber)));

    // find account by customer id
    Accounts accounts =
        accountsRepository
            .findByCustomerId(customer.getCustomerId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Account not found for customer id:%s"
                            .formatted(customer.getCustomerId())));

    // map accounts to account dto

    AccountsDto accountsDto = accountsMapper.toDto(accounts);
    CustomerDto customerDto = customerMapper.toDto(customer);
    customerDto.setAccountsDto(accountsDto);
    return customerDto;
  }

  /**
   * @param customerDto
   * @return
   */
  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    boolean isAccountUpdated = false;
    // get accounts dto from customer dto
    Long accountNumber = customerDto.getAccountsDto().getAccountNumber();
    if (accountNumber != null) {
      Accounts accounts =
          accountsRepository
              .findById(accountNumber)
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Account not found with account number:%s".formatted(accountNumber)));

      accountsMapper.update(customerDto.getAccountsDto(), accounts);
      accountsRepository.save(accounts);
      // get customer
      Customer customer =
          customerRepository
              .findById(accounts.getCustomerId())
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Customer not found with customer id:%s"
                              .formatted(accounts.getCustomerId())));

      customerMapper.update(customerDto, customer);
      customerRepository.save(customer);
      isAccountUpdated = true;
    }

    return isAccountUpdated;
  }

  private Accounts createAccounts(Long customerId) {
    long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
    return Accounts.builder()
        .customerId(customerId)
        .accountNumber(randomAccountNumber)
        .accountType(AccountsConstants.SAVINGS)
        .branchAddress(AccountsConstants.ADDRESS)
        .build();
  }
}
