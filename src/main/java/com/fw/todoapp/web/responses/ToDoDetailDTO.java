package com.fw.todoapp.web.responses;

import com.fw.todoapp.entities.Tag;
import java.util.UUID;

public class ToDoDetailDTO {

  private UUID id;
  private String createdAt;
  private String finishedAt;
  private Tag tag;
  private String task;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(String finishedAt) {
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
