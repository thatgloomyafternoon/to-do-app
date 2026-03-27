package com.fw.todoapp.mappers;

import com.fw.todoapp.entities.ToDo;
import com.fw.todoapp.web.responses.ToDoDetailDTO;
import com.fw.todoapp.web.responses.ToDoListDTO;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class ToDoMapper {

  public ToDoListDTO mapEntityToListDTO(ToDo toDo) {
    ToDoListDTO toDoListDTO = new ToDoListDTO();
    toDoListDTO.setId(toDo.getId());
    toDoListDTO.setCreatedAt(toDo.getCreatedAt().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm z")));
    if(toDo.getFinishedAt() != null) {
      toDoListDTO.setFinishedAt(toDo.getFinishedAt().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm z")));
    }
    toDoListDTO.setTag(toDo.getTag().getName());
    if(toDo.getTask().length() > 50) {
      toDoListDTO.setTask(toDo.getTask().substring(0, 47) + "...");
    } else {
      toDoListDTO.setTask(toDo.getTask());
    }
    return toDoListDTO;
  }

  public ToDoDetailDTO mapEntityToDetailDTO(ToDo toDo) {
    ToDoDetailDTO toDoDetailDTO = new ToDoDetailDTO();
    toDoDetailDTO.setTag(toDo.getTag());
    toDoDetailDTO.setTask(toDo.getTask());
    return toDoDetailDTO;
  }

}
