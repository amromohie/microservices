package com.codex.accounts.repository;

import com.codex.accounts.entity.Accounts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
  Optional<Accounts> findByCustomerId(Long customerId);
}
