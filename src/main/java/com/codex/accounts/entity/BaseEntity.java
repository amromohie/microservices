package com.codex.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;
  @Column(name = "created_by", nullable = false, updatable = false)
  private String createdBy;
  @Column(name = "updated_by", nullable = false,insertable = false)
  private String updatedBy;
  @Column(name = "updated_at", nullable = false,insertable = false)
  private LocalDateTime updatedAt;
}
