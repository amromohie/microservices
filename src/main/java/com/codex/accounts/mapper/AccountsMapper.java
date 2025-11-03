package com.codex.accounts.mapper;

import com.codex.accounts.dto.AccountsDto;
import com.codex.accounts.entity.Accounts;
import org.mapstruct.Mapper;

@Mapper
public interface AccountsMapper {
  Accounts toEntity(AccountsDto accountsDto);
  AccountsDto toDto(Accounts accounts);
}
