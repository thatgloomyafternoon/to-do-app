package com.fw.todoapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "to_do")
@Entity
public class ToDo {

  @Column(name = "id")
  @Id
  private UUID id = UUID.randomUUID();

  @Column(name = "created_at")
  @CreationTimestamp
  private ZonedDateTime createdAt;

  @Column(name = "finished_at")
  private ZonedDateTime finishedAt;

  /**
   * if the @ManyToOne is removed, will trigger 'Could not determine recommended JdbcType for Java type <class>' upon startup;
   * if the @ManyToOne is changed to @OneToOne, we could not save 2 ToDo with the same Tag.
   */
  @JoinColumn(name = "tag_id")
  @ManyToOne
  @NotNull(message = "tag must not be null")
  private Tag tag;

  @Column(name = "task")
  @Size(max = 4000, message = "task length must not exceed 4000 characters")
  @NotNull(message = "task must not be null")
  @NotBlank(message = "task must not be blank")
  private String task;

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

  public ZonedDateTime getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(ZonedDateTime finishedAt) {
    this.finishedAt = finishedAt;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

}
