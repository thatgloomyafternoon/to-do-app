package com.fw.todoapp.repositories;

import com.fw.todoapp.entities.ToDo;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, UUID> {

  List<ToDo> findAllByOrderByCreatedAtAsc();

}
