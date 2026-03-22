package com.fw.todoapp.web.requests;

import java.util.UUID;

public class FinishToDoRequest {

  private UUID toDoId;

  public UUID getToDoId() {
    return toDoId;
  }

  public void setToDoId(UUID toDoId) {
    this.toDoId = toDoId;
  }

}
