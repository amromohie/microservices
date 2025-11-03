package com.codex.accounts.mapper;

import com.codex.accounts.dto.CustomerDto;
import com.codex.accounts.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
  Customer toEntity(CustomerDto customerDto);

  CustomerDto toDto(Customer customer);
}
