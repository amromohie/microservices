package com.codex.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
  @NotNull(message = "Account number is required")
  @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be 10 digits")
  private Long accountNumber;
  @NotNull(message = "Account type is required")
  private String accountType;
  @NotNull(message = "Branch address is required")
  private String branchAddress;
}
