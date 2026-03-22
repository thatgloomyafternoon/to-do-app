package com.fw.todoapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "tag")
@Entity
public class Tag {

  @Column(name = "id")
  @Id
  private UUID id = UUID.randomUUID();

  @Column(name = "created_at")
  @CreationTimestamp
  private ZonedDateTime createdAt;

  @Column(name = "name")
  @Size(min = 3, max = 100, message = "tag name must be between 3-100 characters long")
  @NotNull(message = "tag name must not be null")
  @NotBlank(message = "tag name must not be blank")
  private String name;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
