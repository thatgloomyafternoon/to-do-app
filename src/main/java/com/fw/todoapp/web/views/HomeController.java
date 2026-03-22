package com.fw.todoapp.web.views;

import com.fw.todoapp.mappers.ToDoMapper;
import com.fw.todoapp.repositories.TagRepository;
import com.fw.todoapp.repositories.ToDoRepository;
import com.fw.todoapp.web.responses.ToDoListDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  private ToDoMapper toDoMapper;
  private final TagRepository tagRepository;
  private final ToDoRepository toDoRepository;

  public HomeController(
    ToDoMapper toDoMapper,
    TagRepository tagRepository,
    ToDoRepository toDoRepository
  ) {
    this.toDoMapper = toDoMapper;
    this.tagRepository = tagRepository;
    this.toDoRepository = toDoRepository;
  }

  @GetMapping
  public String index(Model model) {
    List<ToDoListDTO> toDoListDTOs = toDoRepository.findAllByOrderByCreatedAtAsc()
      .stream()
      .map(t -> toDoMapper.mapEntityToListDTO(t))
      .collect(Collectors.toList());
    model.addAttribute("todos", toDoListDTOs);
    model.addAttribute("tags", tagRepository.findAllByOrderByNameAsc());
    return "index";
  }

}
