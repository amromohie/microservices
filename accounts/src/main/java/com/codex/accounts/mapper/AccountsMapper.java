package com.codex.accounts.mapper;

import com.codex.accounts.dto.AccountsDto;
import com.codex.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface AccountsMapper {
  Accounts toEntity(AccountsDto accountsDto);
  AccountsDto toDto(Accounts accounts);
  @Mapping(target = "accountNumber", ignore = true)
  void update(AccountsDto accountsDto, @MappingTarget Accounts accounts);
}
