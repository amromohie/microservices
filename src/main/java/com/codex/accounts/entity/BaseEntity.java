package com.codex.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
  @Column(name = "updated_by", nullable = false)
  private String updatedBy;
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.createdBy = "system"; // حط اسم اليوزر لو عندك Security
    this.updatedBy = "system";
  }
  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = "system";
  }

}
