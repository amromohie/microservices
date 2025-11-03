package com.codex.accounts.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.codex.accounts.constants.AccountsConstants;
import com.codex.accounts.dto.CustomerDto;
import com.codex.accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "api/accounts",
    produces = {APPLICATION_JSON_VALUE})
public class AccountsController {

  @PostMapping
  public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
    // business logic move to
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            ResponseDto.builder()
                .statusCode(AccountsConstants.STATUS_201)
                .statusMessage(AccountsConstants.MESSAGE_201)
                .build());
  }
}
