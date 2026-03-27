package com.fw.todoapp.web.api;

import com.fw.todoapp.entities.Tag;
import com.fw.todoapp.entities.ToDo;
import com.fw.todoapp.mappers.ToDoMapper;
import com.fw.todoapp.repositories.TagRepository;
import com.fw.todoapp.repositories.ToDoRepository;
import com.fw.todoapp.web.requests.CreateToDoRequest;
import com.fw.todoapp.web.requests.FinishToDoRequest;
import com.fw.todoapp.web.requests.UpdateToDoRequest;
import com.fw.todoapp.web.responses.ToDoDetailDTO;
import jakarta.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class ToDoController {

  private final ToDoMapper toDoMapper;
  private final TagRepository tagRepository;
  private final ToDoRepository toDoRepository;

  public ToDoController(
    ToDoMapper toDoMapper,
    TagRepository tagRepository,
    ToDoRepository toDoRepository
  ) {
    this.toDoMapper = toDoMapper;
    this.tagRepository = tagRepository;
    this.toDoRepository = toDoRepository;
  }

  /**
   * @param request
   * @return
   */
  @PostMapping("/create")
  public ResponseEntity<?> createToDo(@RequestBody @Valid CreateToDoRequest request) {
    Optional<Tag> optTag = tagRepository.findById(request.getTagId());
    if(!optTag.isPresent()) {
      throw new IllegalArgumentException(String.format("Tag with id %s not exists", request.getTagId()));
    }
    ToDo toDo = new ToDo();
    toDo.setTag(optTag.get());
    toDo.setTask(request.getTask());
    toDoRepository.save(toDo);
    return ResponseEntity.ok("OK");
  }

  /**
   * @param id
   * @return
   */
  @GetMapping("/view")
  public ResponseEntity<?> viewToDo(@RequestParam UUID id) {
    Optional<ToDo> optToDo = toDoRepository.findById(id);
    if(!optToDo.isPresent()) {
      throw new IllegalArgumentException(String.format("ToDo with id %s not exists", id));
    }
    ToDoDetailDTO toDoDetailDTO = toDoMapper.mapEntityToDetailDTO(optToDo.get());
    return ResponseEntity.ok(toDoDetailDTO);
  }

  @PatchMapping("/update")
  public ResponseEntity<?> updateToDo(@RequestBody @Valid UpdateToDoRequest request) {
    Optional<ToDo> optToDo = toDoRepository.findById(request.getToDoId());
    if(!optToDo.isPresent()) {
      throw new IllegalArgumentException(String.format("ToDo with id %s not exists", request.getToDoId()));
    }
    Optional<Tag> optTag = tagRepository.findById(request.getTagId());
    if(!optTag.isPresent()) {
      throw new IllegalArgumentException(String.format("Tag with id %s not exists", request.getTagId()));
    }
    ToDo toDo = optToDo.get();
    toDo.setTag(optTag.get());
    toDo.setTask(request.getTask());
    toDoRepository.save(toDo);
    return ResponseEntity.ok("OK");
  }

  /**
   * @param request
   * @return
   */
  @PatchMapping("/finish")
  public ResponseEntity<?> finishToDo(@RequestBody @Valid FinishToDoRequest request) {
    Optional<ToDo> optToDo = toDoRepository.findById(request.getToDoId());
    if(!optToDo.isPresent()) {
      throw new IllegalArgumentException(String.format("ToDo with id %s not exists", request.getToDoId()));
    }
    ToDo toDo = optToDo.get();
    toDo.setFinishedAt(ZonedDateTime.now());
    toDoRepository.save(toDo);
    return ResponseEntity.ok("OK");
  }

  /**
   * @param request
   * @return
   */
  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteToDo(@RequestBody @Valid FinishToDoRequest request) {
    Optional<ToDo> optToDo = toDoRepository.findById(request.getToDoId());
    if(!optToDo.isPresent()) {
      throw new IllegalArgumentException(String.format("ToDo with id %s not exists", request.getToDoId()));
    }
    toDoRepository.deleteById(optToDo.get().getId());
    return ResponseEntity.ok("OK");
  }

}
