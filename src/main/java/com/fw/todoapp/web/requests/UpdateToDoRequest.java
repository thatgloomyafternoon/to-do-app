package com.fw.todoapp.web.requests;

import java.util.UUID;

public class UpdateToDoRequest {

  private UUID toDoId;
  private UUID tagId;
  private String task;

  public UUID getToDoId() {
    return toDoId;
  }

  public void setToDoId(UUID toDoId) {
    this.toDoId = toDoId;
  }

  public UUID getTagId() {
    return tagId;
  }

  public void setTagId(UUID tagId) {
    this.tagId = tagId;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

}
