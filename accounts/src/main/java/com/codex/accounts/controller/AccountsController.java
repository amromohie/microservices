package com.codex.accounts.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.codex.accounts.constants.AccountsConstants;
import com.codex.accounts.dto.CustomerDto;
import com.codex.accounts.dto.ResponseDto;
import com.codex.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "api/accounts",
    produces = {APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Tag(
    name = "AccountsController",
   description = "Http rest"
)
public class AccountsController {
  private final AccountsService accountsService;
  @Operation(summary = "Create new account",
      description = "Create new account"
  )
  @ApiResponse(responseCode = "201", description = "Created")
  @PostMapping("create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    accountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            ResponseDto.builder()
                .statusCode(AccountsConstants.STATUS_201)
                .statusMessage(AccountsConstants.MESSAGE_201)
                .build());
  }

  @GetMapping("/fetch")
  public ResponseEntity<CustomerDto> fetchAccountsDetails(
      @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
      @RequestParam String mobileNumber) {
    CustomerDto customerDto = accountsService.fetchAccountDetails(mobileNumber);
    return ResponseEntity.ok(customerDto);
  }

  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
    accountsService.updateAccount(customerDto);
    return ResponseEntity.ok(
        ResponseDto.builder()
            .statusCode(AccountsConstants.STATUS_200)
            .statusMessage(AccountsConstants.MESSAGE_200)
            .build());
  }


}
