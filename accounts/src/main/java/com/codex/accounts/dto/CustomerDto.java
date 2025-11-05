package com.codex.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
  @NotNull(message = "Name is required")
  @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
  private String name;
  @NotNull(message = "Email is required")
  @Email(message = "Invalid email address")
  private String email;
  @NotNull(message = "Mobile number is required")
  private String mobileNumber;
  private AccountsDto accountsDto;
}
